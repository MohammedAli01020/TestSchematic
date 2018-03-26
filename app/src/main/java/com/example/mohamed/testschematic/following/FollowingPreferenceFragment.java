package com.example.mohamed.testschematic.following;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.SwitchPreferenceCompat;

import com.example.mohamed.testschematic.R;
import com.google.firebase.messaging.FirebaseMessaging;

public class FollowingPreferenceFragment extends PreferenceFragmentCompat implements
        SharedPreferences.OnSharedPreferenceChangeListener{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        Preference preference = findPreference(s);

         if (preference != null && preference instanceof SwitchPreferenceCompat) {

            boolean isOn = sharedPreferences.getBoolean(s,
                    getResources().getBoolean(R.bool.follow_default_message_subscription));

            if (isOn) {
                FirebaseMessaging.getInstance().subscribeToTopic(s);
            } else {
                FirebaseMessaging.getInstance().unsubscribeFromTopic(s);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}

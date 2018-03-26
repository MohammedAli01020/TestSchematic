package com.example.mohamed.testschematic.fcm;


import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;

import com.example.mohamed.testschematic.data.LibraryContract;
import com.example.mohamed.testschematic.data.LibraryProvider;
import com.example.mohamed.testschematic.ui.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class BookFirebaseMessageService extends FirebaseMessagingService {

    private static final int NOTIFICATION_MAX_CHARACTERS = 30;
    private static final int PENDING_INTENT_ID = 205;
    private static final int NOTIFICATION_MANAGER_ID = 301;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();

        sendNotification(data);
        addToDatabase(data);
    }

    private void addToDatabase(final Map<String, String> data) {

         @SuppressLint("StaticFieldLeak")
         AsyncTask<Void, Void, Void> insertBookTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                String message = data.get("message");

                if (message.length() > NOTIFICATION_MAX_CHARACTERS) {
                    message = message.substring(0, NOTIFICATION_MAX_CHARACTERS) + "\u2026";
                }

                ContentValues values = new ContentValues();
                values.put(LibraryContract.BookEntry.COLUMN_MESSAGE, message);

                getContentResolver().insert(LibraryProvider.Books.CONTENT_URI, values);
                return null;
            }
        };

        insertBookTask.execute();

    }

    private void sendNotification(Map<String, String> data) {

        String author = data.get("author");
        String message = data.get("message");

        if (message.length() > NOTIFICATION_MAX_CHARACTERS) {
            message = message.substring(0, NOTIFICATION_MAX_CHARACTERS) + "\u2026";
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                PENDING_INTENT_ID,
                intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setContentTitle(author)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.notify(NOTIFICATION_MANAGER_ID, notificationBuilder.build());
    }
}

package com.example.mohamed.testschematic.sync;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.mohamed.testschematic.data.LibraryProvider;
import com.example.mohamed.testschematic.utils.DataUtils;

public class BooksIntentService extends IntentService {
    public static final String ACTION_SYNC = "action-sync";

    public BooksIntentService() {
        super("BooksIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        assert intent != null;
        String action = intent.getAction();

        assert action != null;
        if (action.equals(ACTION_SYNC)) {
            syncData();
        }
    }

    synchronized private void syncData() {

        try {
            ContentValues[] values = DataUtils.getFakeData();
            getContentResolver().bulkInsert(LibraryProvider.Books.CONTENT_URI, values);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

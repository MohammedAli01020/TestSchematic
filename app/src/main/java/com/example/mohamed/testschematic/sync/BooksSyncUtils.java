package com.example.mohamed.testschematic.sync;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;

import com.example.mohamed.testschematic.data.LibraryProvider;

public final class BooksSyncUtils {
    private static boolean sInitialized;
    @SuppressLint("StaticFieldLeak")
    synchronized public static void initialize(final Context context) {

        if (sInitialized) return;
        sInitialized = true;

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Cursor cursor = context.getContentResolver().query(
                        LibraryProvider.Books.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);

                if (null == cursor || cursor.getCount() == 0) {
                    Intent intent = new Intent(context, BooksIntentService.class);
                    intent.setAction(BooksIntentService.ACTION_SYNC);
                    context.startService(intent);
                }

                /* Make sure to close the Cursor to avoid memory leaks! */
                assert cursor != null;
                cursor.close();
                return null;
            }
        }.execute();

    }
}


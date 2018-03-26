package com.example.mohamed.testschematic.utils;


import android.content.ContentValues;

import com.example.mohamed.testschematic.data.LibraryContract;

public final class DataUtils {

    public static ContentValues[] getFakeData() {

        ContentValues[] values = new ContentValues[10];

        for (int i = 0; i < values.length; i++) {
            ContentValues currentValues = new ContentValues();
            currentValues.put(LibraryContract.BookEntry.COLUMN_MESSAGE, "Title" + (i+ 1));
            values[i] =currentValues;
        }

        return values;
    }
}


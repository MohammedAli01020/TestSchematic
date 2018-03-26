package com.example.mohamed.testschematic.data;

import android.provider.BaseColumns;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

public final class LibraryContract {

    public static final class BookEntry implements BaseColumns {

        @DataType(DataType.Type.INTEGER) @PrimaryKey @AutoIncrement
        public static final String _ID = BaseColumns._ID;

        @DataType(DataType.Type.TEXT) @NotNull
        public static final String COLUMN_MESSAGE = "title";
    }
}

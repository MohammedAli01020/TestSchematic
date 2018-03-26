package com.example.mohamed.testschematic.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

@Database(version = LibraryDatabase.DATABASE_VERSION)
final class LibraryDatabase {

    static final int DATABASE_VERSION = 2;

    @Table(LibraryContract.BookEntry.class)
    static final String TABLE_BOOK = "books";
}


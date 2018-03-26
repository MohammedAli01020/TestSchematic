package com.example.mohamed.testschematic.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

@ContentProvider(authority = LibraryProvider.AUTHORITY,
        database = LibraryDatabase.class)
public final class LibraryProvider {

    static final String AUTHORITY = "com.example.mohamed.testschematic";

    @TableEndpoint(table = LibraryDatabase.TABLE_BOOK)
    public static class Books {
        @ContentUri(
                path = "books",
                type = "vnd.android.cursor.dir/books")
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/books");
    }
}

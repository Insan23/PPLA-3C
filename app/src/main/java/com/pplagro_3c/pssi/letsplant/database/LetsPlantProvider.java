package com.pplagro_3c.pssi.letsplant.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
/**
 * Created by Aleq on 06/10/2017.
 */

public class LetsPlantProvider {
    /* tag untuk pesan log */
    public static final String LOG_TAG = LetsPlantProvider.class.getSimpleName();

    private static final int LETS_PLANT = 200;
    private static final int NAMA_PEMAIN = 100;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI();
    }
}

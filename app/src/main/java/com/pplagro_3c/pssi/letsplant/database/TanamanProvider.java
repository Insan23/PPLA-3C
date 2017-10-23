package com.pplagro_3c.pssi.letsplant.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Aleq on 23/10/2017.
 */

public class TanamanProvider extends ContentProvider {

    /* tipe akses ke tabel tanaman */
    private static final int JENIS_TANAMAN = 310;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        /**
         *
         */
        sUriMatcher.addURI(LetsPlantContract.CONTENT_AUTHORITY, LetsPlantContract.PATH_LETS_PLANT, JENIS_TANAMAN);
    }

    private LetsPlantDBHelper TanamanDBHelper;

    @Override
    public boolean onCreate() {
        TanamanDBHelper = new LetsPlantDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}

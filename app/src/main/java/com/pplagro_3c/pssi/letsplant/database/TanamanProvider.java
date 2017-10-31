package com.pplagro_3c.pssi.letsplant.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pplagro_3c.pssi.letsplant.database.LetsPlantContract.PemainEntry;
import com.pplagro_3c.pssi.letsplant.database.LetsPlantContract.LahanEntry;
import com.pplagro_3c.pssi.letsplant.database.LetsPlantContract.TanamanEntry;
import com.pplagro_3c.pssi.letsplant.objek.Lahan;
import com.pplagro_3c.pssi.letsplant.objek.Tanaman;

/**
 * Created by Aleq on 23/10/2017.
 */

public class TanamanProvider extends ContentProvider {

    /* tipe akses ke tabel tanaman */
    private static final int TANAMAN = 300;
    private static final int JENIS_TANAMAN = 310;
    private static final int LOKASI = 311;
    private static final int BUAH = 312;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        /**
         *
         */
        sUriMatcher.addURI(LetsPlantContract.CONTENT_AUTHORITY, LetsPlantContract.PATH_LETS_PLANT, JENIS_TANAMAN);

        /**
         *
         */
        sUriMatcher.addURI(LetsPlantContract.CONTENT_AUTHORITY, LetsPlantContract.PATH_LETS_PLANT, LOKASI);

        /**
         *
         */
        sUriMatcher.addURI(LetsPlantContract.CONTENT_AUTHORITY, LetsPlantContract.PATH_LETS_PLANT, BUAH);
    }

    private LetsPlantDBHelper TanamanDBHelper;

    @Override
    public boolean onCreate() {
        TanamanDBHelper = new LetsPlantDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] column, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = TanamanDBHelper.getReadableDatabase();
        Cursor output = null;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case TANAMAN:

                break;
            case JENIS_TANAMAN:
                break;
            case LOKASI:
                break;
            case BUAH:
                break;
        }
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

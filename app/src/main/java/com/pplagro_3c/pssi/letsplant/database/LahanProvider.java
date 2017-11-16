package com.pplagro_3c.pssi.letsplant.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.pplagro_3c.pssi.letsplant.database.LetsPlantContract.LahanEntry;
import com.pplagro_3c.pssi.letsplant.database.LetsPlantContract.TanamanEntry;

/**
 * Created by Aleq on 23/10/2017.
 */

public class LahanProvider extends ContentProvider {

    public static final String LOG_TAG = LahanProvider.class.getSimpleName();

    /* tipe akses ke tabel lahan */
    private static final int LAHAN = 200;
    private static final int TIPE_LAHAN = 210;
    private static final int TANAMAN = 211;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        /**
         *
         */
        sUriMatcher.addURI(LetsPlantContract.CONTENT_AUTHORITY, LetsPlantContract.PATH_LETS_PLANT, LAHAN);
        /**
         *
         */
        sUriMatcher.addURI(LetsPlantContract.CONTENT_AUTHORITY, LetsPlantContract.PATH_LETS_PLANT, TIPE_LAHAN);

        /**
         *
         */
        sUriMatcher.addURI(LetsPlantContract.CONTENT_AUTHORITY, LetsPlantContract.PATH_LETS_PLANT + "/#", TANAMAN);
    }

    private LetsPlantDBHelper LahanDBHelper;

    @Override
    public boolean onCreate() {
        LahanDBHelper = new LetsPlantDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] column, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = LahanDBHelper.getReadableDatabase();
        Cursor output = null;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case LAHAN:

                break;
            case TANAMAN:
                selection = LahanEntry._ID + "=?";
                SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
                builder.setTables(
                        LahanEntry.KOLOM_ID_PEMAIN
                                + " JOIN " + TanamanEntry.NAMA_TABEL + " ON " + LahanEntry.NAMA_TABEL + "." + LahanEntry._ID
                                + " = " + TanamanEntry.NAMA_TABEL + "." + TanamanEntry.KOLOM_IDLahan
                );
                output = builder.query(db, column, selection, selectionArgs, null, null, sortOrder);
                break;
            case TIPE_LAHAN:
                //nothing
                break;
            default:
                ;
        }
        return output;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = LahanDBHelper.getWritableDatabase();

        long id = db.insert(LahanEntry.NAMA_TABEL, null, contentValues);
        if (id == -1) {
            Log.e(LOG_TAG, "Gagal Menyimpan ke table pemain, " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
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

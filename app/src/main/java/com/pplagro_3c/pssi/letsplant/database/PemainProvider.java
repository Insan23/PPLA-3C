package com.pplagro_3c.pssi.letsplant.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.pplagro_3c.pssi.letsplant.database.LetsPlantContract.PemainEntry;

/**
 * Created by Aleq on 06/10/2017.
 */

public class PemainProvider extends ContentProvider {
    // tag untuk pesan log
    public static final String LOG_TAG = PemainProvider.class.getSimpleName();

    /* tipe akses ke tabel pemain */
    private static final int NAMA_PEMAIN = 110;
    private static final int ASET_PEMAIN = 120;

    private static final int KOIN_PEMAIN = 121;
    private static final int COKLAT_PEMAIN = 122;
    private static final int KAKAO_PEMAIN = 123;
    private static final int BIBIT_PEMAIN = 124;
    private static final int POLYBAG_PEMAIN = 125;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        /**
         *
         */
        sUriMatcher.addURI(LetsPlantContract.CONTENT_AUTHORITY, LetsPlantContract.PATH_LETS_PLANT + "/#", NAMA_PEMAIN);

        /**
         *
         */
        sUriMatcher.addURI(LetsPlantContract.CONTENT_AUTHORITY, LetsPlantContract.PATH_LETS_PLANT + "/#", ASET_PEMAIN);

        /**
         *
         */
        sUriMatcher.addURI(LetsPlantContract.CONTENT_AUTHORITY, LetsPlantContract.PATH_LETS_PLANT, KOIN_PEMAIN);

        /**
         *
         */
        sUriMatcher.addURI(LetsPlantContract.CONTENT_AUTHORITY, LetsPlantContract.PATH_LETS_PLANT, COKLAT_PEMAIN);

        /**
         *
         */
        sUriMatcher.addURI(LetsPlantContract.CONTENT_AUTHORITY, LetsPlantContract.PATH_LETS_PLANT, KAKAO_PEMAIN);

        /**
         *
         */
        sUriMatcher.addURI(LetsPlantContract.CONTENT_AUTHORITY, LetsPlantContract.PATH_LETS_PLANT, BIBIT_PEMAIN);

        /**
         *
         */
        sUriMatcher.addURI(LetsPlantContract.CONTENT_AUTHORITY, LetsPlantContract.PATH_LETS_PLANT, POLYBAG_PEMAIN);
    }

    private LetsPlantDBHelper PemainDBHelper;

    @Override
    public boolean onCreate() {
        PemainDBHelper = new LetsPlantDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] column, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //mengambil database yg bisa dibcaca
        SQLiteDatabase db = PemainDBHelper.getReadableDatabase();
        Cursor output = null;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case NAMA_PEMAIN:
                column = new String[]{
                        PemainEntry.KOLOM_NAMA
                };
                selection = PemainEntry._ID + "=?";
                selectionArgs = new String[]{
                        String.valueOf(ContentUris.parseId(uri))
                };
                output = db.query(PemainEntry.NAMA_TABEL, column, selection, selectionArgs, null, null, sortOrder);
                break;
            case ASET_PEMAIN:
                column = new String[]{
                        PemainEntry.KOLOM_JUMLAH_KOIN,
                        PemainEntry.KOLOM_JUMLAH_COKLAT,
                        PemainEntry.KOLOM_JUMLAH_BUAH_KAKAO,
                        PemainEntry.KOLOM_JUMLAH_BIBIT,
                        PemainEntry.KOLOM_JUMLAH_POLYBAG
                };
                selection = PemainEntry._ID + "=?";
                selectionArgs = new String[]{
                        String.valueOf(ContentUris.parseId(uri))
                };
                output = db.query(PemainEntry.NAMA_TABEL, column, selection, selectionArgs, null, null, sortOrder);
                break;
            case KOIN_PEMAIN:
                column = new String[]{
                        PemainEntry.KOLOM_JUMLAH_KOIN
                };
                selection = PemainEntry._ID + "=?";
                selectionArgs = new String[]{
                        String.valueOf(ContentUris.parseId(uri))
                };
                output = db.query(PemainEntry.NAMA_TABEL, column, selection, selectionArgs, null, null, sortOrder);
                break;
            case COKLAT_PEMAIN:
                column = new String[]{
                        PemainEntry.KOLOM_JUMLAH_COKLAT
                };
                selection = PemainEntry._ID + "=?";
                selectionArgs = new String[]{
                        String.valueOf(ContentUris.parseId(uri))
                };
                output = db.query(PemainEntry.NAMA_TABEL, column, selection, selectionArgs, null, null, sortOrder);
                break;
            case KAKAO_PEMAIN:
                column = new String[]{
                        PemainEntry.KOLOM_JUMLAH_BUAH_KAKAO
                };
                selection = PemainEntry._ID + "=?";
                selectionArgs = new String[]{
                        String.valueOf(ContentUris.parseId(uri))
                };
                output = db.query(PemainEntry.NAMA_TABEL, column, selection, selectionArgs, null, null, sortOrder);
                break;
            case BIBIT_PEMAIN:
                column = new String[]{
                        PemainEntry.KOLOM_JUMLAH_BIBIT
                };
                selection = PemainEntry._ID + "=?";
                selectionArgs = new String[]{
                        String.valueOf(ContentUris.parseId(uri))
                };
                output = db.query(PemainEntry.NAMA_TABEL, column, selection, selectionArgs, null, null, sortOrder);
                break;
            case POLYBAG_PEMAIN:
                column = new String[]{
                        PemainEntry.KOLOM_JUMLAH_POLYBAG
                };
                selection = PemainEntry._ID + "=?";
                selectionArgs = new String[]{
                        String.valueOf(ContentUris.parseId(uri))
                };
                output = db.query(PemainEntry.NAMA_TABEL, column, selection, selectionArgs, null, null, sortOrder);
                break;
            default: /* nothing */
                ;
        }

        output.setNotificationUri(getContext().getContentResolver(), uri);
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
        SQLiteDatabase db = PemainDBHelper.getWritableDatabase();

        long id = db.insert(PemainEntry.NAMA_TABEL, null, contentValues);
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
    public int update(@NonNull Uri uri, @Nullable ContentValues value, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = PemainDBHelper.getWritableDatabase();
        int perubahan = 0;
        /**
        int match = sUriMatcher.match(uri);
        switch (match) {
            case NAMA_PEMAIN:
                //ga bisa ganti nama, jadi ga ada kode yang faedah
                break;
            case ASET_PEMAIN:
                //aset pemain = seluruh aset, update sekaligus, kurang bermanfaat
                break;
            case KOIN_PEMAIN:

                break;
            case COKLAT_PEMAIN:
                break;
            case KAKAO_PEMAIN:
                break;
            case BIBIT_PEMAIN:
                break;
            case POLYBAG_PEMAIN:
                break;
            default:
                ;
        }
         */

        selection = PemainEntry._ID + "=?";
        selectionArgs = new String[]{
                String.valueOf(ContentUris.parseId(uri))
        };

        perubahan = db.update(PemainEntry.NAMA_TABEL, value, selection, selectionArgs);
        return perubahan;
    }

}

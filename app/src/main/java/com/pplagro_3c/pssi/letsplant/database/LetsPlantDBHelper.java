package com.pplagro_3c.pssi.letsplant.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pplagro_3c.pssi.letsplant.database.LetsPlantContract.PemainEntry;
import com.pplagro_3c.pssi.letsplant.database.LetsPlantContract.LahanEntry;
import com.pplagro_3c.pssi.letsplant.database.LetsPlantContract.TanamanEntry;

/**
 * Created by Aleq on 06/10/2017.
 */

public class LetsPlantDBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = LetsPlantDBHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "letsplant.db";

    private static final int DATABASE_VERSION = 1;

    public LetsPlantDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_PEMAIN = "CREATE TABLE " + PemainEntry.NAMA_TABEL
                + " ("
                + PemainEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PemainEntry.KOLOM_NAMA + " TEXT NOT NULL, "
                + PemainEntry.KOLOM_JUMLAH_KOIN + " INTEGER NOT NULL, "
                + PemainEntry.KOLOM_JUMLAH_COKLAT + " INTEGER NOT NULL, "
                + PemainEntry.KOLOM_JUMLAH_BUAH_KAKAO + " INTEGER NOT NULL, "
                + PemainEntry.KOLOM_JUMLAH_BIBIT + " INTEGER NOT NULL, "
                + PemainEntry.KOLOM_JUMLAH_POLYBAG + " INTEGER NOT NULL"
                + ");";
        String CREATE_TABLE_LAHAN = "CREATE TABLE " + LahanEntry.NAMA_TABEL
                + " ("
                + LahanEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LahanEntry.KOLOM_TIPE_LAHAN + " TEXT, "
                + LahanEntry.KOLOM_ID_PEMAIN + " INTEGER, "
                + " FOREIGN KEY(" + LahanEntry.KOLOM_ID_PEMAIN + ") REFERENCES " + PemainEntry.NAMA_TABEL + "(" + PemainEntry._ID + ")"
                + ");";
        String CREATE_TABLE_TANAMAN = "CREATE TABLE " + TanamanEntry.NAMA_TABEL
                + " ("
                + TanamanEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TanamanEntry.KOLOM_IDLahan + " INTEGER, "
                + TanamanEntry.KOLOM_LOKASI + " TEXT, "
                + TanamanEntry.KOLOM_JENIS + " TEXT, "
                + TanamanEntry.KOLOM_BUAH + " INTEGER, "
                + "FOREIGN KEY(" + TanamanEntry.KOLOM_IDLahan + ") REFERENCES " + LahanEntry.NAMA_TABEL + " (" + LahanEntry._ID + ")"
                + ");";
        sqLiteDatabase.execSQL(CREATE_TABLE_PEMAIN);
        sqLiteDatabase.execSQL(CREATE_TABLE_LAHAN);
        sqLiteDatabase.execSQL(CREATE_TABLE_TANAMAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //yang terjadi bila ada update database
    }
}

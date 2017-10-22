package com.pplagro_3c.pssi.letsplant.database;

/**
 * Created by Aleq on 06/10/2017.
 */

import android.net.Uri;
import android.content.ContentResolver;
import android.provider.BaseColumns;

public class LetsPlantContract {
    //untuk mencegah terjadinya eksekusi program secara ilegal, maka konstruktor dikosongi
    private LetsPlantContract() {}

    /**
     * CONTENT AUTHORITY adalah nama untuk seluruh provider, seperti relasi antara domain dan webnya(server)
     * String yang digunakan adalah nama package aplikasinya, sehingga dapat terjamin keunikannya diantara aplikasi lain
     */
    public static final String CONTENT_AUTHORITY = "com.pplagro_3c.pssi.letsplant";

    /**
     * gunakan CONTENT_AUTHORITY untuk membuat dasar dari semua URI yang akan digunakan oleh aplikasi
     * untuk menghubungkannya dengan content privider
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     *
     */
    public static final String PATH_LETS_PLANT = "letsplant";

    /**
     * inner class yang mendefinisikan nilai konstan untuk tabel dan database letsplant
     *
     */
    public static final class TanamanEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_LETS_PLANT);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LETS_PLANT;
        /**
         * nama tabel
         *
         * tanaman
         */
        public static final String TABLE_NAME = "tanaman";

        /**
         * kolom id
         *
         * tipe data : INTEGER
         */
        public static final String _ID = BaseColumns._ID;

        /**
         * kolom lokasi
         *
         * tipe data : VARCHAR
         */
        public static final String COLUMN_LOKASI = "lokasi";

        /**
         * kolom jenis
         *
         * tipe data : ENUM (bibit, tunas, siap_tanam, pohonbesar_takbuah, pohonbesar_buahsedang, pohonbesar_buahbesar)
         */
        public static final String COLUMN_JENIS = "jenis";

        /**
         * kolom buah
         *
         * tipe data : ENUM (sedang, besar, siap_panen)
         */
        public static final String COLUMN_BUAH = "buah";
    }

    public static final class LahanEntry implements BaseColumns {

    }
}

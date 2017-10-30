package com.pplagro_3c.pssi.letsplant.database;

/**
 * Created by Aleq on 06/10/2017.
 */

import android.net.Uri;
import android.provider.BaseColumns;

public class LetsPlantContract {
    //untuk mencegah terjadinya eksekusi program secara ilegal, maka konstruktor dikosongi
    private LetsPlantContract() {
    }

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
     * inner class untuk definisi isi table pemain
     */
    public static final class PemainEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_LETS_PLANT + "/pemain");

        /**
         * nama tabel
         */
        public static final String NAMA_TABEL = "pemain";

        /**
         * kolom id
         */
        public static final String _ID = "_IDPemain";

        /**
         * kolom nama
         * 
         * tipe data : TEXT
         */
        public static final String KOLOM_NAMA = "nama";

        /**
         * kolom jmlKoin
         * 
         * tipe data : INTEGER
         */
        public static final String KOLOM_JUMLAH_KOIN = "jmlKoin";

        /**
         * kolom jmlCoklat
         * 
         * tipe data : INTEGER
         */
        public static final String KOLOM_JUMLAH_COKLAT = "jmlCoklat";

        /**
         * kolom jmlBuahKakao
         * 
         * tipe data : INTEGER
         */
        public static final String KOLOM_JUMLAH_BUAH_KAKAO = "jmlBuahKakao";

        /**
         * kolom jmlBibit
         * 
         * tipe data : INTEGER
         */
        public static final String KOLOM_JUMLAH_BIBIT = "jmlBibit";

        /**
         * kolom jmlPolybag
         * 
         * tipe data INTEGER
         */
        public static final String KOLOM_JUMLAH_POLYBAG = "jmlPolybag";
    }

    public static final class LahanEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_LETS_PLANT + "/lahan");

        /**
         * nama tabel
         */
        public static final String NAMA_TABEL = "lahan";

        /**
         * kolom id
         *
         * tipe data : INTEGER
         */
        public static final String _ID = "_IDLahan";

        /**
         * kolom tipeLahan
         *
         * tipe data : TEXT (Lahan_Polybag, Lahan_Siap_Tanam)
         */
        public static final String KOLOM_TIPE_LAHAN = "tipeLahan";

        /**
         * kolom idPemain
         */
        public static final String KOLOM_ID_PEMAIN = PemainEntry._ID;

        /**
         * tipe-tipe dari lahan
         */
        public static final String LAHAN_POLYBAG = "Lahan Polybag";
        public static final String LAHAN_SIAP_TANAM = "Lahan Siap Tanam";
    }

    /**
     * inner class yang mendefinisikan nilai konstan untuk tabel dan database letsplant
     */
    public static final class TanamanEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_LETS_PLANT + "/tanaman");

        /**
         * nama tabel
         *
         * tanaman
         */
        public static final String NAMA_TABEL = "tanaman";

        /**
         * kolom id
         *
         * tipe data : INTEGER
         */
        public static final String _ID = "_IDTanaman";

        /**
         * kolom idLahan (foreign key)
         *
         * tipe data : INTEGER
         */
        public static final String KOLOM_IDLahan = LahanEntry._ID;

        /**
         * kolom lokasi
         *
         * tipe data : VARCHAR
         */
        public static final String KOLOM_LOKASI = "lokasi";

        /**
         * kolom jenis
         *
         * tipe data : TEXT (bibit, tunas, siap_tanam, pohonbesar_takbuah, pohonbesar_buahsedang, pohonbesar_buahbesar)
         */
        public static final String KOLOM_JENIS = "jenis";

        /**
         * kolom buah
         *
         * tipe data : INTEGER
         * atau
         * tipe data : TEXT (sedang, besar, siap_panen)
         */
        public static final String KOLOM_BUAH = "buah";

        /**
         * tipe-tipe dari kolom jenis
         * (Tipe Tipe Perkembangan Tanaman)
         */
        public static final String TANAMAN_BIBIT = "bibit";
        public static final String TANAMAN_TUNAS = "tunas";
        public static final String TANAMAN_SIAP_TANAM = "siap_tanam";
        public static final String TANAMAN_BESAR_TANPA_BUAH = "pohon_besar_tak_berbuah";
        public static final String TANAMAN_BESAR_BUAH_SEDANG = "pohon_besar_buah_sedang";
        public static final String TANAMAN_BESAR_BUAH_BESAR  ="pohon_besar_buah_besar";



        /**
         * tipe-tipe dari kolom buah
         */
    }

}

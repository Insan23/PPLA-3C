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
         * <p>
         * tipe data : TEXT
         */
        public static final String KOLOM_NAMA = "nama";

        /**
         * kolom jmlKoin
         * <p>
         * tipe data : INTEGER
         */
        public static final String KOLOM_JUMLAH_KOIN = "jmlKoin";

        /**
         * kolom jmlCoklat
         * <p>
         * tipe data : INTEGER
         */
        public static final String KOLOM_JUMLAH_COKLAT = "jmlCoklat";

        /**
         * kolom jmlBuahKakao
         * <p>
         * tipe data : INTEGER
         */
        public static final String KOLOM_JUMLAH_BUAH_KAKAO = "jmlBuahKakao";

        /**
         * kolom jmlBibit
         * <p>
         * tipe data : INTEGER
         */
        public static final String KOLOM_JUMLAH_BIBIT = "jmlBibit";

        /**
         * kolom jmlPolybag
         * <p>
         * tipe data INTEGER
         */
        public static final String KOLOM_JUMLAH_POLYBAG = "jmlPolybag";

        /**
         * kolom jumlah tanaman siap tanam yang disimpan
         */
        public static final String KOLOM_JUMLAH_TANAMAN_SIAP_TANAM = "jmlTanamanSiapTanam";
    }

    public static final class LahanEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_LETS_PLANT + "/lahan");

        /**
         * nama tabel
         */
        public static final String NAMA_TABEL = "lahan";

        /**
         * kolom id
         * <p>
         * tipe data : INTEGER
         */
        public static final String _ID = "_IDLahan";

        /**
         * kolom tipeLahan
         * <p>
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
         * <p>
         * tanaman
         */
        public static final String NAMA_TABEL = "tanaman";

        /**
         * kolom id
         * <p>
         * tipe data : INTEGER
         */
        public static final String _ID = "_IDTanaman";

        /**
         * kolom idLahan (foreign key)
         * <p>
         * tipe data : INTEGER
         */
        public static final String KOLOM_IDLahan = LahanEntry._ID;

        /**
         * kolom lokasi
         * <p>
         * tipe data : VARCHAR
         */
        public static final String KOLOM_LOKASI = "lokasi";

        /**
         * kolom jenis
         * <p>
         * tipe data : TEXT (bibit, tunas, siap_tanam, pohonbesar_takbuah, pohonbesar_buahsedang, pohonbesar_buahbesar)
         */
        public static final String KOLOM_JENIS = "jenis";

        /**
         * kolom buah
         * <p>
         * tipe data : INTEGER
         * atau
         * tipe data : TEXT (sedang, besar, siap_panen)
         */
        public static final String KOLOM_BUAH = "buah";

        /**
         * tipe-tipe dari kolom jenis
         * (Tipe Tipe Perkembangan Tanaman)
         */
        public static final String TANAMAN_KOSONG = "kosong";
        public static final String TANAMAN_POLYBAG = "polybag";
        public static final String TANAMAN_BIBIT = "bibit";
        public static final String TANAMAN_TUNAS = "tunas";
        public static final String TANAMAN_TUNAS_KUNING = "tunas_kuning";
        public static final String TANAMAN_SIAP_TANAM = "siap_tanam";
        public static final String TANAMAN_SIAP_TANAM_KUNING = "siap_tanam_kuning";
        public static final String TANAMAN_BESAR_TANPA_BUAH = "pohon_besar_tak_berbuah";
        public static final String TANAMAN_BESAR_TANPA_BUAH_KUNING = "pohon_besar_tak_berbuah_kuning";
        public static final String TANAMAN_BESAR_TANPA_BUAH_KUNING_SEMUA = "pohon_besar_tak_berbuah_kuning_semua";
        public static final String TANAMAN_BESAR_BUAH_SEDANG = "pohon_besar_buah_sedang";
        public static final String TANAMAN_BESAR_BUAH_SEDANG_KUNING = "pohon_besar_buah_sedang_kuning";
        public static final String TANAMAN_BESAR_BUAH_SEDANG_KUNING_SEMUA = "pohon_besar_buah_sedang_kuning_semua";
        public static final String TANAMAN_BESAR_BUAH_BESAR = "pohon_besar_buah_besar";
        public static final String TANAMAN_BESAR_BUAH_BESAR_KUNING = "pohon_besar_buah_besar_kuning";
        public static final String TANAMAN_BESAR_BUAH_BESAR_KUNING_SEMUA = "pohon_besar_buah_besar_kuning_semua";

        /**
         * koordinat Lahan
         */

        public static final String POS_11 = "11";
        public static final String POS_21 = "21";
        public static final String POS_31 = "31";
        public static final String POS_41 = "41";
        public static final String POS_51 = "51";

        public static final String POS_12 = "12";
        public static final String POS_22 = "22";
        public static final String POS_32 = "32";
        public static final String POS_42 = "42";
        public static final String POS_52 = "52";

        public static final String POS_13 = "13";
        public static final String POS_23 = "23";
        public static final String POS_33 = "33";
        public static final String POS_43 = "43";
        public static final String POS_53 = "53";

        /**
         * tipe-tipe dari kolom buah
         */
    }

}

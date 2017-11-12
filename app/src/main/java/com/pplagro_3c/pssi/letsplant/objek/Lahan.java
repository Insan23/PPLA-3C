package com.pplagro_3c.pssi.letsplant.objek;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.pplagro_3c.pssi.letsplant.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.pplagro_3c.pssi.letsplant.database.LetsPlantContract.TanamanEntry.TANAMAN_BIBIT;

/**
 * Created by Aleq on 23/10/2017.
 */

public class Lahan {

    private static enum LAHAN {
        LAHAN_POLYBAG,
        LAHAN_TANAM
    }

    private static enum AKSI_USER {
        BIBIT, POLYBAG, PUPUK,
        AIR, CANGKUL, SABIT
    }

    /**
     * Tanaman Bibit, Tunas, dan Siap Tanam, masih di dalam polybag
     *
     * Tanaman Besar
     *
     */
    private static enum TANAMAN {
        POLYBAG,
        BIBIT,
        TUNAS,
        SIAP_TANAM,
        BESAR_TANPA_BUAH,
        BESAR_BUAH_SEDANG,
        BESAR_BUAH_BESAR
    }

    private Context konteks;
    private ImageView lahan;
    private ImageView notif;
    private LAHAN LAHAN_SAAT_INI;

    private int pemupukan;
    private int penyiraman;
    private int jumlahBuah = 10;

    TANAMAN SAAT_INI;

    private enum AKSI {
        AIR,
        PUPUK,
        CANGKUL
    }

    public Lahan(Context konteks, ImageView L, ImageView N) {
        this.konteks = konteks;
        lahan = L;
        notif = N;
    }

    public void tindakan(AKSI_USER aksi) {
        switch (aksi) {
            case POLYBAG:
                letakPoly();
                break;
            case BIBIT:
                letakBibit();
                break;
            case PUPUK:
                beriPupuk();
                break;
            case AIR:
                beriAir();
                break;
            case CANGKUL:
                mencangkulTanah();
                break;
            case SABIT:
                basmiHama();
                break;
            default:
                Toast.makeText(konteks, "Tindakan GAGAL, Tidak Diketahui", Toast.LENGTH_LONG);
        }
    }

    private void basmiHama() {

    }

    private void mencangkulTanah() {

    }

    private void beriAir() {

    }

    private void beriPupuk() {

    }

    private void letakBibit() {
        lahan.setImageResource(R.drawable.tanaman_bibit);
        SAAT_INI = TANAMAN.BIBIT;
    }

    private void letakPoly() {
        lahan.setImageResource(R.drawable.tanaman_polybag);
        SAAT_INI = TANAMAN.POLYBAG;
        LAHAN_SAAT_INI = LAHAN.LAHAN_POLYBAG;
    }

}

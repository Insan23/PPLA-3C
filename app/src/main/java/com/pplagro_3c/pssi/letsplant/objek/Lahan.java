package com.pplagro_3c.pssi.letsplant.objek;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.pplagro_3c.pssi.letsplant.R;


/**
 * Created by Aleq on 23/10/2017.
 */

public class Lahan {

    public static enum LAHAN {
        LAHAN_POLYBAG,
        PINDAH,
        LAHAN_TANAM
    }

    public static enum AKSI_USER {
        TIDAK_ADA,
        BIBIT, POLYBAG, PUPUK, LAHAN_TANAM, LAHAN_POLY,
        SIAP_TANAM,
        AIR, CANGKUL, SABIT,
    }

    /**
     * Tanaman Bibit, Tunas, dan Siap Tanam, masih di dalam polybag
     * <p>
     * Tanaman Besar
     */
    private static enum TANAMAN {
        KOSONG,
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

    public Lahan(Context konteks, ImageView L, ImageView N) {
        this.konteks = konteks;
        lahan = L;
        notif = N;
        LAHAN_SAAT_INI = LAHAN.LAHAN_POLYBAG;
    }

    public void setLahan(LAHAN lahan) {
        LAHAN_SAAT_INI = lahan;
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
            case LAHAN_TANAM:
                break;
            case LAHAN_POLY:
                break;
            case SIAP_TANAM:
                break;

            case AIR:
                beriAir();
                break;
            case CANGKUL:
                mencangkulTanah();
                break;
            case SABIT:
                pakaiSabit();
                break;
            default:
                Toast.makeText(konteks, "Tindakan GAGAL, Tidak Diketahui", Toast.LENGTH_LONG);
        }
    }



    private void pakaiSabit() {

    }

    private void mencangkulTanah() {

    }

    private void beriAir() {

    }

    private void beriPupuk() {

    }

    private void letakBibit() {
        if (SAAT_INI == TANAMAN.POLYBAG && LAHAN_SAAT_INI == LAHAN.LAHAN_POLYBAG) {
            lahan.setImageResource(R.drawable.tanaman_bibit);
            SAAT_INI = TANAMAN.BIBIT;
        } else {
            //koding jika petak saat ini bukan polybag
        }
    }

    private void letakPoly() {
        if (SAAT_INI == TANAMAN.KOSONG && LAHAN_SAAT_INI == LAHAN.LAHAN_POLYBAG) {
            lahan.setImageResource(R.drawable.tanaman_polybag);
            SAAT_INI = TANAMAN.POLYBAG;
        } else {
            //koding jika lahan tidak kosong
        }
    }

}

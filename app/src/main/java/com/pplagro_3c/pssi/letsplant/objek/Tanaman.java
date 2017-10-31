package com.pplagro_3c.pssi.letsplant.objek;

import android.widget.ImageView;

import com.pplagro_3c.pssi.letsplant.R;
import com.pplagro_3c.pssi.letsplant.database.LetsPlantContract.TanamanEntry;

/**
 * Created by Aleq on 23/10/2017.
 */

public class Tanaman {

    private ImageView iTanam;
    private String jenisTanaman;
    private String lokasi;
    private int penyiraman;
    private int pemupukan;
    private int idLahan;

    //lahan poly
    private static int BIBIT = R.drawable.tanaman_bibit;
    private static int TUNAS = R.drawable.tanaman_tunas;
    private static int SIAP_TANAM = R.drawable.tanaman_siap_tanam;


    public Tanaman(/*ImageView i, String jenis, String lokasi, int idLahan*/) {
        /*this.iTanam = i;
        this.jenisTanaman = jenis;
        this.lokasi = lokasi;
        this.idLahan = idLahan;
        */
    }

    public void setiTanam(ImageView iv) {
        this.iTanam = iv;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public void setJenisTanaman(String jenis) {
        this.jenisTanaman = jenis;
        switch (jenis) {
            case TanamanEntry.TANAMAN_BIBIT:
                iTanam.setImageResource(BIBIT);
                break;
            case TanamanEntry.TANAMAN_TUNAS:
                iTanam.setImageResource(TUNAS);
                break;
            case TanamanEntry.TANAMAN_SIAP_TANAM:
                iTanam.setImageResource(SIAP_TANAM);
                break;
        }
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getJenisTanaman() {
        return jenisTanaman;
    }

}

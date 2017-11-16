package com.pplagro_3c.pssi.letsplant.objek;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by Aleq on 16/11/2017.
 */

public class Toko {
    public static enum AKSI_USER_TOKO {
        TIDAK_ADA,
        BIBIT_TOKO, POLYBAG_TOKO, PUPUK_TOKO, LAHAN_TANAM_TOKO, LAHAN_POLY_TOKO
    }

    private Context konteks;

    public Toko(Context konteks) {
        this.konteks = konteks;
    }

    private void pakaiToko(final AKSI_USER_TOKO toko) {
        int koin = -1;
        switch (toko) {
            case BIBIT_TOKO:
                koin= 40;
                break;
            case PUPUK_TOKO:
                koin = 500;
                break;
            case POLYBAG_TOKO:
                koin = 40;
                break;
            case LAHAN_TANAM_TOKO:
                koin = 2000;
                break;
            case LAHAN_POLY_TOKO:
                koin = 2000;
                break;
            default:
                Toast.makeText(konteks, "Tindakan GAGAL, Tidak Diketahui", Toast.LENGTH_LONG);
        }
        new AlertDialog.Builder(konteks).setMessage("Yakin ingin beli? Koinmu akan berkurang sebanyak " + koin)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        beliProduk(toko);
                    }
                })
                .setNegativeButton("Tidak", null).show();
    }

    private void beliProduk(AKSI_USER_TOKO produk) {
        switch (produk) {
            case BIBIT_TOKO:

                break;
            case PUPUK_TOKO:

                break;
            case POLYBAG_TOKO:

                break;
            case LAHAN_TANAM_TOKO:

                break;
            case LAHAN_POLY_TOKO:

                break;
            default:
                Toast.makeText(konteks, "Tindakan GAGAL, Tidak Diketahui", Toast.LENGTH_LONG);
        }
    }
}

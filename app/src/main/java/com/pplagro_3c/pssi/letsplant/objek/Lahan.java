package com.pplagro_3c.pssi.letsplant.objek;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Handler;

import com.pplagro_3c.pssi.letsplant.R;


/**
 * Created by Aleq on 23/10/2017.
 */

public class Lahan {

    public static enum LAHAN {
        LAHAN_POLYBAG, PINDAH, LAHAN_TANAM
    }

    public static enum AKSI_USER {
        TIDAK_ADA,
        BIBIT, POLYBAG, PUPUK, LAHAN_TANAM, LAHAN_POLY,
        SIAP_TANAM,
        AIR, CANGKUL, SABIT,
    }

    Handler mHandler;

    /**
     * Tanaman Bibit, Tunas, dan Siap Tanam, masih di dalam polybag
     * <p>
     * Tanaman Besar
     */
    public static enum TANAMAN {
        KOSONG, CANGKUL,
        POLYBAG, BIBIT,
        TUNAS, TUNAS_KUNING,
        SIAP_TANAM, SIAP_TANAM_KUNING,

        KECIL, KECIL_KUNING,
        BESAR_TANPA_BUAH, BESAR_TANPA_BUAH_KUNING, BESAR_TANPA_BUAH_KUNING_SEMUA,
        BESAR_BUAH_SEDANG, BESAR_BUAH_SEDANG_KUNING, BESAR_BUAH_SEDANG_KUNING_SEMUA,
        BESAR_BUAH_BESAR, BESAR_BUAH_BESAR_KUNING, BESAR_BUAH_BESAR_KUNING_SEMUA
    }

    private static enum PERAWATAN {
        TIDAK_ADA,
        AIR_BUTUH, PUPUK_BUTUH, PANEN_BUTUH,
        AIR_SUKSES, PUPUK_SUSKES, PANEN_SUKSES
    }

    private Context konteks;
    private ImageView lahan;
    private ImageView notif;
    private LAHAN LAHAN_SAAT_INI;

    private int pemupukan;
    private int tidak_memupuk;
    private int penyiraman;
    private int tidak_menyiram;
    private int jumlahBuah = 10;

    private delayMemupuk waktuPemupukan;
    private delayMenyiram waktuPenyiraman;

    private boolean butuhAir;
    private boolean butuhPupuk;
    private boolean butuhPanen;

    TANAMAN TANAMAN_SAAT_INI;

    public Lahan(Context konteks, ImageView L, ImageView N) {
        mHandler = new Handler(Looper.getMainLooper());
        this.konteks = konteks;
        lahan = L;
        notif = N;
        LAHAN_SAAT_INI = LAHAN.LAHAN_POLYBAG;
        TANAMAN_SAAT_INI = TANAMAN.KOSONG;

        waktuPemupukan = new delayMemupuk();
        waktuPenyiraman = new delayMenyiram();
    }

    public void setLahan(LAHAN lahan) {
        LAHAN_SAAT_INI = lahan;
    }

    public TANAMAN getTANAMAN_SAAT_INI() {
        return TANAMAN_SAAT_INI;
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
                Toast.makeText(konteks, "Tindakan GAGAL, Tidak Diketahui: TINDAKAN_USER", Toast.LENGTH_LONG);
        }
    }


    private void pakaiSabit() {

    }

    private void mencangkulTanah() {
        if (LAHAN_SAAT_INI == LAHAN.LAHAN_TANAM && TANAMAN_SAAT_INI == TANAMAN.CANGKUL) {
            TANAMAN_SAAT_INI = TANAMAN.CANGKUL;
            gantiImageTanaman(TANAMAN_SAAT_INI);
        } else {
            //kode bila tanah sudah dicangkul
        }
    }

    private void beriAir() {
        if (butuhAir) {
            gantiImageNotif(PERAWATAN.AIR_SUKSES);
            penyiraman--;
            mHandler.removeCallbacks(waktuPenyiraman);
            Toast.makeText(konteks, "Debug: Penyiraman Selesai", Toast.LENGTH_LONG);
            pertumbuhan();
        }
    }

    private void beriPupuk() {
        if (butuhPupuk) {
            gantiImageNotif(PERAWATAN.PUPUK_SUSKES);
            pemupukan--;
            mHandler.removeCallbacks(waktuPemupukan);
            Toast.makeText(konteks, "Debug: Pemupukan Selesai", Toast.LENGTH_LONG);
            pertumbuhan();
        }
    }

    private void letakBibit() {
        penyiraman = 3;
        tidak_menyiram = 0;
        pemupukan = 2;
        tidak_memupuk = 0;
        if (TANAMAN_SAAT_INI == TANAMAN.POLYBAG && LAHAN_SAAT_INI == LAHAN.LAHAN_POLYBAG) {
            gantiImageTanaman(TANAMAN.BIBIT);
            TANAMAN_SAAT_INI = TANAMAN.BIBIT;
            pertumbuhan();
        } else {
            //koding jika petak saat ini bukan polybag
        }
    }

    private void pertumbuhan() {
        if (penyiraman > 0) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gantiImageNotif(PERAWATAN.AIR_BUTUH);
                    mHandler.postDelayed(waktuPenyiraman, 60000); //jeda 1 menit untuk melakukan penyiraman
                }
            }, 180000); //menunggu 3 menit untuk dapat menyiram (3m * 60d = 180 * 1000milidetik = 180.000
        } else if (penyiraman == 0) {
            if (LAHAN_SAAT_INI == LAHAN.LAHAN_POLYBAG) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gantiImageNotif(PERAWATAN.PUPUK_BUTUH);
                        mHandler.postDelayed(waktuPemupukan, 300000);
                        if (pemupukan == 1) {
                            TANAMAN_SAAT_INI = TANAMAN.TUNAS;
                        } else if (pemupukan == 0) {
                            TANAMAN_SAAT_INI = TANAMAN.SIAP_TANAM;
                        } else {
                            Toast.makeText(konteks, "Debug: siap dipindah", Toast.LENGTH_LONG);
                        }
                    }
                }, 360000);
            } else if (LAHAN_SAAT_INI == LAHAN.PINDAH) {

            } else if (LAHAN_SAAT_INI == LAHAN.LAHAN_TANAM) {

            }
            penyiraman = 3;
        }
        gantiImageTanaman(TANAMAN_SAAT_INI);
    }

    private class delayMemupuk implements Runnable {

        @Override
        public void run() {
            if (butuhPupuk) {
                Toast.makeText(konteks, "Debug: Masih Butuh Pupuk", Toast.LENGTH_LONG);
                gantiImageNotif(PERAWATAN.TIDAK_ADA);
                tidak_memupuk--;
                if (tidak_memupuk == 2) {
                    if (LAHAN_SAAT_INI == LAHAN.LAHAN_POLYBAG) {
                        TANAMAN_SAAT_INI = TANAMAN.POLYBAG;
                        gantiImageTanaman(TANAMAN_SAAT_INI);
                    } else if (LAHAN_SAAT_INI == LAHAN.LAHAN_TANAM) {
                        TANAMAN_SAAT_INI = TANAMAN.KOSONG;
                        gantiImageTanaman(TANAMAN_SAAT_INI);
                    }
                }
            }
            pertumbuhan();
        }
    }

    private class delayMenyiram implements Runnable {

        @Override
        public void run() {
            if (butuhAir) {
                Toast.makeText(konteks, "Debug: Masih Butuh Air", Toast.LENGTH_LONG);
                gantiImageNotif(PERAWATAN.TIDAK_ADA);
                tidak_menyiram++;
                if (tidak_menyiram == 1) {
                    switch (TANAMAN_SAAT_INI) {
                        case BIBIT:
                            break;
                        case TUNAS:
                            TANAMAN_SAAT_INI = TANAMAN.TUNAS_KUNING;
                            break;
                        case SIAP_TANAM:
                            TANAMAN_SAAT_INI = TANAMAN.SIAP_TANAM_KUNING;
                            break;
                        case KECIL:
                            TANAMAN_SAAT_INI = TANAMAN.KECIL_KUNING;
                            break;
                        case BESAR_TANPA_BUAH:
                            TANAMAN_SAAT_INI = TANAMAN.BESAR_TANPA_BUAH_KUNING;
                            break;
                        case BESAR_BUAH_SEDANG:
                            TANAMAN_SAAT_INI = TANAMAN.BESAR_BUAH_SEDANG_KUNING;
                            break;
                        case BESAR_BUAH_BESAR:
                            TANAMAN_SAAT_INI = TANAMAN.BESAR_BUAH_BESAR_KUNING;
                            break;
                        default:
                            Toast.makeText(konteks, "Debug: Tanaman Tidak Diketahui, tidak_menyiram_1", Toast.LENGTH_LONG);
                    }
                } else if (tidak_menyiram == 2) {
                    switch (TANAMAN_SAAT_INI) {
                        case BIBIT:
                            break;
                        case TUNAS_KUNING:
                            TANAMAN_SAAT_INI = TANAMAN.TUNAS_KUNING;
                            break;
                        case SIAP_TANAM_KUNING:
                            TANAMAN_SAAT_INI = TANAMAN.SIAP_TANAM_KUNING;
                            break;
                        case KECIL_KUNING:
                            TANAMAN_SAAT_INI = TANAMAN.KECIL_KUNING;
                            break;
                        case BESAR_TANPA_BUAH_KUNING:
                            TANAMAN_SAAT_INI = TANAMAN.BESAR_TANPA_BUAH_KUNING_SEMUA;
                            break;
                        case BESAR_BUAH_SEDANG_KUNING:
                            TANAMAN_SAAT_INI = TANAMAN.BESAR_BUAH_SEDANG_KUNING_SEMUA;
                            break;
                        case BESAR_BUAH_BESAR_KUNING:
                            TANAMAN_SAAT_INI = TANAMAN.BESAR_BUAH_BESAR_KUNING_SEMUA;
                            break;
                        default:
                            Toast.makeText(konteks, "Debug: Tanaman Tidak Diketahui, tidak_menyiram_2", Toast.LENGTH_LONG);
                    }
                } else if (tidak_menyiram == 3) {
                    switch (TANAMAN_SAAT_INI) {
                        case BIBIT:
                            TANAMAN_SAAT_INI = TANAMAN.POLYBAG;
                            break;
                        case TUNAS_KUNING:
                            TANAMAN_SAAT_INI = TANAMAN.POLYBAG;
                            break;
                        case SIAP_TANAM_KUNING:
                            TANAMAN_SAAT_INI = TANAMAN.POLYBAG;
                            break;
                        case KECIL_KUNING:
                            TANAMAN_SAAT_INI = TANAMAN.KECIL_KUNING;
                            break;
                        case BESAR_TANPA_BUAH_KUNING_SEMUA:
                            TANAMAN_SAAT_INI = TANAMAN.BESAR_TANPA_BUAH_KUNING_SEMUA;
                            break;
                        case BESAR_BUAH_SEDANG_KUNING_SEMUA:
                            TANAMAN_SAAT_INI = TANAMAN.BESAR_BUAH_SEDANG_KUNING_SEMUA;
                            break;
                        case BESAR_BUAH_BESAR_KUNING_SEMUA:
                            TANAMAN_SAAT_INI = TANAMAN.BESAR_BUAH_BESAR_KUNING_SEMUA;
                            break;
                        default:
                            Toast.makeText(konteks, "Debug: Tanaman Tidak Diketahui, tidak_menyiram_3", Toast.LENGTH_LONG);
                    }
                }
                gantiImageTanaman(TANAMAN_SAAT_INI);
            }
            pertumbuhan();
        }
    }

    private void letakPoly() {
        if (TANAMAN_SAAT_INI == TANAMAN.KOSONG && LAHAN_SAAT_INI == LAHAN.LAHAN_POLYBAG) {
            TANAMAN_SAAT_INI = TANAMAN.POLYBAG;
            gantiImageTanaman(TANAMAN_SAAT_INI);
        } else {
            //koding jika lahan tidak kosong
        }
    }

    private void gantiImageNotif(PERAWATAN p) {
        switch (p) {
            case TIDAK_ADA:
                notif.setImageResource(R.color.transparan);
                break;
            case AIR_BUTUH:
                notif.setImageResource(R.drawable.ic_air_warning);
                butuhAir = true;
                break;
            case AIR_SUKSES:
                notif.setImageResource(R.drawable.ic_air_complete);
                butuhAir = false;
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gantiImageNotif(PERAWATAN.TIDAK_ADA);
                    }
                }, 500);
                break;
            case PUPUK_BUTUH:
                notif.setImageResource(R.drawable.ic_pupuk_warning);
                butuhPupuk = true;
                break;
            case PUPUK_SUSKES:
                notif.setImageResource(R.drawable.ic_pupuk_complete);
                butuhPupuk = false;
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gantiImageNotif(PERAWATAN.TIDAK_ADA);
                    }
                }, 500);
                break;
            case PANEN_BUTUH:
                notif.setImageResource(R.drawable.ic_sabit_warning);
                butuhPanen = true;
                break;
            case PANEN_SUKSES:
                notif.setImageResource(R.drawable.ic_sabit_complete);
                butuhPanen = false;
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gantiImageNotif(PERAWATAN.TIDAK_ADA);
                    }
                }, 300);
                break;
            default:
                Toast.makeText(konteks, "Tindakan GAGAL, Tidak Diketahui: GANTI NOTIF", Toast.LENGTH_LONG);

        }
    }

    private void gantiImageTanaman(TANAMAN t) {
        switch (t) {
            case KOSONG:
                lahan.setImageResource(R.color.transparan);
                break;
            case CANGKUL:
                lahan.setImageResource(R.drawable.tanah_retak);
                break;
            case POLYBAG:
                lahan.setImageResource(R.drawable.tanaman_polybag);
                break;
            case BIBIT:
                lahan.setImageResource(R.drawable.tanaman_bibit);
                break;
            case TUNAS:
                lahan.setImageResource(R.drawable.tanaman_sedang);
                break;
            case TUNAS_KUNING:
                lahan.setImageResource(R.drawable.tanaman_sedang_kuning_sebagian);
                break;
            case SIAP_TANAM:
                lahan.setImageResource(R.drawable.tanaman_siap_tanam);
                break;
            case SIAP_TANAM_KUNING:
                lahan.setImageResource(R.drawable.tanaman_siap_tanam_kuning);
                break;
            case KECIL:
                lahan.setImageResource(R.drawable.tanaman_kecil);
                break;
            case KECIL_KUNING:
                lahan.setImageResource(R.drawable.tanaman_kecil_kuning);
                break;
            case BESAR_TANPA_BUAH:
                lahan.setImageResource(R.drawable.tanaman_besar_tanpa_buah);
                break;
            case BESAR_TANPA_BUAH_KUNING:
                lahan.setImageResource(R.drawable.tanaman_besar_tanpa_buah_kuning);
                break;
            case BESAR_TANPA_BUAH_KUNING_SEMUA:
                lahan.setImageResource(R.drawable.tanaman_besar_tanpa_buah_kuning_semua);
                break;
            case BESAR_BUAH_SEDANG:
                lahan.setImageResource(R.drawable.tanaman_besar_buah_sedang);
                break;
            case BESAR_BUAH_SEDANG_KUNING:
                lahan.setImageResource(R.drawable.tanaman_besar_buah_sedang_kuning);
                break;
            case BESAR_BUAH_SEDANG_KUNING_SEMUA:
                lahan.setImageResource(R.drawable.tanaman_besar_tanpa_buah_kuning_semua);
                break;
            case BESAR_BUAH_BESAR:
                lahan.setImageResource(R.drawable.tanaman_besar_buah_besar);
                break;
            case BESAR_BUAH_BESAR_KUNING:
                lahan.setImageResource(R.drawable.tanaman_besar_buah_besar_kuning);
                break;
            case BESAR_BUAH_BESAR_KUNING_SEMUA:
                lahan.setImageResource(R.drawable.tanaman_besar_buah_besar_kuning_semua);
                break;
            default:
                Toast.makeText(konteks, "Tindakan GAGAL, Tidak Diketahui: GANTI_TANAMAN", Toast.LENGTH_LONG);
        }
    }

}

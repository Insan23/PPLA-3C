package com.pplagro_3c.pssi.letsplant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuAwal extends AppCompatActivity {

    private boolean suara = false;
    private boolean musik = false;

    private static boolean tPengaturan = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_awal);

        ImageView tSuara = (ImageView) findViewById(R.id.suara);
        ImageView tMusik = (ImageView) findViewById(R.id.musik);
        if (!tPengaturan) {
            tSuara.setVisibility(View.GONE);
            tMusik.setVisibility(View.GONE);
        }

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.main);
    }

    @Override
    public void onBackPressed() {
        //pop up saat menekan tombol kembali
        keluar();
    }

    public void tombolMain(View view) {
        startActivity(new Intent(MenuAwal.this, MenuPermainan.class));
    }

    public void tombolKeluar(View view) {
        keluar();
    }

    private void keluar() {
        new AlertDialog.Builder(this)
                .setMessage("Yakin Ingin Keluar?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //kode saat memilih ya
                        finish();
                    }
                }).setNegativeButton("Tidak", null)
                .show();

    }

    public void tombolTentang(View view) {
        Intent i = new Intent(MenuAwal.this, MenuTentang.class);
        startActivity(i);
    }

    public void tombolPetunjuk(View view) {
        Intent i = new Intent(MenuAwal.this, MenuPetunjuk.class);
        startActivity(i);
    }

    public void tombolPengaturan(View view) {
        ImageView tSuara = (ImageView) findViewById(R.id.suara);
        ImageView tMusik = (ImageView) findViewById(R.id.musik);

        if (!tPengaturan) {
            tSuara.setVisibility(View.VISIBLE);
            tMusik.setVisibility(View.VISIBLE);
            tPengaturan = true;
        } else {
            tSuara.setVisibility(View.GONE);
            tMusik.setVisibility(View.GONE);
            tPengaturan = false;
        }

    }

    public void tombolUbahSuara(View view) {
        //kode saat tombol suara ditekan (gambar speaker)
        ImageView s = (ImageView) findViewById(R.id.suara);
        if (!this.suara) {
            this.suara = true;
            s.setImageResource(R.drawable.suara_on);
        } else if (this.suara) {
            this.suara = false;
            s.setImageResource(R.drawable.suara_off);
        }
    }

    public void tombolUbahMusik(View view) {
        //kode saat tombol musik ditekan (gambar not balok)
        ImageView m = (ImageView) findViewById(R.id.musik);
        if (!this.musik) {
            this.musik = true;
            m.setImageResource(R.drawable.musik_on);
        } else if (this.musik) {
            this.musik = false;
            m.setImageResource(R.drawable.musik_off);
        }
    }

}

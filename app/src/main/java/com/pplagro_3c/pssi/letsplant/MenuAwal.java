package com.pplagro_3c.pssi.letsplant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuAwal extends AppCompatActivity {

    private boolean suara = false;
    private boolean musik = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_awal);


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

    public void keluar() {
        if (this.isTaskRoot()) {
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
    }

    public void tombolKeluar(View view) {
        if (this.isTaskRoot()) {
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

        tSuara.setVisibility(View.VISIBLE);
        tMusik.setVisibility(View.VISIBLE);
    }

    public void pengaturanHilang(View view) {
        ImageView tSuara = (ImageView) findViewById(R.id.suara);
        ImageView tMusik = (ImageView) findViewById(R.id.musik);

        tSuara.setVisibility(View.INVISIBLE);
        tMusik.setVisibility(View.INVISIBLE);
    }

    public void tombolUbahSuara(View view) {
        //kode saat tombol suara ditekan
    }

    public void tombolUbahMusik(View view) {
        //kode saat tombol musik ditekan
    }

}

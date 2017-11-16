package com.pplagro_3c.pssi.letsplant;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import static java.security.AccessController.getContext;

public class MenuAwal extends AppCompatActivity {

    private boolean suara;
    private boolean musik;

    private boolean tPengaturan = false;

    SharedPreferences pengaturan = this.getSharedPreferences(
            getString(R.string.pengaturan), Context.MODE_PRIVATE
    );


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
        View decorView = getWindow().getDecorView();
        //hide the status bar
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        MediaPlayer player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        player.setLooping(true);
        player.start();
    }



    @Override
    public void onBackPressed() {
        //pop up saat menekan tombol kembali
        keluar();
    }

    public void tombolMain(View view) {
        Intent i = new Intent(MenuAwal.this, MenuPermainan.class);
        startActivity(i);
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

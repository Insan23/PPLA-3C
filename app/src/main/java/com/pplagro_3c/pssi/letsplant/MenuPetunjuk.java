package com.pplagro_3c.pssi.letsplant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Aleq on 25/09/2017.
 */

public class MenuPetunjuk extends Activity {

    private int index = 0;
    private String gambar[] = {};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_petunjuk);

    }

    @Override
    public void onBackPressed() {
        finish();

    }

    public void menuAwal(View view) {
        finish();

    }

    public void next(View view) {
        ganti(true);
    }

    public void prev(View view) {
        ganti(false);
    }

    public void ganti(boolean b) {
        ImageView seb = (ImageView) findViewById(R.id.sebelumnya);
        ImageView sel = (ImageView) findViewById(R.id.sebelumnya);
        if (b) {
            if (!(index == 2)) {
                index++;
                gantiPetunjuk();
            }
        } else if (!b) {
            if (!(index == 0)) {
                index--;
                gantiPetunjuk();
            }
        }
    }

    public void gantiPetunjuk() {
        ImageView gambarPetunjuk = findViewById(R.id.gambarPetunjuk);
        switch (index) {
            case 0:
                gambarPetunjuk.setImageResource(R.drawable.petunjuk1);
                break;
            case 1:
                gambarPetunjuk.setImageResource(R.drawable.petunjuk2);
                break;
            case 2:
                gambarPetunjuk.setImageResource(R.drawable.petunjuk3);
                break;
            default:
                //nothing
        }
    }

}

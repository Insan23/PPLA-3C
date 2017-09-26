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
        Intent i = new Intent(this, MenuAwal.class);
        startActivity(i);

    }

    public void menuAwal(View view) {
        Intent i = new Intent(this, MenuAwal.class);
        startActivity(i);

    }

    public void next(View view) {
        ImageView v = findViewById(R.id.selanjutnya);
        if (index < 2) {
            index++;
            gantiPetunjuk();
        } else if (index == 2) {
            v.setVisibility(View.INVISIBLE);
        } else if (index != 2) {
            v.setVisibility(View.VISIBLE);
        }
    }

    public void prev(View view) {
        ImageView v = findViewById(R.id.sebelumnya);
        if (index > 0) {
            index--;
            gantiPetunjuk();
        } else if (index == 0) {
            v.setVisibility(View.INVISIBLE);
        } else if (index != 0) {
            v.setVisibility(View.VISIBLE);
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

package com.pplagro_3c.pssi.letsplant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


public class MenuTentang extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tentang);
    }

    @Override
    public void onBackPressed() {
        finish();

    }

    public void kembali(View view) {
        finish();
    }
}

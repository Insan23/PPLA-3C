package com.pplagro_3c.pssi.letsplant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.R.attr.editable;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MenuPermainan extends AppCompatActivity {

    private boolean firstStart = true;
    private boolean dialogPengaturan = false;
    private boolean allowedName = true;

    //objek pada menu aku
    private View layoutKamu = findViewById(R.id.relKamu);

    //objek pada menu inventaris
    private View layoutInventaris = findViewById(R.id.relInventaris);

    //objek pada menu toko
    private View layoutToko = findViewById(R.id.relToko);

    //objek pada menu pengaturan
    private ImageView tombolPengaturan = (ImageView) findViewById(R.id.tombolPengaturan);

    //objek di dalam dialog awal main
    private TextView teks1 = (TextView) findViewById(R.id.teks1);
    private TextView teks2 = (TextView) findViewById(R.id.teks2);
    private TextView teks3 = (TextView) findViewById(R.id.teks3);
    private TextView simpan = (TextView) findViewById(R.id.simpan);
    private TextView batal = (TextView) findViewById(R.id.batal);
    private TextView error = (TextView) findViewById(R.id.teksError);
    private EditText inputNama = (EditText) findViewById(R.id.inputNama);
    private View layoutDialog = findViewById(R.id.dialog);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_permainan);

        error.setText("");


        if (firstStart) {
            layoutInventaris.setVisibility(View.GONE);
            layoutKamu.setVisibility(View.GONE);
            layoutToko.setVisibility(View.GONE);
            tombolPengaturan.setVisibility(View.GONE);
        } else if (!firstStart) {

        }

        inputNama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int after) {
                if (cekChar(cs.charAt(cs.length() - 1))) {
                    allowedName = false;
                    error.setText("karakter tidak diperbolehkan");
                } else {
                    error.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private boolean cekChar(char c) {

        if () {

        }
        return true;
    }

    /**
     * teks setelah input nama
     * hello @String nama
     * ini adalah lahanmu untuk menanam kakao, rajinlah merawat kakaomu dan jadilah pekebun yang baik
     * selamat berkebun
     * <button>lewati>
     */

}

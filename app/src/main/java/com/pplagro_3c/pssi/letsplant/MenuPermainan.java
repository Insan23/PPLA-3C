package com.pplagro_3c.pssi.letsplant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuPermainan extends AppCompatActivity {


    private boolean firstStart = true;
    private boolean dialogPengaturan = false;
    private boolean allowedName = true;

    //dialog
    ImageView petani;
    View dialog;

    //nama player
    private String nama;

    //objek pada menu kamu
    private RelativeLayout layoutKamu;
    private BottomSheetBehavior menuKamu;
    private ImageView tombolKamu;
    View menuKamuView;
    View menuInventarisView;
    View menuTokoView;

    //objek pada menu inventaris
    private RelativeLayout layoutInventaris;
    private BottomSheetBehavior menuInventaris;
    private ImageView tombolInventaris;

    //objek pada menu toko
    private RelativeLayout layoutToko;
    private BottomSheetBehavior menuToko;
    private ImageView tombolToko;

    //objek pada menu pengaturan
    private ImageView tombolPengaturan;
    private ImageView tombolKeluar;
    private ImageView tombolMusik;
    private ImageView tombolSuara;
    private ImageView tombolHome;
    private LinearLayout Pengaturan;

    //objek di dalam dialog awal main
    private TextView teks1, teks2, teks3, simpan, batal, error;
    private EditText inputNama;
    private RelativeLayout layoutDialog;

    public void init() {
        petani = (ImageView) findViewById(R.id.petani);
        dialog = findViewById(R.id.dialog);

        layoutKamu = (RelativeLayout) findViewById(R.id.relKamu);
        layoutInventaris = (RelativeLayout) findViewById(R.id.relInventaris);
        layoutToko = (RelativeLayout) findViewById(R.id.relToko);
        tombolPengaturan = (ImageView) findViewById(R.id.tombolPengaturan);
        tombolHome = (ImageView) findViewById(R.id.tombolHome);
        tombolMusik = (ImageView) findViewById(R.id.tombolMusik);
        tombolSuara = (ImageView) findViewById(R.id.tombolSuara);
        tombolKeluar = (ImageView) findViewById(R.id.tombolKeluar);
        Pengaturan = (LinearLayout) findViewById(R.id.dialogPengaturan);

        tombolKamu = (ImageView) findViewById(R.id.tombolKamu);
        tombolInventaris = (ImageView) findViewById(R.id.tombolInventaris);
        tombolToko = (ImageView) findViewById(R.id.tombolToko);

        menuKamuView = findViewById(R.id.menu_kamu);
        menuInventarisView = findViewById(R.id.menu_inventaris);
        menuTokoView = findViewById(R.id.menu_toko);
        menuKamu = BottomSheetBehavior.from(menuKamuView);
        menuInventaris = BottomSheetBehavior.from(menuInventarisView);
        menuToko = BottomSheetBehavior.from(menuTokoView);
        menuKamu.setHideable(true);
        menuInventaris.setHideable(true);
        menuToko.setHideable(true);
        menuKamu.setState(BottomSheetBehavior.STATE_HIDDEN);
        menuInventaris.setState(BottomSheetBehavior.STATE_HIDDEN);
        menuToko.setState(BottomSheetBehavior.STATE_HIDDEN);

        tombolKamu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuKamu.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    menuKamu.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
        tombolInventaris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuInventaris.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    menuInventaris.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
        tombolToko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuToko.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    menuToko.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });

        tombolPengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!dialogPengaturan) {
                    Pengaturan.setVisibility(View.VISIBLE);
                    dialogPengaturan = true;
                } else if (dialogPengaturan) {
                    Pengaturan.setVisibility(View.GONE);
                    dialogPengaturan = false;
                }
            }
        });
        tombolHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tombolMusik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tombolSuara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tombolKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        menuKamu.setBottomSheetCallback(new BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    layoutKamu.setVisibility(View.GONE);
                } else if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    layoutKamu.setVisibility(View.VISIBLE);
                } else if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    layoutKamu.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
        menuInventaris.setBottomSheetCallback(new BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    layoutInventaris.setVisibility(View.GONE);
                } else if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    layoutInventaris.setVisibility(View.VISIBLE);
                } else if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    layoutInventaris.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
        menuToko.setBottomSheetCallback(new BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    layoutToko.setVisibility(View.GONE);
                } else if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    layoutToko.setVisibility(View.VISIBLE);
                } else if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    layoutToko.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

        teks1 = (TextView) findViewById(R.id.teks1);
        teks2 = (TextView) findViewById(R.id.teks2);
        teks3 = (TextView) findViewById(R.id.teks3);
        simpan = (TextView) findViewById(R.id.simpan);
        batal = (TextView) findViewById(R.id.batal);
        error = (TextView) findViewById(R.id.teksError);
        inputNama = (EditText) findViewById(R.id.inputNama);
        layoutDialog = (RelativeLayout) findViewById(R.id.dialog);
        if (firstStart) {
            layoutInventaris.setVisibility(View.GONE);
            layoutKamu.setVisibility(View.GONE);
            layoutToko.setVisibility(View.GONE);
            tombolPengaturan.setVisibility(View.GONE);
            petani.setVisibility(View.VISIBLE);
            dialog.setVisibility(View.VISIBLE);
        } else if (!firstStart) {
            layoutInventaris.setVisibility(View.VISIBLE);
            layoutKamu.setVisibility(View.VISIBLE);
            layoutToko.setVisibility(View.VISIBLE);
            tombolPengaturan.setVisibility(View.VISIBLE);
            petani.setVisibility(View.GONE);
            dialog.setVisibility(View.GONE);
        }
        if (!dialogPengaturan) {
            Pengaturan.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_permainan);

        init();
        error.setText("");

        inputNama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() != 0) {
                    if (cekChar(editable.toString())) {
                        error.setText("nama tidak diperbolehkan");
                    }
                } else {

                }
            }
        });

    }


    private boolean cekChar(String s) {
        String bannedChar = "1234567890`~!@#$%^&*()_+-=[]\\{}|;':,./<>?\"";
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < bannedChar.length(); j++) {
                if (s.charAt(s.charAt(i)) == bannedChar.charAt(j)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * teks setelah input nama
     * hello @String nama
     * ini adalah lahanmu untuk menanam kakao, rajinlah merawat kakaomu dan jadilah pekebun yang baik
     * selamat berkebun
     * <button>lewati>
     */

}

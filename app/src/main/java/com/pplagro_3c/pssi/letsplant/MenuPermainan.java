package com.pplagro_3c.pssi.letsplant;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pplagro_3c.pssi.letsplant.database.LetsPlantContract.PemainEntry;
import com.pplagro_3c.pssi.letsplant.database.LetsPlantContract.LahanEntry;
import com.pplagro_3c.pssi.letsplant.database.LetsPlantContract.TanamanEntry;
import com.pplagro_3c.pssi.letsplant.objek.Lahan;
import com.pplagro_3c.pssi.letsplant.objek.Toko;

import static android.R.attr.id;

public class MenuPermainan extends AppCompatActivity {

    private boolean firstStart;
    private boolean dialogPengaturan = false;
    private boolean isInputNama = true;
    private boolean allowedName = true;

    private Lahan.AKSI_USER aksi = Lahan.AKSI_USER.TIDAK_ADA;
    private Lahan.LAHAN lahanSaatIni = Lahan.LAHAN.LAHAN_POLYBAG;
    private Toko.AKSI_USER_TOKO aksiToko = Toko.AKSI_USER_TOKO.TIDAK_ADA;
    private Toko toko;

    private Lahan petak_lahan[][] = new Lahan[3][5];
    private Lahan petak_poly[][] = new Lahan[3][5];
    private ImageView ikonLahanTanam[][] = new ImageView[3][5];
    private ImageView ikonLahanPoly[][] = new ImageView[3][5];
    private ImageView notif_lahan_tanam[][] = new ImageView[3][5];
    private ImageView notif_lahan_poly[][] = new ImageView[3][5];
    /**
     * List Petak Lahan Tanam
     */
    private int resLahan[][] = {
            {R.id.l_1_1, R.id.l_2_1, R.id.l_3_1, R.id.l_4_1, R.id.l_5_1},
            {R.id.l_1_2, R.id.l_2_2, R.id.l_3_2, R.id.l_4_2, R.id.l_5_2},
            {R.id.l_1_3, R.id.l_2_3, R.id.l_3_3, R.id.l_4_3, R.id.l_5_3}
    };
    private int resNotifLahan[][] = {
            {R.id.n_l_1_1, R.id.n_l_2_1, R.id.n_l_3_1, R.id.n_l_4_1, R.id.n_l_5_1},
            {R.id.n_l_1_2, R.id.n_l_2_2, R.id.n_l_3_2, R.id.n_l_4_2, R.id.n_l_5_2},
            {R.id.n_l_1_3, R.id.n_l_2_3, R.id.n_l_3_3, R.id.n_l_4_3, R.id.n_l_5_3}
    };

    /**
     * List Petak Lahan Polybag
     */
    private int resPoly[][] = {
            {R.id.p_1_1, R.id.p_2_1, R.id.p_3_1, R.id.p_4_1, R.id.p_5_1},
            {R.id.p_1_2, R.id.p_2_2, R.id.p_3_2, R.id.p_4_2, R.id.p_5_2},
            {R.id.p_1_3, R.id.p_2_3, R.id.p_3_3, R.id.p_4_3, R.id.p_5_3}
    };
    private int resNotifPoly[][] = {
            {R.id.n_p_1_1, R.id.n_p_2_1, R.id.n_p_3_1, R.id.n_p_4_1, R.id.n_p_5_1},
            {R.id.n_p_1_2, R.id.n_p_2_2, R.id.n_p_3_2, R.id.n_p_4_2, R.id.n_p_5_2},
            {R.id.n_p_1_3, R.id.n_p_2_3, R.id.n_p_3_3, R.id.n_p_4_3, R.id.n_p_5_3}
    };

    //atribut atribut

    //dialog
    ImageView petani;
    View dialog;
    private View overlay;
    private ImageView bg_lahan;
    private ImageView bg_poly;
    private ImageView genteng_poly;

    //nama player
    private String nama;

    //objek pada menu kamu
    private RelativeLayout layoutKamu;
    private TextView teksKamu;
    private View bibit;
    private View pupuk;
    private View polybag;
    private View lahan_tanam;
    private View lahan_poly;
    private BottomSheetBehavior menuKamu;
    private ImageView tombolKamu;
    View menuKamuView;

    //objek pada menu inventaris
    private RelativeLayout layoutInventaris;
    private TextView teksInventaris;
    private ImageView air;
    private ImageView sabit;
    private ImageView cangkul;
    private BottomSheetBehavior menuInventaris;
    private ImageView tombolInventaris;
    View menuInventarisView;

    //objek pada menu toko
    private RelativeLayout layoutToko;
    private TextView teksToko;
    private View bibit_toko;
    private View pupuk_toko;
    private View polybag_toko;
    private View lahan_toko;
    private View lahan_poly_toko;
    private Button tombol_beli;
    private BottomSheetBehavior menuToko;
    private ImageView tombolToko;
    View menuTokoView;

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

    private class listenerBG implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            aksi = Lahan.AKSI_USER.TIDAK_ADA;
        }
    }

    public void init() {
        petani = (ImageView) findViewById(R.id.petani);
        dialog = findViewById(R.id.dialog);
        teks1 = (TextView) findViewById(R.id.teks1);
        teks2 = (TextView) findViewById(R.id.teks2);
        teks3 = (TextView) findViewById(R.id.teks3);
        simpan = (TextView) findViewById(R.id.simpan);
        batal = (TextView) findViewById(R.id.batal);

        overlay = findViewById(R.id.overlay);
        bg_poly = (ImageView) findViewById(R.id.bg_lahan_poly);
        bg_lahan = (ImageView) findViewById(R.id.bg_lahan_tanam);
        genteng_poly = (ImageView) findViewById(R.id.genteng_lahan_poly);

        layoutKamu = (RelativeLayout) findViewById(R.id.relKamu);
        tombolKamu = (ImageView) findViewById(R.id.tombolKamu);
        menuKamuView = findViewById(R.id.menu_kamu);
        teksKamu = (TextView) findViewById(R.id.teksKamu);
        bibit = findViewById(R.id.line_bibit);
        pupuk = findViewById(R.id.pupuk_line);
        polybag = findViewById(R.id.polybag_line);
        lahan_tanam = findViewById(R.id.lahan_line);
        lahan_poly = findViewById(R.id.lahan_poly_line);
        menuKamu = BottomSheetBehavior.from(menuKamuView);
        menuKamu.setHideable(true);
        menuKamu.setState(BottomSheetBehavior.STATE_HIDDEN);

        layoutInventaris = (RelativeLayout) findViewById(R.id.relInventaris);
        tombolInventaris = (ImageView) findViewById(R.id.tombolInventaris);
        tombolToko = (ImageView) findViewById(R.id.tombolToko);
        menuInventarisView = findViewById(R.id.menu_inventaris);
        teksInventaris = (TextView) findViewById(R.id.teksInventaris);
        air = (ImageView) findViewById(R.id.air_ikon);
        sabit = (ImageView) findViewById(R.id.sabit_ikon);
        cangkul = (ImageView) findViewById(R.id.cangkul_ikon);
        menuInventaris = BottomSheetBehavior.from(menuInventarisView);
        menuInventaris.setHideable(true);
        menuInventaris.setState(BottomSheetBehavior.STATE_HIDDEN);

        layoutToko = (RelativeLayout) findViewById(R.id.relToko);
        menuTokoView = findViewById(R.id.menu_toko);
        teksToko = (TextView) findViewById(R.id.teksToko);
        bibit_toko = findViewById(R.id.bibit_toko_line);
        pupuk_toko = findViewById(R.id.pupuk_toko_line);
        polybag_toko = findViewById(R.id.polybag_toko_line);
        lahan_toko = findViewById(R.id.lahan_toko_line);
        lahan_poly_toko = findViewById(R.id.lahan_poly_toko_line);
        menuToko = BottomSheetBehavior.from(menuTokoView);
        menuToko.setHideable(true);
        menuToko.setState(BottomSheetBehavior.STATE_HIDDEN);
        tombol_beli = (Button) findViewById(R.id.tombol_beli);
        toko = new Toko(this);

        tombolPengaturan = (ImageView) findViewById(R.id.tombolPengaturan_main);
        tombolHome = (ImageView) findViewById(R.id.tombolHome);
        tombolMusik = (ImageView) findViewById(R.id.tombolMusik);
        tombolSuara = (ImageView) findViewById(R.id.tombolSuara);
        tombolKeluar = (ImageView) findViewById(R.id.tombolKeluar_main);
        Pengaturan = (LinearLayout) findViewById(R.id.dialogPengaturan);


        menuKamu.setBottomSheetCallback(new BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    layoutKamu.setVisibility(View.GONE);
                    overlay.setVisibility(View.VISIBLE);
                } else if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    layoutKamu.setVisibility(View.VISIBLE);
                    overlay.setVisibility(View.VISIBLE);
                } else if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    layoutKamu.setVisibility(View.VISIBLE);
                    overlay.setVisibility(View.GONE);
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
                    overlay.setVisibility(View.VISIBLE);
                } else if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    layoutInventaris.setVisibility(View.VISIBLE);
                    overlay.setVisibility(View.VISIBLE);
                } else if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    layoutInventaris.setVisibility(View.VISIBLE);
                    overlay.setVisibility(View.GONE);
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
                    overlay.setVisibility(View.VISIBLE);
                } else if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    layoutToko.setVisibility(View.VISIBLE);
                    overlay.setVisibility(View.VISIBLE);
                } else if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    layoutToko.setVisibility(View.VISIBLE);
                    overlay.setVisibility(View.GONE);
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
            petani.setImageResource(R.color.transparan);
            dialog.setVisibility(View.GONE);
        }
        if (!dialogPengaturan) {
            Pengaturan.setVisibility(View.GONE);
        }

    }

    private void initOnClickCallback() {
        /**
         * klik Listener untuk item-item pada menu kamu
         */
        tombolKamu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuKamu.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    menuKamu.setState(BottomSheetBehavior.STATE_EXPANDED);
                    overlay.setVisibility(View.VISIBLE);
                }
            }
        });
        bibit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aksi = Lahan.AKSI_USER.BIBIT;
                menuKamu.setState(BottomSheetBehavior.STATE_HIDDEN);
                overlay.setVisibility(View.GONE);
            }
        });
        pupuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aksi = Lahan.AKSI_USER.PUPUK;
                menuKamu.setState(BottomSheetBehavior.STATE_HIDDEN);
                overlay.setVisibility(View.GONE);
            }
        });
        polybag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aksi = Lahan.AKSI_USER.POLYBAG;
                menuKamu.setState(BottomSheetBehavior.STATE_HIDDEN);
                overlay.setVisibility(View.GONE);
            }
        });
        lahan_tanam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aksi = Lahan.AKSI_USER.LAHAN_TANAM;
                menuKamu.setState(BottomSheetBehavior.STATE_HIDDEN);
                overlay.setVisibility(View.GONE);
            }
        });
        lahan_poly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aksi = Lahan.AKSI_USER.LAHAN_POLY;
                menuKamu.setState(BottomSheetBehavior.STATE_HIDDEN);
                overlay.setVisibility(View.GONE);
            }
        });

        /**
         * klik Listener untuk item-item pada menu inventaris
         */
        tombolInventaris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuInventaris.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    menuInventaris.setState(BottomSheetBehavior.STATE_EXPANDED);
                    overlay.setVisibility(View.VISIBLE);
                }
            }
        });
        sabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aksi = Lahan.AKSI_USER.SABIT;
                menuInventaris.setState(BottomSheetBehavior.STATE_HIDDEN);
                overlay.setVisibility(View.GONE);
            }
        });
        cangkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aksi = Lahan.AKSI_USER.CANGKUL;
                menuInventaris.setState(BottomSheetBehavior.STATE_HIDDEN);
                overlay.setVisibility(View.GONE);
            }
        });
        air.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aksi = Lahan.AKSI_USER.AIR;
                menuInventaris.setState(BottomSheetBehavior.STATE_HIDDEN);
                overlay.setVisibility(View.GONE);
            }
        });

        tombolToko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuToko.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    menuToko.setState(BottomSheetBehavior.STATE_EXPANDED);
                    overlay.setVisibility(View.VISIBLE);
                }
            }
        });

        /**
         * klik listener untuk item-item pada menu toko
         */
        bibit_toko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aksiToko = Toko.AKSI_USER_TOKO.BIBIT_TOKO;
                menuToko.setState(BottomSheetBehavior.STATE_HIDDEN);
                overlay.setVisibility(View.GONE);
            }
        });
        pupuk_toko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aksiToko = Toko.AKSI_USER_TOKO.PUPUK_TOKO;
                menuToko.setState(BottomSheetBehavior.STATE_HIDDEN);
                overlay.setVisibility(View.GONE);
            }
        });
        polybag_toko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aksiToko = Toko.AKSI_USER_TOKO.POLYBAG_TOKO;
                menuToko.setState(BottomSheetBehavior.STATE_HIDDEN);
                overlay.setVisibility(View.GONE);
            }
        });
        lahan_toko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aksiToko = Toko.AKSI_USER_TOKO.LAHAN_TANAM_TOKO;
                menuToko.setState(BottomSheetBehavior.STATE_HIDDEN);
                overlay.setVisibility(View.GONE);
            }
        });
        lahan_poly_toko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aksiToko = Toko.AKSI_USER_TOKO.LAHAN_POLY_TOKO;
                menuToko.setState(BottomSheetBehavior.STATE_HIDDEN);
                overlay.setVisibility(View.GONE);
            }
        });
        tombol_beli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toko.pakaiToko(aksiToko);
                aksiToko = Toko.AKSI_USER_TOKO.TIDAK_ADA;
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInputNama) {
                    if (inputNama.getText().toString().trim().equalsIgnoreCase("")) {
                        error.setText("Nama Harus Diisi");
                    } else {
                        nama = inputNama.getText().toString().trim();
                        dialog("next");
                        isInputNama = false;
                    }
                } else {
                    dialog("close");
                }
                hideS();
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
                keluar();
            }
        });

        teksKamu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuKamu.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        teksInventaris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuInventaris.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        teksToko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuToko.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });


    }

    private void hideS() {
        View decorView = getWindow().getDecorView();
        //hide the status bar
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void initPetak() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                ikonLahanTanam[i][j] = (ImageView) findViewById(resLahan[i][j]);
                notif_lahan_tanam[i][j] = (ImageView) findViewById(resNotifLahan[i][j]);
                ikonLahanPoly[i][j] = (ImageView) findViewById(resPoly[i][j]);
                notif_lahan_poly[i][j] = (ImageView) findViewById(resNotifPoly[i][j]);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                petak_lahan[i][j] = new Lahan(this, ikonLahanTanam[i][j], notif_lahan_tanam[i][j]);
                petak_poly[i][j] = new Lahan(this, ikonLahanPoly[i][j], notif_lahan_poly[i][j]);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                final int finalI = i;
                final int finalJ = j;
                ikonLahanTanam[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        petak_lahan[finalI][finalJ].tindakan(aksi);
                        aksi = Lahan.AKSI_USER.TIDAK_ADA;
                    }
                });
                ikonLahanPoly[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        petak_poly[finalI][finalJ].tindakan(aksi);
                        aksi = Lahan.AKSI_USER.TIDAK_ADA;
                    }
                });
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_permainan);

        hideS();

        init();
        initOnClickCallback();
        initPetak();
        error.setText("");

        inputNama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int after) {
                error.setText("");
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

    @Override
    public void onSaveInstanceState(Bundle save) {
        super.onSaveInstanceState(save);

    }

    @Override
    public void onBackPressed() {
        konfirmasi();
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

    private boolean cekChar(String s) {
        String bannedChar = "1234567890`~!@#$%^&*()_+-=[]\\{}|;':,./<>?\"";
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < bannedChar.length(); j++) {
                if (s.charAt(i) == bannedChar.charAt(j)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void konfirmasi() {
        new AlertDialog.Builder(this)
                .setMessage("Yakin Ingin Kembali?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //kode saat memilih ya
                        finish();
                    }
                }).setNegativeButton("Tidak", null)
                .show();
    }

    public void dialog(String kondisi) {
        switch (kondisi) {
            case "next":
                simpanNama();

                batal.setVisibility(View.GONE);
                inputNama.setVisibility(View.GONE);
                error.setVisibility(View.GONE);
                teks1.setText("Halo " + nama);
                teks2.setText("Ini adalah lahanmu untuk menanam kakao, rajinlah merawat kakaomu dan jadilah pekebun yang baik");
                teks3.setText("Selamat Berkebun");
                simpan.setText("Lewati");
                break;
            case "close":
                firstStart = false;
                layoutInventaris.setVisibility(View.VISIBLE);
                layoutKamu.setVisibility(View.VISIBLE);
                layoutToko.setVisibility(View.VISIBLE);
                tombolPengaturan.setVisibility(View.VISIBLE);
                petani.setVisibility(View.GONE);
                dialog.setVisibility(View.GONE);
                break;
            default:
                Toast.makeText(this, "dialog bermasalah", Toast.LENGTH_SHORT);
        }
    }

    private void simpanNama() {
        // nama koin coklat kakao bibit poly
        String nama = inputNama.getText().toString().trim();
        ContentValues values = new ContentValues();
        values.put(PemainEntry.KOLOM_NAMA, nama);
        values.put(PemainEntry.KOLOM_JUMLAH_KOIN, 25000);
        values.put(PemainEntry.KOLOM_JUMLAH_COKLAT, 0);
        values.put(PemainEntry.KOLOM_JUMLAH_BUAH_KAKAO, 0);
        values.put(PemainEntry.KOLOM_JUMLAH_BIBIT, 5);
        values.put(PemainEntry.KOLOM_JUMLAH_POLYBAG, 0);

        Uri uri = getContentResolver().insert(PemainEntry.CONTENT_URI, values);
        if (uri == null) {
            Toast.makeText(this, "gagal menyimpan nama", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "berhasil menyimpan nama", Toast.LENGTH_SHORT).show();
        }
    }

    private void isiLahan() {
        for (int i = 0; i < 15; i++) {

        }
    }

    private void gantiLahan() {

    }

    private class LahanLoader implements LoaderManager.LoaderCallbacks<Cursor> {

        Context context;

        public LahanLoader(Context context) {
            this.context = context;
        }

        @Override
        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            String projection[] = {
                    LahanEntry._ID,
                    LahanEntry.KOLOM_TIPE_LAHAN,
                    TanamanEntry._ID,
                    TanamanEntry.KOLOM_LOKASI,
                    TanamanEntry.KOLOM_JENIS,
            };
            return new CursorLoader(context,
                    LahanEntry.CONTENT_URI,
                    projection,
                    null,
                    null,
                    null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            cursor.isBeforeFirst();
            int kolomID = cursor.getColumnIndex(LahanEntry._ID);
            int kolomTipe = cursor.getColumnIndex(LahanEntry.KOLOM_TIPE_LAHAN);
            int kolomIDTanaman = cursor.getColumnIndex(TanamanEntry._ID);
            int kolomLokasi = cursor.getColumnIndex(TanamanEntry.KOLOM_LOKASI);
            int kolomJenis = cursor.getColumnIndex(TanamanEntry.KOLOM_JENIS);

            int i = 0;
            while (cursor.moveToNext()) {

            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    }


    /**
     * teks setelah input nama
     *
     * hello @String nama
     * ini adalah lahanmu untuk menanam kakao, rajinlah merawat kakaomu dan jadilah pekebun yang baik
     * selamat berkebun
     * <button>lewati>
     *
     *  aset awal
     *  koin = 25000
     *  1 lahan poly
     *  1 pupuk
     *  5 bibit
     *  1 lahan siap tanam
     *
     *
     *  1 lahan poly 7110
     *  1 pupuk 200
     *  5 bibit 15 koin @ 3 koin
     *  1 lahan siap tanam 7000
     *  1 polybag 2 koin
     *
     *  coklat + 600 koin
     *
     *  1 lahan poly dan siap tanam 5 x 3 tanaman
     *
     */

}

package com.pplagro_3c.pssi.letsplant;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pplagro_3c.pssi.letsplant.database.LetsPlantContract.PemainEntry;

public class MenuPermainan extends AppCompatActivity {


    private boolean firstStart = true;
    private boolean dialogPengaturan = false;
    private boolean isInputNama = true;
    private boolean allowedName = true;

    //dialog
    ImageView petani;
    View dialog;

    //nama player
    private String nama;

    //objek pada menu kamu
    private RelativeLayout layoutKamu;
    private TextView teksKamu;
    private BottomSheetBehavior menuKamu;
    private ImageView tombolKamu;
    View menuKamuView;


    //objek pada menu inventaris
    private RelativeLayout layoutInventaris;
    private TextView teksInventaris;
    private BottomSheetBehavior menuInventaris;
    private ImageView tombolInventaris;
    View menuInventarisView;

    //objek pada menu toko
    private RelativeLayout layoutToko;
    private TextView teksToko;
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

    public void init() {
        petani = (ImageView) findViewById(R.id.petani);
        dialog = findViewById(R.id.dialog);

        layoutKamu = (RelativeLayout) findViewById(R.id.relKamu);
        layoutInventaris = (RelativeLayout) findViewById(R.id.relInventaris);
        layoutToko = (RelativeLayout) findViewById(R.id.relToko);
        tombolPengaturan = (ImageView) findViewById(R.id.tombolPengaturan_main);
        tombolHome = (ImageView) findViewById(R.id.tombolHome);
        tombolMusik = (ImageView) findViewById(R.id.tombolMusik);
        tombolSuara = (ImageView) findViewById(R.id.tombolSuara);
        tombolKeluar = (ImageView) findViewById(R.id.tombolKeluar_main);
        Pengaturan = (LinearLayout) findViewById(R.id.dialogPengaturan);

        tombolKamu = (ImageView) findViewById(R.id.tombolKamu);
        tombolInventaris = (ImageView) findViewById(R.id.tombolInventaris);
        tombolToko = (ImageView) findViewById(R.id.tombolToko);

        teks1 = (TextView) findViewById(R.id.teks1);
        teks2 = (TextView) findViewById(R.id.teks2);
        teks3 = (TextView) findViewById(R.id.teks3);
        simpan = (TextView) findViewById(R.id.simpan);
        batal = (TextView) findViewById(R.id.batal);

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

    private void initOnClickCallback() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_permainan);

        hideS();

        init();
        initOnClickCallback();
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
    public void onBackPressed() {
        konfirmasi();
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
                teks1.setText("Halo " + nama);
                teks2.setText("Ini adalah lahanmu untuk menanam kakao, rajinlah merawat kakaomu dan jadilah pekebun yang baik");
                teks3.setText("Selamat Berkebun");
                simpan.setText("Lewati");
                break;
            case "close":
                firstStart = false;
                Toast.makeText(this, "tombol lewati ditekan", Toast.LENGTH_SHORT).show();
                layoutInventaris.setVisibility(View.VISIBLE);
                layoutKamu.setVisibility(View.VISIBLE);
                layoutToko.setVisibility(View.VISIBLE);
                tombolPengaturan.setVisibility(View.VISIBLE);
                petani.setVisibility(View.GONE);
                dialog.setVisibility(View.GONE);
                break;
            default:
                ;
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

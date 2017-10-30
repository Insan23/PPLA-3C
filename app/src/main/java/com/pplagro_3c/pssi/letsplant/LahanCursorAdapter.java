package com.pplagro_3c.pssi.letsplant;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.pplagro_3c.pssi.letsplant.database.LetsPlantContract.TanamanEntry;
import com.pplagro_3c.pssi.letsplant.objek.Tanaman;

/**
 * Created by Aleq on 30/10/2017.
 */

public class LahanCursorAdapter extends CursorAdapter {

    public LahanCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        return LayoutInflater.from(context).inflate();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int kolomIndeksLokasi = cursor.getColumnIndex(TanamanEntry.KOLOM_LOKASI);
        int kolomIndeksJenis = cursor.getColumnIndex(TanamanEntry.KOLOM_JENIS);

        String lokasi = cursor.getString(kolomIndeksLokasi);
        String jenis = cursor.getString(kolomIndeksJenis);
    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}

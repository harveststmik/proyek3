package id.harvest.stmik.proyek3;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by jasiph on 4/26/2016.
 */
public class AdapterTeman extends CursorAdapter {

    public AdapterTeman(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_view_item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvNama = (TextView) view.findViewById(R.id.list_item_nama);
        String nama = cursor.getString(cursor.getColumnIndexOrThrow("nama"));
        tvNama.setText(nama);
        TextView tvAlamat = (TextView) view.findViewById(R.id.list_item_alamat);
        String alamat = cursor.getString(cursor.getColumnIndexOrThrow("alamat"));
        tvAlamat.setText(alamat);

    }
}

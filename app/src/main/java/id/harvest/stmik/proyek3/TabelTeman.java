package id.harvest.stmik.proyek3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by jasiph on 4/26/2016.
 */
public final class TabelTeman {

    public static int id;

    public TabelTeman() {}

    public static abstract class InputTeman implements BaseColumns {
        public static final String NAMA_TABEL = "teman";
        public static final String KOLOM_ID = "_id";
        public static final String KOLOM_NAMA = "nama";
        public static final String KOLOM_ALAMAT = "alamat";
    }

    public static final String SQL_HAPUS_TABEL =
            "DROP TABLE IF EXISTS " + InputTeman.NAMA_TABEL;

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_BUAT_TABEL =
            "CREATE TABLE " + InputTeman.NAMA_TABEL +
                    "(" +
                    InputTeman._ID + " INTEGER PRIMARY KEY," +
                    InputTeman.KOLOM_NAMA+ TEXT_TYPE + COMMA_SEP +
                    InputTeman.KOLOM_ALAMAT + TEXT_TYPE +
                    ")";

    public static final Cursor getRowById(Context context, Long id) {
        DbHelperTeman dbHelperTeman = new DbHelperTeman(context);
        SQLiteDatabase db = dbHelperTeman.getReadableDatabase();
        String[] args = {String.valueOf(id)};
        Cursor cursor = db.rawQuery("Select * from "+InputTeman.NAMA_TABEL + " where _id =?",args);
        return cursor;
    }

    public static final void updateById(ContentValues content, String id,Context context) {
        DbHelperTeman dbHelperTeman = new DbHelperTeman(context);
        SQLiteDatabase db = dbHelperTeman.getWritableDatabase();
        ContentValues update = new ContentValues();
        String args[] = {id};
        String where = InputTeman.KOLOM_ID + "=?";
        db.update(InputTeman.NAMA_TABEL,content,where,args);
    }

    public static final Cursor getAllRow(Context context) {
        DbHelperTeman dbHelperTeman = new DbHelperTeman(context);
        SQLiteDatabase db = dbHelperTeman.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + InputTeman.NAMA_TABEL,null);
        return cursor;
    }
}

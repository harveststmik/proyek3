package id.harvest.stmik.proyek3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jasiph on 4/26/2016.
 */
public class DbHelperTeman extends SQLiteOpenHelper {
    public static final int VERSI_DATABASE = 2;
    public static final String NAMA_DATABASE = "temanku.db";

    public DbHelperTeman(Context context) {
        super(context, NAMA_DATABASE, null, VERSI_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TabelTeman.SQL_BUAT_TABEL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TabelTeman.SQL_HAPUS_TABEL);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }
}

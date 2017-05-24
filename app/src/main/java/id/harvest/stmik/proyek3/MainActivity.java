package id.harvest.stmik.proyek3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    public final int TAMBAH_DATA = 1;
    public final int UPDATE_DATA = 2;
    public AdapterTeman adapterTeman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TambahActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent, TAMBAH_DATA);
            }
        });

        ListView listTeman = (ListView) findViewById(R.id.listView);
        DbHelperTeman dbhelper = new DbHelperTeman(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursorTeman = db.rawQuery("SELECT * FROM " + TabelTeman.InputTeman.NAMA_TABEL, null);
        adapterTeman = new AdapterTeman(this,cursorTeman,0);
        listTeman.setEmptyView(findViewById(android.R.id.empty));
        listTeman.setAdapter(adapterTeman);

        listTeman.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdapterTeman adapterTeman = (AdapterTeman) parent.getAdapter();
                Cursor cursor = adapterTeman.getCursor();
                if(cursor.moveToPosition(position)) {
                    Long itemId = cursor.getLong(cursor.getColumnIndex("_id"));
                    Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.putExtra("ITEMID", itemId);
                    startActivityForResult(intent,UPDATE_DATA);
                }
            }
        });


        listTeman.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAMBAH_DATA) {
            if (resultCode == RESULT_OK) {
                String nama = data.getStringExtra("NAMA");
                String alamat = data.getStringExtra("ALAMAT");
                Log.v("MYTAG", nama);
                Log.v("MYTAG", alamat);
                simpanData(nama,alamat);
            }
        } else if(requestCode == UPDATE_DATA) {
            if (resultCode == RESULT_OK) {
                String id = String.valueOf(data.getLongExtra("ID",0));
                String nama = data.getStringExtra("NAMA");
                String alamat = data.getStringExtra("ALAMAT");
                Log.v("MYTAG", nama);
                Log.v("MYTAG", alamat);
                updateData(id,nama,alamat);
            }
        }
    }

    private void simpanData(String nama,String alamat) {
        DbHelperTeman dbhelper = new DbHelperTeman(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(TabelTeman.InputTeman.KOLOM_NAMA, nama);
        values.put(TabelTeman.InputTeman.KOLOM_ALAMAT, alamat);

        long newRowId;
        newRowId = db.insert(TabelTeman.InputTeman.NAMA_TABEL,null,values);

        Cursor cursorTeman = db.rawQuery("SELECT * FROM " + TabelTeman.InputTeman.NAMA_TABEL, null);
        adapterTeman.swapCursor(cursorTeman);
        if(db.isOpen()) db.close();
        dbhelper.close();
    }

    public void updateData(String id,String nama, String alamat) {
        ContentValues values = new ContentValues();
        values.put(TabelTeman.InputTeman.KOLOM_NAMA,nama);
        values.put(TabelTeman.InputTeman.KOLOM_ALAMAT,alamat);
        TabelTeman.updateById(values,id,this);
        Log.v("MYTAG",nama + alamat);
        updateAdapter();
    }

    public void updateAdapter() {
        DbHelperTeman dbhelper = new DbHelperTeman(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = TabelTeman.getAllRow(this);
        adapterTeman.swapCursor(cursor);
    }
}

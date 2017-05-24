package id.harvest.stmik.proyek3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        final Long itemid = extras.getLong("ITEMID");

        Cursor cursor = TabelTeman.getRowById(this,itemid);
        EditText txtNama = (EditText) findViewById(R.id.txtNama_detail);
        EditText txtAlamat = (EditText) findViewById(R.id.txtAlamatDetail);
        if(cursor.moveToFirst()) {
            txtNama.setText(cursor.getString(cursor.getColumnIndex(TabelTeman.InputTeman.KOLOM_NAMA)));
            txtAlamat.setText(cursor.getString(cursor.getColumnIndex(TabelTeman.InputTeman.KOLOM_ALAMAT)));
        }

        Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tvNama = (EditText) findViewById(R.id.txtNama_detail);
                EditText tvAlamat = (EditText) findViewById(R.id.txtAlamatDetail);
                Intent intent = new Intent();
                if(tvNama.getText().toString() != "") {
                    intent.putExtra("ID",itemid);
                    intent.putExtra("NAMA", tvNama.getText().toString());
                    intent.putExtra("ALAMAT", tvAlamat.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}

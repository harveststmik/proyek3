package id.harvest.stmik.proyek3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TambahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        Button btnTambah = (Button) findViewById(R.id.btnTambah);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tvNama = (EditText) findViewById(R.id.tvNama);
                EditText tvAlamat = (EditText) findViewById(R.id.tvAlamat);
                Intent intent = new Intent();
                if(tvNama.getText().toString() != "") {
                    intent.putExtra("NAMA", tvNama.getText().toString());
                    intent.putExtra("ALAMAT", tvAlamat.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });


        Button btnBatal = (Button) findViewById(R.id.btnBatal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED,getIntent());
                finish();
            }
        });
    }
}

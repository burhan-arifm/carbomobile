package zeinaf.carbomobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        TextView title = findViewById(R.id.toolbar_title);
        title.setText("home".toUpperCase());

        RelativeLayout pendahuluan = findViewById(R.id.pendahuluan);
        RelativeLayout tujuan = findViewById(R.id.tujuan);
        RelativeLayout materi = findViewById(R.id.materi);
        RelativeLayout evaluasi = findViewById(R.id.evaluasi);
        RelativeLayout referensi = findViewById(R.id.referensi);
        RelativeLayout profil = findViewById(R.id.profil);

        pendahuluan.setOnClickListener(this);
        tujuan.setOnClickListener(this);
        materi.setOnClickListener(this);
        evaluasi.setOnClickListener(this);
        referensi.setOnClickListener(this);
        profil.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pendahuluan:
                startActivity(new Intent(this, PendahuluanActivity.class));
                break;
            case R.id.tujuan:
                startActivity(new Intent(this, TujuanActivity.class));
                break;
            case R.id.materi:
                startActivity(new Intent(this, MateriActivity.class));
                break;
            case R.id.evaluasi:
                startActivity(new Intent(this, EvaluasiActivity.class));
                break;
            case R.id.referensi:
                startActivity(new Intent(this, ReferensiActivity.class));
                break;
            case R.id.profil:
                startActivity(new Intent(this, ProfilActivity.class));
                break;
        }
    }
}

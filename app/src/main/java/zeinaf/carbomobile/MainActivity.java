package zeinaf.carbomobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout pendahuluan, tujuan, materi, evaluasi, referensi, profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle(getString(R.string.petunjuk_penggunaan).toUpperCase());

        TextView title = findViewById(R.id.toolbar_title);
        title.setText(getString(R.string.app_name).toUpperCase());

        pendahuluan = findViewById(R.id.pendahuluan);
        tujuan = findViewById(R.id.tujuan);
        materi = findViewById(R.id.materi);
        evaluasi = findViewById(R.id.evaluasi);
        referensi = findViewById(R.id.referensi);
        profil = findViewById(R.id.profil);

        pendahuluan.setOnClickListener(this);
        tujuan.setOnClickListener(this);
        materi.setOnClickListener(this);
        evaluasi.setOnClickListener(this);
        referensi.setOnClickListener(this);
        profil.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.help) {
            startActivity(new Intent(getApplicationContext(), PetunjukActivity.class));
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

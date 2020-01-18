package zeinaf.carbomobile;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MateriActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout monosakarida, disakarida, polisakarida;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView title = findViewById(R.id.toolbar_title);
        title.setText(getString(R.string.materi).toUpperCase());

        linearLayout = findViewById(R.id.layout_materi);

        monosakarida = findViewById(R.id.monosakarida);
        disakarida = findViewById(R.id.disakarida);
        polisakarida = findViewById(R.id.polisakarida);

        monosakarida.setOnClickListener(this);
        disakarida.setOnClickListener(this);
        polisakarida.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
            case R.id.home:
                finish();
                break;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        Snackbar snackbar;

        switch (v.getId()) {
            case R.id.monosakarida:
//                startActivity(new Intent(this, MonosakaridaActivity.class));
                snackbar = Snackbar.make(linearLayout, "Menuju ke " + getString(R.string.monosakarida).toUpperCase(), Snackbar.LENGTH_SHORT);
                snackbar.show();
                break;
            case R.id.disakarida:
//                startActivity(new Intent(this, DisakaridaActivity.class));
                snackbar = Snackbar.make(linearLayout, "Menuju ke " + getString(R.string.disakarida).toUpperCase(), Snackbar.LENGTH_SHORT);
                snackbar.show();
                break;
            case R.id.polisakarida:
//                startActivity(new Intent(this, PolisakaridaActivity.class));
                snackbar = Snackbar.make(linearLayout, "Menuju ke " + getString(R.string.polisakarida).toUpperCase(), Snackbar.LENGTH_SHORT);
                snackbar.show();
                break;
        }
    }
}

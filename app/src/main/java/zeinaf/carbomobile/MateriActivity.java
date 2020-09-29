package zeinaf.carbomobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MateriActivity extends AppCompatActivity implements View.OnClickListener {
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

        RelativeLayout monosakarida = findViewById(R.id.monosakarida);
        RelativeLayout disakarida = findViewById(R.id.disakarida);
        RelativeLayout polisakarida = findViewById(R.id.polisakarida);

        monosakarida.setOnClickListener(this);
        disakarida.setOnClickListener(this);
        polisakarida.setOnClickListener(this);

        WebView content_wrapper = findViewById(R.id.content_main);
        content_wrapper.getSettings().setJavaScriptEnabled(true);
        content_wrapper.loadUrl("file:///android_asset/contents/content_main.html");
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
                finish();
                break;
            case R.id.home:
                Intent intent = new Intent(this, CoverActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        Snackbar snackbar;
        Intent intent = new Intent(this, PendalamanMateriActivity.class);

        switch (v.getId()) {
            case R.id.monosakarida:
                intent.putExtra("materi", "monosakarida");
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

        startActivity(intent);
    }
}

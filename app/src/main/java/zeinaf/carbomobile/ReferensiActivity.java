package zeinaf.carbomobile;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bluejamesbond.text.DocumentView;

import java.util.Objects;

public class ReferensiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referensi);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView title = findViewById(R.id.toolbar_title);
        title.setText(getString(R.string.referensi).toUpperCase());

        WebView daftarPustaka = findViewById(R.id.daftar_pustaka);
        WebView daftarGambar = findViewById(R.id.sumber_gambar);
        WebView daftarVideo = findViewById(R.id.sumber_video);

        daftarPustaka.getSettings().setJavaScriptEnabled(true);
        daftarGambar.getSettings().setJavaScriptEnabled(true);
        daftarVideo.getSettings().setJavaScriptEnabled(true);

        daftarPustaka.loadUrl("file:///android_asset/contents/daftar_pustaka.html");
        daftarGambar.loadUrl("file:///android_asset/contents/sumber_gambar.html");
        daftarVideo.loadUrl("file:///android_asset/contents/sumber_video.html");
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
}

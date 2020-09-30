package zeinaf.carbomobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class TujuanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tujuan);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView title = findViewById(R.id.toolbar_title);
        title.setText(getString(R.string.tujuan).toUpperCase());

        WebView tujuan_1 = findViewById(R.id.tujuan_1);
        WebView tujuan_2 = findViewById(R.id.tujuan_2);
        WebView tujuan_3 = findViewById(R.id.tujuan_3);

        tujuan_1.getSettings().setJavaScriptEnabled(true);
        tujuan_2.getSettings().setJavaScriptEnabled(true);
        tujuan_3.getSettings().setJavaScriptEnabled(true);

        tujuan_1.loadUrl("file:///android_asset/contents/targets/tujuan_1.html");
        tujuan_2.loadUrl("file:///android_asset/contents/targets/tujuan_2.html");
        tujuan_3.loadUrl("file:///android_asset/contents/targets/tujuan_3.html");
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

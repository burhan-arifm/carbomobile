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

public class ProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView title = findViewById(R.id.toolbar_title);
        title.setText(getString(R.string.profil).toUpperCase());

        WebView profile1 = findViewById(R.id.profile_1);
        profile1.getSettings().setJavaScriptEnabled(true);
        profile1.loadUrl("file:///android_asset/contents/profiles/profile_1.html");

        WebView profile2 = findViewById(R.id.profile_2);
        profile2.getSettings().setJavaScriptEnabled(true);
        profile2.loadUrl("file:///android_asset/contents/profiles/profile_2.html");

        WebView profile3 = findViewById(R.id.profile_3);
        profile3.getSettings().setJavaScriptEnabled(true);
        profile3.loadUrl("file:///android_asset/contents/profiles/profile_3.html");
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
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }

        return true;
    }
}

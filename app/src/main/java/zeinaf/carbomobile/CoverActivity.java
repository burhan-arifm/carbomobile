package zeinaf.carbomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class CoverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.help:
                startActivity(new Intent(this, PetunjukActivity.class));
                break;
            case R.id.start:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
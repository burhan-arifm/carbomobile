package zeinaf.carbomobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class EvaluasiActivity extends AppCompatActivity {

    private QuestionFragment question;
    private TextView questionButton;
    private TextView questionNumberView;

    private int questionNumber;
    private int score;
    private boolean answered;
    private boolean finished;
    private Iterator<QuestionFragment> iterator;

    private List<QuestionFragment> getQuestions() {
        List<QuestionFragment> questions = new ArrayList<QuestionFragment>();
        String[][][] answers = {
                {
                        {"2", "true"},
                        {"1", "false"},
                        {"3", "false"},
                        {"4", "false"}
                },
                {
                        {"Diastereomer", "true"},
                        {"Tautomer", "false"},
                        {"Enansiomer", "false"},
                        {"Isomer konstitusional", "false"}
                },
                {
                        {"Enansiomer", "true"},
                        {"Tautomer", "false"},
                        {"Diastereomer", "false"},
                        {"Isomer konstitusional", "false"}
                },
                {
                        {"(2)", "true"},
                        {"(1)", "false"},
                        {"(3)", "false"},
                        {"(2) dan (3)", "false"}
                },
                {
                        {"1", "true"},
                        {"2", "false"},
                        {"3", "false"},
                        {"4", "false"}
            },
            {
                {"4", "true"},
                {"1", "false"},
                {"2", "false"},
                {"3", "false"}
            },
            {
                {"3", "true"},
                    {"1", "false"},
                    {"2", "false"},
                    {"4", "false"}
            },
                {
                        {"2", "true"},
                        {"1", "false"},
                        {"3", "false"},
                        {"4", "false"}
                },
                {
                        {"Anomer tunggal", "true"},
                        {"Mutarotasi mengacu pada konversi anomer yang berasal dari hemiasetal karbohidrat menjadi campuran dua anomer yang berada dalam keadaan kesetimbangan.", "false"},
                        {"β-D-glukopiranosa lebih stabil dari α-D-glukopiranosa", "false"},
                        {"Anomer adalah diastereomer satu sama lain", "false"}
                },
                {
                        {"4", "true"},
                        {"1", "false"},
                        {"2", "false"},
                        {"3", "false"}
                }
        };

        for (int i = 0; i < answers.length; i++) {
            questions.add(QuestionFragment.newInstance(i + 1, answers[i]));
        }
        Collections.shuffle(questions);

        return questions;
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
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluasi);

        List<QuestionFragment> questions = getQuestions();
        iterator = questions.iterator();
        questionNumber = 1;
        score = 0;
        answered = false;
        finished = false;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView title = findViewById(R.id.toolbar_title);
        title.setText(getString(R.string.evaluasi).toUpperCase());

        WebView rules = findViewById(R.id.rules);
        rules.loadUrl("file:///android_asset/contents/questions/rules.html");
        questionNumberView = findViewById(R.id.question_number);
        getSupportFragmentManager().beginTransaction().add(R.id.question, iterator.next()).commit();
        questionButton = findViewById(R.id.next_button);
        questionButton.setText("PERIKSA JAWABAN");
    }

    public void onClick(View view) {
        if (!finished) {
            if (!answered) {
                QuestionFragment fragment = (QuestionFragment) getSupportFragmentManager().findFragmentById(R.id.question);
                String result = fragment.checkAnswer();
                if (!result.equals("unanswered")) {
                    if (Boolean.valueOf(result)) {
                        score++;
                    }
                    answered = !answered;
                    if (iterator.hasNext()) {
                        questionButton.setText("SOAL SELANJUTNYA");
                    } else {
                        questionButton.setText("LIHAT SKOR SAYA");
                    }
                }
            } else {
                if (iterator.hasNext()) {
                    questionNumberView.setText(String.format("%d.", ++questionNumber));
                    answered = false;
                    questionButton.setText("PERIKSA JAWABAN");
                    getSupportFragmentManager().beginTransaction().replace(R.id.question, iterator.next(), "Next Question").commit();
                } else {
//                    questionNumberView.setVisibility(View.INVISIBLE);
                    ViewGroup parent = (ViewGroup) questionNumberView.getParent();
                    parent.removeView(questionNumberView);
                    questionButton.setText("KEMBALI KE MENU UTAMA");
                    finished = true;
                    getSupportFragmentManager().beginTransaction().replace(R.id.question, ScoreFragment.newInstance(String.valueOf(score)), "Final Score").commit();
                }
            }
        } else {
            finish();
        }
    }
}

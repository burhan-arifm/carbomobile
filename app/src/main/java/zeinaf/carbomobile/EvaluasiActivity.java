package zeinaf.carbomobile;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EvaluasiActivity extends AppCompatActivity {
    private TextView nextButton;

    private static String[][] shuffleAnswers(String[][] answers) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = answers.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String[] a = answers[index];
            answers[index] = answers[i];
            answers[i] = a;
        }

        return answers;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluasi);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView title = findViewById(R.id.toolbar_title);
        title.setText(getString(R.string.evaluasi).toUpperCase());

        WebView rules = findViewById(R.id.rules);
        rules.loadUrl("file:///android_asset/contents/questions/rules.html");

        //  Q&A Generator
        WebView question = findViewById(R.id.question_wrapper);
        TextView questionNumber = findViewById(R.id.question_number);
        final RadioGroup answersWrapper = findViewById(R.id.answer_group);
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

        int i = 0;
        do {
            final String[][] shuffledAnswers = shuffleAnswers(answers[i]);
            question.loadUrl("file:///android_asset/contents/questions/question_" + (i+1) + ".html");
            questionNumber.setText((i+1) + ".");
            for (int j = 0; j < shuffledAnswers.length; j++) {
                RadioButton button = new RadioButton(this);
                button.setId(j);
                button.setText(shuffledAnswers[j][0]);
                button.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/alegreya_sans.ttf"), Typeface.BOLD);
                button.setTextSize(15);
                button.setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                answersWrapper.addView(button);
            }

            // Button trigger
            nextButton = findViewById(R.id.next_button);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedAnswer = answersWrapper.getCheckedRadioButtonId();
                    if (selectedAnswer > -1) {
                        if (shuffledAnswers[selectedAnswer][1].equals("true")) {
                            answersWrapper.getChildAt(selectedAnswer).setBackgroundColor(getResources().getColor(R.color.trueBackground));
                            ((RadioButton) answersWrapper.getChildAt(selectedAnswer)).setTextColor(getResources().getColor(R.color.trueAnswer));
                            for (int i = 0; i < shuffledAnswers.length; i++) {
                                if (i != selectedAnswer) {
                                    ((RadioButton) answersWrapper.getChildAt(i)).setTextColor(getResources().getColor(R.color.disabledText));
                                    answersWrapper.getChildAt(i).setEnabled(false);
                                }
                            }
                        } else {
                            answersWrapper.getChildAt(selectedAnswer).setBackgroundColor(getResources().getColor(R.color.falseBackground));
                            ((RadioButton) answersWrapper.getChildAt(selectedAnswer)).setTextColor(getResources().getColor(R.color.falseAnswer));
                            for (int i = 0; i < shuffledAnswers.length; i++) {
                                if (i != selectedAnswer) {
                                    answersWrapper.getChildAt(i).setEnabled(false);
                                    if (shuffledAnswers[i][1].equals("true")) {
                                        answersWrapper.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.trueBackground));
                                        ((RadioButton) answersWrapper.getChildAt(i)).setTextColor(getResources().getColor(R.color.trueAnswer));
                                    } else {
                                        ((RadioButton) answersWrapper.getChildAt(i)).setTextColor(getResources().getColor(R.color.disabledText));
                                    }
                                }
                            }
                        }
                    }
                }
            });
        } while (i<answers.length);
    }
}

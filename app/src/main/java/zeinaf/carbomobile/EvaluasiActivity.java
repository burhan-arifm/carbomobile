package zeinaf.carbomobile;

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
            case R.id.home:
                finish();
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
        rules.loadUrl("file:///android_asset/contents/rules.html");

        //  Q&A Generator
        WebView question = findViewById(R.id.question_wrapper);
        TextView questionNumber = findViewById(R.id.question_number);
        final RadioGroup answersWrapper = findViewById(R.id.answer_group);
        String[][] answers = {{"α-D-glukosa", "true"}, {"D-glukosa", "false"}, {"β-D-glukosa", "false"}, {"α-L-glukosa", "false"}, {"β-L-glukosa", "false"}};

        final String[][] shuffledAnswers = shuffleAnswers(answers);
        question.loadUrl("file:///android_asset/contents/question_" + 1 + ".html");
        questionNumber.setText(1 + ".");
        for (int i = 0; i < shuffledAnswers.length; i++) {
            RadioButton button = new RadioButton(this);
            button.setId(i);
            button.setText(shuffledAnswers[i][0]);
            button.setTypeface(Typeface.createFromAsset(getAssets(), "font/gisha_regular.ttf"), Typeface.BOLD);
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
    }
}

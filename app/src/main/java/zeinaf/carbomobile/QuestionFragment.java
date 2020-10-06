package zeinaf.carbomobile;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {

    private static final String ARG_QUESTION_NUMBER = "question_number";
    private static final String ARG_ANSWERS = "answer_group";

    private RadioGroup answersWrapper;
    private String[][] shuffledAnswers;

    private int questionNumber;
    private String[][] answers;

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param questionNumber Parameter 1.
     * @param answerGroup    Parameter 2.
     * @return A new instance of fragment QuestionFragment.
     */
    public static QuestionFragment newInstance(int questionNumber, String[][] answerGroup) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();

        args.putInt(ARG_QUESTION_NUMBER, questionNumber);
        args.putSerializable(ARG_ANSWERS, answerGroup);
        fragment.setArguments(args);

        return fragment;
    }

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questionNumber = getArguments().getInt(ARG_QUESTION_NUMBER);
            answers = (String[][]) getArguments().getSerializable(ARG_ANSWERS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        WebView question = view.findViewById(R.id.question_wrapper);
        answersWrapper = view.findViewById(R.id.answer_group);
        shuffledAnswers = shuffleAnswers(answers);

        question.loadUrl("file:///android_asset/contents/questions/question_" + questionNumber + ".html");
        for (int j = 0; j < shuffledAnswers.length; j++) {
            RadioButton button = new RadioButton(getContext());
            button.setId(j);
            button.setText(shuffledAnswers[j][0]);
            button.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/alegreya_sans_bold.ttf"), Typeface.NORMAL);
            button.setTextSize(18);
            button.setTextColor(getActivity().getResources().getColor(R.color.defaultText));
            button.setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            answersWrapper.addView(button);
        }
        return view;
    }

    public String checkAnswer() {
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

            return String.valueOf(shuffledAnswers[selectedAnswer][1].equals("true"));
        }

        return "unanswered";
    }
}
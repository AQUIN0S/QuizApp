package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Welcome to this Ancient Mythology quiz app! This app is to quickly test your knowledge on the old
 * stories, just asking a few questions about the mythology of various cultures.
 * <p>
 * How the app works:
 * When the "Submit" button is pressed, the app goes ahead and first
 * checks if all the answers are answered (checking radial button groups only, as checkbox questions
 * could potentially have no right answer listed). If an answer is not checked, then a toast
 * message will prompt the user to answer all questions.
 * <p>
 * Once all questions have been answered, the app will then check which question is right. For
 * every question answered correctly, it will display the question heading as green. Every question
 * answered wrong will be displayed as red. A toast message will also pop up letting the user know
 * their score, and prompting them to answer any wrong questions if there are.
 * <p>
 * Finally, once the user has answered every question correctly and pressed "Submit", a new window
 * will be shown, which displays the congratulations page. This page is the final place for them,
 * they passed!
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.answer_1) RadioGroup Answer1;
    @BindView(R.id.answer_2) RadioGroup Answer2;
    @BindView(R.id.answer_3a) CheckBox Answer3a;
    @BindView(R.id.answer_3b) CheckBox Answer3b;
    @BindView(R.id.answer_3c) CheckBox Answer3c;
    @BindView(R.id.answer_3d) CheckBox Answer3d;
    @BindView(R.id.answer_4) RadioGroup Answer4;
    @BindView(R.id.answer_5) RadioGroup Answer5;
    @BindView(R.id.answer_6) RadioGroup Answer6;
    @BindView(R.id.answer_1b) RadioButton correctAnswer1;
    @BindView(R.id.answer_2c) RadioButton correctAnswer2;
    @BindView(R.id.answer_4d) RadioButton correctAnswer4;
    @BindView(R.id.answer_5d) RadioButton correctAnswer5;
    @BindView(R.id.answer_6a) RadioButton correctAnswer6;

    private RadioGroup[] radioGroupAnswerGroups = {
            Answer1, Answer2, Answer4,
            Answer5, Answer6
    };
    private RadioButton[] radioGroupCorrectAnswers = {
            correctAnswer1, correctAnswer2, correctAnswer4,
            correctAnswer5, correctAnswer6
    };
    private CheckBox[] question3CheckBoxAnswers = {
            Answer3a, Answer3b, Answer3c,
            Answer3d
    };
    private boolean[] question3CorrectCheckBoxAnswers = {
            true, false, true,
            false
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.submit_button)
    public void submitAnswers() {

        /*
        if (checkAllQuestionsAnswered()) {
            boolean[] RGCorrectAnswers = checkRadioGroupCorrectAnswers();
            boolean[] CBCorrectAnswers = checkCheckBoxCorrectAnswers();
            // TODO: Fix this area
        } else {
        */
            Toast toast = Toast.makeText(this, R.string.NotAllAnswered, Toast.LENGTH_SHORT);
            toast.show();
        /*
        }
        */
    }

    /**
     * Check whether all of the questions that have RadioButton answers have been answered.
     *
     * @return either true or false.
     */
    private boolean checkAllQuestionsAnswered() {
        for (RadioGroup answerGroup : radioGroupAnswerGroups) {
            if (answerGroup.getCheckedRadioButtonId() == -1) {
                return false;
            }
        }
        return true;
    }

    private boolean[] checkRadioGroupCorrectAnswers() {
        boolean[] RGCorrectAnswers = new boolean[radioGroupAnswerGroups.length];
        for (int i = 0; i < radioGroupAnswerGroups.length; i += 1) {
            RGCorrectAnswers[i] = (radioGroupAnswerGroups[i].getCheckedRadioButtonId() == radioGroupCorrectAnswers[i].getId());
        }
        return RGCorrectAnswers;
    }

    private boolean[] checkCheckBoxCorrectAnswers() {
        boolean[] CBCorrectAnswers = new boolean[question3CheckBoxAnswers.length];
        for (int i = 0; i < question3CheckBoxAnswers.length; i += 1) {
            CBCorrectAnswers[i] = (question3CheckBoxAnswers[i].isChecked() == question3CorrectCheckBoxAnswers[i]);
        }
        return CBCorrectAnswers;
    }

}

package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Welcome to this Ancient Mythology quiz app! This app is to quickly test your knowledge on the old
 * stories, just asking a few questions about the mythology of various cultures.
 *
 * How the app works:
 * When the "Submit" button is pressed, the app goes ahead and first
 * checks if all the answers are answered (checking radial button groups only, as checkbox questions
 * could potentially have no right answer listed). If an answer is not checked, then a toast
 * message will prompt the user to answer all questions.
 *
 * Once all questions have been answered, the app will then check which question is right. For
 * every question answered correctly, it will display the question heading as green. Every question
 * answered wrong will be displayed as red. A toast message will also pop up letting the user know
 * their score, and prompting them to answer any wrong questions if there are.
 *
 * Finally, once the user has answered every question correctly and pressed "Submit", a new window
 * will be shown, which displays the congratulations page. This page is the final place for them,
 * they passed!
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.answer_1) RadioGroup Answer1;
    @BindView(R.id.answer_2) RadioGroup Answer2;
    @BindView(R.id.answer_4) RadioGroup Answer4;
    @BindView(R.id.answer_5) RadioGroup Answer5;
    @BindView(R.id.answer_6) RadioGroup Answer6;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    // TODO: Fill body of submitAnswers()
    @OnClick(R.id.submit_button) public void submitAnswers() {

        if (checkAllQuestionsAnswered()) {

            // Do something

        } else {
        }
    }

    /**
     * Check whether all of the questions that have RadioButton answers have been answered.
     * @return either true or false.
     */
    private boolean checkAllQuestionsAnswered() {
        RadioGroup[] answerGroupsToCheck = {Answer1, Answer2, Answer4, Answer5, Answer6};
        for (RadioGroup answerGroup : answerGroupsToCheck) {
            if (answerGroup.getCheckedRadioButtonId() == -1) {
                return false;
            }
        }
        return true;
    }
}

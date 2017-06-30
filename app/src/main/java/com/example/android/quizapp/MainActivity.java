package com.example.android.quizapp;

import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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

    @BindView(R.id.answer_1) RadioGroup answer1;
    @BindView(R.id.answer_2) RadioGroup answer2;
    @BindView(R.id.answer_3a) CheckBox answer3A;
    @BindView(R.id.answer_3b) CheckBox answer3B;
    @BindView(R.id.answer_3c) CheckBox answer3C;
    @BindView(R.id.answer_3d) CheckBox answer3D;
    @BindView(R.id.answer_4) RadioGroup answer4;
    @BindView(R.id.answer_5) RadioGroup answer5;
    @BindView(R.id.answer_6) RadioGroup answer6;
    @BindView(R.id.answer_1b) RadioButton correctAnswer1;
    @BindView(R.id.answer_2c) RadioButton correctAnswer2;
    @BindView(R.id.answer_4d) RadioButton correctAnswer4;
    @BindView(R.id.answer_5d) RadioButton correctAnswer5;
    @BindView(R.id.answer_6a) RadioButton correctAnswer6;
    @BindView(R.id.question_1_heading) TextView question1Heading;
    @BindView(R.id.question_2_heading) TextView question2Heading;
    @BindView(R.id.question_3_heading) TextView question3Heading;
    @BindView(R.id.question_4_heading) TextView question4Heading;
    @BindView(R.id.question_5_heading) TextView question5Heading;
    @BindView(R.id.question_6_heading) TextView question6Heading;

    @ColorInt int correctAnswerColour = 0xFF81C784;
    @ColorInt int wrongAnswerColour = 0xFFE57373;

    // Create the ArrayLists that will be used throughout this program. If any questions and answers
    // are added, the above BindViews and the setArrayList methods at the bottom of this program are
    // the areas that will need to be edited. Make sure all ArrayLists are the correct length and
    // contain the correct views and values.
    // If new CheckBox questions are made, the process is a little more complex however. I may or
    // may not edit this at a later date.
    private ArrayList<RadioGroup> radioGroupAnswerGroups = new ArrayList<>();
    private ArrayList<RadioButton> radioGroupCorrectAnswers = new ArrayList<>();
    private ArrayList<CheckBox> question3CheckBoxAnswers = new ArrayList<>();
    private ArrayList<Boolean> question3CorrectCheckBoxAnswers = new ArrayList<>();
    private ArrayList<TextView> radioGroupQuestionHeadings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setRadioGroupAnswerGroups(new ArrayList<RadioGroup>());
        setRadioGroupCorrectAnswers(new ArrayList<RadioButton>());
        setQ3CheckBoxAnswers(new ArrayList<CheckBox>());
        setQ3CorrectCheckBoxAnswers(new ArrayList<Boolean>());
        setRadioGroupQuestionHeadings(new ArrayList<TextView>());
    }

    @OnClick(R.id.submit_button)
    public void submitAnswers() {
        if (checkAllQuestionsAnswered()) {
            ArrayList<Boolean> RGCorrectAnswers = checkRadioGroupCorrectAnswers();
            ArrayList<Boolean> CBCorrectAnswers = checkQ3CheckBoxCorrectAnswers();
            for (int i = 0; i < radioGroupQuestionHeadings.size(); i += 1) {
                if (RGCorrectAnswers.get(i)) {
                    radioGroupQuestionHeadings.get(i).setBackgroundColor(correctAnswerColour);
                } else {
                    radioGroupQuestionHeadings.get(i).setBackgroundColor(wrongAnswerColour);
                }
            }
            if (CBCorrectAnswers.contains(false)) {
                question3Heading.setBackgroundColor(wrongAnswerColour);
            } else {
                question3Heading.setBackgroundColor(correctAnswerColour);
            }
        } else {
            Toast toast = Toast.makeText(this, R.string.NotAllAnswered, Toast.LENGTH_LONG);
            toast.show();
        }
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

    /**
     * Check which answers are correct and which are wrong among the RadioGroups.
     *
     * @return an array of booleans that tell whether their corresponding answer is correct (true)
     * or wrong (false).
     */
    private ArrayList<Boolean> checkRadioGroupCorrectAnswers() {
        ArrayList<Boolean> RGCorrectAnswers = new ArrayList<>();
        for (int i = 0; i < radioGroupAnswerGroups.size(); i += 1) {
            RGCorrectAnswers.add(radioGroupAnswerGroups.get(i).getCheckedRadioButtonId() == radioGroupCorrectAnswers.get(i).getId());
        }
        return RGCorrectAnswers;
    }

    /**
     * Check which of the answers among the CheckBoxes are correct.
     *
     * @return an array of booleans that tell whether their corresponding CheckBox in the third
     * question is correct (true) or wrong (false).
     */
    private ArrayList<Boolean> checkQ3CheckBoxCorrectAnswers() {
        ArrayList<Boolean> CBCorrectAnswers = new ArrayList<>();
        for (int i = 0; i < question3CheckBoxAnswers.size(); i += 1) {
            CBCorrectAnswers.add(question3CheckBoxAnswers.get(i).isChecked() == question3CorrectCheckBoxAnswers.get(i));
        }
        return CBCorrectAnswers;
    }

    /**
     * Set all of the ArrayLists.
     */

    public void setRadioGroupAnswerGroups(ArrayList<RadioGroup> radioGroupAnswerGroups) {
        radioGroupAnswerGroups.add(answer1);
        radioGroupAnswerGroups.add(answer2);
        radioGroupAnswerGroups.add(answer4);
        radioGroupAnswerGroups.add(answer5);
        radioGroupAnswerGroups.add(answer6);
        this.radioGroupAnswerGroups = radioGroupAnswerGroups;
    }

    private void setRadioGroupCorrectAnswers(ArrayList<RadioButton> radioGroupCorrectAnswers) {
        radioGroupCorrectAnswers.add(correctAnswer1);
        radioGroupCorrectAnswers.add(correctAnswer2);
        radioGroupCorrectAnswers.add(correctAnswer4);
        radioGroupCorrectAnswers.add(correctAnswer5);
        radioGroupCorrectAnswers.add(correctAnswer6);
        this.radioGroupCorrectAnswers = radioGroupCorrectAnswers;
    }

    public void setQ3CheckBoxAnswers(ArrayList<CheckBox> question3CheckBoxAnswers) {
        question3CheckBoxAnswers.add(answer3A);
        question3CheckBoxAnswers.add(answer3B);
        question3CheckBoxAnswers.add(answer3C);
        question3CheckBoxAnswers.add(answer3D);
        this.question3CheckBoxAnswers = question3CheckBoxAnswers;
    }

    // This one could be just a normal array, but I've decided to make it an ArrayList for
    // consistency.
    public void setQ3CorrectCheckBoxAnswers(ArrayList<Boolean> question3CorrectCheckBoxAnswers) {
        question3CorrectCheckBoxAnswers.add(true);
        question3CorrectCheckBoxAnswers.add(false);
        question3CorrectCheckBoxAnswers.add(true);
        question3CorrectCheckBoxAnswers.add(false);
        this.question3CorrectCheckBoxAnswers = question3CorrectCheckBoxAnswers;
    }

    public void setRadioGroupQuestionHeadings(ArrayList<TextView> radioGroupQuestionHeadings) {
        radioGroupQuestionHeadings.add(question1Heading);
        radioGroupQuestionHeadings.add(question2Heading);
        radioGroupQuestionHeadings.add(question4Heading);
        radioGroupQuestionHeadings.add(question5Heading);
        radioGroupQuestionHeadings.add(question6Heading);
        this.radioGroupQuestionHeadings = radioGroupQuestionHeadings;
    }

}

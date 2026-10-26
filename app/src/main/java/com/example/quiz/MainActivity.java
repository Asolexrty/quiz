package com.example.quiz;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button mTrueButton;
    Button mFalseButton;
    TextView mQuestionTextView;
    TextView mScoreTextView;
    ProgressBar mProgressBar;
    int mIndex;
    int mQuestion;
    int mScore;
    int[] mOrder;
    public static final String  Tag = MainActivity.class.getSimpleName();

    private final TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true),
            new TrueFalse(R.string.question_13, true),
            new TrueFalse(R.string.question_14, true),
            new TrueFalse(R.string.question_15, true),
            new TrueFalse(R.string.question_16, false),
            new TrueFalse(R.string.question_17, false),
            new TrueFalse(R.string.question_18, false),
            new TrueFalse(R.string.question_19, true),
            new TrueFalse(R.string.question_20, false),
            new TrueFalse(R.string.question_21, true),
            new TrueFalse(R.string.question_22, false),
            new TrueFalse(R.string.question_23, true),
            new TrueFalse(R.string.question_24, true),
            new TrueFalse(R.string.question_25, true)
    };

    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / mQuestionBank.length);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");
            mOrder = savedInstanceState.getIntArray("OrderKey");
        } else {
            mScore = 0;
            mIndex = 0;
        }

        if (mOrder == null) {
            mOrder = new int[mQuestionBank.length];
            for (int i = 0; i < mOrder.length; i++) {
                mOrder[i] = i;
            }
            Random random = new Random();
            for (int i = mOrder.length - 1; i > 0; i--) {
                int j = random.nextInt(i + 1);
                int temp = mOrder[i];
                mOrder[i] = mOrder[j];
                mOrder[j] = temp;
            }
        }
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
            mTrueButton = (Button) findViewById(R.id.true_button);
            mFalseButton = (Button) findViewById(R.id.false_button);
            mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
            mScoreTextView = (TextView) findViewById(R.id.score);
            mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

            mProgressBar.setProgress(mIndex * PROGRESS_BAR_INCREMENT);

            mQuestion = mQuestionBank[mOrder[mIndex]].getmQuestionId();
            mQuestionTextView.setText(mQuestion);
            mScoreTextView.setText("Score " +mScore + "/" + mQuestionBank.length );


            mTrueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                    updateQuestion();
                }
            });
            mFalseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                    updateQuestion();
                }
            });
        }
        private void updateQuestion(){
            mIndex = (mIndex + 1) % mQuestionBank.length;
            if(mIndex == 0){
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Game Over");
                alert.setCancelable(false);
                alert.setMessage("You scored " + mScore + " points!");
                alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alert.show();
                return;
            }
            mQuestion = mQuestionBank[mOrder[mIndex]].getmQuestionId();
            mQuestionTextView.setText(mQuestion);
            mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
            mScoreTextView.setText("Score " +mScore + "/" + mQuestionBank.length );

        }
        private void checkAnswer(boolean userSelection){
            boolean correctAnswer = mQuestionBank[mOrder[mIndex]].ismAnswer();
            if(userSelection == correctAnswer){
                Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
                mScore += 1;
            }else{
                Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onSaveInstanceState(Bundle outState){
            super.onSaveInstanceState(outState);
            outState.putInt("ScoreKey",mScore);
            outState.putInt("IndexKey",mIndex);
            outState.putIntArray("OrderKey", mOrder);
        }
        TrueFalse exampleQuestion= new TrueFalse(R.string.question_1, true);

    }
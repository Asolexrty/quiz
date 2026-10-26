package com.example.quiz;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button mTrueButton;
    Button mFalseButton;

    public static final String  Tag = MainActivity.class.getSimpleName();

    TextView mQuestionTextView;

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1,true),
            new TrueFalse(R.string.question_1,true),
            new TrueFalse(R.string.question_1,true),
            new TrueFalse(R.string.question_1,true),
            new TrueFalse(R.string.question_1,true),
            new TrueFalse(R.string.question_1,false),
            new TrueFalse(R.string.question_1,true),
            new TrueFalse(R.string.question_1,false),
            new TrueFalse(R.string.question_1,true),
            new TrueFalse(R.string.question_1,true),
            new TrueFalse(R.string.question_1,false),
            new TrueFalse(R.string.question_1,false),
            new TrueFalse(R.string.question_1,true),


    };

    // estrapoliamo la domanda dalla questionBank
    mQuestion = mQuestionBank[mIndex].getQuestionID();
    mQuestionTextView.setText(mQuestion);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Tag,"True Button Clicked");
                Toast toast = Toast.makeText(getApplicationContext(),"True Pressed", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Tag,"False Button Clicked");
                Toast toast = Toast.makeText(getApplicationContext(),"False Pressed", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        TrueFalse exampleQuestion= new TrueFalse(R.string.question_1, true);

    }
}
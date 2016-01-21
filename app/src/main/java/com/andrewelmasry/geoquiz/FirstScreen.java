package com.andrewelmasry.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstScreen extends Activity {

    private TextView question;
    private Button trueBtn;
    private Button falseBtn;
    private int points = 0;
    private TextView score;
    private Button next;
    private Button prev;

    private TrueFalse[] questionBank;

    private int currentIndex=0;

    int questionNumber;

    private static final String TAG = "FirstScreen";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);

        question = (TextView)findViewById(R.id.question);
        trueBtn = (Button) findViewById(R.id.true_button);
        falseBtn = (Button) findViewById(R.id.false_button);
        score = (TextView)findViewById(R.id.score);
        next = (Button)findViewById(R.id.next);
        prev = (Button)findViewById(R.id.prev);


        questionBank = new TrueFalse[] {
            new TrueFalse(R.string.q1, true),
            new TrueFalse(R.string.q2, false),
            new TrueFalse(R.string.q3, false),
            new TrueFalse(R.string.q4, true),
            new TrueFalse(R.string.q5, true),

        };

        currentIndex=0;

        updateQuestion();

        score.setText("Score is " + points);
        trueBtn.setText("True");
        falseBtn.setText("False");



        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);

            }
        });

        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkAnswer(false);

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1)% questionBank.length;
                updateQuestion();
                trueBtn.setEnabled(true);
                falseBtn.setEnabled(true);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentIndex = (currentIndex -1);
                if (currentIndex==-1) {
                    currentIndex=questionBank.length - 1;
                    updateQuestion();
                    trueBtn.setEnabled(true);
                    falseBtn.setEnabled(true);
                } else {
                    updateQuestion();
                    trueBtn.setEnabled(true);
                    falseBtn.setEnabled(true);
                }
            }
        });

    }

    private void updateQuestion () {
        questionNumber = questionBank[currentIndex].getQuestion();
        question.setText(questionNumber);

    }

    private void addPoints() {
        points++;
        score.setText("Score is " + points);
        trueBtn.setEnabled(false);
        falseBtn.setEnabled(false);
    }

    private void lowerPoints() {
        points--;
        score.setText("Score is " + points);
        trueBtn.setEnabled(false);
        falseBtn.setEnabled(false);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = questionBank[currentIndex].getTrueQuestion();
        if (userPressedTrue == answerIsTrue) {
            addPoints();
        } else {
            lowerPoints();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }

}

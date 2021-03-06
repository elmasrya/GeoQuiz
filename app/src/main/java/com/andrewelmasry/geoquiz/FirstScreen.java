package com.andrewelmasry.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstScreen extends Activity {

    private TextView question;
    private Button trueBtn;
    private Button falseBtn;
    private int points;
    private TextView score;
    private Button next;
    private Button prev;
    private Button cheat;

    private TrueFalse[] questionBank;

    private int currentIndex;

    private boolean isCheater;

    int questionNumber;

    private static final String TAG = "FirstScreen";
    private static final String KEY_INDEX = "index";
    private static final String KEY_INDEX_TWO = "index_two";



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
        cheat = (Button)findViewById(R.id.cheat);


        questionBank = new TrueFalse[] {
            new TrueFalse(R.string.q1, true),
            new TrueFalse(R.string.q2, false),
            new TrueFalse(R.string.q3, false),
            new TrueFalse(R.string.q4, true),
            new TrueFalse(R.string.q5, true),

        };



        if (savedInstanceState !=null) {
            currentIndex=savedInstanceState.getInt(KEY_INDEX,0);
            points=savedInstanceState.getInt(KEY_INDEX_TWO,0);
        } else {
            points = 0;
            currentIndex=0;
        }

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


        cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstScreen.this, CheatActivity.class);
                boolean answerIsTrue = questionBank[currentIndex].getTrueQuestion();
                intent.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                startActivityForResult(intent, 0);
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

    }//end of onCreate

    private void updateQuestion () {
        Log.d(TAG, "Updating question text for question #" + currentIndex, new Exception());
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

    protected void onActivityResult(int requestCode, Intent data) {
        if (data == null ) {
            return;
        }

        isCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_IS_SHOWN, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, currentIndex);
        savedInstanceState.putInt(KEY_INDEX_TWO, points);

    }

    public int sumDouble(int a, int b) {
        if(a==b) {
            return 2*(a+b);
        }
        else {
            return a+b;
        }
    }




}

package com.andrewelmasry.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Andrew on 1/25/16.
 */
public class CheatActivity extends Activity {

    private boolean answerIsTrue;
    private TextView answerTextView;
    private Button cheatButton;

    public static final String EXTRA_ANSWER_IS_TRUE = "com.andrewelmasry.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_IS_SHOWN = "com.andrewelmasry.geoquiz.answer_shown";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cheat_activity);

        setAnswerShownResult(false);

        answerTextView = (TextView)findViewById(R.id.answer);

        cheatButton = (Button)findViewById(R.id.show_answer);

        answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerIsTrue) {
                    answerTextView.setText("True");
                }

                else {
                    answerTextView.setText("False");
                }

                setAnswerShownResult(true);
            }
        });

    }

    private void setAnswerShownResult (boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_IS_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);

    }
}

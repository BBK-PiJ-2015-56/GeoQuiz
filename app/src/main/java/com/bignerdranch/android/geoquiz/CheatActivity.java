package com.bignerdranch.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.bignerdranch.android.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN =
            "com.bignerdranch.android.geoquiz.answer_shown";

    private boolean mAnswerIsTrue;
    private Button mShowAnswer;
    private TextView mAnswerTextView;

    // we declare a static method that can be called from within QuizActivity
    // to create a configured intent ie one that has the info required
    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }
    //a static method that can be called from parent to determined if result was shown
    // the parent will call it, using the intent data that was send back to it
    // via setResult(). getBooleanExtra() looks up the value of the k-v pairing on the intent
    //that has the key EXTRA_ANSWER_SHOWN
    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        // use Activity.getIntent()   and Intent.getBooleanExtra() methods
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        //wire up the show answer button to the corresponding widget in activity_cheat.xml
        mShowAnswer = (Button) findViewById(R.id.show_answer_button);
        //wire up the answer text view button to the corresponding widget
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        //add a listener to it which reveals mAnswerIsTrue value when clicked
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show answer
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });
    }
    // a method for creating an Intent with the answer shown k-v pair to send back to parent
    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        //send to parent via setResult
        setResult(RESULT_OK, data);
    }

}

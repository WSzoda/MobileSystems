package wszoda.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PromptActivity extends AppCompatActivity {
    private boolean correctAnswer;
    private Button buttonPrompt;
    private TextView answerText;

    public static final String KEY_EXTRA_ANSWER_SHOWN = "wszoda.quizapp.PromptActivity.answerShown";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);
        correctAnswer = getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER, true);

        buttonPrompt = findViewById(R.id.button_prompt);
        answerText = findViewById(R.id.answerText);

        buttonPrompt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int answer = correctAnswer ? R.string.button_true : R.string.button_false;
                answerText.setText(answer);
                setAnswerShownResult(true);
            }
        });
    }
    private void setAnswerShownResult(boolean answerWasShown){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN, answerWasShown);
        setResult(RESULT_OK, resultIntent);
    }
}
package wszoda.quizapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button _trueButton;
    private Button _falseButton;
    private Button _nextButton;
    private Button answerButton;
    private TextView _questionTextView;
    private int _currentIndex = 0;
    private static final String KEY_CURRENT_INDEX = "_currentIndex";
    public static final String KEY_EXTRA_ANSWER = "wszoda.quizapp.MainActivity.correctAnswer";
    private static final int REQUEST_CODE_PROMPT = 0;
    private boolean answerWasShown;


    private Question[] questions = new Question[]
            {
            new Question(R.string.question_ksiezyc, false),
            new Question(R.string.question_planeta, true),
            new Question(R.string.question_tlen, true),
            new Question(R.string.question_woda, true),
            new Question(R.string.question_ziemia, true)
            };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _trueButton = findViewById(R.id.button_true);
        _falseButton = findViewById(R.id.button_false);
        _nextButton = findViewById(R.id.button_next);
        answerButton = findViewById(R.id.button_answer);
        _questionTextView = findViewById(R.id.question_text_view);

        _questionTextView.setText(questions[_currentIndex].GetId());

        if(savedInstanceState != null){
            _currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }


        _trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerCorrectness(true);
            }
        });
        _falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerCorrectness(false);
            }
        });
        _nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _currentIndex = (_currentIndex+1)%questions.length;
                answerWasShown = false;
                setNextQuestion();
            }
        });
        answerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, PromptActivity.class);
            boolean correctAnswer = questions[_currentIndex].IsTrueAnswer();
            intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
            startActivityForResult(intent, REQUEST_CODE_PROMPT);
        }
        });
    }

    private void checkAnswerCorrectness(boolean userAnswer)
    {
        boolean correctAnswer = questions[_currentIndex].IsTrueAnswer();
        int messageResultId = 0;
        if(answerWasShown) {
            messageResultId = R.string.answer_was_shown;
        } else {
            if (userAnswer == correctAnswer) {
                messageResultId = R.string.answer_correct;
            } else {
                messageResultId = R.string.answer_wrong;
            }
        }
        Toast.makeText(this, messageResultId, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("JD", "OnStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("JD", "OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("JD", "OnDestroy");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("JD", "OnActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK){
            Log.d("JD", "D");
            return;
        }
        if(requestCode == REQUEST_CODE_PROMPT){
            Log.d("JD", "X");
            if(data == null) {return;}
            answerWasShown = data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN, false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("JD", "OnPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("JD", "OnResume");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("JD", "OnSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX, _currentIndex);
    }

    private void setNextQuestion()
    {
        _questionTextView.setText(questions[_currentIndex].GetId());
    }

}
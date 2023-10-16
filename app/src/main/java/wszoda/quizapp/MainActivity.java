package wszoda.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button _trueButton;
    private Button _falseButton;
    private Button _nextButton;
    private TextView _questionTextView;

    private int _currentIndex = 0;
    private int _points;


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
        _questionTextView = findViewById(R.id.question_text_view);

        _questionTextView.setText(questions[_currentIndex].GetId());

        _trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAnswerCorrectness(true);
            }
        });

        _falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAnswerCorrectness(false);
            }
        });

        _nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _currentIndex = (_currentIndex+1)%questions.length;
                SetNextQuestion();
            }
        });
    }

    private void CheckAnswerCorrectness(boolean userAnswer)
    {
        boolean correctAnswer = questions[_currentIndex].IsTrueAnswer();
        int messageResultId = 0;
        if(userAnswer == correctAnswer)
        {
            messageResultId = R.string.answer_correct;
        }
        else
        {
            messageResultId = R.string.answer_wrong;
        }
        Toast.makeText(this, messageResultId, Toast.LENGTH_SHORT).show();
    }

    private void SetNextQuestion()
    {
        _questionTextView.setText(questions[_currentIndex].GetId());
    }

}
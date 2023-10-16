package wszoda.quizapp;

public class Question {
    private int _questionId;
    private boolean _trueAnswer;

    public Question(int questionId, boolean trueAnswer)
    {
        _questionId = questionId;
        _trueAnswer = trueAnswer;
    }

    public boolean IsTrueAnswer()
    {
        return _trueAnswer;
    }

    public int GetId()
    {
        return _questionId;
    }

}

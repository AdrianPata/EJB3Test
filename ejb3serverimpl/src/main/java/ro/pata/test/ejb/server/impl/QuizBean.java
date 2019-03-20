package ro.pata.test.ejb.server.impl;

import ro.pata.test.ejb.server.api.ILocalQuiz;
import ro.pata.test.ejb.server.api.ILocalQuizQuestionGenerator;
import ro.pata.test.ejb.server.api.IQuiz;
import ro.pata.test.ejb.server.api.IRemoteQuiz;
import ro.pata.test.ejb.server.api.entities.LevelQuestion;

import javax.ejb.*;

@Stateful
@Remote(IRemoteQuiz.class)
@Local(ILocalQuiz.class)
public class QuizBean implements IQuiz {

    @EJB
    ILocalQuizQuestionGenerator levelQuestionGenerator;

    private String playerName;
    private String askedQuestion;
    private Long expectedAnswer;
    private int score=0;
    private int level=1;

    @Override
    public void begin(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String generateQuestionAndAnswer() {
        LevelQuestion levelQuestion = levelQuestionGenerator.generateQuestion(level);
        askedQuestion = levelQuestion.getQuestion();
        expectedAnswer = levelQuestion.getExpectedAswer();
        return askedQuestion;
    }

    @Override
    public boolean verifyAnswerAndReward(int result) {
        if(expectedAnswer == result){
            score++;
            level++;
            return true;
        }
        return false;
    }

    @Remove
    @Override
    public void end() {
        System.out.println("QuizBean instance will be removed..");
    }

    // Some getters to be able to congratulate the player all with showing him name, score,...

    @Override
    public String getPlayerName() {
        return playerName;
    }

    public String getAskedQuestion() {
        return askedQuestion;
    }

    @Override
    public int getScore() {
        return score;
    }
}

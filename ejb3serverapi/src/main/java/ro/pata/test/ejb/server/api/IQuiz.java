package ro.pata.test.ejb.server.api;

public interface IQuiz {
    void begin(String userName);
    String generateQuestionAndAnswer();
    boolean verifyAnswerAndReward(int result);
    void end();
    int getScore();
    String getPlayerName();
}

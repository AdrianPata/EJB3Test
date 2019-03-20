package ro.pata.test.ejb.server.impl;

import ro.pata.test.ejb.server.api.ILocalQuizQuestionGenerator;
import ro.pata.test.ejb.server.api.entities.LevelQuestion;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.concurrent.ThreadLocalRandom;

@Stateless
@Local
public class QuizQuestionGeneratorBean implements ILocalQuizQuestionGenerator {
    @Override
    public LevelQuestion generateQuestion(int level) {
        long range = (long) Math.pow(10,level);
        long a = ThreadLocalRandom.current().nextLong(range /10, range + 1);
        long b = ThreadLocalRandom.current().nextLong(range/10, range + 1);
        LevelQuestion levelQuestion = new LevelQuestion();
        levelQuestion.setQuestion( a+" + "+b+" = ?");
        levelQuestion.setExpectedAswer(a + b);
        return levelQuestion;
    }
}

package ro.pata.test.ejb.server.api;

import ro.pata.test.ejb.server.api.entities.LevelQuestion;

public interface ILocalQuizQuestionGenerator {
    LevelQuestion generateQuestion(int level);
}

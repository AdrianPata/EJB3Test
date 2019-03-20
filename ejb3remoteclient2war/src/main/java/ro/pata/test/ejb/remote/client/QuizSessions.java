package ro.pata.test.ejb.remote.client;

import ro.pata.test.ejb.server.api.IQuiz;

import java.util.HashMap;
import java.util.Map;

public class QuizSessions {
    static public Map<String, IQuiz> map = new HashMap<String,IQuiz>();
}

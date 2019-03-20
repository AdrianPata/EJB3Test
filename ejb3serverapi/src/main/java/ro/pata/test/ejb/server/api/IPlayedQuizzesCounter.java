package ro.pata.test.ejb.server.api;

public interface IPlayedQuizzesCounter {
    void increment();
    long getNumber();
}

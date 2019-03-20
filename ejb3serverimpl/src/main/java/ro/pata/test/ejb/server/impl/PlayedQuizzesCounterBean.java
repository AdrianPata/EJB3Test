package ro.pata.test.ejb.server.impl;

import ro.pata.test.ejb.server.api.ILocalPlayedQuizzesCounter;
import ro.pata.test.ejb.server.api.IPlayedQuizzesCounter;
import ro.pata.test.ejb.server.api.IRemotePlayedQuizzesCounter;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;

@Singleton
@Remote(IRemotePlayedQuizzesCounter.class)
@Local(ILocalPlayedQuizzesCounter.class)
public class PlayedQuizzesCounterBean implements IPlayedQuizzesCounter {
    long playedQuizzesNumber = 0;

    @Override
    public void increment() {
        playedQuizzesNumber++;
    }

    @Override
    public long getNumber() {
        return playedQuizzesNumber;
    }
}

package ro.pata.test.ejb.remote.client;

import ro.pata.test.ejb.server.api.IQuiz;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("Session Created : "+httpSessionEvent.getSession().getId());
        System.out.println("Session Inactivity Timeout : "+httpSessionEvent.getSession().getMaxInactiveInterval());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        String sessionId = httpSessionEvent.getSession().getId();
        System.out.println("Session Id : "+sessionId +" has expired");

        IQuiz object = QuizSessions.map.get(sessionId);

        if(object != null ){
            object.end();
        }
    }
}

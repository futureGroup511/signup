package cn.edu.hist.weilai.signup.listener;

import cn.edu.hist.weilai.signup.service.StatisticsService;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        StatisticsService.oneLineAdd(1);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        StatisticsService.oneLineAdd(-1);
    }
}

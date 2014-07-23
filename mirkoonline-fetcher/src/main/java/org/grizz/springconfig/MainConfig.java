package org.grizz.springconfig;

import org.grizz.model.UserActivity;
import org.grizz.service.HTTPRequestService;
import org.grizz.service.UserActivityFilter;
import org.grizz.service.UserActivityProvider;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Created by Grizz on 2014-07-23.
 */
@Configuration
@EnableScheduling
@EnableAutoConfiguration
@ComponentScan("org.grizz")
public class MainConfig {
    private final int _30minutes = 30 * 60 * 1000;
    private final String SET_COUNTER_URL = "http://mirkoonline.herokuapp.com/setNewCounter/";
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserActivityProvider provider;
    @Autowired
    private UserActivityFilter filter;
    @Autowired
    private HTTPRequestService requestService;

    @Scheduled(fixedDelay = 5 * 60 * 1000) //min, sec, ms
    public void run() {
        List<UserActivity> activeUsers = fetchData(); //Pobiera dane z 50 stron mirko i 10 stron goracych. Czy jakos tak - do sprawdzenia
        List<UserActivity> lastlyActiveUsers = extractUserActivity(activeUsers, _30minutes); //ekstraktuje aktywnosc uzytkownikow i filtruje ich aby zostali tylko Ci ktorzy byli aktywni przez ostatnie 30 min
        setExternalCounter(lastlyActiveUsers.size()); //ustawia licznik na heroku
    }

    private List<UserActivity> fetchData() {
        return provider.provide();
    }

    private List<UserActivity> extractUserActivity(List<UserActivity> users, int minutes) {
        return filter.filterOutAllOlderThan(DateTime.now().minusMinutes(minutes), users);
    }

    private void setExternalCounter(int userCount) {
        log.info("Setting new user counter to " + userCount);
        String response = requestService.sendGet(SET_COUNTER_URL + userCount);
        log.info("Response from server was: " + response);
    }
}

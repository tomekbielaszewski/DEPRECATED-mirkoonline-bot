package org.grizz.springconfig;

import org.grizz.model.UserActivity;
import org.grizz.service.*;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

import static org.grizz.service.KeyProvider.Key;

/**
 * Created by Grizz on 2014-07-23.
 */
@Configuration
@EnableScheduling
@EnableAutoConfiguration
@ComponentScan("org.grizz")
public class MainConfig {
    private final int _30minutes = 30;
    private final String SET_COUNTER_URL = "http://178.62.127.171:8080/setNewCounter/";
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private Provider provider;
    @Autowired
    private UserActivityFilter oldUserActivitiesFilter;
    @Autowired
    private DuplicateFilter duplicateFilter;
    @Autowired
    private HTTPRequestService requestService;
    @Autowired
    private KeyProvider keyProvider;

    @Scheduled(fixedDelay = 15 * 60 * 1000) //min, sec, ms
    public void run() {
        List<UserActivity> activeUsers = fetchData(); //Pobiera dane z ostatnich 99 stron mirko
        List<UserActivity> lastlyActiveUsers = extractUserActivity(activeUsers, _30minutes); //ekstraktuje aktywnosc uzytkownikow i filtruje ich aby zostali tylko Ci ktorzy byli aktywni przez ostatnie 30 min
        setExternalCounter(lastlyActiveUsers.size()); //ustawia licznik w aplikacji webowej

        log.info("Switching application key!");
        keyProvider.switchKey();
    }

    private List<UserActivity> fetchData() {
        return provider.provide();
    }

    private List<UserActivity> extractUserActivity(List<UserActivity> users, int minutes) {
        List<UserActivity> onlyNewActivities = oldUserActivitiesFilter.filterOutAllOlderThan(DateTime.now().minusMinutes(minutes), users);
        List<UserActivity> uniqueActivities = duplicateFilter.filter(onlyNewActivities);
        return uniqueActivities;
    }

    private void setExternalCounter(int userCount) {
        log.info("Setting new user counter to " + userCount);
        String response = requestService.sendGet(SET_COUNTER_URL + userCount);
        log.info("Response from server was: " + response);
    }

    @Bean
    public WykopUrlSigner wykopUrlSigner() {
        return new WykopUrlSigner();
    }
}

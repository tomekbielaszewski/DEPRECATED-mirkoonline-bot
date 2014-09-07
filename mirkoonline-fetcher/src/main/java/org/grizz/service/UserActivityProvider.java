package org.grizz.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.grizz.model.UserActivity;
import org.grizz.util.UserActivitiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.grizz.service.KeyProvider.Key;

/**
 * Created by Grizz on 2014-07-23.
 */
@Service
public class UserActivityProvider implements Provider {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private HTTPRequestService requestService;
    @Autowired
    private KeyProvider keyProvider;

    public List<UserActivity> provide() {
        List<UserActivity> activities = new ArrayList<UserActivity>();
        Gson gson = new Gson();
        log.info("Getting last 99 pages of mikroblog...");
        for (int i = 1; i <= 99; i++) {
            String json = requestService.sendGet(getStreamIndexUrl(i));
            UserActivity[] activitiesFromJSON = null;
            try {
                activitiesFromJSON = gson.fromJson(json, UserActivity[].class);
            } catch (JsonSyntaxException e) {
                System.out.print("[API key is exhausted - switching...]");
                i--;
                keyProvider.switchKey();
                continue;
            }
            Collections.addAll(activities, activitiesFromJSON);

            System.out.print(i + "|");
        }
        System.out.println();
        log.info("Collected "+activities.size()+" entries!");
        return activities;
    }

    private String getStreamIndexUrl(int page) {
        Key key = keyProvider.getKey();
        return String.format("http://a.wykop.pl/stream/index/appkey,%s,page,%d,output,clear", key.getAppKey(), page);
    }
}

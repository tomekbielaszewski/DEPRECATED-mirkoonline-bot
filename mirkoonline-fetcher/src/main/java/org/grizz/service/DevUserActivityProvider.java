package org.grizz.service;

import com.google.gson.Gson;
import org.grizz.model.UserActivity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Grizz on 2014-07-25.
 */
//@Service
public class DevUserActivityProvider implements Provider {
    @Override
    public List<UserActivity> provide() {
        List<String> jsonLines = Collections.emptyList();
        List<UserActivity> activities = new ArrayList<UserActivity>();
        Gson gson = new Gson();
        int counter = 1;

        System.out.println("Reading file...");

        try {
            jsonLines = Files.readAllLines(Paths.get("testEntries.json"), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String jsonLine : jsonLines) {
            System.out.println("Building entries... page " + (counter++));

            UserActivity[] activitiesFromJSON = gson.fromJson(jsonLine, UserActivity[].class);
            activities.addAll(Arrays.asList(activitiesFromJSON));
        }

        System.out.println("Loaded!\n");

        return activities;
    }
}

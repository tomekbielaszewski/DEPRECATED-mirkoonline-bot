package org.grizz.service;

import org.grizz.model.UserActivity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Grizz on 2014-07-25.
 */
@Service
public class DuplicateFilter {
    public List<UserActivity> filter(List<UserActivity> activities) {
        HashSet<UserActivity> filteredActivities = new HashSet<UserActivity>(activities);
        List<UserActivity> filteredActivitiesList = new ArrayList<UserActivity>(filteredActivities);
        return filteredActivitiesList;
    }
}

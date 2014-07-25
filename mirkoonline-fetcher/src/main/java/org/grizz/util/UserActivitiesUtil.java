package org.grizz.util;

import org.grizz.model.UserActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Grizz on 2014-07-25.
 */
public class UserActivitiesUtil {
    public static List<UserActivity> flatten(List<UserActivity> activities) {
        List<UserActivity> flattenActivities = new ArrayList<UserActivity>();

        for (UserActivity activity : activities) {
            flattenActivities.addAll(flatten(activity));
        }

        return flattenActivities;
    }

    public static List<UserActivity> flatten(UserActivity activity) {
        List<UserActivity> flattenActivities = new ArrayList<UserActivity>();

        flattenActivities.add(activity);
        flattenActivities.addAll(activity.getComments());
        flattenActivities.addAll(activity.getVoters());

        activity.setComments(Collections.<UserActivity>emptyList());
        activity.setVoters(Collections.<UserActivity>emptyList());

        return flattenActivities;
    }
}

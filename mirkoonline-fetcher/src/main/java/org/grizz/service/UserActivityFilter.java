package org.grizz.service;

import org.grizz.model.UserActivity;
import org.grizz.util.UserActivitiesUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Grizz on 2014-07-23.
 */
@Service
public class UserActivityFilter {
    private Logger log = LoggerFactory.getLogger(getClass());

    public List<UserActivity> filterOutAllOlderThan(DateTime givenDate, List<UserActivity> rawActivities) {
        List<UserActivity> activities = UserActivitiesUtil.flatten(rawActivities);
        List<UserActivity> filteredActivities = new ArrayList<UserActivity>();

        log.info("Filtering "+activities.size()+" user activities...");

        for (UserActivity activity : activities) {
            if(givenDate.isBefore(activity.getActivity().getTime())) {
                filteredActivities.add(activity);
            }
        }

        log.info(filteredActivities.size()+" left after filtering!");

        return filteredActivities;
    }
}

package org.grizz.service;

import org.grizz.model.UserActivity;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Grizz on 2014-07-23.
 */
@Service
public class UserActivityFilter {
    public List<UserActivity> filterOutAllOlderThan(DateTime givenDate, List<UserActivity> users) {
        return null;
    }
}

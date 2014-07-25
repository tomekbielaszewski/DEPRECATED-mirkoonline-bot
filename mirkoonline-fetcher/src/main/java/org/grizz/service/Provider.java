package org.grizz.service;

import org.grizz.model.UserActivity;

import java.util.List;

/**
 * Created by Grizz on 2014-07-25.
 */
public interface Provider {
    List<UserActivity> provide();
}

package org.grizz.web;

import org.grizz.model.UserCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Grizz on 2014-07-19.
 */
@RestController
public class UsersOnlineController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserCounter userCounter;

    @RequestMapping(value = "/", method = GET)
    public UserCounter getUserCount() {
        return userCounter;
    }

    @RequestMapping(value = "/setNewCounter/{count}", method = GET)
    public boolean saveUserCount(@PathVariable("count") final int count) {
        log.info("Setting new counter to " + count);
        this.userCounter.setCount(count);
        return true;
    }
}

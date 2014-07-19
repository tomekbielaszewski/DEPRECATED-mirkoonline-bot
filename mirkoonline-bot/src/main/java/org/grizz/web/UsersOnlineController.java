package org.grizz.web;

import org.grizz.model.UserCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Grizz on 2014-07-19.
 */
@RestController
public class UsersOnlineController {
    @Autowired
    private UserCounter userCounter;

    @RequestMapping(value = "/", method = GET)
    public UserCounter home() {
        return userCounter;
    }
}

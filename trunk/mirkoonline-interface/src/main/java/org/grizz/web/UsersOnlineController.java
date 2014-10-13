package org.grizz.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.grizz.model.UserCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Grizz on 2014-07-19.
 */
@RestController
public class UsersOnlineController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private UserCounter userCounter;

    @RequestMapping(value = "/", method = GET, produces = MediaType.TEXT_HTML_VALUE)
    public RedirectView getUserCount() throws IOException {
        return new RedirectView("http://mirkoonline.grizwold.pl/");
    }

    @RequestMapping(value = "/setNewCounter/{count}", method = GET)
    public boolean saveUserCount(@PathVariable("count") final int count) {
        log.info("Setting new counter to " + count);
        this.userCounter.setCount(count);
        return true;
    }

    @RequestMapping(value = "/jsonp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String jsonp(@RequestParam("callback")String callBack) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("counter", userCounter.getCount());
        map.put("updated", userCounter.getUpdated());
        return objectMapper.writeValueAsString(new JSONPObject(callBack,map));
    }

    @RequestMapping(value = "/history/jsonp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String history(@RequestParam("callback")String callBack) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("history", userCounter.getHistory());
        return objectMapper.writeValueAsString(new JSONPObject(callBack, userCounter.getHistory()));
    }
}

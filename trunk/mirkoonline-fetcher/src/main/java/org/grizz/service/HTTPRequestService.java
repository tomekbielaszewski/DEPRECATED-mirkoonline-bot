package org.grizz.service;

import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class HTTPRequestService {

    public String sendGet(String url) {
        String responseContent = "";
        Request getRequest = Request.Get(url);

        try {
            responseContent = getRequest.execute().returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseContent;
    }
}

package org.grizz.service;

import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;


@Service
public class HTTPRequestService {
    @Autowired
    private WykopUrlSigner signer;

    public String sendGet(String url) {
        String responseContent = "";
        Request getRequest = Request.Get(url);

        getRequest.addHeader(new BasicHeader("apisign", signer.sign(url, Collections.EMPTY_MAP)));

        try {
            responseContent = getRequest.execute().returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseContent;
    }
}

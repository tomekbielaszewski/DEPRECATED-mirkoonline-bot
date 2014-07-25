package org.grizz.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Grizz on 2014-06-22.
 */
@Service
public class WykopUrlSigner {
    @Autowired
    private KeyProvider keyProvider;

    public String sign(String url, Map<String, String> postContent) {
        StringBuffer postParams = new StringBuffer();
        List<String> postKeysList = new ArrayList<String>();
        postKeysList.addAll(postContent.keySet());
        Collections.sort(postKeysList);

        buildPostParamsString(postContent, postKeysList, postParams);
        if(postParams.length() > 0) {
            removeLastComma(postParams);
        }

        return DigestUtils.md5Hex(keyProvider.getKey().getSecretKey() + url + postParams.toString());
    }

    private void removeLastComma(StringBuffer postParams) {
        postParams.setLength(postParams.length() - 1);
    }

    private void buildPostParamsString(Map<String, String> postContent, List<String> postKeysList, StringBuffer postParams) {
        for (String key : postKeysList) {
            postParams.append(postContent.get(key));
            postParams.append(",");
        }
    }
}

package org.grizz.service;

import lombok.Data;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

/**
 * Created by Grizz on 2014-07-23.
 */
@Service
public class KeyProvider {
    private Logger log = LoggerFactory.getLogger(getClass());

    private static LinkedList<Key> keys = new LinkedList<Key>();
    static {
        keys.add(new Key("BaYW8xM5Dw","avBr72akl2"));
        keys.add(new Key("uaWqdwozqz","75GbuKjMZc"));
        keys.add(new Key("Ot3JeTTYbL","ub5KD2PEl8"));
    }


    public Key getKey() {
        return keys.getFirst();
    }

    public void switchKey() {
        Key first = keys.removeFirst();
        keys.addLast(first);
    }

    public static class Key {
        @Getter
        private String appKey;
        @Getter
        private String secretKey;

        public Key(String appKey, String secretKey) {
            this.appKey = appKey;
            this.secretKey = secretKey;
        }
    }
}

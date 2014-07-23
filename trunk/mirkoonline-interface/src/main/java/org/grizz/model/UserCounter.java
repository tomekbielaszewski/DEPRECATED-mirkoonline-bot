package org.grizz.model;

import org.springframework.stereotype.Service;

/**
 * Created by Grizz on 2014-07-19.
 */
@Service
public class UserCounter {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

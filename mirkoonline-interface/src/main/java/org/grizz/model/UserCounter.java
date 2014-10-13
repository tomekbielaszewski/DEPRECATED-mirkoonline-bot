package org.grizz.model;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by Grizz on 2014-07-19.
 */
@Service
public class UserCounter {
    private int count;
    private Date updated;
    private Map<Long, Integer> history = Maps.newHashMap();

    public int getCount() {
        return count;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setCount(int count) {
        if(this.updated != null) {
            history.put(this.updated.getTime(), this.count);
        }

        this.count = count;
        this.updated = new Date();
    }

    public Map<Long, Integer> getHistory() {
        return history;
    }
}

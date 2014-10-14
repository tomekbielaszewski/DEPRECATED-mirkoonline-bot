package org.grizz.model;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Grizz on 2014-07-19.
 */
@Service
public class UserCounter {
    private int count;
    private Date updated;
    private TreeMap<Long, Integer> history = Maps.newTreeMap();

    public int getCount() {
        return count;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setCount(int count) {
        this.count = count;
        this.updated = new Date();

        history.put(this.updated.getTime(), this.count);
        optimizeHistory();
    }

    private void optimizeHistory() {
        while(history.size() > 250) { //okolo 24-36 godzin
            history.remove(history.firstKey());
        }
    }

    public Map<Long, Integer> getHistory() {
        return history;
    }
}

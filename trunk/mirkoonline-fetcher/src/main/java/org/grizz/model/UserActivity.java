package org.grizz.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;
import java.util.List;

/**
 * Created by Grizz on 2014-07-23.
 */
@Data
public class UserActivity {

    @SerializedName("author")
    private String nick;
    @SerializedName("date")
    private Date activity;

    private List<UserActivity> comments;
    private List<UserActivity> voters;


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        UserActivity rhs = (UserActivity) obj;
        return new EqualsBuilder()
                .append(this.nick, rhs.nick)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(nick)
                .toHashCode();
    }
}

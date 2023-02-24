package com.hawolt.xmpp.event.objects.friends.impl;

import com.hawolt.xmpp.event.objects.friends.GenericFriend;
import org.json.JSONObject;

/**
 * Created: 14/04/2022 07:30
 * Author: Twitter @hawolt
 **/

public class OnlineFriend extends GenericFriend {

    protected final String lolName;

    public OnlineFriend(JSONObject o) {
        super(o);
        this.lolName = o.get("name").toString();
    }

    public String getLOLName() {
        return lolName;
    }

    @Override
    public String toString() {
        return "OnlineFriend{" +
                "lolName='" + lolName + '\'' +
                ", type=" + type +
                ", tagline=" + tagline +
                ", name=" + name +
                ", jid='" + jid + '\'' +
                ", puuid='" + puuid + '\'' +
                ", isGrouped=" + isGrouped +
                ", group='" + group + '\'' +
                ", priority=" + priority +
                '}';
    }
}

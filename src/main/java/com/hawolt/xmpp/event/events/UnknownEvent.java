package com.hawolt.xmpp.event.events;

import com.hawolt.xmpp.event.AbstractEvent;
import com.hawolt.xmpp.event.GenericEvent;
import com.hawolt.xmpp.event.objects.other.PlainData;

/**
 * Created: 11/04/2022 00:11
 * Author: Twitter @hawolt
 **/

public class UnknownEvent extends GenericEvent<PlainData> {

    private final PlainData object;

    public UnknownEvent(AbstractEvent event) {
        super(event);
        this.object = new PlainData(event.getPlain());
    }

    @Override
    public PlainData get() {
        return object;
    }

    @Override
    public String toString() {
        return "Unknown{" +
                "raw='" + plain +
                '}';
    }
}

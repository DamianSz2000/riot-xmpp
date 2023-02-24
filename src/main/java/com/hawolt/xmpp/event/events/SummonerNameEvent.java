package com.hawolt.xmpp.event.events;

import com.hawolt.xmpp.event.AbstractEvent;
import com.hawolt.xmpp.event.GenericEvent;
import com.hawolt.xmpp.event.objects.connection.SummonerName;
import org.json.XML;

/**
 * Created: 30/04/2022 20:54
 * Author: Twitter @hawolt
 **/

public class SummonerNameEvent extends GenericEvent<SummonerName> {

    private final SummonerName name;

    public SummonerNameEvent(AbstractEvent event) {
        super(event);
        this.name = new SummonerName(XML.toJSONObject(event.getPlain()));
    }

    @Override
    public SummonerName get() {
        return name;
    }
}

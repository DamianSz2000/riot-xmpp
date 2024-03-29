package com.hawolt.xmpp.core.input.impl;

import com.hawolt.xmpp.event.objects.connection.ChatIdentity;
import com.hawolt.xmpp.misc.IRiotDataCallback;
import com.hawolt.xmpp.core.output.IOutput;
import com.hawolt.xmpp.core.input.AbstractInterpreter;

/**
 * Created: 10/04/2022 20:31
 * Author: Twitter @hawolt
 **/

public class InputInterpreter extends AbstractInterpreter {
    public InputInterpreter() {
        map.put("message", new MessageInterpreter());
        map.put("iq", new IQInterpreter());
        map.put("stream:features", new StreamFeatureInterpreter());
        map.put("success", new AbstractInterpreter() {
            @Override
            public void handle(String line, ChatIdentity identity, IRiotDataCallback callback, IOutput output) {
                output.write(String.format(preset(), String.format("%s.pvp.net", callback.getChatServer().getDomain())));
            }

            @Override
            public String preset() {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><stream:stream to=\"%s\" xml:lang=\"en\" version=\"1.0\" xmlns=\"jabber:client\" xmlns:stream=\"http://etherx.jabber.org/streams\">";
            }
        });
    }
}

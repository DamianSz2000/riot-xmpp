package com.hawolt.xmpp.core.input;

import com.hawolt.xmpp.event.AbstractEvent;
import com.hawolt.xmpp.event.objects.connection.ChatIdentity;
import com.hawolt.xmpp.misc.IRiotDataCallback;
import com.hawolt.xmpp.core.output.IOutput;
import com.hawolt.xmpp.event.IEvent;
import com.hawolt.logger.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created: 10/04/2022 20:31
 * Author: Twitter @hawolt
 **/

public abstract class AbstractInterpreter implements IInterpreter {
    protected final Map<String, AbstractInterpreter> map = new HashMap<>();
    private IEvent callback;

    @Override
    public void handle(String line, ChatIdentity identity, IRiotDataCallback callback, IOutput output) {
        int spaceIndex = line.indexOf(' ');
        int closeIndex = line.indexOf('>');
        String mapping;
        if (spaceIndex != -1 && spaceIndex < closeIndex) {
            mapping = line.substring(1, spaceIndex);
        } else {
            mapping = line.substring(1, closeIndex);
        }
        Logger.debug("[xmpp-interpreter] {}", mapping);
        if (!map.containsKey(mapping)) unknown(mapping, line, identity, callback, output);
        else map.get(mapping).handle(line, identity, callback, output);
    }

    public void setCallback(IEvent callback) {
        try {
            for (AbstractInterpreter interpreter : map.values()) {
                if (interpreter == null) continue;
                interpreter.setCallback(callback);
            }
        } catch (Exception e) {
            Logger.error(e);
        }
        this.callback = callback;
    }

    protected void unknown(String id, String line, ChatIdentity identity, IRiotDataCallback callback, IOutput output) {
        if (id.equalsIgnoreCase("?xml")) return;
        Logger.debug("[xmpp-unknown] {}", id);
        AbstractEvent event = new AbstractEvent(output, id, line, identity, callback);
        if (this.callback != null) this.callback.onEvent(event);
    }

    @Override
    public String preset() {
        return null;
    }

}

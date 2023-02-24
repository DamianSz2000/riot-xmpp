package com.hawolt.xmpp.core.input.impl;

import com.hawolt.xmpp.event.objects.connection.ChatIdentity;
import com.hawolt.xmpp.misc.IRiotDataCallback;
import com.hawolt.xmpp.core.output.IOutput;
import com.hawolt.logger.Logger;
import com.hawolt.xmpp.core.input.AbstractInterpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created: 12/04/2022 01:15
 * Author: Twitter @hawolt
 **/

public class PushInterpreter extends AbstractInterpreter {

    private final Pattern pattern = Pattern.compile("subscription='(.*?)'");

    public PushInterpreter() {
        map.put("pending_out", null);
        map.put("pending_in", null);
        map.put("remove", null);
        map.put("both", null);
    }

    @Override
    public void handle(String line, ChatIdentity identity, IRiotDataCallback callback, IOutput output) {
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) return;
        String match = matcher.group(1);
        if (!map.containsKey(match)) return;
        Logger.debug("[xmpp-interpreter-push] {}", match);
        unknown(match, line, identity, callback, output);
    }
}

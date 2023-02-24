package com.hawolt.xmpp.core.input.impl;

import com.hawolt.xmpp.event.objects.connection.ChatIdentity;
import com.hawolt.xmpp.misc.IRiotDataCallback;
import com.hawolt.xmpp.core.output.IOutput;
import com.hawolt.xmpp.core.input.AbstractInterpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created: 10/04/2022 20:31
 * Author: Twitter @hawolt
 **/

public class MessageInterpreter extends AbstractInterpreter {

    private final Pattern pattern = Pattern.compile("from='(.*?)'");

    @Override
    public void handle(String line, ChatIdentity identity, IRiotDataCallback callback, IOutput output) {
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) return;
        unknown("message", line, identity, callback, output);
    }

}

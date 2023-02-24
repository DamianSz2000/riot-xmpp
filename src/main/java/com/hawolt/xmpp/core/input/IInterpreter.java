package com.hawolt.xmpp.core.input;

import com.hawolt.xmpp.event.objects.connection.ChatIdentity;
import com.hawolt.xmpp.misc.IRiotDataCallback;
import com.hawolt.xmpp.core.output.IOutput;

/**
 * Created: 10/04/2022 20:31
 * Author: Twitter @hawolt
 **/

public interface IInterpreter {
    void handle(String line, ChatIdentity identity, IRiotDataCallback callback, IOutput output);

    String preset();
}

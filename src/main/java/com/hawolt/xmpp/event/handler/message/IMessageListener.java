package com.hawolt.xmpp.event.handler.message;

import com.hawolt.xmpp.event.objects.conversation.history.impl.FailedMessage;
import com.hawolt.xmpp.event.objects.conversation.history.impl.OutgoingMessage;
import com.hawolt.xmpp.event.objects.conversation.history.impl.IncomingMessage;

/**
 * Created: 18/04/2022 01:18
 * Author: Twitter @hawolt
 **/

public interface IMessageListener {
    void onMessageReceived(IncomingMessage message);

    void onMessageSent(OutgoingMessage message);

    void onFailedMessage(FailedMessage message);
}

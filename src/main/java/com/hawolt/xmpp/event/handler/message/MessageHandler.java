package com.hawolt.xmpp.event.handler.message;

import com.hawolt.xmpp.event.handler.AbstractObserver;
import com.hawolt.xmpp.event.objects.conversation.history.AbstractMessage;
import com.hawolt.xmpp.event.objects.conversation.history.impl.FailedMessage;
import com.hawolt.xmpp.event.objects.conversation.history.impl.OutgoingMessage;
import com.hawolt.xmpp.event.objects.conversation.history.impl.IncomingMessage;

/**
 * Created: 18/04/2022 01:23
 * Author: Twitter @hawolt
 **/

public class MessageHandler extends AbstractObserver<AbstractMessage, IMessageListener> {

    @Override
    public void dispatch(AbstractMessage message) {
        for (IMessageListener listener : observers) {
            if (message instanceof OutgoingMessage) {
                listener.onMessageSent((OutgoingMessage) message);
            } else if (message instanceof IncomingMessage) {
                listener.onMessageReceived((IncomingMessage) message);
            }else if(message instanceof FailedMessage){
                listener.onFailedMessage((FailedMessage) message);
            }
        }
    }
}

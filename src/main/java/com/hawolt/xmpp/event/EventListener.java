package com.hawolt.xmpp.event;

/**
 * Created: 14/04/2022 02:38
 * Author: Twitter @hawolt
 **/

public interface EventListener<T extends BaseObject> {
    void onEvent(T event);
}

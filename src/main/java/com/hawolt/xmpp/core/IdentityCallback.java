package com.hawolt.xmpp.core;

import com.hawolt.xmpp.event.objects.connection.ChatIdentity;

/**
 * Created: 30/04/2022 14:38
 * Author: Twitter @hawolt
 **/

public interface IdentityCallback {
    ChatIdentity getIdentity();
}

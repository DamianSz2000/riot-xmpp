package com.hawolt.xmpp.misc;

import java.util.function.Supplier;

/**
 * Created: 10/04/2022 16:44
 * Author: Twitter @hawolt
 **/

public interface IRiotDataCallback {
    String getGameRegion();

    String getXMPPToken();

    String getIdentifier();

    RiotChatServer getChatServer();

    Supplier<String> getEntitlementSupplier();

    Supplier<String> getAccessTokenSupplier();
}

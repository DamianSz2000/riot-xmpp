package com.hawolt.misc;

/**
 * Created: 10/04/2022 15:38
 * Author: Twitter @hawolt
 **/

public class StaticConfig {

    public static final String HEARTBEAT = " ";

    public static String SOCKET_CONNECT(RiotChatServer server) {
        return String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?><stream:stream to=\"%s.pvp.net\" xml:lang=\"en\" version=\"1.0\" xmlns=\"jabber:client\" xmlns:stream=\"http://etherx.jabber.org/streams\">", server.getDomain());
    }
}

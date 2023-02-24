package com.hawolt.misc.impl;

import com.hawolt.cryptography.SHA256;
import com.hawolt.misc.IRiotDataCallback;
import com.hawolt.misc.RiotChatServer;
import com.hawolt.virtual.leagueclient.VirtualLeagueClient;
import com.hawolt.virtual.riotclient.VirtualRiotClient;
import org.json.JSONObject;

import java.util.Base64;
import java.util.function.Supplier;

/**
 * Created: 24/02/2023 14:25
 * Author: Twitter @hawolt
 **/

public class RiotDataCallback implements IRiotDataCallback {
    private final VirtualLeagueClient virtualLeagueClient;
    private final RiotChatServer riotChatServer;
    private final String identifier;

    public RiotDataCallback(VirtualLeagueClient virtualLeagueClient) {
        this.virtualLeagueClient = virtualLeagueClient;
        VirtualRiotClient virtualRiotClient = virtualLeagueClient.getVirtualRiotClient();
        JSONObject object = new JSONObject(new String(Base64.getDecoder().decode(getXMPPToken().split("\\.")[1])));
        this.riotChatServer = RiotChatServer.valueOf(object.getString("affinity").replace("-", "_").toUpperCase());
        this.identifier = SHA256.hash(String.join(":", virtualRiotClient.getUsername(), virtualRiotClient.getPassword()));
    }

    @Override
    public String getGameRegion() {
        return virtualLeagueClient.getVirtualLeagueClientInstance().getPlatformId();
    }

    @Override
    public String getXMPPToken() {
        return virtualLeagueClient.getGeoPas().get("xmpp_token");
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public RiotChatServer getChatServer() {
        return riotChatServer;
    }

    @Override
    public Supplier<String> getEntitlementSupplier() {
        return () -> virtualLeagueClient.getEntitlement().get("entitlement.lol.entitlements_token", true);
    }

    @Override
    public Supplier<String> getAccessTokenSupplier() {
        return () -> virtualLeagueClient.getVirtualLeagueClientInstance().getLeagueClientSupplier().get("lol.access_token", true);
    }
}

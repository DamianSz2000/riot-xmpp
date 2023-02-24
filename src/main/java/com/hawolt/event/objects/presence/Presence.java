package com.hawolt.event.objects.presence;

import com.hawolt.event.objects.presence.impl.*;
import org.json.JSONObject;

/**
 * Created: 13/04/2022 12:36
 * Author: Twitter @hawolt
 **/

public class Presence extends JSONObject {

    public String toHTML() {
        String rawHtml = toString();
        int rawHtmlLength = rawHtml.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < rawHtmlLength; i++) {
            char ch = rawHtml.charAt(i);
            if (ch == '<') {
                builder.append("&lt;");
            } else if (ch == '>') {
                builder.append("&gt;");
            } else if (ch == '"') {
                builder.append("&quot;");
            } else if (ch == '&') {
                builder.append("&amp;");
            } else if (ch < ' ' || ch == '\'') {
                builder.append("&#").append((int) ch).append(';');
            } else {
                builder.append(ch);
            }
        }
        return builder.toString();
    }

    public static AbstractPresence create(JSONObject o) {
        if (!o.has("show")) {
            if (o.has("id")) {
                return new DisconnectPresence(o);
            } else {
                return new OfflinePresence(o);
            }
        } else {
            if (o.getString("show").equals("mobile")) {
                if (!o.has("last_online")) {
                    return new FakeMobilePresence(o);
                } else {
                    return new MobilePresence(o);
                }
            } else {
                if (!o.has("game")) {
                    if (!o.has("status")) {
                        return new DeceivePresence(o);
                    } else {
                        return new OnlinePresence(o);
                    }
                } else {
                    return new GamePresence(o);
                }
            }
        }
    }

    public static class Builder {
        private String championId, companionId, damageSkinId, gameQueueType, gameStatus, iconOverride, initRankStat, level, mapId, mapSkinId, masteryScore, profileIcon, puuid, skinVariant, skinname, timestamp, gameMode, observable, pty;
        private Regalia regalia = new Regalia();

        public Builder setRegalia(String bannerType, String crestType, String selectedPrestigeCrest) {
            this.regalia = new Regalia(bannerType, crestType, selectedPrestigeCrest);
            return this;
        }

        public Builder setChampionId(String championId) {
            this.championId = championId;
            return this;
        }

        public Builder setCompanionId(String companionId) {
            this.companionId = companionId;
            return this;
        }

        public Builder setDamageSkinId(String damageSkinId) {
            this.damageSkinId = damageSkinId;
            return this;
        }

        public Builder setGameQueueType(String gameQueueType) {
            this.gameQueueType = gameQueueType;
            return this;
        }

        public Builder setGameStatus(String gameStatus) {
            this.gameStatus = gameStatus;
            return this;
        }

        public Builder setIconOverride(String iconOverride) {
            this.iconOverride = iconOverride;
            return this;
        }

        public Builder setInitRankStat(String initRankStat) {
            this.initRankStat = initRankStat;
            return this;
        }

        public Builder setLevel(String level) {
            this.level = level;
            return this;
        }

        public Builder setMapId(String mapId) {
            this.mapId = mapId;
            return this;
        }

        public Builder setMapSkinId(String mapSkinId) {
            this.mapSkinId = mapSkinId;
            return this;
        }

        public Builder setMasteryScore(String masteryScore) {
            this.masteryScore = masteryScore;
            return this;
        }

        public Builder setProfileIcon(String profileIcon) {
            this.profileIcon = profileIcon;
            return this;
        }

        public Builder setPuuid(String puuid) {
            this.puuid = puuid;
            return this;
        }

        public Builder setSkinVariant(String skinVariant) {
            this.skinVariant = skinVariant;
            return this;
        }

        public Builder setSkinname(String skinname) {
            this.skinname = skinname;
            return this;
        }

        public Builder setTimestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder setGameMode(String gameMode) {
            this.gameMode = gameMode;
            return this;
        }

        public Builder setIsObservable(String observable) {
            this.observable = observable;
            return this;
        }

        public Builder setPTY(String s) {
            this.pty = pty;
            return this;
        }

        public Presence build() {
            Presence presence = new Presence();
            presence.put("championId", championId);
            presence.put("companionId", companionId);
            presence.put("damageSkinId", damageSkinId);
            presence.put("gameMode", gameMode);
            presence.put("gameQueueType", gameQueueType);
            presence.put("gameStatus", gameStatus);
            presence.put("iconOverride", iconOverride);
            presence.put("initRankStat", initRankStat);
            presence.put("level", level);
            presence.put("mapId", mapId);
            presence.put("mapSkinId", mapSkinId);
            presence.put("masteryScore", masteryScore);
            presence.put("profileIcon", profileIcon);
            presence.put("pty", pty);
            presence.put("puuid", puuid);
            presence.put("regalia", regalia.build());
            presence.put("skinVariant", skinVariant);
            presence.put("skinname", skinname);
            presence.put("timeStamp", timestamp);
            return presence;
        }
    }
}

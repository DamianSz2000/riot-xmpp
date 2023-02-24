# riot-xmpp

riot-xmpp allows you to connect to riots chat server which all features the original chat offers

## Maven

to use riot-xmpp in your maven project include the following repository

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
and this dependency

```xml
<dependency>
    <groupId>com.github.hawolt</groupId>
    <artifactId>riot-xmpp</artifactId>
    <version>09ec0f6916</version>
</dependency>
```

riot-xmpp requires the following dependency

```xml
<dependency>
    <groupId>com.github.hawolt</groupId>
    <artifactId>league-client-api</artifactId>
    <version>fe40d42c2c</version>
</dependency>
```

## Usage

an example usage that will connect you to a chat server looks as follows

```java
import com.hawolt.authentication.LocalCookieSupplier;
import com.hawolt.event.EventListener;
import com.hawolt.event.EventType;
import com.hawolt.event.objects.other.PlainData;
import com.hawolt.logger.Logger;
import com.hawolt.manifest.RMANCache;
import com.hawolt.misc.impl.RiotDataCallback;
import com.hawolt.virtual.leagueclient.VirtualLeagueClient;
import com.hawolt.virtual.leagueclient.VirtualLeagueClientInstance;
import com.hawolt.virtual.leagueclient.exception.LeagueException;
import com.hawolt.virtual.riotclient.VirtualRiotClient;
import com.hawolt.virtual.riotclient.VirtualRiotClientInstance;
import com.hawolt.xmpp.VirtualRiotXMPPClient;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * Created: 24/02/2023 14:21
 * Author: Twitter @hawolt
 **/

public class Example {
    public static void main(String[] args) {
        RMANCache.active = true;
        LocalCookieSupplier localCookieSupplier = new LocalCookieSupplier();
        VirtualRiotClientInstance virtualRiotClientInstance = VirtualRiotClientInstance.create(localCookieSupplier);
        try {
            VirtualRiotClient virtualRiotClient = virtualRiotClientInstance.login(args[0], args[1]);
            VirtualLeagueClientInstance virtualLeagueClientInstance = virtualRiotClient.createVirtualLeagueClientInstance();
            CompletableFuture<VirtualLeagueClient> virtualLeagueClientFuture = virtualLeagueClientInstance.login(true, false);
            virtualLeagueClientFuture.whenComplete(((virtualLeagueClient, throwable) -> {
                if (throwable != null) throwable.printStackTrace();
                else {
                    Logger.info("Client setup complete");
                    RiotDataCallback riotDataCallback = new RiotDataCallback(virtualLeagueClient);
                    VirtualRiotXMPPClient virtualRiotXMPPClient = new VirtualRiotXMPPClient(riotDataCallback);
                    virtualRiotXMPPClient.addHandler(EventType.ON_READY, (EventListener<PlainData>) event -> {
                        Logger.info("XMPP connected");
                    });
                    virtualRiotXMPPClient.connect();
                }
            }));
        } catch (IOException e) {
            Logger.error(e);
        } catch (LeagueException e) {
            throw new RuntimeException(e);
        }
    }
}

```

package hu.otpmobil.ticket.configuration;

import com.netflix.loadbalancer.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//@RibbonClient(name = "core")
public class CoreClientConfiguration {

    public IRule ribbonCoreRule() {
        return new RoundRobinRule();
    }

    public IPing corePingConfiguration(ServerList<Server> servers) {
        return new NoOpPing();
    }

    public ServerList<Server> coreServerList() {
        return new ServerList<Server>() {
            private final List<Server> servers = Collections.singletonList(new Server("http", "localhost", 8081));

            @Override
            public List<Server> getInitialListOfServers() {
                return servers;
            }

            @Override
            public List<Server> getUpdatedListOfServers() {
                return servers;
            }
        };
    }


}

package hu.otpmobil.ticket.configuration;

import com.netflix.loadbalancer.*;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;

//@RibbonClient(name = "partner")
public class PartnerClientConfiguration {

    public IRule ribbonPartnerRule() {
        return new RoundRobinRule();
    }

    public IPing partnerPingConfiguration(ServerList<Server> servers) {
        return new NoOpPing();
    }

    public ServerList<Server> partnerServerList() {
        return new ServerList<Server>() {
            private final List<Server> servers = Collections.singletonList(new Server("http", "localhost", 8082));

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

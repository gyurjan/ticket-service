package hu.otpmobile.ticket.api.configuration;

import com.netflix.loadbalancer.*;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@RibbonClient(name = "core-service")
public class CoreClientConfiguration {

    @Bean
    public IRule loadBalancingRule() {
        return new RoundRobinRule();
    }

    @Bean
    public IPing pingConfiguration(ServerList<Server> servers) {
        return new NoOpPing();
    }

    @Bean
    public ServerList<Server> serverList() {
        return new ServerList<Server>() {
            private final List<Server> servers = Arrays.asList(new Server("http", "localhost", 8081));

            @Override
            public List<Server> getInitialListOfServers() {
                return servers;
            }

            @Override
            public List<Server> getUpdatedListOfServers() {
                List<Server> serverList = servers;
                return servers;
            }
        };
    }


}

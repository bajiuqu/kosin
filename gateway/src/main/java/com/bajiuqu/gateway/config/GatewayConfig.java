package com.bajiuqu.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.support.ipresolver.RemoteAddressResolver;
import org.springframework.cloud.gateway.support.ipresolver.XForwardedRemoteAddressResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    RemoteAddressResolver resolver = XForwardedRemoteAddressResolver.maxTrustedIndex(1);

    //访问IP白名单
    private static final String[] whitAddress = {
            "39.105.132.152"
    };

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("service-data", r -> r.remoteAddr(whitAddress).uri("lb://service-data"))
//                .build();

        return builder.routes()
                .route("service-data",
                        r -> r.path("/service-data/**")
                                .and().remoteAddr(resolver, whitAddress)
                                .filters(f -> f.stripPrefix(1))
                                .uri("lb://service-data")
                ).build();
    }

}

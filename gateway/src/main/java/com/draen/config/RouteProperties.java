package com.draen.config;

import com.draen.routing.data.Route;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("custom.gateway")
@Getter
@Setter
public class RouteProperties {
    private List<Route> routes;
}

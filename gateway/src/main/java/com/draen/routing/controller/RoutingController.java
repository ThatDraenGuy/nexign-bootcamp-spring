package com.draen.routing.controller;

import com.draen.config.RouteProperties;
import com.draen.routing.data.Route;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class RoutingController {
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private final RouteProperties routeProperties;

    public RoutingController(RouteProperties routeProperties) {
        this.routeProperties = routeProperties;
    }

    @GetMapping("/**")
    public ResponseEntity<?> get(ProxyExchange<?> proxy) {
        getUri(proxy);
        return proxy.get();
    }

    @PostMapping("/**")
    public ResponseEntity<?> post(ProxyExchange<?> proxy) {
        getUri(proxy);
        return proxy.post();
    }

    @PatchMapping("/**")
    public ResponseEntity<?> patch(ProxyExchange<?> proxy) {
        getUri(proxy);
        return proxy.patch();
    }

    @DeleteMapping("/**")
    public ResponseEntity<?> delete(ProxyExchange<?> proxy) {
        getUri(proxy);
        return proxy.delete();
    }

    private void getUri(ProxyExchange<?> proxy) {
        for (Route route : routeProperties.getRoutes()) {
            if (pathMatcher.match(route.getPath(), proxy.path())) {
                proxy.uri(route.getUri() + proxy.path());
                return;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No service found");
    }
}

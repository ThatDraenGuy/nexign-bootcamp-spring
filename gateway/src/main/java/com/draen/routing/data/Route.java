package com.draen.routing.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Route {
    private String name;
    private String uri;
    private String path;
}

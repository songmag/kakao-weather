package com.mark.server.config;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GoApi {
    private final String name;
    private final String key;
    private final String url;
}

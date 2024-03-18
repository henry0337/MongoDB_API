package com.henry.newmongodbapi.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt.token", ignoreUnknownFields = false)
public class TokenProperties {
    /**
     * An auto-generated unique string for authentication purpose
     */
    private String raw_token;

    /**
     * Default lifetime for token, count by milliseconds (ms)
     */
    private long tokenLifetime;

    /**
     * Default lifetime for refreshing token, count by milliseconds (ms)
     */
    private long refreshLifetime;
}

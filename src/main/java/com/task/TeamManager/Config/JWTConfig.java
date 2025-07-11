package com.task.TeamManager.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class JWTConfig {

        @Value("${jwt.secret}")
        private String secretKey;

        @Value("${jwt.expiration}")
        private long expirationTime;

        public String getSecretKey() {
            return secretKey;
        }

        public long getExpirationTime() {
            return expirationTime;
        }
    }



package org.example.server.rateLimit;

import org.example.server.rateLimit.impl.TokenBucketRateLimit;

import java.util.HashMap;
import java.util.Map;

public class RateLimitProvider {
    private Map<String, RateLimit> rateLimitMap;
    public RateLimitProvider() {
        rateLimitMap = new HashMap<>();
    }
    public RateLimit getRateLimit(String serviceName) {
        if (!rateLimitMap.containsKey(serviceName)) {
            RateLimit rateLimit = new TokenBucketRateLimit(1, 100000);
            rateLimitMap.put(serviceName, rateLimit);
        }
        return rateLimitMap.get(serviceName);
    }
}

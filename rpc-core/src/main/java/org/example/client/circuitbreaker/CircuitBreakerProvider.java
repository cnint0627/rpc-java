package org.example.client.circuitbreaker;

import java.util.HashMap;
import java.util.Map;

public class CircuitBreakerProvider {
    Map<String, CircuitBreaker> circuitBreakerMap;
    public CircuitBreakerProvider() {
        circuitBreakerMap = new HashMap<>();
    }
    public CircuitBreaker getCircuitBreaker(String serviceName) {
        if (!circuitBreakerMap.containsKey(serviceName)) {
            CircuitBreaker circuitBreaker = new CircuitBreaker(1, 0.5, 100000);
            circuitBreakerMap.put(serviceName, circuitBreaker);
        }
        return circuitBreakerMap.get(serviceName);
    }
}

package org.example.client.circuitbreaker;

import java.util.concurrent.atomic.AtomicInteger;

enum CircuitBreakerState {
    CLOSED, OPEN, HALF_OPEN
}

public class CircuitBreaker {
    private CircuitBreakerState state = CircuitBreakerState.CLOSED;
    private int successCount = 0;
    private int failuerCount = 0;
    private int requestCount = 0;
    private int failuerThreshold;
    private double halfOpenSuccessRate;
    private long retryTimePeriod;
    private long lastFailureTimestamp;
    public CircuitBreaker(int failureThreshold, double halfOpenSuccessRate, long retryTimePeriod) {
        this.lastFailureTimestamp = System.currentTimeMillis();
        this.failuerThreshold = failureThreshold;
        this.halfOpenSuccessRate = halfOpenSuccessRate;
        this.retryTimePeriod = retryTimePeriod;
    }
    public synchronized boolean allowRequest() {
        switch (state) {
            case OPEN:
                if (System.currentTimeMillis() - lastFailureTimestamp >= retryTimePeriod) {
                    state = CircuitBreakerState.HALF_OPEN;
                    resetCount();
                    return true;
                }
                return false;
            case HALF_OPEN:
                requestCount++;
                return true;
            case CLOSED:
            default:
                return true;
        }
    }
    public synchronized void recordSuccess() {
        // 注意只有HALF_OPEN或者CLOSED状态才会调用该方法
        if (state == CircuitBreakerState.HALF_OPEN) {
            successCount++;
            if (successCount >= requestCount * halfOpenSuccessRate) {
                state = CircuitBreakerState.CLOSED;
                resetCount();
            }
        } else {
            resetCount();
        }
    }
    public synchronized void recordFailure() {
        // 注意只有HALF_OPEN或者CLOSED状态才会调用该方法
        failuerCount++;
        if (state == CircuitBreakerState.HALF_OPEN) {
            state = CircuitBreakerState.OPEN;
        } else if (failuerCount >= failuerThreshold) {
            state = CircuitBreakerState.OPEN;
        }
        lastFailureTimestamp = System.currentTimeMillis();
    }
    private synchronized void resetCount() {
        successCount = 0;
        failuerCount = 0;
        requestCount = 0;
    }
}

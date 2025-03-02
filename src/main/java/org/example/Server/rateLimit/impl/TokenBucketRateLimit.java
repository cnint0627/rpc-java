package org.example.Server.rateLimit.impl;

import org.example.Server.rateLimit.RateLimit;

public class TokenBucketRateLimit implements RateLimit {
    private int rate;
    private int capacity;
    private volatile int remaining;
    private volatile long lastTimestamp;
    public TokenBucketRateLimit(int capacity, int rate) {
        this.rate = rate;
        this.capacity = capacity;
        this.remaining = capacity;
        this.lastTimestamp = System.currentTimeMillis();
    }
    @Override
    public synchronized boolean getToken() {
        if (remaining > 0) {
            remaining--;
            return true;
        }
        long timestamp = System.currentTimeMillis();
        if (timestamp - lastTimestamp >= rate) {
            remaining = Math.min(capacity, remaining + (int)((timestamp - lastTimestamp) / rate));
            remaining--;
            lastTimestamp = timestamp;
            return true;
        }
        return false;
    }
}

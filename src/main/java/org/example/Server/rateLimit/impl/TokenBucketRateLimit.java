package org.example.Server.rateLimit.impl;

import org.example.Server.rateLimit.RateLimit;

public class TokenBucketRateLimit implements RateLimit {
    private int rate;
    private int capacity;
    private volatile int remaining;
    private volatile long lastTimeStamp;
    public TokenBucketRateLimit(int capacity, int rate) {
        this.rate = rate;
        this.capacity = capacity;
        this.remaining = capacity;
        this.lastTimeStamp = System.currentTimeMillis();
    }
    @Override
    public synchronized boolean getToken() {
        if (remaining > 0) {
            remaining--;
            return true;
        }
        long timeStamp = System.currentTimeMillis();
        if (timeStamp - lastTimeStamp >= rate) {
            remaining = Math.min(capacity, remaining + (int)((timeStamp - lastTimeStamp) / rate));
            remaining--;
            lastTimeStamp = timeStamp;
            return true;
        }
        return false;
    }
}

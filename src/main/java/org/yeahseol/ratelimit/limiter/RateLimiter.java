package org.yeahseol.ratelimit.limiter;

public interface RateLimiter {

    Boolean acquire(String key);
}

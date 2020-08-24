package org.yeahseol.ratelimit.service;

import org.springframework.stereotype.Service;
import org.yeahseol.ratelimit.exception.OverRateLimitException;
import org.yeahseol.ratelimit.limiter.RateLimiter;

import java.util.Date;

@Service
public class YeahseolServiceImpl implements YeahseolService {

    private final RateLimiter rateLimiter;

    public YeahseolServiceImpl(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    public String yeahseol(String name) {

        if (rateLimiter.acquire(name)) {
            return name + ": " + new Date();
        } else {
            throw new OverRateLimitException(name);
        }
    }
}

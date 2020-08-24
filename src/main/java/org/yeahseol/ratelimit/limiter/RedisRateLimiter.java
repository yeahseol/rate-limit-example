package org.yeahseol.ratelimit.limiter;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class RedisRateLimiter implements RateLimiter {

    private final RedisTemplate<Object, Object> redisTemplate;
    private final RedisScript<Boolean> rateLimitScript;

    public RedisRateLimiter(RedisTemplate<Object, Object> redisTemplate, RedisScript<Boolean> rateLimitScript) {
        this.redisTemplate = redisTemplate;
        this.rateLimitScript = rateLimitScript;
    }

    @Override
    public Boolean acquire(String key) {

        return redisTemplate.execute(rateLimitScript, Collections.singletonList(key));
    }
}

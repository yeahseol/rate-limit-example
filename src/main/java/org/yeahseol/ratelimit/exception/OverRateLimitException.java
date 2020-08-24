package org.yeahseol.ratelimit.exception;

public class OverRateLimitException extends RuntimeException {

    public OverRateLimitException(String key) {
        super("Over Rate Limit with (" + key + ")");
    }
}

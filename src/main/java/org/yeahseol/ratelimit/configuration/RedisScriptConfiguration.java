package org.yeahseol.ratelimit.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

@Configuration
public class RedisScriptConfiguration {

    @Bean
    public RedisScript<Boolean> rateLimitScript() {

        DefaultRedisScript<Boolean> rateLimitScript = new DefaultRedisScript<>();
        rateLimitScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("META-INF/scripts/rate_limit.lua")));
        rateLimitScript.setResultType(Boolean.class);
        return rateLimitScript;
    }
}

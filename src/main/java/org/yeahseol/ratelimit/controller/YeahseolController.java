package org.yeahseol.ratelimit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yeahseol.ratelimit.exception.OverRateLimitException;
import org.yeahseol.ratelimit.service.YeahseolService;
import reactor.core.publisher.Mono;

@Controller
public class YeahseolController {

    private final YeahseolService service;

    public YeahseolController(YeahseolService service) {
        this.service = service;
    }

    @GetMapping(value = "/yeahseol/{name}", produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody
    Mono<String> yeahseol(@PathVariable("name") String name) {

        return Mono.just(service.yeahseol(name));
    }

    @ExceptionHandler(OverRateLimitException.class)
    public ResponseEntity<String> overRateLimit(OverRateLimitException e) {

        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body(e.getMessage());
    }
}

package ru.startsev.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisController(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @RequestMapping("/put/{id}/{message}")
    public void put(@PathVariable("id") String id, @PathVariable("message") String message) {
        stringRedisTemplate.opsForValue().set(id, message, 30L, TimeUnit.SECONDS);
    }

    @RequestMapping("/get/{id}")
    public String get(@PathVariable String id) {
        return stringRedisTemplate.opsForValue().get(id);
    }
}

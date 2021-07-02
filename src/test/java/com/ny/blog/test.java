package com.ny.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yang
 * @version 1.0
 * @date 2021/4/14 18:20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class test {

    private RedisTemplate redisTemplate;

    @Test
    public void test1() {

        Boolean aBoolean = redisTemplate.hasKey("111");
    }

}

package com.whl.store;

import com.whl.store.util.redis.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class StoreApplicationTests {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    void redistest(){
        redisUtils.set("name","welcometo china!",1);

        System.out.println(redisUtils.getExpire("name"));

        System.out.println(redisUtils.get("name"));

    }

}

package com.sumixer.ys.api.service;

import com.sumixer.ys.api.config.Constants;
import com.sumixer.ys.api.core.Token;
import com.sumixer.ys.api.utils.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author : coderWu
 * @date : Created on 19:31 2018/6/12
 */
@Service
public class TokenService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public Token generate(String tokenValue) {
        if (!StringUtils.isEmpty(tokenValue)) {
            Token newToken = create(redisTemplate.boundValueOps(tokenValue).get());
            delete(tokenValue);
            return newToken;
        }
        return null;
    }

    public Token create(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        String tokenValue = RandomUtils.string("token", id);
        LocalDateTime updateTime = LocalDateTime.now();
        LocalDateTime expiresTime = updateTime.plusHours(Constants.TOKEN_EXPIRE_HOUR);
        Token token = new Token().setUserId(id)
                .setToken(tokenValue).setUpdateTime(updateTime).setExpireTime(expiresTime);
        redisTemplate.boundValueOps(tokenValue).set(id, Constants.TOKEN_EXPIRE_HOUR, TimeUnit.HOURS);
        return token;
    }

    public boolean check(Token token) {
        if (token == null) {
            return false;
        }
        String id = redisTemplate.boundValueOps(token.getToken()).get();
        return token.getUserId() != null && id.equals(token.getUserId());
    }

    public void delete(String tokenValue) {
        if (!StringUtils.isEmpty(tokenValue)) {
            redisTemplate.delete(tokenValue);
        }
    }

    public String getId(String tokenValue) {
        if (!StringUtils.isEmpty(tokenValue)) {
            return redisTemplate.boundValueOps(tokenValue).get();
        }
        return null;
    }


}

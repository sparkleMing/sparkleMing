package com.sumixer.ys.api.core;

import java.time.LocalDateTime;

/**
 * @author : coderWu
 * @date : Created on 18:45 2018/6/12
 */
public class Token {

    private String userId;
    private String token;
    private LocalDateTime expireTime;
    private LocalDateTime updateTime;

    public String getUserId() {
        return userId;
    }

    public Token setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getToken() {
        return token;
    }

    public Token setToken(String token) {
        this.token = token;
        return this;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public Token setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Token setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}

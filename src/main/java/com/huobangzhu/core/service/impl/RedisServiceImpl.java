package com.huobangzhu.core.service.impl;

import com.huobangzhu.core.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author: lxl
 */
@Service
public class RedisServiceImpl implements RedisService{

    @Autowired
    private RedisTemplate redisTemplate;

    private byte[] rawKey;

    private BoundListOperations<String, String> listOperations;//noblocking

    @Override
    public void pushFromHead(String key, String value){
        bindKeys(key);
        listOperations.leftPush(value);
    }

    @Override
    public void pushFromTail(String key, String value){
        bindKeys(key);
        listOperations.rightPush(value);
    }

    @Override
    public String removeFromHead(String key){
        bindKeys(key);
        return listOperations.leftPop();
    }

    @Override
    public String removeFromTail(String key){
        bindKeys(key);
        return listOperations.rightPop();
    }

    @Override
    public Long getKeySize(String key){
        bindKeys(key);
        return listOperations.size();
    }

    private void bindKeys(String key){
        rawKey = redisTemplate.getKeySerializer().serialize(key);
        listOperations = redisTemplate.boundListOps(key);
    }
}

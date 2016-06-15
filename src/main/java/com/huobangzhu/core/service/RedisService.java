package com.huobangzhu.core.service;

/**
 * @author: lxl
 */
public interface RedisService {

    void pushFromHead(String key, String value);

    void pushFromTail(String key, String value);

    String removeFromHead(String key);

    String removeFromTail(String key);

    Long getKeySize(String key);

}

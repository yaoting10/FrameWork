package com.huobangzhu.foundation.model;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Map;

/**
 * @author: lxp
 * Date: 2015/5/5 21:48
 * 66cf-v2
 */
@Getter
public class RedirectModel{

    private String url;

    private Map<String, Object> params = Maps.newHashMap();

    private Map<String, Object> attributes = Maps.newHashMap();

    public RedirectModel(String url){
        this.url = url;
    }

    public static RedirectModel of(String url){
        return new RedirectModel(url);
    }

    public RedirectModel param(String key, Object value){
        this.params.put(key, value);
        return this;
    }

    public RedirectModel attributes(String key, Object value){
        this.attributes.put(key, value);
        return this;
    }
}

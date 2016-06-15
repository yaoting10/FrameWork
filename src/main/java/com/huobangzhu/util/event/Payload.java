package com.huobangzhu.util.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * User: liuchang
 * Date: 15/3/24
 */
@Getter
@Setter
public class Payload {
    @JsonProperty("t")
    protected int type;
    @JsonProperty("ps")
    protected Map<String, String> params;

    @JsonCreator
    public Payload(
            @JsonProperty(value = "t", required = true) int type,
            @JsonProperty("ps") Map<String, String> params) {
        this.type = type;
        this.params = params;
    }
}

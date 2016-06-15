package com.huobangzhu.util.event;

import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * User: liuchang
 * Date: 15/3/24
 */
@Getter
@Setter
public class Body {
    @JsonProperty("t")
    private long timestamp = TimeUtils.currentMillis();
    @JsonProperty("p")
    private Payload payload;

    @JsonCreator
    public Body(@JsonProperty(value = "p", required = true) Payload payload) {
        this.payload = payload;
    }
}

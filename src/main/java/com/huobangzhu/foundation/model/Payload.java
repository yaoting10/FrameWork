package com.huobangzhu.foundation.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author: Tim.Yao
 */
@Setter
@Getter
public class Payload{
    @JsonProperty("t")
    private long timestamp;

    @JsonProperty("c")
    private Object content;

    @JsonCreator
    public Payload(@NotNull @JsonProperty("t") long timestamp,
                   @JsonProperty("c") Object content) {
        this.timestamp = timestamp;
        this.content = content;
    }
}

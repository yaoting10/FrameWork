package com.huobangzhu.foundation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author: Tim.Yao
 */

@Data
@Embeddable
public class AuthItem implements Serializable{

    @ApiModelProperty(value = "Data", required = true)
    @JsonProperty(value = "d")
    private String data;

    @ApiModelProperty(value = "Authenticated", required = true)
    @JsonProperty(value = "at")
    private boolean authenticated;

    @JsonIgnore
    private String token;
}

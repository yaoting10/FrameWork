package com.huobangzhu.api.v1.security;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huobangzhu.util.ModelUtils;
import com.huobangzhu.util.RespondableException;
import com.huobangzhu.foundation.model.Token;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import static com.huobangzhu.foundation.model.ErrorCode.TOKEN_INVALID;
import static java.lang.String.format;
import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;
import static org.apache.commons.lang.StringUtils.*;

/**
 * @author Tim.Yao
 */
@Getter
@Setter
@EqualsAndHashCode
public class SessionToken implements Token<Long> {

    private static final String SEPARATOR_CHARS = "-";

    private static final String PATTERN = format("%s%s%s%s%s", "%s",
            SEPARATOR_CHARS, "%s", SEPARATOR_CHARS, "%s");

    @JsonIgnore
    @Id
    private long userId;

    @JsonIgnore
    private long refreshed;

    @JsonIgnore
    private String hex;

    @Override
    @JsonIgnore
    public Long getKey(){
        return userId;
    }

    public SessionToken(long userId){
        super();
        this.userId = userId;
        this.refreshed = ModelUtils.currentMillis();
        hex = sha1Hex(String.valueOf(refreshed));
    }

    @PersistenceConstructor
    public SessionToken(long userId, long refreshed, @NotBlank String hex){
        super();
        this.userId = userId;
        this.refreshed = refreshed;
        this.hex = hex;
    }

    @JsonCreator
    public static SessionToken valueOf(
            @NotBlank @JsonProperty("token") String serializedToken){
        String parts[] = split(serializedToken, SEPARATOR_CHARS);
        if(null == parts || 3 != parts.length || isBlank(parts[0])
                || !isNumeric(parts[0]) || isBlank(parts[1])
                || !isNumeric(parts[1]) || isBlank(parts[2]))
            throw new RespondableException(TOKEN_INVALID);
        return new SessionToken(Long.valueOf(parts[0]), Long.valueOf(parts[1]), parts[2]);
    }

    @Override
    @ApiModelProperty(value = "Token", required = true)
    @JsonProperty("t")
    public String toString(){
        return format(PATTERN, userId, refreshed, hex);
    }

}

package com.huobangzhu.api.v1.security;

import com.huobangzhu.foundation.model.Token;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author Tim.Yao
 */
@Component("sessionTokenValidator")
public class SessionTokenValidator{

    public boolean validate(@NotNull Token<Long> token,
                            @NotNull Token<Long> cachedToken){
        return token.equals(cachedToken);
    }
}
package com.huobangzhu.api.v1.security;

import com.google.common.collect.Maps;
import com.huobangzhu.foundation.model.Token;
import com.huobangzhu.foundation.model.TokenCache;
import com.huobangzhu.foundation.model.TokenFactory;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author Tim.Yao
 * 
 */
public class SessionTokenFactory implements TokenFactory<Long> {

    /**
     * TODO Introduces third cache, such as MongoDB.
     */
	private TokenCache<Long> cache = new TokenCache<Long>(){

        private Map<Long, Token<Long>> tokens = Maps.newConcurrentMap();

        @Override
        public Token<Long> get(Long key){
            return tokens.get(key);
        }

        @Override
        public Token<Long> put(Token<Long> token){
            return tokens.put(token.getKey(), token);
        }

        @Override
        public boolean remove(Long key){
            return null != tokens.remove(key);
        }
    };

	@Override
	public Token<Long> createToken(@NotNull Long key) {
        SessionToken token = new SessionToken(key);
        cache.put(token);
        return token;
	}

	@Override
	public Token<Long> get(@NotNull Long key) {
		return cache.get(key);
	}

	@Override
	public boolean remove(@NotNull Long key) {
		return cache.remove(key);
	}

}

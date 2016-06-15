package com.huobangzhu.foundation.model;

import java.io.Serializable;

/**
 * 
 * @author Tim.Yao
 * 
 */
public interface TokenCache<K extends Serializable> {

	Token<K> get(K key);

	Token<K> put(Token<K> token);

	boolean remove(K key);
}
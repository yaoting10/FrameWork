package com.huobangzhu.foundation.model;

import java.io.Serializable;

/**
 * 
 * @author Tim.Yao
 *
 */
public interface TokenFactory<K extends Serializable> {

	Token<K> createToken(K key);

	Token<K> get(K key);

	boolean remove(K key);

}
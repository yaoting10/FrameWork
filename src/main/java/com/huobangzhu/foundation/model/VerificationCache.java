package com.huobangzhu.foundation.model;

import java.io.Serializable;

/**
 * @author: Tim.Yao
 */
public interface VerificationCache<K extends Serializable, C extends Serializable, T extends Serializable>{

    C produceCode(K key);

    boolean consumeCode(K key, C code);

    T produceTicket(K key, C code);

    boolean consumeTicket(K key, T ticket);

    K guessKey(T ticket);
}

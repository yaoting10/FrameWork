package com.huobangzhu.foundation.model;

import java.io.Serializable;

/**
 * @author Tim.Yao
 *
 */
public interface Token<K extends Serializable> {

	K getKey();
}

package com.huobangzhu.foundation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * @author: Tim.Yao
 */
@MappedSuperclass
@ToString(callSuper = true, exclude = {"version"})
@EqualsAndHashCode(callSuper = true, exclude = {"version"})
public abstract class OptimisticLockDomain extends Domain<Long>{

    @JsonIgnore
    @Version
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private long version;
}

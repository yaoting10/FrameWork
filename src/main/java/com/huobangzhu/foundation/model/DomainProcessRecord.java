package com.huobangzhu.foundation.model;

/**
 * @author: Tim.Yao
 */

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Getter
@ToString(callSuper = true, exclude = {"process", "operator"})
@EqualsAndHashCode(callSuper = true, exclude = {"process", "operator"})
public abstract class DomainProcessRecord<R extends DomainProcessRecord<R, P, S>,
        P extends DomainProcess,
        S extends Enum<S>> extends Record<Long>{

    @NotNull
    @Enumerated(EnumType.STRING)
    private S status;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "process_id", nullable = false, updatable = false)
    private P process;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "operator_id", nullable = false, updatable = false)
    private Customer operator;

    public R record(@NotNull S status, @NotNull P process, @NotNull Customer operator){
        this.status = status;
        this.process = process;
        this.operator = operator;
        return (R) this;
    }
}

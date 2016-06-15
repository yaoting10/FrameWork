package com.huobangzhu.foundation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: Tim.Yao
 */

@MappedSuperclass
@Getter
@ToString(callSuper = true, exclude = {"domain", "latestOperator", "records"})
@EqualsAndHashCode(callSuper = true, exclude = {"domain", "latestOperator", "records"})
public abstract class DomainProcess<P extends DomainProcess<P, D, R, S>,
        D extends Domain,
        R extends DomainProcessRecord,
        S extends Enum<S>> extends OptimisticLockDomain{

    @ApiModelProperty(value = "DomainProcess status", dataType = "enum", required = true)
    @JsonProperty("s")
    @NotNull
    @Setter(AccessLevel.PROTECTED)
    @Enumerated(EnumType.STRING)
    private S status;

    @JsonIgnore
    @NotNull
    @Setter(AccessLevel.PROTECTED)
    @OneToOne(optional = false)
    @JoinColumn(name = "domain_id", nullable = false, updatable = false)
    private D domain;

    @JsonIgnore
    @NotNull
    @Setter(AccessLevel.PROTECTED)
    @ManyToOne(optional = false)
    @JoinColumn(name = "latest_operator_id", nullable = false)
    private Customer latestOperator;

    @JsonIgnore
    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("created")
    private List<R> records = Lists.newArrayList();

    public List<R> getRecords(){
        return ImmutableList.copyOf(records);
    }

    protected abstract R createRecord();

    @SuppressWarnings("unchecked")
    protected R state(S status, Customer operator){
        this.status = status;
        this.latestOperator = operator;
        R r = (R) createRecord().record(status, this, operator);
        records.add(r);
        updateLastModified();
        return r;
    }
}

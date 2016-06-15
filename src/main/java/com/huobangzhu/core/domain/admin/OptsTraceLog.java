package com.huobangzhu.core.domain.admin;

import com.dsecet.foundation.model.Record;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lxl on 15-06-15.
 */
@Table(name = "admin_opts_track_log")
@Entity
@Data
public class OptsTraceLog extends Record<Long>{

    @NotBlank
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "real_name", length = 32)
    private String realName;

    @Column(length = 64)
    private String operation;

    @Column
    private String params;

    @Column(nullable = false)
    private boolean succeed = true;

    @Column
    private String exception;

}

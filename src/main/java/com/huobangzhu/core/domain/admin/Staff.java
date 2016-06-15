package com.huobangzhu.core.domain.admin;

import com.huobangzhu.foundation.model.Customer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table(name = "admin_staff")
@Getter
@Setter
@ToString(callSuper = true, exclude = {"platform"})
@EqualsAndHashCode(callSuper = true, exclude = {"platform"})
public class Staff extends Customer{

    private static final String SEPARATOR = ",";

    @Column(name = "is_root", nullable = false, updatable = false)
    private boolean isRoot;

    @Column(length = 255, nullable = false)
    private String roles;

    public Staff register(@NotBlank String username, @NotBlank String password) {
        super.register(username, password);
        return this;
    }

    @Transient
    public List<String> getRolesList(){
        String roles = getRoles();
        return StringUtils.isBlank(roles) ? Collections.emptyList() : Arrays.asList(roles.split(SEPARATOR, -1));
    }

}

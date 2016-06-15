package com.huobangzhu.core.service.admin.impl;

import com.dsecet.core.domain.admin.Staff;
import com.dsecet.core.repository.admin.StaffListRepository;
import com.dsecet.core.repository.admin.StaffListRepositoryImpl;
import com.dsecet.core.repository.admin.StaffRepository;
import com.dsecet.foundation.exception.RespondableException;
import com.dsecet.foundation.model.ErrorCode;
import com.dsecet.foundation.model.query.StaffVo;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Set;

/**
 * Created by lxl on 14-12-12.
 */
@Service
@Transactional
public class StaffService implements com.dsecet.core.service.admin.StaffService{

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StaffListRepository staffListRepository;

    @Override
    @Transactional
    public Staff save(@NotNull Staff staff){
        if(null == staff.getId()){
            if(usernameExists(staff.getUsername()))
                throw new RespondableException(ErrorCode.USER_DUPLICATE);
            return staffRepository.save(staff);
        }
        staff.updateLastModified();
        ;
        return staffRepository.save(staff);
    }

    @Override
    @Transactional(readOnly = true)
    public Staff get(@NotNull Long id){
        return staffRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean usernameExists(@NotNull String username){
        return staffRepository.findByAccountUsername(username) == null ? false : true;
    }

    @Override
    @Transactional(readOnly = true)
    public Staff getByUserName(@NotNull String username){
        return staffRepository.findByAccountUsername(username);
    }

    @Override
    public Page<Staff> getStaffByPage(@NotNull Pageable pageable){
        return staffRepository.findAll(pageable);
    }

    @Override
    public Page<Staff> getByStaff(@NotNull Staff staff, Pageable pageable){
        if(staff == null){
            return new PageImpl<>(Collections.emptyList(), pageable, 0L);
        }
        Set<String> uniqueFields = Sets.newHashSet(staff.getUsername());
        return getByRelatedUniqueFields(uniqueFields, pageable);
    }

    @Transactional(readOnly = true)
    private Page<Staff> getByRelatedUniqueFields(@NotNull Set<String> uniqueFields, Pageable pageable){
        return (!uniqueFields.isEmpty()) ?
                staffRepository.findByRelatedUniqueFields(uniqueFields, pageable)
                : new PageImpl<>(Collections.emptyList(), pageable, 0L);
    }

    @Override
    public Page<Staff> queryByConditions(StaffVo vo, Pageable pageable){
        StaffListRepositoryImpl.StaffQueryBuilder builder = StaffListRepositoryImpl.StaffQueryBuilder.newBuilder();
        builder.usernameCondiftion(vo.getUsername())
               .activeCondiftion(vo.getActive());
        return staffListRepository.findStaffList(builder,pageable);
    }
}

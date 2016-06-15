package com.huobangzhu.core.service.admin.impl;

import com.dsecet.core.domain.system.SystemDefaultProperty;
import com.dsecet.core.domain.system.cooperation.Platform;
import com.dsecet.core.domain.system.rating.Credit;
import com.dsecet.core.domain.user.Guarantor;
import com.dsecet.core.repository.admin.SystemDefaultPropertyRepository;
import com.dsecet.core.service.CreditService;
import com.dsecet.core.service.GuarantorService;
import com.dsecet.core.service.PlatformService;
import com.dsecet.core.service.admin.SystemDefaultPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with 66cf-v2
 * User : Ting.Yao
 * Date : 2015/3/2.
 */
@Service
@Transactional
public class SystemDefaultPropertyServiceImpl implements SystemDefaultPropertyService{

    @Autowired
    private SystemDefaultPropertyRepository systemDefaultPropertyRepository;

    @Autowired
    private GuarantorService guarantorService;

    @Autowired
    private PlatformService platformService;

    @Autowired
    private CreditService creditService;

    @Override
    public SystemDefaultProperty save(SystemDefaultProperty systemDefaultProperty, Long platformId, Long guarantorId, Long creditId) {
        Guarantor guarantor = guarantorService.get(guarantorId);
        Platform platform = platformService.get(platformId);
        Credit credit = creditService.get(creditId);
        systemDefaultProperty.setGuarantor(guarantor);
        systemDefaultProperty.setPlatform(platform);
        systemDefaultProperty.setDefaultCredit(credit);
        return systemDefaultPropertyRepository.save(systemDefaultProperty);
    }

    @Override
    public SystemDefaultProperty delete(Long id) {
        return null;
    }

    @Override
    public SystemDefaultProperty update(SystemDefaultProperty systemDefaultProperty, Long platformId, Long guarantorId, Long creditId) {
        if (null == systemDefaultProperty.getId() || systemDefaultProperty.getId() == 0L) {
            return null;
        }
        systemDefaultProperty.updateLastModified();
        return save(systemDefaultProperty, platformId, guarantorId, creditId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemDefaultProperty> findAll() {
        return systemDefaultPropertyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public SystemDefaultProperty getUsed() {
        return systemDefaultPropertyRepository.findByActive(true);
    }

    @Override
    public SystemDefaultProperty get(Long id) {
        return systemDefaultPropertyRepository.getOne(id);
    }
}

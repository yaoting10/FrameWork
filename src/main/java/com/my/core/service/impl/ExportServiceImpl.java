package com.my.core.service.impl;

import com.my.Utils.csveed.customer.EmbeddableCsvClientImpl;
import com.my.core.service.ExportService;
import com.my.website.controller.vo.UserExportVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Reader;
import java.util.List;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/17.
 */
@Service
@Transactional
public class ExportServiceImpl implements ExportService{

    @Override
    public List<UserExportVo> read(Reader reader) {
        EmbeddableCsvClientImpl<UserExportVo> csvClient = new EmbeddableCsvClientImpl<>(reader, UserExportVo.class);
        List<UserExportVo> userList = csvClient.readBeans();
        return userList;
    }
}

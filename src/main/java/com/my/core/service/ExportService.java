package com.my.core.service;

import com.my.core.domain.User;
import com.my.website.controller.vo.UserExportVo;

import java.io.Reader;
import java.util.List;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/17.
 */
public interface ExportService {

    List<UserExportVo> read(Reader reader);
}

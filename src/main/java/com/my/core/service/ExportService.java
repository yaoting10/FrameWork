package com.my.core.service;

import com.my.core.domain.WayBill;
import com.my.website.controller.vo.WayBillExportVo;
import com.my.website.controller.vo.WayBillStatisticsVo;

import java.io.OutputStream;
import java.util.List;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/25.
 */
public interface ExportService {
    List<WayBillExportVo> exportWayBill(Long from, Long to , OutputStream out)  throws Exception;

    List<WayBillStatisticsVo> exportStatisticsVo(Long from, Long to , OutputStream out)  throws Exception;
}

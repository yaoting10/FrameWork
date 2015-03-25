package com.my.core.service.impl;

import com.my.Utils.PoiUtill;
import com.my.core.domain.WayBill;
import com.my.core.service.ExportService;
import com.my.core.service.WayBillService;
import com.my.website.controller.vo.WayBillExportVo;
import com.my.website.controller.vo.WayBillStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.util.List;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/25.
 */
@Service
@Transactional
public class ExportServiceImpl implements ExportService{

    @Autowired
    private WayBillService wayBillService;

    @Override
    public List<WayBillExportVo> exportWayBill(Long from, Long to, OutputStream out) throws Exception{
        List<WayBillExportVo> wayBillList = wayBillService.exportWayBill(from, to);
        String [] names = new String[]{"运单号","业务员编号","重量", "地址", "区域", "时间","方式", "操作费", "续重费", "中转费"};
        PoiUtill<WayBillExportVo> excel = new PoiUtill<>();
        excel.writeExcel(out, names, wayBillList, "运单");
        return wayBillList;
    }

    @Override
    public List<WayBillStatisticsVo> exportStatisticsVo(Long from, Long to, OutputStream out) throws Exception {
        List<WayBillStatisticsVo> wayBillList = wayBillService.statisticWayBill(from, to);
        List<WayBillStatisticsVo> companyList = wayBillService.statisticForCompany(from, to);
        companyList.addAll(wayBillList);
        String [] names = new String[]{"业务员编号", "运单数量", "中转费总额", "操作费总额", "续重费总额"};
        PoiUtill<WayBillStatisticsVo> excel = new PoiUtill<>();
        excel.writeExcel(out, names, companyList, "运单");
        return companyList;
    }
}

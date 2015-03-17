package com.my.website.controller.vo;

import lombok.Data;
import org.csveed.annotations.CsvCell;

import javax.persistence.Column;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/17.
 */
@Data
public class UserExportVo {

    @CsvCell(columnName = "编号")
    private String userNumber;

    @CsvCell(columnName = "姓名")
    private String userName;

}

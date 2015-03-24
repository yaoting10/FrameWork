package com.my.website.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.my.Utils.PoiUtill;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/poiTest")
public class PoiTest {
	@RequestMapping(value = "/readXls",method= RequestMethod.POST)
    public List readXls(@RequestParam("file") MultipartFile file,int cell) throws IOException {
        InputStream is = file.getInputStream();
        Workbook hssfWorkbook = null;
        try {
            hssfWorkbook = WorkbookFactory.create(is);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        List list = new ArrayList();

        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {

            Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
             // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {

                Row hssfRow = hssfSheet.getRow(rowNum);

                if (hssfRow == null) {

                    continue;

                }

                List cellList = new  ArrayList();
                // 循环列Cell

                // 0学号 1姓名 2学院 3课程名 4 成绩
                for (int i=0;i<cell;i++){
                    Cell xh = hssfRow.getCell(i);
                    if (xh == null) {
                        cellList.add(xh);
                        continue;

                    }
                }

                list.add(cellList);

            }

        }

        return list;

    }
}

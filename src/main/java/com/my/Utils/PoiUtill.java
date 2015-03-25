package com.my.Utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @Description: poi工具类
 * @Company: tq
 * @create Author: 黄新强
 * @create Date: 2015-3-21 下午02:31:06
 * @version 1.0
 */
public class PoiUtill<T> {

    /**
     * 读写excel
     * @param file excel文件
     * @param cell excele列数
     * @return List里面包含List
     * @throws IOException
     */
    public static List readXls(MultipartFile file,int cell) throws IOException {
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
                for (int i=0;i<cell;i++){
                    Cell xh = hssfRow.getCell(i);
                    if (xh == null) {
                        continue;
                    }
                    cellList.add(xh);
                }
                list.add(cellList);
            }
        }
        return list;
    }

    /**
     *
     * @param names 列名
     * @param list 内容list里面放list
     * @param excelName excel名字
     * @throws Exception
     */
    public void writeExcel(OutputStream out,String[] names,List<T> list,String excelName) throws Exception {
        // 创建Excel文档
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet(excelName);

        // sheet 对应一个工作页
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HSSFRow row = sheet.createRow(0); // 下标为0的行开始
        Cell[] firstcell = new Cell[names.length];
        for (int j = 0; j < names.length; j++) {
            firstcell[j] = row.createCell(j);
            firstcell[j].setCellValue(new XSSFRichTextString(names[j]));
        }

        Iterator<T> it = list.iterator();

        int index = 0;

        while (it.hasNext()) {

            index++;

            row = sheet.createRow(index);

            T t = (T) it.next();

            //利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值

            Field[] fields = t.getClass().getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {

                HSSFCell cell = row.createCell(i);

                Field field = fields[i];

                String fieldName = field.getName();

                String getMethodName = "get"

                        + fieldName.substring(0, 1).toUpperCase()

                        + fieldName.substring(1);

                try {

                    Class tCls = t.getClass();

                    Method getMethod = tCls.getMethod(getMethodName,

                            new Class[]{});

                    Object value = getMethod.invoke(t, new Object[]{});

                    //判断值的类型后进行强制类型转换

                    String textValue = null;

                    textValue = value.toString();

                    HSSFRichTextString richString = new HSSFRichTextString(textValue);

                    cell.setCellValue(richString);

        /*for (int i = 0; i < list.size(); i++) {
            // 创建一行
            Row row = sheet.createRow(i + 1);
            // 得到要插入的每一条记录
            for (int colu = 0; colu <list.size(); colu++) {
                // 在一行内循环
                for (int cell=0;cell<names.length;cell++){
                    Cell xh = row.createCell(cell);
                    xh.setCellValue(list.get(i).get(cell)+"");
                }
            }
        }*/
                    // 创建文件输出流，准备输出电子表格
//        OutputStream out = new FileOutputStream("d:/eccsExcel/"+excelName+".xls");
                    workbook.write(out);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    out.close();
                }
            }
        }
    }
}

package com.hongwei.common.util;

import com.hongwei.common.bean.ExcelEntity;
import com.hongwei.common.interfaces.OutputExcel;
import org.apache.poi.hssf.record.cf.BorderFormatting;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * excel导出工具类(调用构造函数即可)
 */
public class OutputExcelUtil {

    private XSSFWorkbook workbook = null;

    private XSSFSheet sheet = null;

    private List<ExcelEntity> excels = new ArrayList<ExcelEntity>();

    /**
     * excel导出构造函数
     * @param title 标题
     * @param list 数据集合
     * @param c JavaBean.Calss
     */
    public OutputExcelUtil(String fileName,String title, List list, Class c){
        try {
            workbook = new XSSFWorkbook();  //创建Excel工作簿
            sheet = workbook.createSheet(); //创建一个工作表sheet
            sheet.setDefaultRowHeight((short)(15.625*26));  //设置行高
            this.createFirstRow(c,title);   //创建标题行
            this.setCells(list);    //拼写数据
            this.outputExcel(fileName); //导出
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //设置单元格样式
    private XSSFCellStyle getCallStyle(){
        XSSFCellStyle style = workbook.createCellStyle();   //初始化单元格样式
        style.setBorderBottom(BorderFormatting.BORDER_THIN);    //下边框
        style.setBorderRight(BorderFormatting.BORDER_THIN);     //右边框
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);//水平
        return style;
    }

    //创建标题行
    private void createFirstRow(Class c,String title){
        //获取样式
        XSSFCellStyle style = getCallStyle();
        //创建标题
        XSSFRow titleRow = sheet.createRow(0);
        XSSFCell titleCell = titleRow.createCell(0);
        titleCell.setCellStyle(style);
        titleCell.setCellValue(title);

        //创建属性标题行
        XSSFRow row = sheet.createRow(1);

        //获取注解类的导出标题，并排序
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            if(field.isAnnotationPresent(OutputExcel.class)){
                OutputExcel outputExcel = field.getAnnotation(OutputExcel.class);
                excels.add(new ExcelEntity(outputExcel,field.getName()));
            }
        }
        Collections.sort(excels,(s1, s2) -> s1.getSort().compareTo(s2.getSort()));  //排序
        sheet.addMergedRegion(new  CellRangeAddress(0,0,0,excels.size()-1));    //主标题合并单元格
        //插入标题
        ExcelEntity excel = null;
        XSSFCell cell = null;
        for(int i = 0; i < excels.size(); i++){
            excel = excels.get(i);  //获取excel数据
            cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(excel.getTitle());
        }
    }

    //拼写数据单元格
    private void setCells(List<Object> list) throws Exception {
        XSSFCellStyle style = this.getCallStyle();  //获取单元格样式
        XSSFRow row = null;
        XSSFCell cell = null;
        int rowIndex = 2;   //初始化行数下标
        for(Object obj:list){   //遍历导出的对象集合
            row = sheet.createRow(rowIndex);    //创建行
            for (int i = 0;i < excels.size(); i++){ //遍历单元格
                cell = row.createCell(i);   //创建单元格
                cell.setCellStyle(style);   //设置单元格样式
                Method method = obj.getClass().getMethod(excels.get(i).getFieldName()); //获取属性的get方法
                Object invoke = method.invoke(obj); //获取属性get方法的返回值
                cell.setCellValue(invoke==null?"":invoke.toString());   //单元格赋值
            }
            rowIndex++;
        }
    }

    //导出excel
    private void outputExcel(String fileName) throws IOException {
        HttpServletResponse response = HttpUtil.getResponse();
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + Encodes.urlEncode(fileName));
        workbook.write(response.getOutputStream());
    }
}

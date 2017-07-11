package com.hongwei.common.util;

import com.hongwei.common.bean.ExcelEntity;
import com.hongwei.common.interfaces.ImportExcel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * excel导入工具类
 */
public class ImportExcelUtil {

    /** excel数据集合 */
    private List list = null;

    public ImportExcelUtil(MultipartFile file,int rowIndex,Class c){
        try {
            POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());    //写入流
            HSSFWorkbook workbook = new HSSFWorkbook(fs);   //创建workbook
            HSSFSheet sheet = workbook.getSheetAt(0);   //获取sheet页
            int rowNum = sheet.getPhysicalNumberOfRows();   //获取总行数
            HSSFRow row = null;
            HSSFCell cell = null;
            List<ExcelEntity> entitys = getExcelEntitys(c); //获取类注解信息
            this.list = new ArrayList();
            for (int i = rowIndex; i < rowNum ; i++) {
                row = sheet.getRow(i);  //获取行
                Object objCla = c.newInstance();    //实例化对象
                for (ExcelEntity entity : entitys) {
                    cell = row.getCell(Integer.valueOf(entity.getSort()));  //根据注释下标获得对应的单元格
                    Method method = c.getMethod(entity.getFieldName(),entity.getClassType()); //获取属性的set方法
                    System.out.println(cell.getCellTypeEnum());
                    //获取属性set方法的返回值
                    if("STRING".equals(cell.getCellTypeEnum().toString())){
                        Object invoke = method.invoke(objCla,cell.getStringCellValue());
                    }else if("NUMERIC".equals(cell.getCellTypeEnum().toString())){
                        Object invoke = method.invoke(objCla,cell.getNumericCellValue()+"");
                    }else if("BOOLEAN".equals(cell.getCellTypeEnum().toString())){
                        Object invoke = method.invoke(objCla,cell.getBooleanCellValue()+"");
                    }else if("DATE".equals(cell.getCellTypeEnum().toString())){
                        Object invoke = method.invoke(objCla,DateUtils.format(cell.getDateCellValue()));
                    }
                }
                this.list.add(objCla);   //将excel获取的数据添加到集合当中。
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 获取类注解信息 */
    private List<ExcelEntity> getExcelEntitys(Class c){
        List<ExcelEntity> list = new ArrayList<>();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            if(field.isAnnotationPresent(ImportExcel.class)){
                ImportExcel importExcel = field.getAnnotation(ImportExcel.class);
                list.add(new ExcelEntity(importExcel,field.getName(),field.getType()));
            }
        }
        return list;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}

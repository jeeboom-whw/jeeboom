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
 * Created by Administrator on 2017/7/11 0011.
 */
public class ImportExcelUtil {

    private List list = null;

    public ImportExcelUtil(MultipartFile file,int rowIndex,Class c){
        try {
            POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
            HSSFWorkbook workbook = new HSSFWorkbook(fs);
            HSSFSheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getPhysicalNumberOfRows();
            HSSFRow row = null;
            HSSFCell cell = null;
            List<ExcelEntity> entitys = getExcelEntitys(c);
            list = new ArrayList();
            for (int i = rowIndex; i < rowNum ; i++) {
                row = sheet.getRow(i);
                Object objCla = c.newInstance();
                for (ExcelEntity entity : entitys) {
                    cell = row.getCell(Integer.valueOf(entity.getSort()));
                    Method method = c.getMethod(entity.getFieldName(),entity.getClassType()); //获取属性的set方法
                    Object invoke = method.invoke(objCla,cell.getStringCellValue()); //获取属性set方法的返回值
                }
                list.add(objCla);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

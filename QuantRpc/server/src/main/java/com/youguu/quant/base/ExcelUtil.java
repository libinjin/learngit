package com.youguu.quant.base;

import com.youguu.quant.signal.pojo.ProfitCurve;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by SomeBody on 2016/9/19.
 */
public class ExcelUtil {
    public static void writeExcel(List<ProfitCurve> profitCurveList){
        try{
            String path = "e://curve.xls";
            File newFile = new File(path);
            newFile.createNewFile();
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet();

            for(int i = 0 ; i < profitCurveList.size() ; i ++){
                ProfitCurve profitCurve = profitCurveList.get(i);
                HSSFRow row = sheet.createRow(i);
                HSSFCell cell_1 = row.createCell(0);
                HSSFCell cell_2 = row.createCell(1);
                HSSFCell cell_3 = row.createCell(2);
                cell_1.setCellValue(profitCurve.getDate());
                cell_2.setCellValue(profitCurve.getHsProfitRate());
                cell_3.setCellValue(profitCurve.getProfitRate());
            }

            OutputStream out = new FileOutputStream(path);
            wb.write(out);// 写入File
            out.flush();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

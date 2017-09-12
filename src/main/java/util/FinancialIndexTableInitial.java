package util;

import data.SqlManager;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import po.IndexPo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by loohaze on 2017/9/12 上午10:03
 */
public class FinancialIndexTableInitial {

    static SqlManager sqlManager = SqlManager.getSqlManager();

    public static void loadIndexExcel() throws IOException{
        List temp = new ArrayList();

        sqlManager.getConnection();

        FileInputStream fileIn = new FileInputStream(FinancialIndexTableInitial.class.getClassLoader().getResource("IndexInfo.xls").getFile());
        Workbook workbook = new HSSFWorkbook(fileIn);

        for (int i = 0; i < workbook.getNumberOfSheets(); i++){
            if (i == workbook.getNumberOfSheets()-1){
                break;
            }
            Sheet sht = workbook.getSheetAt(i);
            for (Row r : sht){
                IndexPo po = new IndexPo();
                po.setIndustry(sht.getSheetName());
                if (r.getRowNum() == 0 || r.getRowNum()==5 || r.getRowNum()==9 || r.getRowNum()==13){
                    continue;
                }
                try{
                    po.setIndex_name(r.getCell(0).getStringCellValue());
                    po.setExcellent_value(r.getCell(1).getNumericCellValue());
                    po.setFine_value(r.getCell(2).getNumericCellValue());
                    po.setAverage_value(r.getCell(3).getNumericCellValue());
                    po.setLow_value(r.getCell(4).getNumericCellValue());
                    po.setBad_value(r.getCell(5).getNumericCellValue());

                    insertIndexPO(po);
                }catch (java.lang.IllegalStateException e){
                    System.out.println(sht.getSheetName() +  " " + r.getRowNum());

                }

            }
        }
    }

    private static void insertIndexPO(IndexPo po){
        List<Object> params = new ArrayList<>();
        params.add(po.getIndustry());
        params.add(po.getIndex_name());
        params.add(po.getExcellent_value());
        params.add(po.getFine_value());
        params.add(po.getAverage_value());
        params.add(po.getLow_value());
        params.add(po.getBad_value());
        String sql = sqlManager.appendSQL("insert into financial_index values",params.size());
        sqlManager.executeUpdateByList(sql,params);
        sqlManager.commit();
    }

    public static void main(String[] args) throws IOException {
        FinancialIndexTableInitial.loadIndexExcel();
    }
}

package util;

/**
 * Created by zhangzy on 2017/8/16 下午10:22
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelOperate {

    public static void main(String[] args) {
        // 创建Excel表格
        createExcel(getVoucher(),"/Users/zhangzy/Downloads/vouchers.xls");

        // 读取Excel表格
        List<Voucher> list = readExcel("/Users/zhangzy/Downloads/vouchers.xls");
        System.out.println(list.toString());

    }

    /**
     * 初始化数据
     *
     * @return 数据
     */
    public static List<Voucher> getVoucher(){
        List<Voucher> list=new ArrayList<>();
        Voucher voucher1=new Voucher("2017-08-01","记-1","借款","1001",1000,0);
        Voucher voucher2=new Voucher("2017-08-01","记-1","借款","1002",0,1000);
        Voucher voucher3=new Voucher("2017-08-02","记-2","发工资","1001",2000,0);
        Voucher voucher4=new Voucher("2017-08-02","记-2","发工资","1003",0,2000);
        list.add(voucher1);
        list.add(voucher2);
        list.add(voucher3);
        list.add(voucher4);
        return list;
    }

    /**
     * 创建Excel
     *
     * @param list
     *            数据
     */
    public static void createExcel(List<Voucher> list,String filePath)  {
        // 创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表
        HSSFSheet sheet = workbook.createSheet("导出的凭证");
        // 添加表头行
        HSSFRow hssfRow = sheet.createRow(0);
        // 设置单元格格式居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 添加表头内容
        HSSFCell headCell = hssfRow.createCell(0);
        headCell.setCellValue("日期");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(1);
        headCell.setCellValue("凭证号");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(2);
        headCell.setCellValue("摘要");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(3);
        headCell.setCellValue("科目编码");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(4);
        headCell.setCellValue("借方本币");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(5);
        headCell.setCellValue("贷方本币");
        headCell.setCellStyle(cellStyle);




        // 添加数据内容
        for (int i = 0; i < list.size(); i++) {
            hssfRow = sheet.createRow((int) i + 1);
            Voucher voucher = list.get(i);

            // 创建单元格，并设置值
            HSSFCell cell = hssfRow.createCell(0);
            cell.setCellValue(voucher.getDate());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(1);
            cell.setCellValue(voucher.getVoucherId());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(2);
            cell.setCellValue(voucher.getAbstracts());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(3);
            cell.setCellValue(voucher.getSubjectId());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(4);
            cell.setCellValue(voucher.getDebitCurrency());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(5);
            cell.setCellValue(voucher.getCreditCurrency());
            cell.setCellStyle(cellStyle);

        }

        // 保存Excel文件
        try {
            OutputStream outputStream = new FileOutputStream(filePath);
            //"/Users/zhangzy/Downloads/students.xls"
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取Excel
     *
     * @return 数据集合
     */
    public static List<Voucher> readExcel(String filePath) {
        List<Voucher> list = new ArrayList<>();
        HSSFWorkbook workbook = null;

        try {
            // 读取Excel文件
            InputStream inputStream = new FileInputStream(filePath);
            //"/Users/zhangzy/Downloads/students.xls"
            workbook = new HSSFWorkbook(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 循环工作表
        for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = workbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }

                // 将单元格中的内容存入集合
                Voucher voucher=new Voucher();

                HSSFCell cell = hssfRow.getCell(0);
                if (cell == null) {
                    continue;
                }
                voucher.setDate(cell.getStringCellValue());

                cell = hssfRow.getCell(1);
                if (cell == null) {
                    continue;
                }
                voucher.setVoucherId(cell.getStringCellValue());

                cell = hssfRow.getCell(2);
                if (cell == null) {
                    continue;
                }
                voucher.setAbstracts(cell.getStringCellValue());

                cell=hssfRow.getCell(3);
                if(cell==null){
                    continue;
                }
                voucher.setSubjectId(cell.getStringCellValue());

                cell=hssfRow.getCell(4);
                if(cell==null){
                    continue;
                }
                voucher.setDebitCurrency(cell.getNumericCellValue());

                cell=hssfRow.getCell(5);
                if(cell==null){
                    continue;
                }
                voucher.setCreditCurrency(cell.getNumericCellValue());


                list.add(voucher);
            }
        }
        return list;
    }
}

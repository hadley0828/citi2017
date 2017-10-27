package util;

/**
 * Created by zhangzy on 2017/9/16 下午10:22
 */
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelOperate {

    public static void main(String[] args) {
//        // 创建Excel表格
//        createExcel(getVoucher(),"/Users/zhangzy/Downloads/vouchers.xls");
//
//        // 读取Excel表格
//        List<Voucher> list = readExcel("/Users/zhangzy/Downloads/vouchers.xls");
//        System.out.println(list.toString());

        for(double i=65.0;i<=85.0;i=i+0.5){
            readNewExcel("/Users/zhangzy/Downloads/附件一：已结束项目任务数据.xls","/Users/zhangzy/Downloads/"+String.valueOf(i)+"Train",i);

        }



//            excelSplitToThree("/Users/zhangzy/Downloads/附件一：已结束项目任务数据.xls","/Users/zhangzy/Downloads/firstArea","/Users/zhangzy/Downloads/secondArea","/Users/zhangzy/Downloads/thirdArea");

//        calculateMinAndParameter("/Users/zhangzy/Downloads/参数2.0.xls");
//        getAllLocation("/Users/zhangzy/Downloads/附件二：会员信息数据.xls");

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

    public static void calculateMinAndParameter(String filePath){

        HashMap<Integer,Double> firstMap=new HashMap<>();
        HashMap<Integer,Double> secondMap=new HashMap<>();
        HashMap<Integer,Double> thirdMap=new HashMap<>();

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

                int count=rowNum-1;

                //k1
                HSSFCell cell = hssfRow.getCell(0);
                if (cell == null) {
                    continue;
                }
                firstMap.put(count,cell.getNumericCellValue());


                //k2
                cell = hssfRow.getCell(1);
                if (cell == null) {
                    continue;
                }
                secondMap.put(count,cell.getNumericCellValue());


                //k3
                cell = hssfRow.getCell(2);
                if (cell == null) {
                    continue;
                }
                thirdMap.put(count,cell.getNumericCellValue());


            }
        }

        for(int count=0;count<firstMap.size();count++){
            System.out.println(firstMap.get(count)+" "+secondMap.get(count)+" "+thirdMap.get(count));
        }

        double total=1000.0;
        double bestX=0.0;
        double bestY=0.0;
        double bestZ=0.0;

        for(double x=2000;x<=4000;x=x+10){
            for(double y=0;y<=800;y=y+10){
                for(double z=63.0;z<=65.0;z=z+0.05){

                    double current=0;

                    for(int count=0;count<firstMap.size();count++){
                        double k1=firstMap.get(count);
                        double k2=secondMap.get(count);
                        double k3=thirdMap.get(count);

                        current=current+Math.pow(1-x/((k1-y)*(k2-z))-k3,2.0);

                    }

                    if(current<total){
                        total=current;
                        bestX=x;
                        bestY=y;
                        bestZ=z;
                    }

                    String oneResult=x+" "+y+" "+z+" "+current;
                    System.out.println(oneResult);
//                    method3("/Users/zhangzy/Downloads/result",oneResult);

                }
            }
        }

        System.out.println(bestX);
        System.out.println(bestY);
        System.out.println(bestZ);
        System.out.println(total);


    }


    public static void readNewExcel(String filePath,String writePath,double price) {
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
                String oneResult="";

                //纬度
                HSSFCell cell = hssfRow.getCell(1);
                if (cell == null) {
                    continue;
                }
                System.out.println(cell.getNumericCellValue());
                oneResult=oneResult+" "+"1:"+String.valueOf(cell.getNumericCellValue());

                //经度
                cell = hssfRow.getCell(2);
                if (cell == null) {
                    continue;
                }
                System.out.println(cell.getNumericCellValue());
                oneResult=oneResult+" "+"2:"+String.valueOf(cell.getNumericCellValue());

                //定价
                cell = hssfRow.getCell(3);
                if (cell == null) {
                    continue;
                }
                System.out.println(cell.getNumericCellValue());
                oneResult=oneResult+" "+"3:"+price;

                //任务的完成情况
                cell=hssfRow.getCell(4);
                if(cell==null){
                    continue;
                }
                oneResult=new Double(cell.getNumericCellValue()).intValue()+oneResult;

                System.out.println(oneResult);

//                if(rowNum<601){
//                    method3(writePath,oneResult);
//                }else{
//                    method3(newPath,oneResult);
//                }
                method3(writePath,oneResult);


            }
        }

    }

    public static void excelSplitToThree(String filePath,String onePath,String twoPath,String threePath) {
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
                String oneResult="";

                //纬度
                HSSFCell cell = hssfRow.getCell(1);
                if (cell == null) {
                    continue;
                }
                double latitude=cell.getNumericCellValue();

                oneResult=oneResult+" "+"1:"+String.valueOf(cell.getNumericCellValue());

                //经度
                cell = hssfRow.getCell(2);
                if (cell == null) {
                    continue;
                }
                double longitude=cell.getNumericCellValue();
                oneResult=oneResult+" "+"2:"+String.valueOf(cell.getNumericCellValue());

                //定价
                cell = hssfRow.getCell(3);
                if (cell == null) {
                    continue;
                }
                oneResult=oneResult+" "+"3:"+String.valueOf(cell.getNumericCellValue());

                //任务的完成情况
                cell=hssfRow.getCell(4);
                if(cell==null){
                    continue;
                }
                double over=cell.getNumericCellValue();
                oneResult=new Double(cell.getNumericCellValue()).intValue()+oneResult;

                cell=hssfRow.getCell(5);
                if(cell==null){
                    continue;
                }
                double oneClass=cell.getNumericCellValue();
                System.out.println(oneClass);
                System.out.println(rowNum);

                if(oneClass==1.0){
                    method3(onePath,oneResult);
                }else if(oneClass==2.0){
                    method3(twoPath,oneResult);
                }else if(oneClass==3.0){
                    method3(threePath,oneResult);
                }

//                if(longitude<113.63){
//                    method3(onePath,oneResult);
//                }else{
//                    if(latitude>22.86){
//                        method3(twoPath,oneResult);
//                        System.out.println(over);
//                    }else{
//                        method3(threePath,oneResult);
//                    }
//                }

            }
        }


    }



    public static void method3(String fileName, String content) {
        try {
// 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
// 文件长度，字节数
            long fileLength = randomFile.length();
// 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content+"\r\n");
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}



package businesslogic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import businesslogicservice.ProfitTableService;
import data.ProfitAndCashServiceImpl;
import dataservice.ProfitAndCashService;
import po.VoucherAmountPO;
import vo.ProfitTableVo;

/**
 * 
 * @author hyf
 *
 */
public class ProfitTableImpl implements ProfitTableService{
	
	ProfitAndCashService DATA;
	TableCalHelper helper;
	
	public ProfitTableImpl(){
		DATA=new ProfitAndCashServiceImpl();
		helper=new TableCalHelper();
	}

	public ProfitTableVo BusinessIncome_period(String time,String id) {
		List<VoucherAmountPO> list=DATA.getVourchersByPeriod(time, id);
		return BusinessIncome(list);
	}
	
	public ProfitTableVo BusinessIncome_year(String id,String time) {
		List<VoucherAmountPO> list=DATA.getVourchersByYear(time, id);
		return BusinessIncome(list);
	}
	
	private ProfitTableVo BusinessIncome(List<VoucherAmountPO> list){
		double business_income=0;//营业收入
		double temp1=helper.Cal(helper.getBySubject("5001", list));
		double temp2=helper.Cal(helper.getBySubject("5051", list));
		business_income=temp1+temp2;//主营业务收入+其他业务收入
		
		double business_costs=0;//营业成本
		temp1=helper.Cal2(helper.getBySubject("5401", list));
		temp2=helper.Cal2(helper.getBySubject("5451", list));
		business_costs=temp1+temp2;//主营业务成本+其他业务成本
		
		double[] Business_Taxes_and_Surcharges=new double[8];
		Business_Taxes_and_Surcharges[0]=helper.Cal2(helper.getBySubject("5403", list));//税金及附加
		Business_Taxes_and_Surcharges[1]=helper.CreditCal(helper.getBySubject("2221003", list));//应交消费税
		Business_Taxes_and_Surcharges[2]=helper.CreditCal(helper.getBySubject("2221004", list));//应交营业税
		Business_Taxes_and_Surcharges[3]=helper.CreditCal(helper.getBySubject("2221008", list));//应交城市维护建设税
		Business_Taxes_and_Surcharges[4]=helper.CreditCal(helper.getBySubject("2221005", list));//应交资源税
		Business_Taxes_and_Surcharges[5]=helper.CreditCal(helper.getBySubject("2221007", list));//应交土地增值税
		Business_Taxes_and_Surcharges[6]=helper.CreditCal(helper.getBySubject("2221010", list))+//应交城镇土地使用税
				helper.CreditCal(helper.getBySubject("2221011", list))+//应交车船使用税
				helper.CreditCal(helper.getBySubject("2221009", list))+//应交房产税
				helper.CreditCal(helper.getBySubject("2221017", list));//印花税
		Business_Taxes_and_Surcharges[7]=helper.CreditCal(helper.getBySubject("2221013", list))+//教育费附加
				helper.CreditCal(helper.getBySubject("2221014", list))+//地方教育费附加
				helper.CreditCal(helper.getBySubject("2221016", list))+//排污费
				helper.CreditCal(helper.getBySubject("2221015", list));//矿产资源补偿费
		
				
		double[] Selling_expenses=new double[3];
		Selling_expenses[0]=helper.Cal2(helper.getBySubject2("5601", list));//销售费用
		Selling_expenses[1]=helper.Cal2(helper.getBySubject("5601010", list));//商品维修费
		Selling_expenses[2]=helper.Cal2(helper.getBySubject("5601015", list))+
				helper.Cal2(helper.getBySubject("5601016", list));//广告费+业务宣传费
		
		
		double[] Management_expenses=new double[4];
		Management_expenses[0]=helper.Cal2(helper.getBySubject2("5602", list));//管理费用
		Management_expenses[1]=helper.Cal2(helper.getBySubject("5602009", list));//开办费
		Management_expenses[2]=helper.Cal2(helper.getBySubject("5602002", list));//业务招待费
		Management_expenses[3]=helper.Cal2(helper.getBySubject("5602010", list));//研究费用
		
		double[] Financial_expenses=new double[2];//财务费用
		Financial_expenses[0]=helper.Cal2(helper.getBySubject2("5603", list));//财务费用
		Financial_expenses[1]=helper.Cal2(helper.getBySubject("5603001", list));//利息费用	
		
		double investment_proceeds=helper.Cal(helper.getBySubject("5111", list));//投资收益
		
		double operating_profit=business_income+investment_proceeds-business_costs-
				Business_Taxes_and_Surcharges[0]-Selling_expenses[0]-Management_expenses[0]-Financial_expenses[0];
		
		double[] Non_operating_income=new double[2];
		Non_operating_income[0]=helper.Cal(helper.getBySubject2("5301", list));
		Non_operating_income[1]=helper.Cal(helper.getBySubject("5301002", list));//营业外收入-政府补助
		
		double[] Non_operating_expenses=new double[6];
		Non_operating_expenses[0]=helper.Cal2(helper.getBySubject2("5711", list));
		Non_operating_expenses[1]=helper.Cal2(helper.getBySubject("5711005", list));//坏账损失
		Non_operating_expenses[2]=helper.Cal2(helper.getBySubject("5711007", list));//无法收回的长期债券投资损失
		Non_operating_expenses[3]=helper.Cal2(helper.getBySubject("5711008", list));//无法收回的长期股权投资
		Non_operating_expenses[4]=helper.Cal2(helper.getBySubject("5711009", list));//自然灾害等不可抗因素造成的损失
		Non_operating_expenses[5]=helper.Cal2(helper.getBySubject("5711010", list));//税收滞纳金
		
		double Total_profit=operating_profit+Non_operating_income[0]-Non_operating_expenses[0];
		
		double Income_tax_expense=helper.Cal2(helper.getBySubject("5801", list));
		
		double Net_profit=Total_profit-Income_tax_expense;
		
		return new ProfitTableVo(business_income,business_costs,Business_Taxes_and_Surcharges,Selling_expenses,
				Management_expenses,Financial_expenses,investment_proceeds,operating_profit,Non_operating_income,
				Non_operating_expenses,Total_profit,Income_tax_expense,Net_profit);
	}

	public double[] getValue(String id, String time) {
		double res[]=new double[10];
		
		List<VoucherAmountPO> list=DATA.getVourchersByPeriod(time, id);
		List<VoucherAmountPO> list2=DATA.getVourchersByPeriod(helper.lastTime(time), id);
		
		double business_income=0;//营业收入
		double temp1=helper.Cal(helper.getBySubject("5001", list));
		double temp2=helper.Cal(helper.getBySubject("5051", list));
		res[7]=temp2;
		res[8]=temp1;
		res[9]=helper.Cal(helper.getBySubject("5001",list2));
		business_income=temp1+temp2;//主营业务收入+其他业务收入
		
		double business_costs=0;//营业成本
		temp1=helper.Cal2(helper.getBySubject("5401",list));
		res[2]=temp1;
		temp2=helper.Cal2(helper.getBySubject("5451",list));
		business_costs=temp1+temp2;//主营业务成本+其他业务成本
		res[6]=business_costs;
		
		double Business_Taxes_and_Surcharges=0;
		Business_Taxes_and_Surcharges=helper.Cal2(helper.getBySubject("5403",list));//税金及附加
				
		double Selling_expenses=0;
		Selling_expenses=helper.Cal2(helper.getBySubject2("5601",list));//销售费用
		res[3]=Selling_expenses;	
		
		double Management_expenses=0;
		Management_expenses=helper.Cal2(helper.getBySubject2("5602",list));//管理费用
		res[4]=Management_expenses;
		
		double Financial_expenses=0;//财务费用
		Financial_expenses=helper.Cal2(helper.getBySubject2("5603",list));//财务费用
		res[5]=Financial_expenses;
		
		double operating_profit=business_income+helper.Cal(helper.getBySubject("5111",list))-business_costs-
				Business_Taxes_and_Surcharges-Selling_expenses-Management_expenses-Financial_expenses;
		
		double Non_operating_income=0;
		Non_operating_income=helper.Cal(helper.getBySubject("5301",list));
		
		double Non_operating_expenses=0;
		Non_operating_expenses=helper.Cal2(helper.getBySubject("5711",list));
		
		double Total_profit=operating_profit+Non_operating_income-Non_operating_expenses;
		res[1]=Total_profit;
		
		double Income_tax_expense=helper.Cal2(helper.getBySubject("5801",list));
		
		double Net_profit=Total_profit-Income_tax_expense;
		res[0]=Net_profit;
		return res;
	}

	public double getProfit(String id, String time) {	
		List<VoucherAmountPO> list=DATA.getVourchersByPeriod(time, id);
		
		double business_income=0;//营业收入
		double temp1=helper.Cal(helper.getBySubject("5001", list));
		double temp2=helper.Cal(helper.getBySubject("5051", list));
		business_income=temp1+temp2;//主营业务收入+其他业务收入
		
		double business_costs=0;//营业成本
		temp1=helper.Cal2(helper.getBySubject("5401",list));
		temp2=helper.Cal2(helper.getBySubject("5451",list));
		business_costs=temp1+temp2;//主营业务成本+其他业务成本
		
		double Business_Taxes_and_Surcharges=0;
		Business_Taxes_and_Surcharges=helper.Cal2(helper.getBySubject("5403",list));//税金及附加
				
		double Selling_expenses=0;
		Selling_expenses=helper.Cal2(helper.getBySubject2("5601",list));//销售费用
		
		double Management_expenses=0;
		Management_expenses=helper.Cal2(helper.getBySubject2("5602",list));//管理费用
		
		double Financial_expenses=0;//财务费用
		Financial_expenses=helper.Cal2(helper.getBySubject2("5603",list));//财务费用
		
		double operating_profit=business_income+helper.Cal(helper.getBySubject("5111",list))-business_costs-
				Business_Taxes_and_Surcharges-Selling_expenses-Management_expenses-Financial_expenses;
		
		double Non_operating_income=0;
		Non_operating_income=helper.Cal(helper.getBySubject("5301",list));
		
		double Non_operating_expenses=0;
		Non_operating_expenses=helper.Cal2(helper.getBySubject("5711",list));
		
		double Total_profit=operating_profit+Non_operating_income-Non_operating_expenses;
		
		double Income_tax_expense=helper.Cal2(helper.getBySubject("5801",list));
		
		double Net_profit=Total_profit-Income_tax_expense;
		return Net_profit;
	}

	public void CreateProfitTable(String id, String time, String path) {
		ProfitTableVo vo1= BusinessIncome_period(time,id);
		ProfitTableVo vo2= BusinessIncome_year(time,id);
		String s1="";
		String s2="";
		List<String[]> list=new ArrayList<String[]>();
		
		if(vo1.getBusiness_income()!=0)s1=String.valueOf(vo1.getBusiness_income());
		if(vo1.getBusiness_income()!=0)s2=String.valueOf(vo2.getBusiness_income());
		list.add(new String[]{"一、营业收入","1",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getBusiness_costs()!=0)s1=String.valueOf(vo1.getBusiness_costs());
		if(vo1.getBusiness_costs()!=0)s2=String.valueOf(vo2.getBusiness_costs());
		list.add(new String[]{"   减：营业成本","2",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getBusiness_Taxes_and_Surcharges()[0]!=0)s1=String.valueOf(vo1.getBusiness_Taxes_and_Surcharges()[0]);
		if(vo1.getBusiness_Taxes_and_Surcharges()[0]!=0)s2=String.valueOf(vo2.getBusiness_Taxes_and_Surcharges()[0]);
		list.add(new String[]{"   营业税金及附加","3",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getBusiness_Taxes_and_Surcharges()[1]!=0)s1=String.valueOf(vo1.getBusiness_Taxes_and_Surcharges()[1]);
		if(vo1.getBusiness_Taxes_and_Surcharges()[1]!=0)s2=String.valueOf(vo2.getBusiness_Taxes_and_Surcharges()[1]);
		list.add(new String[]{"       其中：消费税","4",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getBusiness_Taxes_and_Surcharges()[2]!=0)s1=String.valueOf(vo1.getBusiness_Taxes_and_Surcharges()[2]);
		if(vo1.getBusiness_Taxes_and_Surcharges()[2]!=0)s2=String.valueOf(vo2.getBusiness_Taxes_and_Surcharges()[2]);
		list.add(new String[]{"      营业税","5",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getBusiness_Taxes_and_Surcharges()[3]!=0)s1=String.valueOf(vo1.getBusiness_Taxes_and_Surcharges()[3]);
		if(vo1.getBusiness_Taxes_and_Surcharges()[3]!=0)s2=String.valueOf(vo2.getBusiness_Taxes_and_Surcharges()[3]);
		list.add(new String[]{"      城市维护建设税","6",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getBusiness_Taxes_and_Surcharges()[4]!=0)s1=String.valueOf(vo1.getBusiness_Taxes_and_Surcharges()[4]);
		if(vo1.getBusiness_Taxes_and_Surcharges()[4]!=0)s2=String.valueOf(vo2.getBusiness_Taxes_and_Surcharges()[4]);
		list.add(new String[]{"      资源税","7",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getBusiness_Taxes_and_Surcharges()[5]!=0)s1=String.valueOf(vo1.getBusiness_Taxes_and_Surcharges()[5]);
		if(vo1.getBusiness_Taxes_and_Surcharges()[5]!=0)s2=String.valueOf(vo2.getBusiness_Taxes_and_Surcharges()[5]);
		list.add(new String[]{"      土地增值税","8",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getBusiness_Taxes_and_Surcharges()[6]!=0)s1=String.valueOf(vo1.getBusiness_Taxes_and_Surcharges()[6]);
		if(vo1.getBusiness_Taxes_and_Surcharges()[6]!=0)s2=String.valueOf(vo2.getBusiness_Taxes_and_Surcharges()[6]);
		list.add(new String[]{"      城镇土地使用税、房产税、车船税、印花税","9",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getBusiness_Taxes_and_Surcharges()[7]!=0)s1=String.valueOf(vo1.getBusiness_Taxes_and_Surcharges()[7]);
		if(vo1.getBusiness_Taxes_and_Surcharges()[7]!=0)s2=String.valueOf(vo2.getBusiness_Taxes_and_Surcharges()[7]);
		list.add(new String[]{"      教育费附加、矿产资源补偿费、排污费","10",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getSelling_expenses()[0]!=0)s1=String.valueOf(vo1.getSelling_expenses()[0]);
		if(vo1.getSelling_expenses()[0]!=0)s2=String.valueOf(vo2.getSelling_expenses()[0]);
		list.add(new String[]{"   销售费用","11",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getSelling_expenses()[1]!=0)s1=String.valueOf(vo1.getSelling_expenses()[1]);
		if(vo1.getSelling_expenses()[1]!=0)s2=String.valueOf(vo2.getSelling_expenses()[1]);
		list.add(new String[]{"      其中：商品维修费","12",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getSelling_expenses()[2]!=0)s1=String.valueOf(vo1.getSelling_expenses()[2]);
		if(vo1.getSelling_expenses()[2]!=0)s2=String.valueOf(vo2.getSelling_expenses()[2]);
		list.add(new String[]{"      广告费和业务宣传费","13",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getManagement_expenses()[0]!=0)s1=String.valueOf(vo1.getManagement_expenses()[0]);
		if(vo1.getManagement_expenses()[0]!=0)s2=String.valueOf(vo2.getManagement_expenses()[0]);
		list.add(new String[]{"   管理费用","14",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getManagement_expenses()[1]!=0)s1=String.valueOf(vo1.getManagement_expenses()[1]);
		if(vo1.getManagement_expenses()[1]!=0)s2=String.valueOf(vo2.getManagement_expenses()[1]);
		list.add(new String[]{"      其中：开办费","15",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getManagement_expenses()[2]!=0)s1=String.valueOf(vo1.getManagement_expenses()[2]);
		if(vo1.getManagement_expenses()[2]!=0)s2=String.valueOf(vo2.getManagement_expenses()[2]);
		list.add(new String[]{"      业务招待费","16",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getManagement_expenses()[3]!=0)s1=String.valueOf(vo1.getManagement_expenses()[3]);
		if(vo1.getManagement_expenses()[3]!=0)s2=String.valueOf(vo2.getManagement_expenses()[3]);
		list.add(new String[]{"      研究费用","17",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getFinancial_expenses()[0]!=0)s1=String.valueOf(vo1.getFinancial_expenses()[0]);
		if(vo1.getFinancial_expenses()[0]!=0)s2=String.valueOf(vo2.getFinancial_expenses()[0]);
		list.add(new String[]{"   财务费用","18",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getFinancial_expenses()[1]!=0)s1=String.valueOf(vo1.getFinancial_expenses()[1]);
		if(vo1.getFinancial_expenses()[1]!=0)s2=String.valueOf(vo2.getFinancial_expenses()[1]);
		list.add(new String[]{"       其中：利息费用（收入以“-”号填列）","19",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getInvestment_proceeds()!=0)s1=String.valueOf(vo1.getInvestment_proceeds());
		if(vo1.getInvestment_proceeds()!=0)s2=String.valueOf(vo2.getInvestment_proceeds());
		list.add(new String[]{"   加：投资收益（亏损以“-”号填列）","20",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getOperating_profit()!=0)s1=String.valueOf(vo1.getOperating_profit());
		if(vo1.getOperating_profit()!=0)s2=String.valueOf(vo2.getOperating_profit());
		list.add(new String[]{"二、营业利润（亏损以“-”号填列）","21",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getNon_operating_income()[0]!=0)s1=String.valueOf(vo1.getNon_operating_income()[0]);
		if(vo1.getNon_operating_income()[0]!=0)s2=String.valueOf(vo2.getNon_operating_income()[0]);
		list.add(new String[]{"   加：营业外收入","22",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getNon_operating_income()[1]!=0)s1=String.valueOf(vo1.getNon_operating_income()[1]);
		if(vo1.getNon_operating_income()[1]!=0)s2=String.valueOf(vo2.getNon_operating_income()[1]);
		list.add(new String[]{"      其中：政府补助","23",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getNon_operating_expenses()[0]!=0)s1=String.valueOf(vo1.getNon_operating_expenses()[0]);
		if(vo1.getNon_operating_expenses()[0]!=0)s2=String.valueOf(vo2.getNon_operating_expenses()[0]);
		list.add(new String[]{"   减：营业外支出","24",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getNon_operating_expenses()[1]!=0)s1=String.valueOf(vo1.getNon_operating_expenses()[1]);
		if(vo1.getNon_operating_expenses()[1]!=0)s2=String.valueOf(vo2.getNon_operating_expenses()[1]);
		list.add(new String[]{"      其中：坏账损失","25",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getNon_operating_expenses()[2]!=0)s1=String.valueOf(vo1.getNon_operating_expenses()[2]);
		if(vo1.getNon_operating_expenses()[2]!=0)s2=String.valueOf(vo2.getNon_operating_expenses()[2]);
		list.add(new String[]{"      无法收回的长期债券投资损失","26",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getNon_operating_expenses()[3]!=0)s1=String.valueOf(vo1.getNon_operating_expenses()[3]);
		if(vo1.getNon_operating_expenses()[3]!=0)s2=String.valueOf(vo2.getNon_operating_expenses()[3]);
		list.add(new String[]{"      无法收回的长期股权投资损失","27",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getNon_operating_expenses()[4]!=0)s1=String.valueOf(vo1.getNon_operating_expenses()[4]);
		if(vo1.getNon_operating_expenses()[4]!=0)s2=String.valueOf(vo2.getNon_operating_expenses()[4]);
		list.add(new String[]{"      自然灾害等不可抗力因素造成的损失","28",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getNon_operating_expenses()[5]!=0)s1=String.valueOf(vo1.getNon_operating_expenses()[5]);
		if(vo1.getNon_operating_expenses()[5]!=0)s2=String.valueOf(vo2.getNon_operating_expenses()[5]);
		list.add(new String[]{"      税收滞纳金","29",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getTotal_profit()!=0)s1=String.valueOf(vo1.getTotal_profit());
		if(vo1.getTotal_profit()!=0)s2=String.valueOf(vo2.getTotal_profit());
		list.add(new String[]{"三、利润总额（亏损总额以“-”号填列）","30",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getIncome_tax_expense()!=0)s1=String.valueOf(vo1.getIncome_tax_expense());
		if(vo1.getIncome_tax_expense()!=0)s2=String.valueOf(vo2.getIncome_tax_expense());
		list.add(new String[]{"   减：所得税费用","31",s1,s2});
		s1="";
		s2="";
		
		if(vo1.getNet_profit()!=0)s1=String.valueOf(vo1.getNet_profit());
		if(vo1.getNet_profit()!=0)s2=String.valueOf(vo2.getNet_profit());
		list.add(new String[]{"四、净利润（净亏损以“-”号填列）","32",s1,s2});
		
		// 创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表
        HSSFSheet sheet = workbook.createSheet("现金流量表");
        // 添加表头行
        HSSFRow hssfRow = sheet.createRow(0);
        // 设置单元格格式居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 添加表头内容
        HSSFCell headCell = hssfRow.createCell(0);
        headCell.setCellValue("项目");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(1);
        headCell.setCellValue("行次");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(2);
        headCell.setCellValue("本年累计金额");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(3);
        headCell.setCellValue("本期金额");
        headCell.setCellStyle(cellStyle);


        // 添加数据内容
        for (int i = 0; i < list.size(); i++) {
            hssfRow = sheet.createRow((int) i + 1);
            String []t=list.get(i);
            // 创建单元格，并设置值
            HSSFCell cell = hssfRow.createCell(0);
            cell.setCellValue(t[0]);
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(1);
            cell.setCellValue(t[1]);
            cell = hssfRow.createCell(2);
            cell.setCellValue(t[2]);
            cell = hssfRow.createCell(3);
            cell.setCellValue(t[3]);
        }

        try {
            OutputStream stream = new FileOutputStream(path);
            workbook.write(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}

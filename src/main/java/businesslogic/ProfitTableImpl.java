package businesslogic;

import businesslogicservice.ProfitTableService;
import data.ProfitAndCashServiceImpl;
import dataservice.ProfitAndCashService;
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

	public ProfitTableVo BusinessIncome_period(String time) {
		// TODO Auto-generated method stub
		double business_income=0;//营业收入
		double temp1=helper.Cal(DATA.getVourchersByPeriod(time, "5001"));
		double temp2=helper.Cal(DATA.getVourchersByPeriod(time, "5051"));
		business_income=temp1+temp2;//主营业务收入+其他业务收入
		
		double business_costs=0;//营业成本
		temp1=helper.Cal2(DATA.getVourchersByPeriod(time, "5401"));
		temp2=helper.Cal2(DATA.getVourchersByPeriod(time, "5451"));
		business_costs=temp1+temp2;//主营业务成本+其他业务成本
		
		double[] Business_Taxes_and_Surcharges=new double[8];
		Business_Taxes_and_Surcharges[0]=helper.Cal2(DATA.getVourchersByPeriod(time, "5403"));//税金及附加
		Business_Taxes_and_Surcharges[1]=helper.CreditCal(DATA.getVourchersByPeriod(time, "2221003"));//应交消费税
		Business_Taxes_and_Surcharges[2]=helper.CreditCal(DATA.getVourchersByPeriod(time, "2221004"));//应交营业税
		Business_Taxes_and_Surcharges[3]=helper.CreditCal(DATA.getVourchersByPeriod(time, "2221008"));//应交城市维护建设税
		Business_Taxes_and_Surcharges[4]=helper.CreditCal(DATA.getVourchersByPeriod(time, "2221005"));//应交资源税
		Business_Taxes_and_Surcharges[5]=helper.CreditCal(DATA.getVourchersByPeriod(time, "2221007"));//应交土地增值税
		Business_Taxes_and_Surcharges[6]=helper.CreditCal(DATA.getVourchersByPeriod(time, "2221010"))+//应交城镇土地使用税
				helper.CreditCal(DATA.getVourchersByPeriod(time, "2221011"))+//应交车船使用税
				helper.CreditCal(DATA.getVourchersByPeriod(time, "2221009"))+//应交房产税
				helper.CreditCal(DATA.getVourchersByPeriod(time, "2221017"));//印花税
		Business_Taxes_and_Surcharges[7]=helper.CreditCal(DATA.getVourchersByPeriod(time, "2221013"))+//教育费附加
				helper.CreditCal(DATA.getVourchersByPeriod(time, "2221014"))+//地方教育费附加
				helper.CreditCal(DATA.getVourchersByPeriod(time, "2221016"))+//排污费
				helper.CreditCal(DATA.getVourchersByPeriod(time, "2221015"));//矿产资源补偿费
		
				
		double[] Selling_expenses=new double[3];
		Selling_expenses[0]=helper.Cal2(DATA.getVourchersByPeriod(time, "5601"));//销售费用
		Selling_expenses[1]=helper.Cal2(DATA.getVourchersByPeriod(time, "5601010"));//商品维修费
		Selling_expenses[2]=helper.Cal2(DATA.getVourchersByPeriod(time, "5601015"))+
				helper.Cal2(DATA.getVourchersByPeriod(time, "5601016"));//广告费+业务宣传费
		
		
		double[] Management_expenses=new double[4];
		Management_expenses[0]=helper.Cal2(DATA.getVourchersByPeriod(time, "5602"));//管理费用
		Management_expenses[1]=helper.Cal2(DATA.getVourchersByPeriod(time, "5602009"));//开办费
		Management_expenses[2]=helper.Cal2(DATA.getVourchersByPeriod(time, "5602002"));//业务招待费
		Management_expenses[3]=helper.Cal2(DATA.getVourchersByPeriod(time, "5602010"));//研究费用
		
		double[] Financial_expenses=new double[2];//财务费用
		Financial_expenses[0]=helper.Cal2(DATA.getVourchersByPeriod(time, "5603"));//财务费用
		Financial_expenses[1]=helper.Cal2(DATA.getVourchersByPeriod(time, "5603001"));//利息费用	
		
		double investment_proceeds=helper.Cal(DATA.getVourchersByPeriod(time, "5111"));//投资收益
		
		double operating_profit=business_income+helper.Cal(DATA.getVourchersByPeriod(time, "5111"))-business_costs-
				Business_Taxes_and_Surcharges[0]-Selling_expenses[0]-Management_expenses[0]-Financial_expenses[0];
		
		double[] Non_operating_income=new double[2];
		Non_operating_income[0]=helper.Cal(DATA.getVourchersByPeriod(time, "5301"));
		Non_operating_income[1]=helper.Cal(DATA.getVourchersByPeriod(time, "5301002"));//营业外收入-政府补助
		
		double[] Non_operating_expenses=new double[6];
		Non_operating_expenses[0]=helper.Cal2(DATA.getVourchersByPeriod(time, "5711"));
		Non_operating_expenses[1]=helper.Cal2(DATA.getVourchersByPeriod(time, "5711005"));//坏账损失
		Non_operating_expenses[2]=helper.Cal2(DATA.getVourchersByPeriod(time, "5711007"));//无法收回的长期债券投资损失
		Non_operating_expenses[3]=helper.Cal2(DATA.getVourchersByPeriod(time, "5711008"));//无法收回的长期股权投资
		Non_operating_expenses[4]=helper.Cal2(DATA.getVourchersByPeriod(time, "5711009"));//自然灾害等不可抗因素造成的损失
		Non_operating_expenses[5]=helper.Cal2(DATA.getVourchersByPeriod(time, "5711010"));//税收滞纳金
		
		double Total_profit=operating_profit+Non_operating_income[0]-Non_operating_expenses[0];
		
		double Income_tax_expense=helper.Cal2(DATA.getVourchersByPeriod(time, "5801"));
		
		double Net_profit=Total_profit-Income_tax_expense;
		
		return new ProfitTableVo(business_income,business_costs,Business_Taxes_and_Surcharges,Selling_expenses,
				Management_expenses,Financial_expenses,investment_proceeds,operating_profit,Non_operating_income,
				Non_operating_expenses,Total_profit,Income_tax_expense,Net_profit);
	}
	
	public ProfitTableVo BusinessIncome_year(String time) {
		// TODO Auto-generated method stub
		double business_income=0;//营业收入
		double temp1=helper.Cal(DATA.getVourchersByYear(time, "5001"));
		double temp2=helper.Cal(DATA.getVourchersByYear(time, "5051"));
		business_income=temp1+temp2;//主营业务收入+其他业务收入
		
		double business_costs=0;//营业成本
		temp1=helper.Cal2(DATA.getVourchersByYear(time, "5401"));
		temp2=helper.Cal2(DATA.getVourchersByYear(time, "5451"));
		business_costs=temp1+temp2;//主营业务成本+其他业务成本
		
		double[] Business_Taxes_and_Surcharges=new double[8];
		Business_Taxes_and_Surcharges[0]=helper.Cal2(DATA.getVourchersByYear(time, "5403"));//税金及附加
		Business_Taxes_and_Surcharges[1]=helper.CreditCal(DATA.getVourchersByYear(time, "2221003"));//应交消费税
		Business_Taxes_and_Surcharges[2]=helper.CreditCal(DATA.getVourchersByYear(time, "2221004"));//应交营业税
		Business_Taxes_and_Surcharges[3]=helper.CreditCal(DATA.getVourchersByYear(time, "2221008"));//应交城市维护建设税
		Business_Taxes_and_Surcharges[4]=helper.CreditCal(DATA.getVourchersByYear(time, "2221005"));//应交资源税
		Business_Taxes_and_Surcharges[5]=helper.CreditCal(DATA.getVourchersByYear(time, "2221007"));//应交土地增值税
		Business_Taxes_and_Surcharges[6]=helper.CreditCal(DATA.getVourchersByYear(time, "2221010"))+//应交城镇土地使用税
				helper.CreditCal(DATA.getVourchersByYear(time, "2221011"))+//应交车船使用税
				helper.CreditCal(DATA.getVourchersByYear(time, "2221009"))+//应交房产税
				helper.CreditCal(DATA.getVourchersByYear(time, "2221017"));//印花税
		Business_Taxes_and_Surcharges[7]=helper.CreditCal(DATA.getVourchersByYear(time, "2221013"))+//教育费附加
				helper.CreditCal(DATA.getVourchersByYear(time, "2221014"))+//地方教育费附加
				helper.CreditCal(DATA.getVourchersByYear(time, "2221016"))+//排污费
				helper.CreditCal(DATA.getVourchersByYear(time, "2221015"));//矿产资源补偿费
		
				
		double[] Selling_expenses=new double[3];
		Selling_expenses[0]=helper.Cal2(DATA.getVourchersByYear(time, "5601"));//销售费用
		Selling_expenses[1]=helper.Cal2(DATA.getVourchersByYear(time, "5601010"));//商品维修费
		Selling_expenses[2]=helper.Cal2(DATA.getVourchersByYear(time, "5601015"))+
				helper.Cal2(DATA.getVourchersByYear(time, "5601016"));//广告费+业务宣传费
		
		
		double[] Management_expenses=new double[4];
		Management_expenses[0]=helper.Cal2(DATA.getVourchersByYear(time, "5602"));//管理费用
		Management_expenses[1]=helper.Cal2(DATA.getVourchersByYear(time, "5602009"));//开办费
		Management_expenses[2]=helper.Cal2(DATA.getVourchersByYear(time, "5602002"));//业务招待费
		Management_expenses[3]=helper.Cal2(DATA.getVourchersByYear(time, "5602010"));//研究费用
		
		double[] Financial_expenses=new double[2];
		Financial_expenses[0]=helper.Cal2(DATA.getVourchersByYear(time, "5603"));//财务费用
		Financial_expenses[1]=helper.Cal2(DATA.getVourchersByYear(time, "5603001"));//利息费用

		double investment_proceeds=helper.Cal(DATA.getVourchersByYear(time, "5111"));//投资收益
		
		double operating_profit=business_income+helper.Cal(DATA.getVourchersByYear(time, "5111"))-business_costs-
				Business_Taxes_and_Surcharges[0]-Selling_expenses[0]-Management_expenses[0]-Financial_expenses[0];
		
		double[] Non_operating_income=new double[2];
		Non_operating_income[0]=helper.Cal(DATA.getVourchersByYear(time, "5301"));
		Non_operating_income[1]=helper.Cal(DATA.getVourchersByYear(time, "5301002"));//营业外收入-政府补助
		
		double[] Non_operating_expenses=new double[6];
		Non_operating_expenses[0]=helper.Cal2(DATA.getVourchersByYear(time, "5711"));
		Non_operating_expenses[1]=helper.Cal2(DATA.getVourchersByYear(time, "5711005"));//坏账损失
		Non_operating_expenses[2]=helper.Cal2(DATA.getVourchersByYear(time, "5711007"));//无法收回的长期债券投资损失
		Non_operating_expenses[3]=helper.Cal2(DATA.getVourchersByYear(time, "5711008"));//无法收回的长期股权投资
		Non_operating_expenses[4]=helper.Cal2(DATA.getVourchersByYear(time, "5711009"));//自然灾害等不可抗因素造成的损失
		Non_operating_expenses[5]=helper.Cal2(DATA.getVourchersByYear(time, "5711010"));//税收滞纳金
		
		double Total_profit=operating_profit+Non_operating_income[0]-Non_operating_expenses[0];
		
		double Income_tax_expense=helper.Cal2(DATA.getVourchersByYear(time, "5801"));
		
		double Net_profit=Total_profit-Income_tax_expense;
		
		return new ProfitTableVo(business_income,business_costs,Business_Taxes_and_Surcharges,Selling_expenses,
				Management_expenses,Financial_expenses,investment_proceeds,operating_profit,Non_operating_income,
				Non_operating_expenses,Total_profit,Income_tax_expense,Net_profit);
	}
}

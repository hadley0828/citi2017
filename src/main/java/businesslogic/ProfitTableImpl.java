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

	public ProfitTableVo BusinessIncome_period(String id,String time) {
		double business_income=0;//营业收入
		double temp1=helper.Cal(DATA.getVourchersByPeriod(time, "5001",id));
		double temp2=helper.Cal(DATA.getVourchersByPeriod(time, "5051",id));
		business_income=temp1+temp2;//主营业务收入+其他业务收入
		
		double business_costs=0;//营业成本
		temp1=helper.Cal2(DATA.getVourchersByPeriod(time, "5401",id));
		temp2=helper.Cal2(DATA.getVourchersByPeriod(time, "5451",id));
		business_costs=temp1+temp2;//主营业务成本+其他业务成本
		
		double[] Business_Taxes_and_Surcharges=new double[8];
		Business_Taxes_and_Surcharges[0]=helper.Cal2(DATA.getVourchersByPeriod(time, "5403",id));//税金及附加
		Business_Taxes_and_Surcharges[1]=helper.CreditCal(DATA.getVourchersByPeriod(time, "2221003",id));//应交消费税
		Business_Taxes_and_Surcharges[2]=helper.CreditCal(DATA.getVourchersByPeriod(time, "2221004",id));//应交营业税
		Business_Taxes_and_Surcharges[3]=helper.CreditCal(DATA.getVourchersByPeriod(time, "2221008",id));//应交城市维护建设税
		Business_Taxes_and_Surcharges[4]=helper.CreditCal(DATA.getVourchersByPeriod(time, "2221005",id));//应交资源税
		Business_Taxes_and_Surcharges[5]=helper.CreditCal(DATA.getVourchersByPeriod(time, "2221007",id));//应交土地增值税
		Business_Taxes_and_Surcharges[6]=helper.CreditCal(DATA.getVourchersByPeriod(time, "2221010",id))+//应交城镇土地使用税
				helper.CreditCal(DATA.getVourchersByPeriod(time, "2221011",id))+//应交车船使用税
				helper.CreditCal(DATA.getVourchersByPeriod(time, "2221009",id))+//应交房产税
				helper.CreditCal(DATA.getVourchersByPeriod(time, "2221017",id));//印花税
		Business_Taxes_and_Surcharges[7]=helper.CreditCal(DATA.getVourchersByPeriod(time, "2221013",id))+//教育费附加
				helper.CreditCal(DATA.getVourchersByPeriod(time, "2221014",id))+//地方教育费附加
				helper.CreditCal(DATA.getVourchersByPeriod(time, "2221016",id))+//排污费
				helper.CreditCal(DATA.getVourchersByPeriod(time, "2221015",id));//矿产资源补偿费
		
				
		double[] Selling_expenses=new double[3];
		Selling_expenses[0]=helper.Cal2(DATA.getVourchersByPeriod(time, "5601",id));//销售费用
		Selling_expenses[1]=helper.Cal2(DATA.getVourchersByPeriod(time, "5601010",id));//商品维修费
		Selling_expenses[2]=helper.Cal2(DATA.getVourchersByPeriod(time, "5601015",id))+
				helper.Cal2(DATA.getVourchersByPeriod(time, "5601016",id));//广告费+业务宣传费
		
		
		double[] Management_expenses=new double[4];
		Management_expenses[0]=helper.Cal2(DATA.getVourchersByPeriod(time, "5602",id));//管理费用
		Management_expenses[1]=helper.Cal2(DATA.getVourchersByPeriod(time, "5602009",id));//开办费
		Management_expenses[2]=helper.Cal2(DATA.getVourchersByPeriod(time, "5602002",id));//业务招待费
		Management_expenses[3]=helper.Cal2(DATA.getVourchersByPeriod(time, "5602010",id));//研究费用
		
		double[] Financial_expenses=new double[2];//财务费用
		Financial_expenses[0]=helper.Cal2(DATA.getVourchersByPeriod(time, "5603",id));//财务费用
		Financial_expenses[1]=helper.Cal2(DATA.getVourchersByPeriod(time, "5603001",id));//利息费用	
		
		double investment_proceeds=helper.Cal(DATA.getVourchersByPeriod(time, "5111",id));//投资收益
		
		double operating_profit=business_income+helper.Cal(DATA.getVourchersByPeriod(time, "5111",id))-business_costs-
				Business_Taxes_and_Surcharges[0]-Selling_expenses[0]-Management_expenses[0]-Financial_expenses[0];
		
		double[] Non_operating_income=new double[2];
		Non_operating_income[0]=helper.Cal(DATA.getVourchersByPeriod(time, "5301",id));
		Non_operating_income[1]=helper.Cal(DATA.getVourchersByPeriod(time, "5301002",id));//营业外收入-政府补助
		
		double[] Non_operating_expenses=new double[6];
		Non_operating_expenses[0]=helper.Cal2(DATA.getVourchersByPeriod(time, "5711",id));
		Non_operating_expenses[1]=helper.Cal2(DATA.getVourchersByPeriod(time, "5711005",id));//坏账损失
		Non_operating_expenses[2]=helper.Cal2(DATA.getVourchersByPeriod(time, "5711007",id));//无法收回的长期债券投资损失
		Non_operating_expenses[3]=helper.Cal2(DATA.getVourchersByPeriod(time, "5711008",id));//无法收回的长期股权投资
		Non_operating_expenses[4]=helper.Cal2(DATA.getVourchersByPeriod(time, "5711009",id));//自然灾害等不可抗因素造成的损失
		Non_operating_expenses[5]=helper.Cal2(DATA.getVourchersByPeriod(time, "5711010",id));//税收滞纳金
		
		double Total_profit=operating_profit+Non_operating_income[0]-Non_operating_expenses[0];
		
		double Income_tax_expense=helper.Cal2(DATA.getVourchersByPeriod(time, "5801",id));
		
		double Net_profit=Total_profit-Income_tax_expense;
		
		return new ProfitTableVo(business_income,business_costs,Business_Taxes_and_Surcharges,Selling_expenses,
				Management_expenses,Financial_expenses,investment_proceeds,operating_profit,Non_operating_income,
				Non_operating_expenses,Total_profit,Income_tax_expense,Net_profit);
	}
	
	public ProfitTableVo BusinessIncome_year(String id,String time) {
		double business_income=0;//营业收入
		double temp1=helper.Cal(DATA.getVourchersByYear(time, "5001",id));
		double temp2=helper.Cal(DATA.getVourchersByYear(time, "5051",id));
		business_income=temp1+temp2;//主营业务收入+其他业务收入
		
		double business_costs=0;//营业成本
		temp1=helper.Cal2(DATA.getVourchersByYear(time, "5401",id));
		temp2=helper.Cal2(DATA.getVourchersByYear(time, "5451",id));
		business_costs=temp1+temp2;//主营业务成本+其他业务成本
		
		double[] Business_Taxes_and_Surcharges=new double[8];
		Business_Taxes_and_Surcharges[0]=helper.Cal2(DATA.getVourchersByYear(time, "5403",id));//税金及附加
		Business_Taxes_and_Surcharges[1]=helper.CreditCal(DATA.getVourchersByYear(time, "2221003",id));//应交消费税
		Business_Taxes_and_Surcharges[2]=helper.CreditCal(DATA.getVourchersByYear(time, "2221004",id));//应交营业税
		Business_Taxes_and_Surcharges[3]=helper.CreditCal(DATA.getVourchersByYear(time, "2221008",id));//应交城市维护建设税
		Business_Taxes_and_Surcharges[4]=helper.CreditCal(DATA.getVourchersByYear(time, "2221005",id));//应交资源税
		Business_Taxes_and_Surcharges[5]=helper.CreditCal(DATA.getVourchersByYear(time, "2221007",id));//应交土地增值税
		Business_Taxes_and_Surcharges[6]=helper.CreditCal(DATA.getVourchersByYear(time, "2221010",id))+//应交城镇土地使用税
				helper.CreditCal(DATA.getVourchersByYear(time, "2221011",id))+//应交车船使用税
				helper.CreditCal(DATA.getVourchersByYear(time, "2221009",id))+//应交房产税
				helper.CreditCal(DATA.getVourchersByYear(time, "2221017",id));//印花税
		Business_Taxes_and_Surcharges[7]=helper.CreditCal(DATA.getVourchersByYear(time, "2221013",id))+//教育费附加
				helper.CreditCal(DATA.getVourchersByYear(time, "2221014",id))+//地方教育费附加
				helper.CreditCal(DATA.getVourchersByYear(time, "2221016",id))+//排污费
				helper.CreditCal(DATA.getVourchersByYear(time, "2221015",id));//矿产资源补偿费
		
				
		double[] Selling_expenses=new double[3];
		Selling_expenses[0]=helper.Cal2(DATA.getVourchersByYear(time, "5601",id));//销售费用
		Selling_expenses[1]=helper.Cal2(DATA.getVourchersByYear(time, "5601010",id));//商品维修费
		Selling_expenses[2]=helper.Cal2(DATA.getVourchersByYear(time, "5601015",id))+
				helper.Cal2(DATA.getVourchersByYear(time, "5601016",id));//广告费+业务宣传费
		
		
		double[] Management_expenses=new double[4];
		Management_expenses[0]=helper.Cal2(DATA.getVourchersByYear(time, "5602",id));//管理费用
		Management_expenses[1]=helper.Cal2(DATA.getVourchersByYear(time, "5602009",id));//开办费
		Management_expenses[2]=helper.Cal2(DATA.getVourchersByYear(time, "5602002",id));//业务招待费
		Management_expenses[3]=helper.Cal2(DATA.getVourchersByYear(time, "5602010",id));//研究费用
		
		double[] Financial_expenses=new double[2];
		Financial_expenses[0]=helper.Cal2(DATA.getVourchersByYear(time, "5603",id));//财务费用
		Financial_expenses[1]=helper.Cal2(DATA.getVourchersByYear(time, "5603001",id));//利息费用

		double investment_proceeds=helper.Cal(DATA.getVourchersByYear(time, "5111",id));//投资收益
		
		double operating_profit=business_income+helper.Cal(DATA.getVourchersByYear(time, "5111",id))-business_costs-
				Business_Taxes_and_Surcharges[0]-Selling_expenses[0]-Management_expenses[0]-Financial_expenses[0];
		
		double[] Non_operating_income=new double[2];
		Non_operating_income[0]=helper.Cal(DATA.getVourchersByYear(time, "5301",id));
		Non_operating_income[1]=helper.Cal(DATA.getVourchersByYear(time, "5301002",id));//营业外收入-政府补助
		
		double[] Non_operating_expenses=new double[6];
		Non_operating_expenses[0]=helper.Cal2(DATA.getVourchersByYear(time, "5711",id));
		Non_operating_expenses[1]=helper.Cal2(DATA.getVourchersByYear(time, "5711005",id));//坏账损失
		Non_operating_expenses[2]=helper.Cal2(DATA.getVourchersByYear(time, "5711007",id));//无法收回的长期债券投资损失
		Non_operating_expenses[3]=helper.Cal2(DATA.getVourchersByYear(time, "5711008",id));//无法收回的长期股权投资
		Non_operating_expenses[4]=helper.Cal2(DATA.getVourchersByYear(time, "5711009",id));//自然灾害等不可抗因素造成的损失
		Non_operating_expenses[5]=helper.Cal2(DATA.getVourchersByYear(time, "5711010",id));//税收滞纳金
		
		double Total_profit=operating_profit+Non_operating_income[0]-Non_operating_expenses[0];
		
		double Income_tax_expense=helper.Cal2(DATA.getVourchersByYear(time, "5801",id));
		
		double Net_profit=Total_profit-Income_tax_expense;
		
		return new ProfitTableVo(business_income,business_costs,Business_Taxes_and_Surcharges,Selling_expenses,
				Management_expenses,Financial_expenses,investment_proceeds,operating_profit,Non_operating_income,
				Non_operating_expenses,Total_profit,Income_tax_expense,Net_profit);
	}

	public double[] getValue(String id, String time) {
		double res[]=new double[10];
		
		double business_income=0;//营业收入
		double temp1=helper.Cal(DATA.getVourchersByPeriod(time, "5001",id));
		double temp2=helper.Cal(DATA.getVourchersByPeriod(time, "5051",id));
		res[7]=temp2;
		res[8]=temp1;
		res[9]=helper.Cal(DATA.getVourchersByPeriod(helper.lastTime(time), "5001",id));
		business_income=temp1+temp2;//主营业务收入+其他业务收入
		
		double business_costs=0;//营业成本
		temp1=helper.Cal2(DATA.getVourchersByPeriod(time, "5401",id));
		res[2]=temp1;
		temp2=helper.Cal2(DATA.getVourchersByPeriod(time, "5451",id));
		business_costs=temp1+temp2;//主营业务成本+其他业务成本
		res[6]=business_costs;
		
		double Business_Taxes_and_Surcharges=0;
		Business_Taxes_and_Surcharges=helper.Cal2(DATA.getVourchersByPeriod(time, "5403",id));//税金及附加
				
		double Selling_expenses=0;
		Selling_expenses=helper.Cal2(DATA.getVourchersByPeriod(time, "5601",id));//销售费用
		res[3]=Selling_expenses;	
		
		double Management_expenses=0;
		Management_expenses=helper.Cal2(DATA.getVourchersByPeriod(time, "5602",id));//管理费用
		res[4]=Management_expenses;
		
		double Financial_expenses=0;//财务费用
		Financial_expenses=helper.Cal2(DATA.getVourchersByPeriod(time, "5603",id));//财务费用
		res[5]=Financial_expenses;
		
		double operating_profit=business_income+helper.Cal(DATA.getVourchersByPeriod(time, "5111",id))-business_costs-
				Business_Taxes_and_Surcharges-Selling_expenses-Management_expenses-Financial_expenses;
		
		double Non_operating_income=0;
		Non_operating_income=helper.Cal(DATA.getVourchersByPeriod(time, "5301",id));
		
		double Non_operating_expenses=0;
		Non_operating_expenses=helper.Cal2(DATA.getVourchersByPeriod(time, "5711",id));
		
		double Total_profit=operating_profit+Non_operating_income-Non_operating_expenses;
		res[1]=Total_profit;
		
		double Income_tax_expense=helper.Cal2(DATA.getVourchersByPeriod(time, "5801",id));
		
		double Net_profit=Total_profit-Income_tax_expense;
		res[0]=Net_profit;
		return res;
	}
}

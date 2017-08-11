package vo;

import java.util.Arrays;

/**
 * 
 * @author hyf
 * business_income 营业收入
 * business_costs  营业成本
 * Business_Taxes_and_Surcharges 营业税金及附加(其中:消费税；营业税；城市维护建设税；资源税；土地增值税；城镇土地使用税、房产税、车船税、印花税；教育费附加、矿井资源补偿费、排污费)
 * Selling_expenses 销售费用(其中:商品维修费；广告费和业务宣传费)
 * Management_expenses管理费用(其中：开办费；业务招待费；研究费用)
 * Financial_expenses财务费用(其中：利息费用)
 * investment_proceeds投资收益
 * 
 *  operating_profit 营业利润
 *  Non_operating_income 营业外收入(其中：政府补助)
 *  Non_operating_expenses营业外支出(其中：1.坏账损失2.无法收回的长期债券投资损失3.无法收回的长期股权投资4.自然灾害等不可抗因素造成的损失5.税收滞纳金)
 *  Total_profit 利润总额
 *  Income_tax_expense所得税费用
 *  Net_profit 净利润
 */

public class ProfitTableVo {
	private double business_income;
	private double business_costs;
	private double[] Business_Taxes_and_Surcharges;
	private double[] Selling_expenses;
	private double[] Management_expenses;
	private double[] Financial_expenses;
	private double investment_proceeds;
	
	private double operating_profit;
	private double[] Non_operating_income;
	private double[] Non_operating_expenses;
	private double Total_profit;
	private double Income_tax_expense;
	private double Net_profit;
	
	public ProfitTableVo(double bi,double bc,double[] bt,double[] se,double[] me,double[] fe,double ip,
			double op,double []noi,double []noe,double tp,double ite,double np){
		this.business_income=bi;
		this.business_costs=bc;
		this.Business_Taxes_and_Surcharges=Arrays.copyOf(bt, bt.length);
		this.Selling_expenses=Arrays.copyOf(se, se.length);
		this.Management_expenses=Arrays.copyOf(me, me.length);
		this.Financial_expenses=Arrays.copyOf(fe, fe.length);
		this.investment_proceeds=ip;
		
		this.operating_profit=op;
		this.Non_operating_income=Arrays.copyOf(noi, noi.length);
		this.Non_operating_expenses=Arrays.copyOf(noe, noe.length);
		this.Total_profit=tp;
		this.Income_tax_expense=ite;
		this.Net_profit=np;
		
	}

	public double getBusiness_income() {
		return business_income;
	}

	public void setBusiness_income(double business_income) {
		this.business_income = business_income;
	}

	public double getBusiness_costs() {
		return business_costs;
	}

	public void setBusiness_costs(double business_costs) {
		this.business_costs = business_costs;
	}

	public double[] getBusiness_Taxes_and_Surcharges() {
		return Business_Taxes_and_Surcharges;
	}

	public void setBusiness_Taxes_and_Surcharges(double[] business_Taxes_and_Surcharges) {
		Business_Taxes_and_Surcharges = business_Taxes_and_Surcharges;
	}

	public double[] getSelling_expenses() {
		return Selling_expenses;
	}

	public void setSelling_expenses(double[] selling_expenses) {
		Selling_expenses = selling_expenses;
	}

	public double[] getManagement_expenses() {
		return Management_expenses;
	}

	public void setManagement_expenses(double[] management_expenses) {
		Management_expenses = management_expenses;
	}

	public double[] getFinancial_expenses() {
		return Financial_expenses;
	}

	public void setFinancial_expenses(double[] financial_expenses) {
		Financial_expenses = financial_expenses;
	}

	public double getInvestment_proceeds() {
		return investment_proceeds;
	}

	public void setInvestment_proceeds(double investment_proceeds) {
		this.investment_proceeds = investment_proceeds;
	}
	
	public double getOperating_profit() {
		return operating_profit;
	}

	public void setOperating_profit(double operating_profit) {
		this.operating_profit = operating_profit;
	}

	public double[] getNon_operating_income() {
		return Non_operating_income;
	}

	public void setNon_operating_income(double[] non_operating_income) {
		Non_operating_income = non_operating_income;
	}

	public double[] getNon_operating_expenses() {
		return Non_operating_expenses;
	}

	public void setNon_operating_expenses(double[] non_operating_expenses) {
		Non_operating_expenses = non_operating_expenses;
	}

	public double getTotal_profit() {
		return Total_profit;
	}

	public void setTotal_profit(double total_profit) {
		Total_profit = total_profit;
	}

	public double getIncome_tax_expense() {
		return Income_tax_expense;
	}

	public void setIncome_tax_expense(double income_tax_expense) {
		Income_tax_expense = income_tax_expense;
	}

	public double getNet_profit() {
		return Net_profit;
	}

	public void setNet_profit(double net_profit) {
		Net_profit = net_profit;
	}
}

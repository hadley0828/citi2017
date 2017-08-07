package vo;

import java.util.Arrays;

/**
 * 
 * @author hyf
 *  包括营业利润、利润总额、净利润的信息
 *  operating_profit 营业利润
 *  Non_operating_income 营业外收入(其中：政府补助)
 *  Non_operating_expenses营业外支出(其中：1.坏账损失2.无法收回的长期债券投资损失3.无法收回的长期股权投资4.自然灾害等不可抗因素造成的损失5.税收滞纳金)
 *  Total_profit 利润总额
 *  Income_tax_expense所得税费用
 *  Net_profit 净利润
 */
public class BusinessProfit_And_OtherVo {
	private double operating_profit;
	private double[] Non_operating_income;
	private double[] Non_operating_expenses;
	private double Total_profit;
	private double Income_tax_expense;
	private double Net_profit;
	
	public BusinessProfit_And_OtherVo(double op,double []noi,double []noe,double tp,double ite,double np){
		this.operating_profit=op;
		this.Non_operating_income=Arrays.copyOf(noi, noi.length);
		this.Non_operating_expenses=Arrays.copyOf(noe, noe.length);
		this.Total_profit=tp;
		this.Income_tax_expense=ite;
		this.Net_profit=np;
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

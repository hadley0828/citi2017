package vopo;

import java.util.Arrays;

/**
 * 
 * @author hyf
 * operating_activities    经营活动产生的现金流量
 * Investment_activities   投资活动产生的现金流量
 * Financing_activities    筹资活动产生的现金流量
 * Net_cash_increase       现金净增加额
 * Final_cash_balance      期末现金余额
 */
public class CashFlowVo {
	private double[] operating_activities;
	private double[] Investment_activities;
	private double[] Financing_activities;
	private double[] Net_cash_increase;
	private double[] Final_cash_balance;
	
	public CashFlowVo(double[] oa,double[] ia,double[] fa,double[] nci,double[] fcb){
		this.operating_activities=Arrays.copyOf(oa, oa.length);
		this.Investment_activities=Arrays.copyOf(ia, ia.length);
		this.Financing_activities=Arrays.copyOf(fa, fa.length);
		this.Net_cash_increase=Arrays.copyOf(nci, nci.length);
		this.Final_cash_balance=Arrays.copyOf(fcb, fcb.length);
	}

	public double[] getOperating_activities() {
		return operating_activities;
	}

	public void setOperating_activities(double[] operating_activities) {
		this.operating_activities = operating_activities;
	}

	public double[] getInvestment_activities() {
		return Investment_activities;
	}

	public void setInvestment_activities(double[] investment_activities) {
		Investment_activities = investment_activities;
	}

	public double[] getFinancing_activities() {
		return Financing_activities;
	}

	public void setFinancing_activities(double[] financing_activities) {
		Financing_activities = financing_activities;
	}

	public double[] getNet_cash_increase() {
		return Net_cash_increase;
	}

	public void setNet_cash_increase(double[] net_cash_increase) {
		Net_cash_increase = net_cash_increase;
	}

	public double[] getFinal_cash_balance() {
		return Final_cash_balance;
	}

	public void setFinal_cash_balance(double[] final_cash_balance) {
		Final_cash_balance = final_cash_balance;
	}
	
}

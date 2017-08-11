package businesslogic;

import java.util.List;

import businesslogicservice.CashFlowTableService;
import dataservice.ProfitAndCashService;
import po.VoucherAmountPO;
import vo.CashFlowVo;

public class CashFlowImpl implements CashFlowTableService{
	
	ProfitAndCashService DATA;
	TableCalHelper helper;
	
	public CashFlowImpl(){
		DATA=new ProfitAndCashServiceImpl();
		helper=new TableCalHelper();
	}

	public CashFlowVo CashFlowTable_month(String time) {
		// TODO Auto-generated method stub
		double[] operating_activities=new double[7];
		double o1=0;
		double temp1=helper.CreditCal(DATA.getVourchersByPeriod(time, "5001"));
		double temp2=helper.CreditCal(DATA.getVourchersByPeriod(time, "5051"));
		
		
		double[] Investment_activities;
		double[] Financing_activities;
		double[] Net_cash_increase;
	    double[] Final_cash_balance;
	    
		return null;
	}

	public CashFlowVo CashFlowTable_year(String time) {
		// TODO Auto-generated method stub
		return null;
	}

}

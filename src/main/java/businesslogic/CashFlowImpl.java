package businesslogic;

import java.util.List;

import businesslogicservice.CashFlowTableService;
import data.ProfitAndCashServiceImpl;
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
		String last=helper.lastTime(time);//上一期
		
		double[] operating_activities=new double[7];
		double temp1=helper.Cal(DATA.getVourchersByPeriod(time, "5001"));//主营业务收入
		double temp2=helper.Cal(DATA.getVourchersByPeriod(time, "5051"));//其他业务收入
		double temp3=helper.Calbalance(DATA.getVourchersByPeriod(last, "1121"));//应收票据期初余额
		double temp4=helper.Calbalance(DATA.getVourchersByPeriod(time, "1121"));//应收票据期末余额
		double temp5=helper.Calbalance(DATA.getVourchersByPeriod(last, "1122"));//应收账款期初余额
		double temp6=helper.Calbalance(DATA.getVourchersByPeriod(time, "1122"));//应收账款期末余额
		double temp7=helper.Calbalance(DATA.getVourchersByPeriod(last, "2203"));//预收账款期初余额
		double temp8=helper.Calbalance(DATA.getVourchersByPeriod(time, "2203"));//预收账款期末余额
		operating_activities[0]=temp1*(1+0.17)+temp2+(temp3-temp4)+(temp5-temp6)+(temp8-temp7);//“销售产成品、商品、提供劳务收到的现金”的本月金额
		
		temp1=helper.CreditCal(DATA.getVourchersByPeriod(time, "5301"));//营业外收入本期贷方发生额
		temp2=helper.CreditCal(DATA.getVourchersByPeriod(time, "5051"));//其他业务收入本期贷方发生额
		temp3=helper.CreditCal(DATA.getVourchersByPeriod(time, "1221"));//其他应收款本期贷方发生额
		temp4=helper.CreditCal(DATA.getVourchersByPeriod(time, "2241"));//其他应付款本期贷方发生额
		temp5=helper.Cal(DATA.getVourchersByPeriod(time, "1002"));//银行存款利息收入??  利息？？？
		operating_activities[1]=temp1+temp2+temp3+temp4+temp5;//“收到其他与经营活动有关的现金”的本月金额
		
		temp1=helper.DebitCal(DATA.getVourchersByPeriod(time, "1403"))+
				+helper.DebitCal(DATA.getVourchersByPeriod(time, "1405"));//低值易耗品+包装物????????
		temp2=helper.Calbalance(DATA.getVourchersByPeriod(last, "2202"))-helper.Calbalance(DATA.getVourchersByPeriod(time, "2202"));//应付账款
		temp3=helper.Calbalance(DATA.getVourchersByPeriod(last, "2201"))-helper.Calbalance(DATA.getVourchersByPeriod(time, "2201"));//应付票据
		temp4=helper.Calbalance(DATA.getVourchersByPeriod(time, "1123"))-helper.Calbalance(DATA.getVourchersByPeriod(last, "1123"));//预付账款
		operating_activities[2]=temp1*(1+0.17)+temp2+temp3+temp4;//“购买原材料、商品、接受劳务支付的现金”的本月金额
		
		double[] Investment_activities=new double[6];
		double[] Financing_activities=new double[6];
		double[] Net_cash_increase=new double[2];
	    double Final_cash_balance=0;
	    
		return new CashFlowVo(operating_activities,Investment_activities,Financing_activities,Net_cash_increase,Final_cash_balance);
	}

	public CashFlowVo CashFlowTable_year(String time) {
		// TODO Auto-generated method stub
		return null;
	}

}

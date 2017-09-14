package businesslogic;

import java.util.List;
import java.util.Map;

import businesslogicservice.CashFlowTableService;
import data.ProfitAndCashServiceImpl;
import dataservice.ProfitAndCashService;
import po.VoucherAmountPO;
import vo.CashFlowVo;

/**
 * 
 * @author hyf
 *
 */
public class CashFlowImpl implements CashFlowTableService{
	
	ProfitAndCashService DATA;
	TableCalHelper helper;
	
	public CashFlowImpl(){
		DATA=new ProfitAndCashServiceImpl();
		helper=new TableCalHelper();
	}

	public CashFlowVo CashFlowTable_month(String time,String id) {
		String last=helper.lastTime(time);//上一期		
		Map<String,double[]> tool=helper.tempCal(id,time);
		double temp1=0,temp2=0,temp3=0,temp4=0,temp5=0,temp6=0;
		
		
		double[] operating_activities=new double[7];
		temp1=helper.Cal(DATA.getVourchersByPeriod(time, "5001",id));//主营业务收入
		temp2=helper.Cal(DATA.getVourchersByPeriod(time, "5051",id));//其他业务收入
		temp3=tool.get("应收票据")[0]-tool.get("应收票据")[1];//应收票据期初余额-应收票据期末余额
	    temp4=tool.get("应收账款")[0]-tool.get("应收账款")[1];//应收账款期初余额-应收账款期末余额
		temp5=tool.get("预收账款")[0]-tool.get("预收账款")[1];//预收账款期初余额-预收账款期末余额
		operating_activities[0]=temp1*(1+0.17)+temp2+temp3+temp4+temp5;//1.1“销售产成品、商品、提供劳务收到的现金”
		
		temp1=helper.DebitCal(DATA.getVourchersByPeriod(time, "1403",id))+
				+helper.DebitCal(DATA.getVourchersByPeriod(time, "1405",id));//原材料、低值易耗品、包装物、库存商品的借方发生额
		temp2=tool.get("应付账款")[0]-tool.get("应付账款")[1];//购买原材料、商品、接受劳务的应付账款(期初 一期末)
		temp3=tool.get("应付票据")[0]-tool.get("应付票据")[1];//购买原材料、商品、接受劳务的应付票据(期初一期末)
		temp4=tool.get("预付账款")[1]-tool.get("预付账款")[0];//购买原材料、商品、接受劳务的预付账款 (期末一期初)
		operating_activities[2]=temp1*(1+0.17)+temp2+temp3+temp4;//1.3“购买原材料、商品、接受劳务支付的现金”的本月金额
		
		temp1=helper.DebitCal(DATA.getVourchersByPeriod(time, "2211",id));//“应付职工薪酬”科目本期借方发生额累计数
		operating_activities[3]=temp1;//“支付的职工薪酬”的本月金额 
		
		temp1=helper.DebitCal(DATA.getVourchersByPeriod(time, "2221",id));//“应交税费”各明细账户本期借方发生额累计数
		temp2=helper.Cal2(DATA.getVourchersByPeriod(time, "222100101",id));//“应交税费-应交增值税-进项税额”	
		operating_activities[4]=temp1-temp2;//1.5“支付的税费”
		
		temp1=helper.Cal2(DATA.getVourchersByPeriod(time, "5711",id))-2*helper.Cal2(DATA.getVourchersByPeriod(time, "5711001",id));//营业外支出（-其中的非流动资产处置净损失）
		temp2=helper.Cal2(DATA.getVourchersByPeriod(time, "5602",id))-2*helper.Cal2(DATA.getVourchersByPeriod(time, "5602001",id))-
				2*helper.Cal2(DATA.getVourchersByPeriod(time, "5602007",id));//管理费用(-其中的应付职工薪酬、折旧费)
		temp3=helper.Cal2(DATA.getVourchersByPeriod(time, "5601",id))-2*helper.Cal2(DATA.getVourchersByPeriod(time, "5601001",id));//销售费用 (-其中的应付职工薪酬)
		temp4=helper.DebitCal(DATA.getVourchersByPeriod(time, "1221",id));//其他应收款本期借方发生额
		temp5=helper.DebitCal(DATA.getVourchersByPeriod(time, "2241",id));//其他应付款本期借方发生额
		temp6=helper.Cal2(DATA.getVourchersByPeriod(time, "5603002",id));//财务费用——手续费
		operating_activities[5]=temp1+temp2+temp3+temp4+temp5+temp6;//1.6“支付其他与经营活动有关的现金”		
		
		
		double[] Investment_activities=new double[6];//二、投资活动产生的现金流量
		temp1=tool.get("短期投资")[0]-tool.get("短期投资")[1];//（短期投资期初数－短期投资期末数）（当期初数＞期末数时）
		if(temp1<0)temp1=0;
		temp2=tool.get("长期股权投资")[0]-tool.get("长期股权投资")[1];//长期股权投资期初数－长期股权投资期末数）（当期初数＞期末数时）
		if(temp2<0)temp2=0;
		temp3=tool.get("长期债券投资")[0]-tool.get("长期债券投资")[1];//长期债券投资期初数－长期债券投资期末数）（当期初数＞期末数时）
		if(temp3<0)temp3=0;
		Investment_activities[0]=temp1+temp2+temp3;//2.1“收回短期投资、长期债券投资和长期股权投资收到的现金”
		
		temp1=helper.Cal(DATA.getVourchersByPeriod(time, "5111",id));//投资收益
		temp2=tool.get("应收利息")[1]-tool.get("应收利息")[0];//（应收利息期末数－应收利息期初数）
		temp3=tool.get("应收股利")[1]-tool.get("应收股利")[0];//（应收股利期末数－应收股利期初数）
		Investment_activities[1]=temp1-temp2-temp3;//2.2“取得投资收益收到的现金”
		
		temp1=helper.CreditCal(DATA.getVourchersByPeriod(time, "1606",id));//“固定资产清理”的贷方余额
		temp2=tool.get("无形资产")[1]-tool.get("无形资产")[0];//（无形资产期末数－无形资产期初数）
		Investment_activities[2]=temp1+temp2;//2.3“处置固定资产、无形资产和其他非流动资产收回的现金净额”
		
		temp1=tool.get("短期投资")[1]-tool.get("短期投资")[0];//（短期投资期末数－短期投资期初数）（当期初数＜期末数时）
		if(temp1<0)temp1=0;
		temp2=tool.get("长期股权投资")[1]-tool.get("长期股权投资")[0];//（长期股权投资期末数－长期股权投资期初数）（当期初数＜期末数时）
		temp4=helper.sumList(DATA.getGivenVourchers(time, "1511", "5111",id));//长期股权投资形成的投资收益
		if(temp2<0)temp2=0;
		else temp2-=temp4;
		temp3=tool.get("长期债券投资")[1]-tool.get("长期债券投资")[0];//〔长期债券投资期末数－长期债权投资期初数）（当期初数＜期末数时）
		temp5=helper.sumList(DATA.getGivenVourchers(time, "1501", "5111",id));//长期债券投资形成的投资收益
		if(temp3<0)temp3=0;
		else temp3-=temp5;
		Investment_activities[3]=temp1+temp2+temp3;//2.4“短期投资、长期债券投资和长期股权投资支付的现金”
		
		temp1=tool.get("在建工程")[1]-tool.get("在建工程")[0];//（在建工程期末数－在建工程期初数）（当期初数＜期末数时）
		if(temp1<0)temp1=0;
		temp2=tool.get("固定资产")[1]-tool.get("固定资产")[0];//（固定资产期末数－固定资产期初数）（当期初数＜期末数时）
		if(temp2<0)temp2=0;
		temp3=tool.get("无形资产")[1]-tool.get("无形资产")[0];//（无形资产期末数－无形资产期初数）（当期初数＜期末数时）
		if(temp3<0)temp3=0;
		Investment_activities[4]=temp1+temp2+temp3;//“购建固定资产、无形资产和其他非流动资产支付的现金”
		
		Investment_activities[5]=Investment_activities[0]+Investment_activities[1]+Investment_activities[2]-
				Investment_activities[3]-Investment_activities[4];//“投资活动产生的现金流量净额”
		
		double[] Financing_activities=new double[6];//三、筹资活动产生的现金流量
		
		temp1=helper.Cal(DATA.getVourchersByPeriod(time, "2001",id));//短期借款
		temp2=helper.Cal(DATA.getVourchersByPeriod(time, "2501",id));//长期借款
		Financing_activities[0]=temp1+temp2;//“取得借款收到的现金”
		
		Financing_activities[1]=helper.Cal(DATA.getVourchersByPeriod(time, "3001",id));//“吸收投资者投资收到的现金”
		
		temp1=//“利润分配-应付利润”本期借方发生额中以现金支付的部分
		Financing_activities[3]=helper.sumList(DATA.getGivenVourchers(time, "2231", "1001",id))
				+helper.sumList(DATA.getGivenVourchers(time,"2231", "1002",id));//3.4偿还借款利息支付的现金
		
		temp1=tool.get("短期借款")[0]-tool.get("短期借款")[1];//（短期借款期初数－短期借款期末数）
		temp2=tool.get("长期借款")[0]-tool.get("长期借款")[1];//（长期借款期初数－长期借款期末数）
		temp3=Financing_activities[3];
		Financing_activities[2]=temp1+temp2-temp3;//3.3“偿还借款本金支付的现金”
		
		Financing_activities[4]=helper.sumList(DATA.getGivenVourchers(time, "3104005", "1001",id))
				+helper.sumList(DATA.getGivenVourchers(time,"3104005", "1002",id));//3.5“分配利润支付的现金”
		
		Financing_activities[5]=Financing_activities[0]+Financing_activities[1]-Financing_activities[2]-
				Financing_activities[3]-Financing_activities[4];//筹资活动产生的现金流量净额
		
		double[] Net_cash_increase=new double[2];
		Net_cash_increase[0]=tool.get("货币资金")[1]-tool.get("货币资金")[0];//“四、现金净增加额”
		Net_cash_increase[1]=new BalanceSheetImpl().getDollarAssent(id,last)[0];
		
	    double Final_cash_balance=Net_cash_increase[0]+Net_cash_increase[1];//“五、期末现金余额”
			
		operating_activities[6]=Net_cash_increase[0]-Investment_activities[5]-Financing_activities[5];//1.7“经营活动产生的现金流量净额”
		operating_activities[1]=operating_activities[6]-operating_activities[0]+operating_activities[2]+
				operating_activities[3]+operating_activities[4]+operating_activities[5];
	    
		return new CashFlowVo(operating_activities,Investment_activities,Financing_activities,Net_cash_increase,Final_cash_balance);
	}

	public CashFlowVo CashFlowTable_year(String time,String id) {	
		Map<String,double[]> tool=helper.tempCal(time,id);
		double temp1=0,temp2=0,temp3=0,temp4=0,temp5=0,temp6=0;
			
		double[] operating_activities=new double[7];
		temp1=helper.Cal(DATA.getVourchersByYear(time, "5001",id));//主营业务收入
		temp2=helper.Cal(DATA.getVourchersByYear(time, "5051",id));//其他业务收入
		temp3=tool.get("应收票据")[0]-tool.get("应收票据")[3];//应收票据期初余额-应收票据期末余额
	    temp4=tool.get("应收账款")[0]-tool.get("应收账款")[3];//应收账款期初余额-应收账款期末余额
		temp5=tool.get("预收账款")[0]-tool.get("预收账款")[3];//预收账款期初余额-预收账款期末余额
		operating_activities[0]=temp1*(1+0.17)+temp2+temp3+temp4+temp5;//1.1“销售产成品、商品、提供劳务收到的现金”
		
		temp1=helper.DebitCal(DATA.getVourchersByYear(time, "1403",id))+
				+helper.DebitCal(DATA.getVourchersByYear(time, "1405",id));//原材料、低值易耗品、包装物、库存商品的借方发生额
		temp2=tool.get("应付账款")[0]-tool.get("应付账款")[3];//购买原材料、商品、接受劳务的应付账款(期初 一期末)
		temp3=tool.get("应付票据")[0]-tool.get("应付票据")[3];//购买原材料、商品、接受劳务的应付票据(期初一期末)
		temp4=tool.get("预付账款")[1]-tool.get("预付账款")[2];//购买原材料、商品、接受劳务的预付账款 (期末一期初)
		operating_activities[2]=temp1*(1+0.17)+temp2+temp3+temp4;//1.3“购买原材料、商品、接受劳务支付的现金”的本月金额
		
		temp1=helper.DebitCal(DATA.getVourchersByYear(time, "2211",id));//“应付职工薪酬”科目本期借方发生额累计数
		operating_activities[3]=temp1;//“支付的职工薪酬”的本月金额 
		
		temp1=helper.DebitCal(DATA.getVourchersByYear(time, "2221",id));//“应交税费”各明细账户本期借方发生额累计数
		temp2=helper.Cal2(DATA.getVourchersByYear(time, "222100101",id));//“应交税费-应交增值税-进项税额”	
		operating_activities[4]=temp1-temp2;//1.5“支付的税费”
		
		temp1=helper.Cal2(DATA.getVourchersByYear(time, "5711",id))-2*helper.Cal2(DATA.getVourchersByYear(time, "5711001",id));//营业外支出（-其中的非流动资产处置净损失）
		temp2=helper.Cal2(DATA.getVourchersByYear(time, "5602",id))-2*helper.Cal2(DATA.getVourchersByYear(time, "5602001",id))-
				2*helper.Cal2(DATA.getVourchersByYear(time, "5602007",id));//管理费用(-其中的应付职工薪酬、折旧费)
		temp3=helper.Cal2(DATA.getVourchersByYear(time, "5601",id))-2*helper.Cal2(DATA.getVourchersByYear(time, "5601001",id));//销售费用 (-其中的应付职工薪酬)
		temp4=helper.DebitCal(DATA.getVourchersByYear(time, "1221",id));//其他应收款本期借方发生额
		temp5=helper.DebitCal(DATA.getVourchersByYear(time, "2241",id));//其他应付款本期借方发生额
		temp6=helper.Cal2(DATA.getVourchersByYear(time, "5603002",id));//财务费用——手续费
		operating_activities[5]=temp1+temp2+temp3+temp4+temp5+temp6;//1.6“支付其他与经营活动有关的现金”		
		
		
		double[] Investment_activities=new double[6];//二、投资活动产生的现金流量
		temp1=tool.get("短期投资")[0]-tool.get("短期投资")[3];//（短期投资期初数－短期投资期末数）（当期初数＞期末数时）
		if(temp1<0)temp1=0;
		temp2=tool.get("长期股权投资")[0]-tool.get("长期股权投资")[3];//长期股权投资期初数－长期股权投资期末数）（当期初数＞期末数时）
		if(temp2<0)temp2=0;
		temp3=tool.get("长期债券投资")[0]-tool.get("长期债券投资")[3];//长期债券投资期初数－长期债券投资期末数）（当期初数＞期末数时）
		if(temp3<0)temp3=0;
		Investment_activities[0]=temp1+temp2+temp3;//2.1“收回短期投资、长期债券投资和长期股权投资收到的现金”
		
		temp1=helper.Cal(DATA.getVourchersByYear(time, "5111",id));//投资收益
		temp2=tool.get("应收利息")[1]-tool.get("应收利息")[2];//（应收利息期末数－应收利息期初数）
		temp3=tool.get("应收股利")[1]-tool.get("应收股利")[2];//（应收股利期末数－应收股利期初数）
		Investment_activities[1]=temp1-temp2-temp3;//2.2“取得投资收益收到的现金”
		
		temp1=helper.CreditCal(DATA.getVourchersByYear(time, "1606",id));//“固定资产清理”的贷方余额
		temp2=tool.get("无形资产")[1]-tool.get("无形资产")[2];//（无形资产期末数－无形资产期初数）
		Investment_activities[2]=temp1+temp2;//2.3“处置固定资产、无形资产和其他非流动资产收回的现金净额”
		
		temp1=tool.get("短期投资")[1]-tool.get("短期投资")[2];//（短期投资期末数－短期投资期初数）（当期初数＜期末数时）
		if(temp1<0)temp1=0;
		temp2=tool.get("长期股权投资")[1]-tool.get("长期股权投资")[2];//（长期股权投资期末数－长期股权投资期初数）（当期初数＜期末数时）
		temp4=helper.sumList(DATA.getGivenVourchersByYear(time, "1511", "5111",id));//长期股权投资形成的投资收益
		if(temp2<0)temp2=0;
		else temp2-=temp4;
		temp3=tool.get("长期债券投资")[1]-tool.get("长期债券投资")[2];//〔长期债券投资期末数－长期债权投资期初数）（当期初数＜期末数时）
		temp5=helper.sumList(DATA.getGivenVourchersByYear(time, "1501", "5111",id));//长期债券投资形成的投资收益
		if(temp3<0)temp3=0;
		else temp3-=temp5;
		Investment_activities[3]=temp1+temp2+temp3;//2.4“短期投资、长期债券投资和长期股权投资支付的现金”
		
		temp1=tool.get("在建工程")[1]-tool.get("在建工程")[2];//（在建工程期末数－在建工程期初数）（当期初数＜期末数时）
		if(temp1<0)temp1=0;
		temp2=tool.get("固定资产")[1]-tool.get("固定资产")[2];//（固定资产期末数－固定资产期初数）（当期初数＜期末数时）
		if(temp2<0)temp2=0;
		temp3=tool.get("无形资产")[1]-tool.get("无形资产")[3];//（无形资产期末数－无形资产期初数）（当期初数＜期末数时）
		if(temp3<0)temp3=0;
		Investment_activities[4]=temp1+temp2+temp3;//“购建固定资产、无形资产和其他非流动资产支付的现金”
		
		Investment_activities[5]=Investment_activities[0]+Investment_activities[1]+Investment_activities[2]-
				Investment_activities[3]-Investment_activities[4];//“投资活动产生的现金流量净额”
		
		double[] Financing_activities=new double[6];//三、筹资活动产生的现金流量
		
		temp1=helper.Cal(DATA.getVourchersByYear(time, "2001",id));//短期借款
		temp2=helper.Cal(DATA.getVourchersByYear(time, "2501",id));//长期借款
		Financing_activities[0]=temp1+temp2;//“取得借款收到的现金”
		
		Financing_activities[1]=helper.Cal(DATA.getVourchersByYear(time, "3001",id));//“吸收投资者投资收到的现金”
		
		temp1=//“利润分配-应付利润”本期借方发生额中以现金支付的部分
		Financing_activities[3]=helper.sumList(DATA.getGivenVourchersByYear(time, "2231", "1001",id))
				+helper.sumList(DATA.getGivenVourchersByYear(time,"2231", "1002",id));//3.4偿还借款利息支付的现金
		
		temp1=tool.get("短期借款")[0]-tool.get("短期借款")[3];//（短期借款期初数－短期借款期末数）
		temp2=tool.get("长期借款")[0]-tool.get("长期借款")[3];//（长期借款期初数－长期借款期末数）
		temp3=Financing_activities[3];
		Financing_activities[2]=temp1+temp2-temp3;//3.3“偿还借款本金支付的现金”		
		
		Financing_activities[4]=helper.sumList(DATA.getGivenVourchersByYear(time, "3104005", "1001",id))
				+helper.sumList(DATA.getGivenVourchersByYear(time,"3104005", "1002",id));//3.5“分配利润支付的现金”
		
		Financing_activities[5]=Financing_activities[0]+Financing_activities[1]-Financing_activities[2]-
				Financing_activities[3]-Financing_activities[4];//筹资活动产生的现金流量净额
		
		double[] Net_cash_increase=new double[2];
		Net_cash_increase[0]=tool.get("货币资金")[1]-tool.get("货币资金")[2];//“四、现金净增加额”
		Net_cash_increase[1]=0;
		
	    double Final_cash_balance=Net_cash_increase[0]+Net_cash_increase[1];//“五、期末现金余额”
			
		operating_activities[6]=Net_cash_increase[0]-Investment_activities[5]-Financing_activities[5];//1.7“经营活动产生的现金流量净额”
		operating_activities[1]=operating_activities[6]-operating_activities[0]+operating_activities[2]+
				operating_activities[3]+operating_activities[4]+operating_activities[5];
	    
		return new CashFlowVo(operating_activities,Investment_activities,Financing_activities,Net_cash_increase,Final_cash_balance);
	}

	public double getValue(String id, String time) {
		String last=helper.lastTime(time);//上一期		
		Map<String,double[]> tool=helper.tempCal(time,id);
		double temp1=0,temp2=0,temp3=0,temp4=0,temp5=0;
		
		double[] Net_cash_increase=new double[2];
		Net_cash_increase[0]=tool.get("货币资金")[1]-tool.get("货币资金")[0];//“四、现金净增加额”
		Net_cash_increase[1]=new BalanceSheetImpl().getDollarAssent(id,last)[0];
		
		double[] Investment_activities=new double[6];//二、投资活动产生的现金流量
		temp1=tool.get("短期投资")[0]-tool.get("短期投资")[1];//（短期投资期初数－短期投资期末数）（当期初数＞期末数时）
		if(temp1<0)temp1=0;
		temp2=tool.get("长期股权投资")[0]-tool.get("长期股权投资")[1];//长期股权投资期初数－长期股权投资期末数）（当期初数＞期末数时）
		if(temp2<0)temp2=0;
		temp3=tool.get("长期债券投资")[0]-tool.get("长期债券投资")[1];//长期债券投资期初数－长期债券投资期末数）（当期初数＞期末数时）
		if(temp3<0)temp3=0;
		Investment_activities[0]=temp1+temp2+temp3;//2.1“收回短期投资、长期债券投资和长期股权投资收到的现金”
		
		temp1=helper.Cal(DATA.getVourchersByPeriod(time, "5111",id));//投资收益
		temp2=tool.get("应收利息")[1]-tool.get("应收利息")[0];//（应收利息期末数－应收利息期初数）
		temp3=tool.get("应收股利")[1]-tool.get("应收股利")[0];//（应收股利期末数－应收股利期初数）
		Investment_activities[1]=temp1-temp2-temp3;//2.2“取得投资收益收到的现金”
		
		temp1=helper.CreditCal(DATA.getVourchersByPeriod(time, "1606",id));//“固定资产清理”的贷方余额
		temp2=tool.get("无形资产")[1]-tool.get("无形资产")[0];//（无形资产期末数－无形资产期初数）
		Investment_activities[2]=temp1+temp2;//2.3“处置固定资产、无形资产和其他非流动资产收回的现金净额”
		
		temp1=tool.get("短期投资")[1]-tool.get("短期投资")[0];//（短期投资期末数－短期投资期初数）（当期初数＜期末数时）
		if(temp1<0)temp1=0;
		temp2=tool.get("长期股权投资")[1]-tool.get("长期股权投资")[0];//（长期股权投资期末数－长期股权投资期初数）（当期初数＜期末数时）
		temp4=helper.sumList(DATA.getGivenVourchers(time, "1511", "5111",id));//长期股权投资形成的投资收益
		if(temp2<0)temp2=0;
		else temp2-=temp4;
		temp3=tool.get("长期债券投资")[1]-tool.get("长期债券投资")[0];//〔长期债券投资期末数－长期债权投资期初数）（当期初数＜期末数时）
		temp5=helper.sumList(DATA.getGivenVourchers(time, "1501", "5111",id));//长期债券投资形成的投资收益
		if(temp3<0)temp3=0;
		else temp3-=temp5;
		Investment_activities[3]=temp1+temp2+temp3;//2.4“短期投资、长期债券投资和长期股权投资支付的现金”
		
		temp1=tool.get("在建工程")[1]-tool.get("在建工程")[0];//（在建工程期末数－在建工程期初数）（当期初数＜期末数时）
		if(temp1<0)temp1=0;
		temp2=tool.get("固定资产")[1]-tool.get("固定资产")[0];//（固定资产期末数－固定资产期初数）（当期初数＜期末数时）
		if(temp2<0)temp2=0;
		temp3=tool.get("无形资产")[1]-tool.get("无形资产")[0];//（无形资产期末数－无形资产期初数）（当期初数＜期末数时）
		if(temp3<0)temp3=0;
		Investment_activities[4]=temp1+temp2+temp3;//“购建固定资产、无形资产和其他非流动资产支付的现金”
		
		Investment_activities[5]=Investment_activities[0]+Investment_activities[1]+Investment_activities[2]-
				Investment_activities[3]-Investment_activities[4];//“投资活动产生的现金流量净额”
		
		double[] Financing_activities=new double[6];//三、筹资活动产生的现金流量
		
		temp1=helper.Cal(DATA.getVourchersByPeriod(time, "2001",id));//短期借款
		temp2=helper.Cal(DATA.getVourchersByPeriod(time, "2501",id));//长期借款
		Financing_activities[0]=temp1+temp2;//“取得借款收到的现金”
		
		Financing_activities[1]=helper.Cal(DATA.getVourchersByPeriod(time, "3001",id));//“吸收投资者投资收到的现金”
		
		temp1=//“利润分配-应付利润”本期借方发生额中以现金支付的部分
		Financing_activities[3]=helper.sumList(DATA.getGivenVourchers(time, "2231", "1001",id))
				+helper.sumList(DATA.getGivenVourchers(time,"2231", "1002",id));//3.4偿还借款利息支付的现金
		
		temp1=tool.get("短期借款")[0]-tool.get("短期借款")[1];//（短期借款期初数－短期借款期末数）
		temp2=tool.get("长期借款")[0]-tool.get("长期借款")[1];//（长期借款期初数－长期借款期末数）
		temp3=Financing_activities[3];
		Financing_activities[2]=temp1+temp2-temp3;//3.3“偿还借款本金支付的现金”
		
		Financing_activities[4]=helper.sumList(DATA.getGivenVourchers(time, "3104005", "1001",id))
				+helper.sumList(DATA.getGivenVourchers(time,"3104005", "1002",id));//3.5“分配利润支付的现金”
		
		Financing_activities[5]=Financing_activities[0]+Financing_activities[1]-Financing_activities[2]-
				Financing_activities[3]-Financing_activities[4];//筹资活动产生的现金流量净额
		
		return Net_cash_increase[0]-Investment_activities[5]-Financing_activities[5];
	}

	public double[] getCashFlow(String company_id, String time) {
		CashFlowVo v1=CashFlowTable_month(time,company_id);
		
		double res[]=new double[3];
		res[0]=v1.getOperating_activities()[0]+v1.getOperating_activities()[1]+
				v1.getInvestment_activities()[0]+v1.getInvestment_activities()[1]+v1.getInvestment_activities()[2]+
				v1.getFinancing_activities()[0]+v1.getFinancing_activities()[1];
		res[1]=v1.getOperating_activities()[2]+v1.getOperating_activities()[3]+v1.getOperating_activities()[4]+v1.getOperating_activities()[5]+
				v1.getInvestment_activities()[3]+v1.getInvestment_activities()[4]+
				v1.getFinancing_activities()[2]+v1.getFinancing_activities()[3]+v1.getFinancing_activities()[4];
		res[2]=v1.getNet_cash_increase()[1];
		return res;
	}

}

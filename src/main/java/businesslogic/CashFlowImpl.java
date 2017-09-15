package businesslogic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import businesslogicservice.CashFlowTableService;
import data.ProfitAndCashServiceImpl;
import data.SupplyChainDataServiceImpl;
import dataservice.ProfitAndCashService;
import dataservice.SupplyChainDataService;
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
	SupplyChainDataService SC;
	
	public CashFlowImpl(){
		DATA=new ProfitAndCashServiceImpl();
		helper=new TableCalHelper();
		SC=new SupplyChainDataServiceImpl();
	}

	public CashFlowVo CashFlowTable_month(String time,String id) {	
		Map<String,double[]> tool=helper.tempCal(time,id);
		double temp1=0,temp2=0,temp3=0,temp4=0,temp5=0,temp6=0;
		List<VoucherAmountPO> list=DATA.getVourchersByPeriod(time, id);
		
		double[] operating_activities=new double[7];
		temp1=helper.Cal(helper.getBySubject("5001", list));//主营业务收入
		temp2=helper.Cal(helper.getBySubject("5051", list));//其他业务收入
		temp3=tool.get("应收票据")[0]-tool.get("应收票据")[1];//应收票据期初余额-应收票据期末余额
	    temp4=tool.get("应收账款")[0]-tool.get("应收账款")[1];//应收账款期初余额-应收账款期末余额
		temp5=tool.get("预收账款")[0]-tool.get("预收账款")[1];//预收账款期初余额-预收账款期末余额
		operating_activities[0]=temp1*(1+0.17)+temp2+temp3+temp4+temp5;//1.1“销售产成品、商品、提供劳务收到的现金”
		
		temp1=helper.DebitCal(helper.getBySubject("1403", list))+
				+helper.DebitCal(helper.getBySubject("1405", list));//原材料、低值易耗品、包装物、库存商品的借方发生额
		temp2=tool.get("应付账款")[0]-tool.get("应付账款")[1];//购买原材料、商品、接受劳务的应付账款(期初 一期末)
		temp3=tool.get("应付票据")[0]-tool.get("应付票据")[1];//购买原材料、商品、接受劳务的应付票据(期初一期末)
		temp4=tool.get("预付账款")[1]-tool.get("预付账款")[0];//购买原材料、商品、接受劳务的预付账款 (期末一期初)
		operating_activities[2]=temp1*(1+0.17)+temp2+temp3+temp4;//1.3“购买原材料、商品、接受劳务支付的现金”的本月金额
		
		temp1=helper.DebitCal(helper.getBySubject("2211", list));//“应付职工薪酬”科目本期借方发生额累计数
		operating_activities[3]=temp1;//“支付的职工薪酬”的本月金额 
		
		temp1=helper.DebitCal(helper.getBySubject2("2221", list));//“应交税费”各明细账户本期借方发生额累计数
		temp2=helper.Cal2(helper.getBySubject("222100101", list));//“应交税费-应交增值税-进项税额”	
		operating_activities[4]=temp1-temp2;//1.5“支付的税费”
		
		temp1=helper.Cal2(helper.getBySubject2("5711", list))-2*helper.Cal2(helper.getBySubject("5711001", list));//营业外支出（-其中的非流动资产处置净损失）
		temp2=helper.Cal2(helper.getBySubject2("5602", list))-2*helper.Cal2(helper.getBySubject("5602001", list))-
				2*helper.Cal2(helper.getBySubject("5602007", list));//管理费用(-其中的应付职工薪酬、折旧费)
		temp3=helper.Cal2(helper.getBySubject2("5601", list))-2*helper.Cal2(helper.getBySubject("5601001", list));//销售费用 (-其中的应付职工薪酬)
		temp4=helper.DebitCal(helper.getBySubject("1221", list));//其他应收款本期借方发生额
		temp5=helper.DebitCal(helper.getBySubject("2241", list));//其他应付款本期借方发生额
		temp6=helper.Cal2(helper.getBySubject("5603002", list));//财务费用——手续费
		operating_activities[5]=temp1+temp2+temp3+temp4+temp5+temp6;//1.6“支付其他与经营活动有关的现金”		
		
		
		double[] Investment_activities=new double[6];//二、投资活动产生的现金流量
		temp1=tool.get("短期投资")[0]-tool.get("短期投资")[1];//（短期投资期初数－短期投资期末数）（当期初数＞期末数时）
		if(temp1<0)temp1=0;
		temp2=tool.get("长期股权投资")[0]-tool.get("长期股权投资")[1];//长期股权投资期初数－长期股权投资期末数）（当期初数＞期末数时）
		if(temp2<0)temp2=0;
		temp3=tool.get("长期债券投资")[0]-tool.get("长期债券投资")[1];//长期债券投资期初数－长期债券投资期末数）（当期初数＞期末数时）
		if(temp3<0)temp3=0;
		Investment_activities[0]=temp1+temp2+temp3;//2.1“收回短期投资、长期债券投资和长期股权投资收到的现金”
		
		temp1=helper.Cal(helper.getBySubject("5111", list));//投资收益
		temp2=tool.get("应收利息")[1]-tool.get("应收利息")[0];//（应收利息期末数－应收利息期初数）
		temp3=tool.get("应收股利")[1]-tool.get("应收股利")[0];//（应收股利期末数－应收股利期初数）
		Investment_activities[1]=temp1-temp2-temp3;//2.2“取得投资收益收到的现金”
		
		temp1=helper.CreditCal(helper.getBySubject("1606", list));//“固定资产清理”的贷方余额
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
		
		temp1=helper.Cal(helper.getBySubject("2001", list));//短期借款
		temp2=helper.Cal(helper.getBySubject("2501", list));//长期借款
		Financing_activities[0]=temp1+temp2;//“取得借款收到的现金”
		
		Financing_activities[1]=helper.Cal(helper.getBySubject("3001", list));//“吸收投资者投资收到的现金”
		
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
		Net_cash_increase[1]=new BalanceSheetImpl().getDollarAssent(id,helper.lastTime(time))[0];
		
	    double Final_cash_balance=Net_cash_increase[0]+Net_cash_increase[1];//“五、期末现金余额”
			
		operating_activities[6]=Net_cash_increase[0]-Investment_activities[5]-Financing_activities[5];//1.7“经营活动产生的现金流量净额”
		operating_activities[1]=operating_activities[6]-operating_activities[0]+operating_activities[2]+
				operating_activities[3]+operating_activities[4]+operating_activities[5];
		return new CashFlowVo(operating_activities,Investment_activities,Financing_activities,Net_cash_increase,Final_cash_balance);
	}

	public CashFlowVo CashFlowTable_year(String time,String id) {	
		Map<String,double[]> tool=helper.tempCal(time,id);
		double temp1=0,temp2=0,temp3=0,temp4=0,temp5=0,temp6=0;
		List<VoucherAmountPO> list=DATA.getVourchersByYear(time, id);
		
		double[] operating_activities=new double[7];
		temp1=helper.Cal(helper.getBySubject("5001", list));//主营业务收入
		temp2=helper.Cal(helper.getBySubject("5051", list));//其他业务收入
		temp3=tool.get("应收票据")[0]-tool.get("应收票据")[1];//应收票据期初余额-应收票据期末余额
	    temp4=tool.get("应收账款")[0]-tool.get("应收账款")[1];//应收账款期初余额-应收账款期末余额
		temp5=tool.get("预收账款")[0]-tool.get("预收账款")[1];//预收账款期初余额-预收账款期末余额
		operating_activities[0]=temp1*(1+0.17)+temp2+temp3+temp4+temp5;//1.1“销售产成品、商品、提供劳务收到的现金”
		
		temp1=helper.DebitCal(helper.getBySubject("1403", list))+
				+helper.DebitCal(helper.getBySubject("1405", list));//原材料、低值易耗品、包装物、库存商品的借方发生额
		temp2=tool.get("应付账款")[0]-tool.get("应付账款")[3];//购买原材料、商品、接受劳务的应付账款(期初 一期末)
		temp3=tool.get("应付票据")[0]-tool.get("应付票据")[3];//购买原材料、商品、接受劳务的应付票据(期初一期末)
		temp4=tool.get("预付账款")[1]-tool.get("预付账款")[2];//购买原材料、商品、接受劳务的预付账款 (期末一期初)
		operating_activities[2]=temp1*(1+0.17)+temp2+temp3+temp4;//1.3“购买原材料、商品、接受劳务支付的现金”的本月金额
		
		temp1=helper.DebitCal(helper.getBySubject("2211", list));//“应付职工薪酬”科目本期借方发生额累计数
		operating_activities[3]=temp1;//“支付的职工薪酬”的本月金额 
		
		temp1=helper.DebitCal(helper.getBySubject2("2221", list));//“应交税费”各明细账户本期借方发生额累计数
		temp2=helper.Cal2(helper.getBySubject("222100101", list));//“应交税费-应交增值税-进项税额”	
		operating_activities[4]=temp1-temp2;//1.5“支付的税费”
		
		temp1=helper.Cal2(helper.getBySubject2("5711", list))-2*helper.Cal2(helper.getBySubject("5711001", list));//营业外支出（-其中的非流动资产处置净损失）
		temp2=helper.Cal2(helper.getBySubject2("5602", list))-2*helper.Cal2(helper.getBySubject("5602001", list))-
				2*helper.Cal2(helper.getBySubject("5602007", list));//管理费用(-其中的应付职工薪酬、折旧费)
		temp3=helper.Cal2(helper.getBySubject2("5601", list))-2*helper.Cal2(helper.getBySubject("5601001", list));//销售费用 (-其中的应付职工薪酬)
		temp4=helper.DebitCal(helper.getBySubject("1221", list));//其他应收款本期借方发生额
		temp5=helper.DebitCal(helper.getBySubject("2241", list));//其他应付款本期借方发生额
		temp6=helper.Cal2(helper.getBySubject("5603002", list));//财务费用——手续费
		operating_activities[5]=temp1+temp2+temp3+temp4+temp5+temp6;//1.6“支付其他与经营活动有关的现金”	
		
		
		double[] Investment_activities=new double[6];//二、投资活动产生的现金流量
		temp1=tool.get("短期投资")[0]-tool.get("短期投资")[3];//（短期投资期初数－短期投资期末数）（当期初数＞期末数时）
		if(temp1<0)temp1=0;
		temp2=tool.get("长期股权投资")[0]-tool.get("长期股权投资")[3];//长期股权投资期初数－长期股权投资期末数）（当期初数＞期末数时）
		if(temp2<0)temp2=0;
		temp3=tool.get("长期债券投资")[0]-tool.get("长期债券投资")[3];//长期债券投资期初数－长期债券投资期末数）（当期初数＞期末数时）
		if(temp3<0)temp3=0;
		Investment_activities[0]=temp1+temp2+temp3;//2.1“收回短期投资、长期债券投资和长期股权投资收到的现金”
		
		temp1=helper.Cal(helper.getBySubject("5111", list));//投资收益
		temp2=tool.get("应收利息")[1]-tool.get("应收利息")[2];//（应收利息期末数－应收利息期初数）
		temp3=tool.get("应收股利")[1]-tool.get("应收股利")[2];//（应收股利期末数－应收股利期初数）
		Investment_activities[1]=temp1-temp2-temp3;//2.2“取得投资收益收到的现金”
		
		temp1=helper.CreditCal(helper.getBySubject("1606", list));//“固定资产清理”的贷方余额
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
		
		temp1=helper.Cal(helper.getBySubject("2001", list));//短期借款
		temp2=helper.Cal(helper.getBySubject("2501", list));//长期借款
		Financing_activities[0]=temp1+temp2;//“取得借款收到的现金”
		
		Financing_activities[1]=helper.Cal(helper.getBySubject("3001", list));//“吸收投资者投资收到的现金”
		
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
		List<VoucherAmountPO> list=DATA.getVourchersByPeriod(time, id);
		
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
		
		temp1=helper.Cal(helper.getBySubject("5111", list));//投资收益
		temp2=tool.get("应收利息")[1]-tool.get("应收利息")[0];//（应收利息期末数－应收利息期初数）
		temp3=tool.get("应收股利")[1]-tool.get("应收股利")[0];//（应收股利期末数－应收股利期初数）
		Investment_activities[1]=temp1-temp2-temp3;//2.2“取得投资收益收到的现金”
		
		temp1=helper.CreditCal(helper.getBySubject("1606", list));//“固定资产清理”的贷方余额
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
		
		temp1=helper.Cal(helper.getBySubject("2001", list));//短期借款
		temp2=helper.Cal(helper.getBySubject("2501", list));//长期借款
		Financing_activities[0]=temp1+temp2;//“取得借款收到的现金”
		
		Financing_activities[1]=helper.Cal(helper.getBySubject("3001", list));//“吸收投资者投资收到的现金”
		
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
		res[2]=v1.getNet_cash_increase()[1]+SC.GetInitial("1001", company_id)+SC.GetInitial("1002", company_id)+SC.GetInitial("1012", company_id);
		return res;
	}

	public void CreateCashFlowTable(String id, String time, String path) {
		CashFlowVo vo1=CashFlowTable_month(time,id);
		CashFlowVo vo2=CashFlowTable_month(time,id);
		
		List<String[]> list=new ArrayList<String[]>();
		String t1[]={"销售产成品、商品、提供劳务收到的现金","收到其他与经营活动有关的现金","购买原材料、商品、接受劳务支付的现金",
				"支付的职工薪酬","支付的税费","支付其他与经营活动有关的现金","经营活动产生的现金流量净额"};
		String t2[]={"收回短期投资、长期债券投资和长期股权投资收到的现金","取得投资收益收到的现金","处置固定资产、无形资产和其他非流动资产收回的现金净额",
				"短期投资、长期债券投资和长期股权投资支付的现金","购建固定资产、无形资产和其他非流动资产支付的现金","投资活动产生的现金流量净额"};
		String t3[]={"取得借款收到的现金","吸收投资者投资收到的现金","偿还借款本金支付的现金",
				"偿还借款利息支付的现金","分配利润支付的现金","筹资活动产生的现金流量净额"};
		
		double []p=vo1.getOperating_activities();
		double []q=vo2.getOperating_activities();
		list.add(new String[]{"一、经营活动产生的现金流量：","","",""});
		for(int i=0;i<t1.length;i++){
			String s1="";
			String s2="";
			if(p[i]!=0)s1=String.valueOf(p[i]);
			if(q[i]!=0)s2=String.valueOf(q[i]);
			list.add(new String[]{t1[i],String.valueOf(i+1),s1,s2});
		}
		p=vo1.getInvestment_activities();
		q=vo2.getInvestment_activities();
		list.add(new String[]{"二、投资活动产生的现金流量：","","",""});
		for(int i=0;i<t2.length;i++){
			String s1="";
			String s2="";
			if(p[i]!=0)s1=String.valueOf(p[i]);
			if(q[i]!=0)s2=String.valueOf(q[i]);
			list.add(new String[]{t2[i],String.valueOf(i+8),s1,s2});
		}
		p=vo1.getFinancing_activities();
		q=vo2.getFinancing_activities();
		list.add(new String[]{"三、筹资活动产生的现金流量：","","",""});
		for(int i=0;i<t3.length;i++){
			String s1="";
			String s2="";
			if(p[i]!=0)s1=String.valueOf(p[i]);
			if(q[i]!=0)s2=String.valueOf(q[i]);
			list.add(new String[]{t3[i],String.valueOf(i+14),s1,s2});
		}
		String s1="";
		String s2="";
		p=vo1.getNet_cash_increase();
		q=vo2.getNet_cash_increase();
		if(p[0]!=0)s1=String.valueOf(p[0]);
		if(q[0]!=0)s2=String.valueOf(q[0]);
		list.add(new String[]{"四、现金净增加额","20",s1,s2});
		s1="";
		s2="";
		if(p[1]!=0)s1=String.valueOf(p[1]);
		if(q[1]!=0)s2=String.valueOf(q[1]);
		list.add(new String[]{"加：期初现金余额","21",s1,s2});
		s1="";
		s2="";
		if(vo1.getFinal_cash_balance()!=0)s1=String.valueOf(vo1.getFinal_cash_balance());
		if(vo2.getFinal_cash_balance()!=0)s2=String.valueOf(vo2.getFinal_cash_balance());
		list.add(new String[]{"五、期末现金余额","22",s1,s2});
		
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

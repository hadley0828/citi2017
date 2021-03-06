package businesslogic;

import businesslogicservice.SupplyChainService;
import data.ProfitAndCashServiceImpl;
import data.SupplyChainDataServiceImpl;
import dataservice.ProfitAndCashService;
import dataservice.SupplyChainDataService;
import po.SupplyChainPO;

import po.VoucherAmountPO;
import vo.BalanceSheetItemVo;
import vo.CompanyVo;
import vo.SupplyChainPerformanceVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author hyf
 *
 */
public class SupplyChainImpl implements SupplyChainService{
	
	private ProfitAndCashService DATA;
	private TableCalHelper helper;
	private SupplyChainDataService SC;
	private FinancialWarningImpl FW;
	private InventoryManagementImpl IM;
	private String[] list={"无警","轻警","中警","重警","巨警"};
	
	public SupplyChainImpl(){
		DATA=new ProfitAndCashServiceImpl();
		helper=new TableCalHelper();
		SC=new SupplyChainDataServiceImpl();
		FW=new FinancialWarningImpl();
		IM=new InventoryManagementImpl();
	}


	public SupplyChainPerformanceVo SupplyChain_Supplier(String Supplier_id,String Manufacturer_id,String Distributor_id,String period) {
		String last=helper.lastTime(period);
		BalanceSheetImpl bs=new BalanceSheetImpl();
		List<VoucherAmountPO> list11=DATA.getVourchersByPeriod(period, Supplier_id);
		List<VoucherAmountPO> list12=DATA.getVourchersByPeriod(period, Supplier_id);
		List<VoucherAmountPO> list21=DATA.getVourchersByPeriod(period, Manufacturer_id);
		List<VoucherAmountPO> list22=DATA.getVourchersByPeriod(period, Manufacturer_id);
		List<VoucherAmountPO> list31=DATA.getVourchersByPeriod(period, Distributor_id);
		List<VoucherAmountPO> list32=DATA.getVourchersByPeriod(period, Distributor_id);
		
		Map<String, ArrayList<BalanceSheetItemVo>> t11=bs.getBalanceSheet(Supplier_id, period);
		Map<String, ArrayList<BalanceSheetItemVo>> t12=bs.getBalanceSheet(Supplier_id, last);
		double this_zyshouru1=helper.Cal(helper.getBySubject("5001", list11));//当期主营业务收入
		double last_zyshouru1=helper.Cal(helper.getBySubject("5001", list12));//上期主营业务收入
		double this_zychenben1=helper.Cal2(helper.getBySubject("5401", list11));//当期主营业务成本
		double last_zychenben1=helper.Cal2(helper.getBySubject("5401", list12));//上期主营业务成本
		double last_zong1=t12.get("资产合计").get(0).getEnding_balance();//上期期末总资产
		double this_zong1=t11.get("资产合计").get(0).getEnding_balance();//本期期末总资产
		double this_qtshouru1=helper.Cal(helper.getBySubject("5051", list11));//当期其他业务收入
		double last_cunhuo1=t12.get("流动资产").get(9).getEnding_balance();//上期期末存货
		double this_cunhuo1=t11.get("流动资产").get(9).getEnding_balance();//当期期末存货
		double zongfu1=t11.get("负债合计").get(0).getEnding_balance();//总负债
		
		double[][]Supplier=new double[4][2];//1.供应商
		Supplier[0][0]=(last_zong1+this_zong1)!=0?2*(this_zyshouru1-this_zychenben1)/(last_zong1+this_zong1):0;//盈利能力-总资产报酬率
		Supplier[0][1]=this_zyshouru1!=0?(this_zyshouru1-this_zychenben1)/this_zyshouru1:0;//盈利能力-销售净利率
		
		Supplier[1][0]=(last_zong1+this_zong1)!=0?2*(this_zyshouru1+this_qtshouru1)/(last_zong1+this_zong1):0;//运营能力-总资产周转率
		Supplier[1][1]=(last_cunhuo1+this_cunhuo1)!=0?2*this_zychenben1/(last_cunhuo1+this_cunhuo1):0;//运营能力-存货周转率
		
		Supplier[2][0]=last_zyshouru1!=0?(this_zyshouru1-last_zyshouru1)/last_zyshouru1:0;//发展能力-销售增长率
		Supplier[2][1]=(last_zyshouru1-last_zychenben1)!=0?((this_zyshouru1-this_zychenben1)-(last_zyshouru1-last_zychenben1))/
				(last_zyshouru1-last_zychenben1):0;//发展能力-利润增长率
		
		Supplier[3][0]=zongfu1!=0?this_zong1/zongfu1:0;//偿债能力
		
		Map<String, ArrayList<BalanceSheetItemVo>> t21=bs.getBalanceSheet(Manufacturer_id, period);
		Map<String, ArrayList<BalanceSheetItemVo>> t22=bs.getBalanceSheet(Manufacturer_id, last);
		double this_zyshouru2=helper.Cal(helper.getBySubject("5001", list21));//当期主营业务收入
		double last_zyshouru2=helper.Cal(helper.getBySubject("5001", list22));//上期主营业务收入
		double this_zychenben2=helper.Cal2(helper.getBySubject("5401", list21));//当期主营业务成本
		double last_zychenben2=helper.Cal2(helper.getBySubject("5401", list22));//上期主营业务成本
		double last_zong2=t22.get("资产合计").get(0).getEnding_balance();//上期期末总资产
		double this_zong2=t21.get("资产合计").get(0).getEnding_balance();//本期期末总资产
		double this_qtshouru2=helper.Cal(helper.getBySubject("5051", list21));//当期其他业务收入
		double last_cunhuo2=t22.get("流动资产").get(9).getEnding_balance();//上期期末存货
		double this_cunhuo2=t21.get("流动资产").get(9).getEnding_balance();//当期期末存货
		double zongfu2=t21.get("负债合计").get(0).getEnding_balance();//总负债
		
		double[][]Manufacturer=new double[4][2];//2.生产商
		Manufacturer[0][0]=(last_zong2+this_zong2)!=0?2*(this_zyshouru2-this_zychenben2)/(last_zong2+this_zong2):0;//盈利能力-总资产报酬率
		Manufacturer[0][1]=this_zyshouru2!=0?(this_zyshouru2-this_zychenben2)/this_zyshouru2:0;//盈利能力-销售净利率
		
		Manufacturer[1][0]=(last_zong2+this_zong2)!=0?2*(this_zyshouru2+this_qtshouru2)/(last_zong2+this_zong2):0;//运营能力-总资产周转率
		Manufacturer[1][1]=(last_cunhuo2+this_cunhuo2)!=0?2*this_zychenben2/(last_cunhuo2+this_cunhuo2):0;//运营能力-存货周转率
		
		Manufacturer[2][0]=last_zyshouru2!=0?(this_zyshouru2-last_zyshouru2)/last_zyshouru2:0;//发展能力-销售增长率
		Manufacturer[2][1]=(last_zyshouru2-last_zychenben2)!=0?((this_zyshouru2-this_zychenben2)-(last_zyshouru2-last_zychenben2))/
				(last_zyshouru2-last_zychenben2):0;//发展能力-利润增长率
		
		Manufacturer[3][0]=zongfu2!=0?this_zong2/zongfu2:0;//偿债能力
		
		Map<String, ArrayList<BalanceSheetItemVo>> t31=bs.getBalanceSheet(Distributor_id, period);
		Map<String, ArrayList<BalanceSheetItemVo>> t32=bs.getBalanceSheet(Distributor_id, last);
		double this_zyshouru3=helper.Cal(helper.getBySubject("5001", list31));//当期主营业务收入
		double last_zyshouru3=helper.Cal(helper.getBySubject("5001", list32));//上期主营业务收入
		double this_zychenben3=helper.Cal2(helper.getBySubject("5401", list31));//当期主营业务成本
		double last_zychenben3=helper.Cal2(helper.getBySubject("5401", list32));//上期主营业务成本
		double last_zong3=t32.get("资产合计").get(0).getEnding_balance();//上期期末总资产
		double this_zong3=t31.get("资产合计").get(0).getEnding_balance();//本期期末总资产
		double this_qtshouru3=helper.Cal(helper.getBySubject("50511", list31));//当期其他业务收入
		double last_cunhuo3=t32.get("流动资产").get(9).getEnding_balance();//上期期末存货
		double this_cunhuo3=t31.get("流动资产").get(9).getEnding_balance();//当期期末存货
		double zongfu3=t11.get("负债合计").get(0).getEnding_balance();//总负债
		
		double[][]Distributor=new double[4][2];//3.分销商
		Distributor[0][0]=(last_zong3+this_zong3)!=0?2*(this_zyshouru3-this_zychenben3)/(last_zong3+this_zong3):0;//盈利能力-总资产报酬率
		Distributor[0][1]=this_zyshouru3!=0?(this_zyshouru3-this_zychenben3)/this_zyshouru3:0;//盈利能力-销售净利率
		
		Distributor[1][0]=(last_zong3+this_zong3)!=0?2*(this_zyshouru3+this_qtshouru3)/(last_zong3+this_zong3):0;//运营能力-总资产周转率
		Distributor[1][1]=(last_cunhuo3+this_cunhuo3)!=0?2*this_zychenben3/(last_cunhuo3+this_cunhuo3):0;//运营能力-存货周转率
		
		Distributor[2][0]=last_zyshouru3!=0?(this_zyshouru3-last_zyshouru3)/last_zyshouru3:0;//发展能力-销售增长率
		Distributor[2][1]=(last_zyshouru3-last_zychenben3)!=0?((this_zyshouru3-this_zychenben3)-(last_zyshouru3-last_zychenben3))/
				(last_zyshouru3-last_zychenben3):0;//发展能力-利润增长率
		
		Distributor[3][0]=zongfu3!=0?this_zong3/zongfu3:0;//偿债能力
		
		CashFlowImpl cf=new CashFlowImpl();
		ProfitTableImpl pt=new ProfitTableImpl();
		
		double cashjinliuru1=cf.CashFlowTable_month(period, Supplier_id).getOperating_activities()[6];//供应商当期经营现金净流入
		double cashjinliuru2=cf.CashFlowTable_month(period, Manufacturer_id).getOperating_activities()[6];//生产商当期经营现金净流入
		double cashjinliuru3=cf.CashFlowTable_month(period, Distributor_id).getOperating_activities()[6];//分销商当期经营现金净流入
		
		double this_lirun1=pt.BusinessIncome_period(period, Supplier_id).getNet_profit();//供应商的当期利润
		double this_lirun2=pt.BusinessIncome_period(period, Manufacturer_id).getNet_profit();//生产商的当期利润
		double this_lirun3=pt.BusinessIncome_period(period, Distributor_id).getNet_profit();//分销商的当期利润
		
		double this_allzong=this_zong1+this_zong2+this_zong3;//本期期末供应链总资产
		double last_allzong=last_zong1+last_zong2+last_zong3;//上期期末供应链总资产
		double temp=(last_zyshouru1-last_zychenben1)+(last_zyshouru2-last_zychenben2)+(last_zyshouru3-last_zychenben3);//供应商+生产商+分销商的（上期主营业务收入-上期主营业务成本）
		
		double[]Supply_chain=new double[4];
		Supply_chain[0]=(this_allzong+last_allzong)!=0?2*(this_lirun1+this_lirun2+this_lirun3)/(this_allzong+last_allzong):0;//盈利能力-供应链资本收益率
		Supply_chain[1]=(cashjinliuru1+cashjinliuru2+cashjinliuru3)!=0?(this_zyshouru1+this_zyshouru2+this_zyshouru3)/(cashjinliuru1+cashjinliuru2+cashjinliuru3):0;//运营能力-现金周转率
		Supply_chain[2]=temp!=0?(this_lirun1+this_lirun2+this_lirun3)/temp:0;//发展能力-利润增长率
		Supply_chain[3]=(zongfu1+zongfu2+zongfu3)!=0?(this_zong1+this_zong2+this_zong3)/(zongfu1+zongfu2+zongfu3):0;//偿债能力-资产负债率
		
		return new SupplyChainPerformanceVo(Supplier,Manufacturer,Distributor,Supply_chain,
				IM.OnTimeDeliveryRateBySupplier_id(Supplier_id,period),IM.OnTimeDeliveryRateByManufacturer_id(Manufacturer_id,period),
				IM.ReturnRateBySupplier_id(Manufacturer_id,period),IM.ReturnRateByDistributor_id(Distributor_id,period));
	}

	public List<String> AcountReceivable(String company_id,String time) {	
		return SC.getAcountReceivable(company_id, time);
	}

	public double ReceivableFinacing(double MortgageAmount, String company_id, String time) {
		
		String warning=FW.getWarningMessage2(company_id, time).substring(0, 2);//预警
		double dis=warningJudge(warning);
		SC.SaveSupplyChain(new SupplyChainPO(time,SC.getCompany(company_id),"应收帐款融资",MortgageAmount,MortgageAmount*dis));
		return MortgageAmount*dis;
	}

	public List<String> InventoryTypes(String company_id,String time) {
		return SC.getRawMaterialAndProduct(company_id, time);
	}

	public double PledgeMovables(double Amount, String company_id, String time) {
		
		String warning=FW.getWarningMessage2(company_id, time).substring(0, 2);//预警
		double dis=warningJudge(warning);
		SC.SaveSupplyChain(new SupplyChainPO(time,SC.getCompany(company_id),"动产质押融资",Amount,Amount*dis));
		return Amount*dis;
	}
	
	public double warningJudge(String warning){
		int i=0;
		for(;i<5;i++){
			if(warning.equals(list[i]))
				break;
		}
		return 0.9-i*0.1;
	}

	public double getNetReceivables(String company_id,String company,String time) {
		return SC.GetInitial("1122", company_id)+
				helper.Cal2(SC.GetVoucherAmountsWithCompany(company_id,"1122",company,time));
	}

	public double getNetInventory(String company_id,String product,String time) {
		return helper.Cal2(SC.GetVoucherAmountsWithProduct(company_id,"1401", product, time))+
				helper.Cal2(SC.GetVoucherAmountsWithProduct(company_id,"1402", product, time))+
				helper.Cal2(SC.GetVoucherAmountsWithProduct(company_id,"1403", product, time))+
				helper.Cal2(SC.GetVoucherAmountsWithProduct(company_id,"1405", product, time))+
				helper.Cal2(SC.GetVoucherAmountsWithProduct(company_id,"1408", product, time))+
				helper.Cal2(SC.GetVoucherAmountsWithProduct(company_id,"1605", product, time))+
				SC.GetInitial("1401", company_id)+
				SC.GetInitial("1402", company_id)+
				SC.GetInitial("1403", company_id)+
				SC.GetInitial("1405", company_id)+
				SC.GetInitial("1408", company_id)+
				SC.GetInitial("1605", company_id);
	}

	public List<SupplyChainPO> GetSupplyChains() {
		return SC.GetSupplyChains();
	}


	public String[] getTheCompanys(String company_id) {
		return SC.getTheCompanys(company_id);
	}
	
	public CompanyVo getCompanyInfo(String companyName){
		return new CompanyVo(SC.getCompanyInfo(companyName));
	}
}

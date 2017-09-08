package businesslogic;

import po.VoucherAmountPO;
import vo.BalanceSheetItemVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author hyf
 *
 */
public class TableCalHelper {
	
	/**
	 * 
	 * @param list
	 * @return 贷方
	 */
	public double CreditCal(List<VoucherAmountPO> list){
		int res=0;
		for(VoucherAmountPO n:list){
			res+=n.getCreditAmount();
		}
		return res;
	}
	
	/**
	 * 
	 * @param list
	 * @return 借方
	 */
	public double DebitCal(List<VoucherAmountPO> list){
		int res=0;
		for(VoucherAmountPO n:list){
			res+=n.getDebitAmount();
		}
		return res;
	}

	/**
	 * 
	 * @param list
	 * @return 贷-借
	 */
	public double Cal(List<VoucherAmountPO> list) {
		return CreditCal(list)-DebitCal(list);
	}

	/**
	 * 
	 * @param list
	 * @return 借-贷
	 */
	public double Cal2(List<VoucherAmountPO> list) {
		return -CreditCal(list)+DebitCal(list);
	}
	
	public String FirstMonth(String time){
		String temp[]=time.split("-");
		int year=Integer.parseInt(temp[0]);
		return String.valueOf(year)+"-01";
	}
	
	public String lastTime(String time){
		String temp[]=time.split("-");
		int year=Integer.parseInt(temp[0]);
		int month=Integer.parseInt(temp[1]);
		if(month!=1)
			month--;
		else{
			year--;
			month=12;
		}
		if(month<10)
			return String.valueOf(year)+"-0"+String.valueOf(month);
		else
			return String.valueOf(year)+"-"+String.valueOf(month);
		
	}
	/**
	 * 
	 * @param time
	 * @return 期末，期初，年初，第一个月的期末
	 */
	public Map<String,double[]> tempCal(String time,String id){
		BalanceSheetImpl bs=new BalanceSheetImpl();
		
		Map<String, ArrayList<BalanceSheetItemVo>> s1=bs.getBalanceSheet(id,lastTime(time));
		Map<String, ArrayList<BalanceSheetItemVo>> s2=bs.getBalanceSheet(id,time);
		Map<String, ArrayList<BalanceSheetItemVo>> s3=bs.getBalanceSheet(id,FirstMonth(time));
		
		Map<String,double[]> res=new HashMap<String,double[]>();
		ArrayList<BalanceSheetItemVo> temp1=s1.get("流动资产");
		ArrayList<BalanceSheetItemVo> temp2=s2.get("流动资产");
		ArrayList<BalanceSheetItemVo> temp3=s3.get("流动资产");
				
		res.put("货币资金", new double[]{temp1.get(0).getEnding_balance(),temp2.get(0).getEnding_balance()
				,temp2.get(0).getBeginning_balance(),temp3.get(0).getEnding_balance()});
		res.put("短期投资", new double[]{temp1.get(1).getEnding_balance(),temp2.get(1).getEnding_balance()
				,temp2.get(1).getBeginning_balance(),temp3.get(1).getEnding_balance()});
		res.put("应收票据", new double[]{temp1.get(2).getEnding_balance(),temp2.get(2).getEnding_balance()
				,temp2.get(2).getBeginning_balance(),temp3.get(2).getEnding_balance()});
		res.put("应收账款", new double[]{temp1.get(3).getEnding_balance(),temp2.get(3).getEnding_balance()
				,temp2.get(3).getBeginning_balance(),temp3.get(3).getEnding_balance()});
		res.put("预付账款", new double[]{temp1.get(4).getEnding_balance(),temp2.get(4).getEnding_balance()
				,temp2.get(4).getBeginning_balance(),temp3.get(4).getEnding_balance()});
		res.put("应收股利", new double[]{temp1.get(5).getEnding_balance(),temp2.get(5).getEnding_balance()
				,temp2.get(5).getBeginning_balance(),temp3.get(5).getEnding_balance()});
		res.put("应收利息", new double[]{temp1.get(6).getEnding_balance(),temp2.get(6).getEnding_balance()
				,temp2.get(6).getBeginning_balance(),temp3.get(6).getEnding_balance()});
		
		
		temp1=s1.get("流动负债");
		temp2=s2.get("流动负债");
		temp3=s3.get("流动负债");
		
		res.put("短期借款", new double[]{temp1.get(0).getEnding_balance(),temp2.get(0).getEnding_balance()
				,temp2.get(0).getBeginning_balance(),temp3.get(0).getEnding_balance()});
		res.put("应付票据", new double[]{temp1.get(1).getEnding_balance(),temp2.get(1).getEnding_balance()
				,temp2.get(1).getBeginning_balance(),temp3.get(1).getEnding_balance()});
		res.put("应付账款", new double[]{temp1.get(2).getEnding_balance(),temp2.get(2).getEnding_balance()
				,temp2.get(2).getBeginning_balance(),temp3.get(2).getEnding_balance()});
		res.put("预收账款", new double[]{temp1.get(3).getEnding_balance(),temp2.get(3).getEnding_balance()
				,temp2.get(3).getBeginning_balance(),temp3.get(3).getEnding_balance()});
		
			
		temp1=s1.get("非流动资产");
		temp2=s2.get("非流动资产");
		temp3=s3.get("非流动资产");
		
		res.put("固定资产", new double[]{temp1.get(2).getEnding_balance(),temp2.get(2).getEnding_balance()
				,temp2.get(2).getBeginning_balance(),temp3.get(2).getEnding_balance()});
		res.put("长期债券投资", new double[]{temp1.get(0).getEnding_balance(),temp2.get(0).getEnding_balance()
				,temp2.get(0).getBeginning_balance(),temp3.get(0).getEnding_balance()});
		res.put("长期股权投资", new double[]{temp1.get(1).getEnding_balance(),temp2.get(1).getEnding_balance()
				,temp2.get(1).getBeginning_balance(),temp3.get(1).getEnding_balance()});
		res.put("无形资产", new double[]{temp1.get(9).getEnding_balance(),temp2.get(9).getEnding_balance()
				,temp2.get(9).getBeginning_balance(),temp3.get(9).getEnding_balance()});
		res.put("在建工程", new double[]{temp1.get(5).getEnding_balance(),temp2.get(5).getEnding_balance()
				,temp2.get(5).getBeginning_balance(),temp3.get(5).getEnding_balance()});
		
		
		temp1=s1.get("非流动负债");
		temp2=s2.get("非流动负债");
		temp3=s3.get("非流动负债");
		
		res.put("长期借款", new double[]{temp1.get(0).getEnding_balance(),temp2.get(0).getEnding_balance()
				,temp2.get(0).getBeginning_balance(),temp3.get(0).getEnding_balance()});
		
		temp1=s1.get("所有者权益");
		temp2=s2.get("所有者权益");
		temp3=s3.get("所有者权益");
		res.put("实收资本", new double[]{temp1.get(0).getEnding_balance(),temp2.get(0).getEnding_balance()
				,temp2.get(0).getBeginning_balance(),temp3.get(0).getEnding_balance()});
		return res;
	}
	
	public double sumList(List<Double> s){
		double res=0;
		for(int i=0;i<s.size();i++){
			res+=s.get(i);
		}
		return res;
	}
}

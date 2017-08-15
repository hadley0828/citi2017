package businesslogic;

import java.util.List;

import po.VoucherAmountPO;

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

	public double Cal(List<VoucherAmountPO> list) {
		return CreditCal(list)-DebitCal(list);
	}
	
	public double Calbalance(List<VoucherAmountPO> list) {
		return Math.abs(CreditCal(list)-DebitCal(list));
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
}

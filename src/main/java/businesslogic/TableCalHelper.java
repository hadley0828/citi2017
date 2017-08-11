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
	
}

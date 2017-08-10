package dataservice;

import java.util.List;

import po.VoucherAmountPO;

/**
 * 
 * @author hyf
 *
 */

public interface ProfitAndCashService {
	/**
	 * 
	 * @param year   yyyy年份
	 * @param accounting_id 会计科目id
	 * @return 获得该年份对应会计科目id所有的信息
	 */
	public List<VoucherAmountPO> getVourchersByYear(String year,String accounting_id);
	
	/**
	 * 
	 * @param year   yyyy-xx 年月
	 * @param accounting_id 会计科目id
	 * @return 获得该年份指定期的对应会计科目id所有的信息
	 */
	public List<VoucherAmountPO> getVourchersByPeriod(String year,String accounting_id);
}

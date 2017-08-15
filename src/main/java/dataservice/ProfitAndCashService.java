package dataservice;

import po.VoucherAmountPO;

import java.util.List;

/**
 * 
 * @author hyf
 *
 */

public interface ProfitAndCashService {
	
	/**
	 * 
	 * @param period yyyy-xx 年月
	 * @param accounting_id 会计科目id
	 * @return 获取该时间（包括该期）之前的所有对应会计科目id的信息
	 */
	public List<VoucherAmountPO> getVourchersBefore(String period,String accounting_id);
	
	/**
	 * 
	 * @param year   yyyy年份
	 * @param accounting_id 会计科目id
	 * @return 获得该年份对应会计科目id所有的信息(4位的id需要获取所有前四位匹配的项目，如id=2221时，222100101将包含在内，下方同理)
	 */
	public List<VoucherAmountPO> getVourchersByYear(String year,String accounting_id);
	
	/**
	 * 
	 * @param period   yyyy-xx 年月
	 * @param accounting_id 会计科目id
	 * @return 获得该年份指定期的对应会计科目id所有的信息
	 */
	public List<VoucherAmountPO> getVourchersByPeriod(String period,String accounting_id);
}

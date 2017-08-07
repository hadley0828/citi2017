package businesslogicservice;

import vopo.CashFlowVo;

/**
 * 
 * @author hyf
 *
 */
public interface CashFlowTableService {
	/**
	 * 
	 * @param time yyyy-xx 某年某月
	 * @return 返回该月现金流量表的信息
	 */
	public CashFlowVo CashFlowTable_month(String time);
	
	/**
	 * 
	 * @param time yyyy 某年
	 * @return 返回当年累积金额的现金流量表的信息
	 */
	public CashFlowVo CashFlowTable_year(String time);
}

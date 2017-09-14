package businesslogicservice;

import vo.CashFlowVo;

/**
 * 
 * @author hyf
 *
 */
public interface CashFlowTableService {
	/**
	 * 
	 * @param time yyyy-xx 某年某月
	 * @param company_id 公司id
	 * @return 返回该月现金流量表的信息
	 */
	public CashFlowVo CashFlowTable_month(String time,String company_id);
	
	/**
	 * 
	 * @param time yyyy 某年
	 * @param company_id 公司id
	 * @return 返回当年累积金额的现金流量表的信息
	 */
	public CashFlowVo CashFlowTable_year(String time,String company_id);

	/**
	 * 得到经营现金净流量
	 * @param time yyyy-xx 某年某月
	 * @param company_id 公司id
	 * @return =
	 */
	public double getValue(String company_id, String time);

	/**
	 * 得到现金流量表里的 现金流入、现金流出、现金池内留存的现金
	 * @param company_id
	 * @param time
	 * @return
	 */
	public double[] getCashFlow(String company_id, String time);
	
	/**
	 * 
	 * @param company_id
	 * @param time yyyy-mm
	 * @param path
	 */
	public void CreateCashFlowTable(String company_id,String time,String path);
}

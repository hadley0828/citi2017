package businesslogicservice;

import vo.ProfitTableVo;

/**
 * 
 * @author hyf
 *
 */
public interface ProfitTableService {
	/**
	 * 
	 * @param id 公司id
	 * @param time 某年某期 yyyy-xx
	 * @return 返回该期的营业收入相关数据
	 */
	public ProfitTableVo BusinessIncome_period(String id,String time);
	
	/**
	 * 
	 * @param id 公司id
	 * @param time 年份 yyyy
	 * @return 返回该年的营业收入相关数据
	 */
	public ProfitTableVo BusinessIncome_year(String id,String time);

	/**
	 * 得到净利润、利润总额、主营业务成本、销售费用、管理费用、财务费用、营业成本、其他业务收入、本期主营业务收入、上一期主营业务收入
	 * @param company_id 公司id
	 * @param time 时间
	 * @return
	 */
	public double[] getValue(String company_id, String time);

	/**
	 * 得到净利润
	 * @param company_id 公司id
	 * @param time 时间
	 * @return
	 */
	public double getProfit(String company_id, String time);
	
	/**
	 * 
	 * @param company_id
	 * @param time yyyy-mm
	 * @param path
	 */
	public void CreateProfitTable(String company_id,String time,String path);
}

package businesslogicservice;

import vo.BusinessIncomeVo;
import vo.BusinessProfit_And_OtherVo;

/**
 * 
 * @author hyf
 *
 */
public interface ProfitTableService {
	/**
	 * 
	 * @param time 某年某期 yyyy-xx
	 * @return 返回该期的营业收入相关数据
	 */
	public BusinessIncomeVo BusinessIncome_period(String time);
	
	/**
	 * 
	 * @param time 年份 yyyy
	 * @return 返回该年的营业收入相关数据
	 */
	public BusinessIncomeVo BusinessIncome_year(String time);
	
	/**
	 * @param time 某年某期 yyyy-xx
	 * @return 返回该期包括营业利润、利润总额、净利润的信息
	 */
	public BusinessProfit_And_OtherVo BusinessProfit_And_OtherVo_period(String time);
	
	/**
	 * @param time 年份 yyyy
	 * @return 返回该年包括营业利润、利润总额、净利润的信息
	 */
	public BusinessProfit_And_OtherVo BusinessProfit_And_OtherVo_year(String time);
	
	
}

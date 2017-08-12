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
	 * @param time 某年某期 yyyy-xx
	 * @return 返回该期的营业收入相关数据
	 */
	public ProfitTableVo BusinessIncome_period(String time);
	
	/**
	 * 
	 * @param time 年份 yyyy
	 * @return 返回该年的营业收入相关数据
	 */
	public ProfitTableVo BusinessIncome_year(String time);
	
	
}

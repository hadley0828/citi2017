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
	 * @param time yyyy-xx 年月
	 * @param id1 借方
	 * @param id2 贷方
	 * @param company_id 公司id
	 * @return 获取凭证中借方含有科目ID=id1，贷方含有科目ID=id2的项目，取该两个科目对应金额中的较小值
	 */
	public List<Double> getGivenVourchers(String time,String id1,String id2,String company_id);
	
	/**
	 * 
	 * @param time yyyy 年
	 * @param id1 借方
	 * @param id2 贷方
	 * @param company_id 公司id
	 * @return 获取凭证中借方含有科目ID=id1，贷方含有科目ID=id2的项目，取该两个科目对应金额中的较小值
	 */
	public List<Double> getGivenVourchersByYear(String time,String id1,String id2,String company_id);
	
	/**
	 * 
	 * @param period yyyy-xx 年月
	 * @param company_id 公司id
	 * @return 获取该时间（包括该期）之前的所有对应会计科目id的信息
	 */
	public List<VoucherAmountPO> getVourchersBefore(String period,String company_id);
	
	/**
	 * 
	 * @param year   yyyy年份
	 * @param company_id 公司id
	 * @return 获得该年份对应公司id所有的信息
	 */
	public List<VoucherAmountPO> getVourchersByYear(String year,String company_id);
	
	/**
	 * 
	 * @param period   yyyy-xx 年月
	 * @param company_id 公司id
	 * @return 获得该年份指定期的对应会计科目id所有的信息
	 */
	public List<VoucherAmountPO> getVourchersByPeriod(String period, String company_id);
}

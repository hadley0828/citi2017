package businesslogicservice;

import vo.SupplyChainPerformanceVo;

/**
 * 
 * @author hyf
 *
 */
public interface SupplyChainService {
	
	/**
	 * 
	 * @param company_id 公司id
	 * @param period 时间 年月yyyy-mm
	 * @return 供应链绩效评价
	 */
	public SupplyChainPerformanceVo SupplyChain_Supplier(String company_id,String period);
	
}

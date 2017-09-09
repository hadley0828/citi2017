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
	 * @param Supplier_id     供应商
	 * @param Manufacturer_id 生产商
	 * @param Distributor_id  分销商
	 * @param period 时间 年月yyyy-mm
	 * @return 供应链绩效评价
	 */
	public SupplyChainPerformanceVo SupplyChain_Supplier(String Supplier_id,String Manufacturer_id,String Distributor_id,String period);
	
}

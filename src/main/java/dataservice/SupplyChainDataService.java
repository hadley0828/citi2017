package dataservice;

import java.util.List;

import po.SupplyChainPO;
import po.VoucherAmountPO;

/**
 * 
 * @author hyf
 *
 */
public interface SupplyChainDataService {
	/**
     * 得到公司截至某一天所有的辅助信息部分的公司名称
     * @param company_id 公司
     * @param time 最后时间 yyyy-mm-dd
     * @return
     */
	public List<String> getAcountReceivable(String company_id, String time);
	
	/**
     * 得到公司截至某一天所有的原材料/产品的种类名称
     * @param company_id 公司
     * @param time 最后时间 yyyy-mm-dd
     * @return
     */
	public List<String> getRawMaterialAndProduct(String company_id, String time);
	
	/**
	 * 
	 * @param po
	 */
	public void SaveSupplyChain(SupplyChainPO po);
	
	/**
	 * 
	 * @return
	 */
	public List<SupplyChainPO>  GetSupplyChains();
	
	/**
	 * 
	 * @param id 科目id
	 * @return 获取期初，没有则返回0
	 */
	public double GetInitial(String id);
	
	/**
	 * 
	 * @param id     科目id
	 * @param company 辅助信息部分的公司名称
	 * @param time   yyyy-mm-dd
	 * @return 该time及之前的所有符合的
	 */
	public List<VoucherAmountPO> GetVoucherAmountsWithCompany(String id,String company,String time);
	
	/**
	 * 
	 * @param id     科目id
	 * @param product 原材料或产品名称
	 * @param time   yyyy-mm-dd
	 * @return 该time及之前的所有符合的
	 */
	public List<VoucherAmountPO> GetVoucherAmountsWithProduct(String id,String product,String time);
	
}

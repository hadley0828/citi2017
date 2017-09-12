package dataservice;

import po.SupplyChainPO;
import po.VoucherAmountPO;

import java.util.List;

/**
 * 
 * @author hyf
 *
 */
public interface SupplyChainDataService {
	
	/**
	 * 
	 * @param company_id
	 * @return 由一个id获得三个公司的id，按供应商，生产商，分销商的顺序
	 */
	public String[] getTheCompanys(String company_id);
	
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
	public double GetInitial(String id,String company_id);
	
	/**
	 * @param company_id 进行融资的公司id
	 * @param id     科目id
	 * @param company 辅助信息部分的公司名称
	 * @param time   yyyy-mm-dd
	 * @return 该time及之前的所有符合的
	 */
	public List<VoucherAmountPO> GetVoucherAmountsWithCompany(String company_id,String id,String company,String time);
	
	/**
	 * @param company_id 进行融资的公司id
	 * @param id     科目id
	 * @param product 原材料或产品名称
	 * @param time   yyyy-mm-dd
	 * @return 该time及之前的所有符合的
	 */
	public List<VoucherAmountPO> GetVoucherAmountsWithProduct(String company_id,String id,String product,String time);
	
	/**
	 * 
	 * @param id
	 * @return 根据公司id获得名称
	 */
	public String getCompany(String id);
	
}

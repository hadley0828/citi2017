package dataservice;

import java.util.List;

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
}

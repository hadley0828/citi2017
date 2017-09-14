package businesslogicservice;

import po.SupplyChainPO;
import vo.CompanyVo;
import vo.SupplyChainPerformanceVo;

import java.util.List;

/**
 * 
 * @author hyf
 *
 */
public interface SupplyChainService {
	
	/**
	 * 
	 * @param company_id
	 * @return 由一个id获得三个公司的id，按供应商，生产商，分销商的顺序
	 */
	public String[] getTheCompanys(String company_id);
	
	/**
	 * 
	 * @param Supplier_id     供应商
	 * @param Manufacturer_id 生产商
	 * @param Distributor_id  分销商
	 * @param period 时间 年月yyyy-mm
	 * @return 供应链绩效评价
	 */
	public SupplyChainPerformanceVo SupplyChain_Supplier(String Supplier_id,String Manufacturer_id,String Distributor_id,String period);
	
	/**
	 * 
	 * @param company_id 公司id
	 * @return 应收帐款对象
	 */
	public List<String> AcountReceivable(String company_id,String time);
	
	/**
	 * @param company_id 进行融资的公司id
	 * @param company 公司名称
	 * @return 应收帐款净额
	 */
	public double getNetReceivables(String company_id,String company,String time);
	
	/**
	 * 
	 * @param MortgageAmount 应收帐款抵押额
	 * @param company_id 
	 * @param time 时间 yyyy-mm-dd
	 * @return 应收帐款融资
	 */
	public double ReceivableFinacing(double MortgageAmount,String company_id,String time);
	
	/**
	 * 
	 * @param company_id
	 * @return 库存种类
	 */
	public List<String> InventoryTypes(String company_id,String time);
	
	/**
	 * @param company_id 进行融资的公司id
	 * @param product 产品/原材料
	 * @return 库存净额
	 */
	public double getNetInventory(String company_id,String product,String time);
	
	
	/**
	 * 
	 * @param Amount
	 * @param company_id
	 * @param time 时间 yyyy-mm-dd
	 * @return 动产质押融资
	 */
	public double PledgeMovables(double Amount,String company_id,String time);
	
	/**
	 * 
	 * @return 融资需求信息
	 */
	public List<SupplyChainPO>  GetSupplyChains();
	
	/**
	 * 
	 * @param companyName
	 * @return 获取公司信息
	 */
	public CompanyVo getCompanyInfo(String companyName);
}

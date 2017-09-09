package businesslogic;

import businesslogicservice.SupplyChainService;
import data.ProfitAndCashServiceImpl;
import dataservice.ProfitAndCashService;
import vo.SupplyChainPerformanceVo;

/**
 * 
 * @author hyf
 *
 */
public class SupplyChainImpl implements SupplyChainService{
	
	ProfitAndCashService DATA;
	TableCalHelper helper;
	
	public SupplyChainImpl(){
		DATA=new ProfitAndCashServiceImpl();
		helper=new TableCalHelper();
	}

	public SupplyChainPerformanceVo SupplyChain_Supplier(String company_id,String period) {
		
		
		double[][]Supplier=new double[4][2];
		
		double[][]Manufacturer=new double[4][2];
		
		double[][]Distributor=new double[4][2];
		
		double[]Supply_chain=new double[4];
		
		return new SupplyChainPerformanceVo(Supplier,Manufacturer,Distributor,Supply_chain);
	}
}

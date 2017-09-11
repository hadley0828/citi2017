package data;

import java.util.List;

import dataservice.SupplyChainDataService;
import po.SupplyChainPO;
import po.VoucherAmountPO;

public class SupplyChainDataServiceImpl implements SupplyChainDataService{

	public List<String> getAcountReceivable(String company_id, String time) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getRawMaterialAndProduct(String company_id, String time) {
		// TODO Auto-generated method stub
		return null;
	}

	public void SaveSupplyChain(SupplyChainPO po) {
		// TODO Auto-generated method stub
		
	}

	public List<SupplyChainPO> GetSupplyChains() {
		// TODO Auto-generated method stub
		return null;
	}

	public double GetInitial(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<VoucherAmountPO> GetVoucherAmountsWithCompany(String id, String company, String time) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<VoucherAmountPO> GetVoucherAmountsWithProduct(String id, String product, String time) {
		// TODO Auto-generated method stub
		return null;
	}

}

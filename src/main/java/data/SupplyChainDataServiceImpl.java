package data;

import dataservice.SupplyChainDataService;
import po.SupplyChainPO;
import po.VoucherAmountPO;

import java.util.List;

public class SupplyChainDataServiceImpl implements SupplyChainDataService{

	public List<String> getAcountReceivable(String company_id, String time) {

		return null;
	}

	public List<String> getRawMaterialAndProduct(String company_id, String time) {
		return null;
	}

	public void SaveSupplyChain(SupplyChainPO po) {

	}

	public List<SupplyChainPO> GetSupplyChains() {
		return null;
	}

	public double GetInitial(String id) {
		return 0;
	}

	public List<VoucherAmountPO> GetVoucherAmountsWithCompany(String id, String company, String time) {
		return null;
	}

	public List<VoucherAmountPO> GetVoucherAmountsWithProduct(String id, String product, String time) {
		return null;
	}

}

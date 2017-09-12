package data;

import dataservice.SupplyChainDataService;
import po.SupplyChainPO;
import po.VoucherAmountPO;

import java.util.List;
import java.util.Map;

public class SupplyChainDataServiceImpl implements SupplyChainDataService{

	SqlManager sqlManager = SqlManager.getSqlManager();

	@Override
	public String[] getTheCompanys(String company_id) {
		sqlManager.getConnection();

		String[] chain = new String[]{"","",""};

		sqlManager.releaseAll();
		return new String[0];
	}

	@Override
	public List<String> getAcountReceivable(String company_id, String time) {
		return null;
	}

	@Override
	public List<String> getRawMaterialAndProduct(String company_id, String time) {
		return null;
	}

	@Override
	public void SaveSupplyChain(SupplyChainPO po) {

	}

	@Override
	public List<SupplyChainPO> GetSupplyChains() {
		return null;
	}

	@Override
	public double GetInitial(String id, String company_id) {
		return 0;
	}

	@Override
	public List<VoucherAmountPO> GetVoucherAmountsWithCompany(String company_id, String id, String company, String time) {
		return null;
	}

	@Override
	public List<VoucherAmountPO> GetVoucherAmountsWithProduct(String company_id, String id, String product, String time) {
		return null;
	}

	@Override
	public String getCompany(String id) {
		sqlManager.getConnection();

		String sql = "select company_name from account_set where company_id=?";
		Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{id});

		String companyName = map.get("company_name").toString();
		sqlManager.releaseAll();
 		return companyName;
	}

	private String getCompanyChainPlace(String company_id){
		String sql = "select chain_place from account_set where company_id=?";
		Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{company_id});

		return map.get("chain_place").toString();
	}
}

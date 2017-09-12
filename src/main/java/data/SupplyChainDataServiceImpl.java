package data;

import dataservice.SupplyChainDataService;
import po.SupplyChainPO;
import po.VoucherAmountPO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SupplyChainDataServiceImpl implements SupplyChainDataService{

	SqlManager sqlManager = SqlManager.getSqlManager();

	@Override
	public String[] getTheCompanys(String company_id) {
		sqlManager.getConnection();

		String[] chain = new String[]{"","",""};
		Map<String,Object> map;

		String chainPlace = getCompanyChainPlace(company_id);
		switch (chainPlace){
			case "供应商":
				String sql1 = "select * from supply_chain where upper_id=?";
				map = sqlManager.querySimple(sql1,new Object[]{company_id});
				chain = getSupplyChain(map);
				break;
			case "生产商":
				String sql2 = "select * from supply_chain where middle_id=?";
				map = sqlManager.querySimple(sql2,new Object[]{company_id});
				chain = getSupplyChain(map);
				break;
			case "分销商":
				String sql3 = "select * from supply_chain where down_id=?";
				map = sqlManager.querySimple(sql3,new Object[]{company_id});
				chain = getSupplyChain(map);
				break;
		}

		sqlManager.releaseAll();
		return chain;
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
		sqlManager.getConnection();

		List<Object> params = new ArrayList<>();
		params.add(Date.valueOf(po.getTime()));
		params.add(po.getCompany());
		params.add(po.getFinacingType());
		params.add(po.getNet());
		params.add(po.getProposedFinancingScale());

		String sql = sqlManager.appendSQL("insert into financing values",params.size());
		sqlManager.executeUpdateByList(sql,params);
		sqlManager.releaseAll();
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

	private String[] getSupplyChain(Map<String,Object> map){
		String[] supplyChain = new String[]{"","",""};
		supplyChain[0] = map.get("upper_id").toString();
		supplyChain[1] = map.get("middle_id").toString();
		supplyChain[2] = map.get("down_id").toString();
		return supplyChain;
	}
}

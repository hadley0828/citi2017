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
		sqlManager.getConnection();

		ArrayList<String> list = new ArrayList<>();
		String sql = "select distinct company_name from credit_item where company_id=? and voucher_id in(select v_id from voucher where company_id=? and date<=?)";
		ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{company_id,company_id,time});

		for (Map<String,Object> map : maps){
			list.add(map.get("company_name").toString());
		}

		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<String> getRawMaterialAndProduct(String company_id, String time) {
		sqlManager.getConnection();

		ArrayList<String> list = new ArrayList<>();

		String sql = "select distinct material_variety from inventory_material where company_id=? and voucher_id in(select v_id from voucher  where company_id=? and date<=?)";
		ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{company_id,company_id,time});

		for (Map<String,Object> map : maps){
			list.add(map.get("material_variety").toString());
		}

		String sql2 = "select distinct product_variety from inventory_product where company_id=? and voucher_id in(select v_id from voucher  where company_id=? and date<=?)";
		ArrayList<Map<String,Object>> maps2 = sqlManager.queryMulti(sql2,new Object[]{company_id,company_id,time});

		for (Map<String,Object> map : maps2){
			list.add(map.get("product_variety").toString());
		}
		sqlManager.releaseAll();
		return list;
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
		sqlManager.getConnection();

		List<SupplyChainPO> list = new ArrayList<>();
		String sql = "select * from financing";
		ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{});

		for (Map<String,Object> map : maps){
			list.add(getSupplyChainPOByMap(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public double GetInitial(String id, String company_id) {
		sqlManager.getConnection();
		String sql = "select * from subjects_balance where date in (select min(date) from subjects_balance where subjects_id=? and company_id=?) and subjects_id=? and company_id=?";
		Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{id,company_id,id,company_id});

		double initial = Double.parseDouble(map.get("balance").toString());
		sqlManager.releaseAll();
		return initial;
	}

	@Override
	public List<VoucherAmountPO> GetVoucherAmountsWithCompany(String company_id, String id, String company, String time) {
		sqlManager.getConnection();

		List<VoucherAmountPO> list = new ArrayList<>();
		String sql = "select * from voucher_amount where subject=? and company_id=? and v_id in (select v_id from voucher  where company_id=? and date<=?) and v_id in (select voucher_id from credit_item where company_id=? and company_name=?)";
		ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{id,company_id,company_id,time,company_id,company});

		for (Map<String,Object> map : maps){
			list.add(getVoucherAmountPOByMap(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<VoucherAmountPO> GetVoucherAmountsWithProduct(String company_id, String id, String product, String time) {

		sqlManager.getConnection();

		List<VoucherAmountPO> list = new ArrayList<>();
		String sql = "select * from voucher_amount where subject=? and company_id=? and v_id in (select v_id from voucher  where company_id=? and date<=?) and v_id in (select voucher_id from inventory_material where company_id=? and material_variety =? union select voucher_id from inventory_product where company_id=? and product_variety=?)";

		ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{id,company_id,company_id,time,company_id,product,company_id,product});

		for (Map<String,Object> map : maps){
			list.add(getVoucherAmountPOByMap(map));
		}
		sqlManager.releaseAll();
		return list;
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

	private SupplyChainPO getSupplyChainPOByMap(Map<String,Object> map){
		SupplyChainPO po = new SupplyChainPO();
		po.setTime(map.get("time").toString());
		po.setCompany(map.get("company_name").toString());
		po.setFinacingType(map.get("financing_type").toString());
		po.setNet(Double.parseDouble(map.get("net").toString()));
		po.setProposedFinancingScale(Double.parseDouble(map.get("scale").toString()));

		return po;
	}

	private VoucherAmountPO getVoucherAmountPOByMap(Map<String,Object> map){
		VoucherAmountPO po = new VoucherAmountPO();

		po.setV_id(map.get("v_id").toString());
		po.setA_id(map.get("a_id").toString());
		po.setDigest(map.get("abstract").toString());
		po.setSubject(map.get("subject").toString());
		po.setDebitAmount(Double.valueOf(map.get("debit_amount").toString()));
		po.setCreditAmount(Double.valueOf(map.get("credit_amount").toString()));

		return po;
	}
}

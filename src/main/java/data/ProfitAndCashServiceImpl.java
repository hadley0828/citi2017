package data;

import dataservice.ProfitAndCashService;
import po.VoucherAmountPO;
import util.DatesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfitAndCashServiceImpl implements ProfitAndCashService{

    SqlManager sqlManager = SqlManager.getSqlManager();

	public List<VoucherAmountPO> getVourchersByYear(String year, String accounting_id) {

	    sqlManager.getConnection();
        ArrayList<VoucherAmountPO> list = new ArrayList<>();
        String sql = "SELECT * FROM voucher_amount WHERE v_id IN(SELECT v_id FROM voucher WHERE year(date)=?) AND subject=?";
        ArrayList<Map<String,Object>> datalist = sqlManager.queryMulti(sql,new Object[]{year,accounting_id});

        for(Map<String,Object> map : datalist){
            list.add(getVoucherAmountPO(map));
        }
        sqlManager.releaseAll();
		return list;
	}

	public List<VoucherAmountPO> getVourchersByPeriod(String period, String accounting_id) {

	    sqlManager.getConnection();
	    ArrayList<VoucherAmountPO> list = new ArrayList<>();
	    String sql = "SELECT * FROM voucher_amount WHERE v_id IN(SELECT v_id FROM voucher WHERE year(date)=? AND month(date)=?) AND subject=?";
	    Map<String,String> dateMap = DatesUtil.datesParser(period);

	    ArrayList<Map<String,Object>> datalist = sqlManager.queryMulti(sql,new Object[]{dateMap.get("year"),dateMap.get("month"),accounting_id});
	    for(Map<String,Object> map : datalist){
	        list.add(getVoucherAmountPO(map));
        }
        sqlManager.releaseAll();
	    return list;
	}

    private VoucherAmountPO getVoucherAmountPO(Map<String,Object> map){

        VoucherAmountPO po = new VoucherAmountPO();
        po.setV_id(map.get("v_id").toString());
        po.setA_id(map.get("a_id").toString());
        po.setDigest(map.get("abstract").toString());
        po.setSubject(map.get("subject").toString());
        po.setDebitAmount(Double.parseDouble(map.get("debit_amount").toString()));
        po.setCreditAmount(Double.parseDouble(map.get("credit_amount").toString()));

        return po;
    }

}

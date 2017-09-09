package data;

import dataservice.ProfitAndCashService;
import po.VoucherAmountPO;
import util.DatesUtil;
import util.MultiValueMap.LinkedMultiValueMap;
import util.MultiValueMap.MultiValueMap;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ProfitAndCashServiceImpl implements ProfitAndCashService{

    SqlManager sqlManager = SqlManager.getSqlManager();

	public List<VoucherAmountPO> getVourchersByYear(String year, String accounting_id,String company_id) {

	    sqlManager.getConnection();
        ArrayList<VoucherAmountPO> list = new ArrayList<>();
        String sql = "SELECT * FROM voucher_amount WHERE v_id IN(SELECT v_id FROM voucher WHERE year(date)=? AND company_id=?) AND subject LIKE ? AND company_id=?";
        ArrayList<Map<String,Object>> datalist = sqlManager.queryMulti(sql,new Object[]{year,company_id,accounting_id+"%",company_id});

        for(Map<String,Object> map : datalist){
            list.add(getVoucherAmountPO(map));
        }
        sqlManager.releaseAll();
		return list;
	}

	public List<VoucherAmountPO> getVourchersByPeriod(String period, String accounting_id,String company_id) {

	    sqlManager.getConnection();
	    ArrayList<VoucherAmountPO> list = new ArrayList<>();
	    String sql = "SELECT * FROM voucher_amount WHERE v_id IN(SELECT v_id FROM voucher WHERE year(date)=? AND month(date)=? AND company_id=?) AND subject LIKE? AND company_id=?";
	    Map<String,String> dateMap = DatesUtil.datesParser(period);

	    ArrayList<Map<String,Object>> datalist = sqlManager.queryMulti(sql,new Object[]{dateMap.get("year"),dateMap.get("month"),company_id,accounting_id+"%",company_id});
	    for(Map<String,Object> map : datalist){
	        list.add(getVoucherAmountPO(map));
        }
        sqlManager.releaseAll();
	    return list;
	}

    public List<VoucherAmountPO> getVourchersBefore(String period, String accounting_id,String company_id) {
	    sqlManager.getConnection();

	    ArrayList<VoucherAmountPO> list = new ArrayList<>();
	    String sql = "SELECT * FROM voucher_amount WHERE v_id IN(SELECT v_id FROM voucher WHERE date<=? AND company_id=? ) AND subject=? AND company_id=?";
//        System.out.println(fillDate(period));
        ArrayList<Map<String,Object>> datalist = sqlManager.queryMulti(sql,new Object[]{Date.valueOf(fillDate(period)),company_id,accounting_id,company_id});
        for(Map<String,Object> map : datalist){
            list.add(getVoucherAmountPO(map));
        }

	    sqlManager.releaseAll();
	    return list;
    }

    public List<Double> getGivenVourchers(String time, String id1, String id2,String company_id) {
	    sqlManager.getConnection();

        Map<String,String> dateMap = DatesUtil.datesParser(time);

        MultiValueMap<String,Object> amountMap = new LinkedMultiValueMap<>();

	    String sql1 = "SELECT v_id,debit_amount FROM voucher_amount WHERE v_id IN(SELECT v_id FROM voucher WHERE year(date)=? AND month(date)=? AND company_id=?) AND subject=? AND debit_amount<>0 AND company_id=?";
        String sql2 = "SELECT v_id,credit_amount FROM voucher_amount WHERE v_id IN(SELECT v_id FROM voucher WHERE year(date)=? AND month(date)=? AND company_id=?) AND subject=? AND credit_amount<>0 AND company_id=?";
	    ArrayList<Map<String,Object>> datalist1 = sqlManager.queryMulti(sql1,new Object[]{dateMap.get("year"),dateMap.get("month"),company_id,id1,company_id});
	    ArrayList<Map<String,Object>> datalist2 = sqlManager.queryMulti(sql2,new Object[]{dateMap.get("year"),dateMap.get("month"),company_id,id2,company_id});

	    for (Map<String,Object> map : datalist1){
	        amountMap.add(map.get("v_id").toString(),map.get("debit_amount"));
        }
        for (Map<String,Object> map : datalist2){
	        amountMap.add(map.get("v_id").toString(),map.get("credit_amount"));
        }


        List<Double> result = new ArrayList<>();

        Set<String> keyset = amountMap.keySet();
        for (String key : keyset){
            List<Object> values = amountMap.getValues(key);
            if (values.size() != 2){
                continue;
            }else{
                double debit = (double) values.get(0);
                double credit = (double) values.get(1);
                result.add(Math.min(debit,credit));
            }
        }

        sqlManager.releaseAll();
        return result;
    }

    public List<Double> getGivenVourchersByYear(String time, String id1, String id2,String company_id) {

	    sqlManager.getConnection();

        MultiValueMap<String,Object> amountMap = new LinkedMultiValueMap<>();

        String sql1 = "SELECT v_id,debit_amount FROM voucher_amount WHERE v_id IN(SELECT v_id FROM voucher WHERE year(date)=? AND company_id=?) AND subject=? AND debit_amount<>0 AND company_id=?";
        String sql2 = "SELECT v_id,credit_amount FROM voucher_amount WHERE v_id IN(SELECT v_id FROM voucher WHERE year(date)=? AND company_id=?) AND subject=? AND credit_amount<>0 AND company_id=?";
        ArrayList<Map<String,Object>> datalist1 = sqlManager.queryMulti(sql1,new Object[]{time,company_id,id1,company_id});
        ArrayList<Map<String,Object>> datalist2 = sqlManager.queryMulti(sql2,new Object[]{time,company_id,id2,company_id});

        for (Map<String,Object> map : datalist1){
            amountMap.add(map.get("v_id").toString(),map.get("debit_amount"));
        }
        for (Map<String,Object> map : datalist2){
            amountMap.add(map.get("v_id").toString(),map.get("credit_amount"));
        }


        List<Double> result = new ArrayList<>();

        Set<String> keyset = amountMap.keySet();
        for (String key : keyset){
            List<Object> values = amountMap.getValues(key);
            if (values.size() != 2){
                continue;
            }else{
                double debit = (double) values.get(0);
                double credit = (double) values.get(1);
                result.add(Math.min(debit,credit));
            }
        }

        sqlManager.releaseAll();
        return result;
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

    private String fillDate(String period){
        String[] date = period.split("-");
        String year = date[0];
        String month = date[1];

        switch (month){
            case "01":case "03":case "05":case "07":case "08":case "10":case "12":
                return year+"-"+month+"-31";
            case "04":case "06":case "09":case "11":
                return year+"-"+month+"-30";
            case "02":
                if((Integer.valueOf(year)%4==0&&Integer.valueOf(year)%100!=0)||Integer.valueOf(year)%400==0){
                    return year+"-"+month+"-29";
                }else{
                    return year+"-"+month+"-28";
                }
        }
        return null;
    }


}

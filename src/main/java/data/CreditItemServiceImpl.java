package data;

import dataservice.CreditItemService;
import po.CreditItemPO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by loohaze on 2017/9/12 下午8:23
 */
public class CreditItemServiceImpl implements CreditItemService{

    SqlManager sqlManager = SqlManager.getSqlManager();

    @Override
    public void SaveCreditItem(ArrayList<CreditItemPO> list, String company_id) {
        sqlManager.getConnection();

        for (CreditItemPO po : list){
            insertOneCreditItemPO(po,company_id);
        }
        sqlManager.releaseAll();
    }

    @Override
    public ArrayList<CreditItemPO> getReceivables(String company_id, String voucher_id) {
        sqlManager.getConnection();
        ArrayList<CreditItemPO> list = new ArrayList<>();

        String sql = "select * from credit_item where company_id=? and voucher_id=? and type='应收账款'";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{company_id,voucher_id});
        for (Map<String,Object> map : maps){
            list.add(getCreditItemPOByMap(map));
        }

        sqlManager.releaseAll();
        return list;
    }

    @Override
    public ArrayList<CreditItemPO> getAccountsPayable(String company_id, String voucher_id) {
        sqlManager.getConnection();
        ArrayList<CreditItemPO> list = new ArrayList<>();

        String sql = "select * from credit_item where company_id=? and voucher_id=? and type='应付账款'";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{company_id,voucher_id});
        for (Map<String,Object> map : maps){
            list.add(getCreditItemPOByMap(map));
        }

        sqlManager.releaseAll();
        return list;
    }


    private void insertOneCreditItemPO(CreditItemPO po,String company_id){
        List<Object> params = new ArrayList<>();
        params.add(company_id);
        params.add(po.getType());
        params.add(po.getItemID());
        params.add(po.getVoucherID());
        params.add(po.getCompanyName());
        params.add(po.getDebitDate());
        params.add(po.getDdl());
        params.add(po.getCreditNum());
        params.add(po.getDiscountPolicy());
        params.add(po.getDiscountDate());
        params.add(po.getComment());

        String sql = sqlManager.appendSQL("insert into credit_item values",params.size());
        sqlManager.executeUpdateByList(sql,params);
    }

    private CreditItemPO getCreditItemPOByMap(Map<String,Object> map){
        CreditItemPO po = new CreditItemPO();
        po.setType(map.get("type").toString());
        po.setItemID(map.get("item_id").toString());
        po.setVoucherID(map.get("voucher_id").toString());
        po.setCompanyName(map.get("company_name").toString());
        po.setDebitDate(Date.valueOf(map.get("debit_date").toString()));
        po.setDdl(Date.valueOf(map.get("ddl").toString()));
        po.setCreditNum(Double.parseDouble(map.get("credit_num").toString()));
        po.setDiscountPolicy(Double.parseDouble(map.get("discount_policy").toString()));
        po.setDebitDate(Date.valueOf(map.get("discount_date").toString()));
        po.setComment(map.get("comment").toString());

        return po;
    }
}

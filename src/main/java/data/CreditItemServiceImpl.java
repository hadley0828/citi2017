package data;

import dataservice.CreditItemService;
import po.CreditItemPO;

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

        String sql = "select * from credit_item where company_id=? and voucher_id=?";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{company_id,voucher_id});
        for (Map<String,Object> map : maps){

        }

        sqlManager.releaseAll();
        return null;
    }

    @Override
    public ArrayList<CreditItemPO> getAccountsPayable(String company_id, String voucher_id) {
        return null;
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
        return null;
    }
}

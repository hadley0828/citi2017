package data;

import dataservice.CreditItemService;
import po.CreditItemPO;

import java.util.ArrayList;

/**
 * Created by loohaze on 2017/9/12 下午8:23
 */
public class CreditItemServiceImpl implements CreditItemService{

    @Override
    public void SaveCreditItem(ArrayList<CreditItemPO> list) {

    }

    @Override
    public ArrayList<CreditItemPO> getReceivables(String company_id, String voucher_id) {
        return null;
    }

    @Override
    public ArrayList<CreditItemPO> getAccountsPayable(String company_id, String voucher_id) {
        return null;
    }
}

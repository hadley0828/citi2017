package dataservice;

import po.CreditItemPO;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/9/12.
 */
public interface CreditItemService {
    /**
     * 保存辅助信息第二个表
     * @param list
     */
    public void SaveCreditItem(ArrayList<CreditItemPO> list, String company_id);

    /**
     * 得到企业的应收账款
     * @param company_id
     * @param voucher_id
     * @return
     */
    public ArrayList<CreditItemPO> getReceivables(String company_id, String voucher_id);

    /**
     * 得到企业的应付账款
     * @param company_id
     * @param voucher_id
     * @return
     */
    public ArrayList<CreditItemPO> getAccountsPayable(String company_id, String voucher_id);
}

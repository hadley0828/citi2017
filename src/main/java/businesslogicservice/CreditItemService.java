package businesslogicservice;

import po.CreditItemPO;
import vo.CreditItemVo;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/9/12.
 */
public interface CreditItemService {
    /**
     * 保存辅助信息第二个表
     * @param list
     */
    public void SaveCreditItem(ArrayList<CreditItemVo> list);

    /**
     * 得到企业的应收账款
     * @param company_id
     * @param voucher_id
     * @return
     */
    public ArrayList<CreditItemVo> getReceivables(String company_id, String voucher_id);

    /**
     * 得到企业的应付账款
     * @param company_id
     * @param voucher_id
     * @return
     */
    public ArrayList<CreditItemVo> getAccountsPayable(String company_id, String voucher_id);
}

package businesslogicservice;

import vo.CreditItemVo;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/9/12.
 */
public interface CreditItemService {
    /**
     * 保存辅助信息第二个表
     * @param list
     * @param company_id
     * @param voucher_id
     */
    public void SaveCreditItem(ArrayList<CreditItemVo> list, String company_id, String voucher_id);

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

    /**
     * 判断距离折扣期限与还款期限是否分别还剩下30%
     * @param company_id
     * @param voucher_id
     * @return
     */
    public boolean IsExpire(String company_id,String voucher_id);

    /**
     * 得到企业的所有应收账款的凭证字
     * @param company_id
     * @return
     */
    public ArrayList<String> getInputCredit(String company_id);

    /**
     * 得到企业的所有应付账款的凭证字
     * @param company_id
     * @return
     */
    public ArrayList<String> getOutputCredit(String company_id);
}

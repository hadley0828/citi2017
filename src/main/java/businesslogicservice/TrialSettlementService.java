package businesslogicservice;

import vo.TrialTableItemVo;
import vo.voucher.VoucherVo;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/8/20.
 */
public interface TrialSettlementService {
    /**
     * 根据凭证id和时期得到试算表的数据
     * @param company_id 公司id
     * @return
     */
    public ArrayList<TrialTableItemVo> getTrialTable(String company_id);

    /**
     * 管理员账号反结账
     * @param voucher_id 凭证id
     * @param voucherVo 修改的一行凭证信息
     * @param company_id 公司id
     */
    public void AntiSettle(String voucher_id, VoucherVo voucherVo, String company_id);
}

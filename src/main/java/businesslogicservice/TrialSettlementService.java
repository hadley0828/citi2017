package businesslogicservice;

import vo.TrialTableItemVo;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/8/20.
 */
public interface TrialSettlementService {
    /**
     * 根据凭证id和时期得到试算表的数据
     * @param voucher_id 凭证id
     * @param time 时期
     * @return
     */
    public ArrayList<TrialTableItemVo> getTrialTable(String voucher_id, String time);
}

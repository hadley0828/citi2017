package businesslogicservice;

import vo.BalanceSheetItemVo;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 费慧通 on 2017/8/7.
 */
public interface BalanceSheetService {
    /**
     * 得到资产负债表数据
     * @param voucher_id 凭证id
     * @param phase 时期
     * @return
     */
    public Map<String, ArrayList<BalanceSheetItemVo>> getBalanceSheet(String voucher_id, String phase);
}

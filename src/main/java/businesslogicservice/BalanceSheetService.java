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
     * @param phase 时期
     * @return
     */
    public Map<String, ArrayList<BalanceSheetItemVo>> getBalanceSheet(String phase);

    /**
     * 得到所有凭证数量
     * @return
     */
    public int getVoucherNumber();

    /**
     * 得到凭证最早时间
     * @return
     */
    public String getEarliestTime();

    /**
     * 得到凭证最晚时间
     * @return
     */
    public String getLatestTime();
}

package businesslogic;

import businesslogicservice.BalanceSheetService;
import vopo.BalanceSheetItem;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/8/7.
 *
 * 资产负债表
 */
public class BalanceSheetImpl implements BalanceSheetService {
    /**
     * 得到资产负债表数据
     * @param voucher_id 凭证id
     * @param phase 时期
     * @return
     */
    public ArrayList<BalanceSheetItem> getBalanceSheet(String voucher_id, String phase){
        return null;
    }
}

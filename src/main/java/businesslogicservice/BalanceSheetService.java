package businesslogicservice;

import vo.BalanceSheetItemVo;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 费慧通 on 2017/8/7.
 *
 */
public interface BalanceSheetService {
    /**
     * 得到资产负债表数据
     * @param company_id 公司id
     * @param phase 时期
     * @return
     */
    public Map<String, ArrayList<BalanceSheetItemVo>> getBalanceSheet(String company_id, String phase);

    /**
     * 按照指定路径导出资产负债表
     * @param path 路径
     */
    public void CreatBalanceSheet(String path);

    /**
     * 得到上一期期末的总资产、本期期末总资产、总负债、流动资产、流动负债、上一期期末应收帐款、本期期末应收帐款、上期期末存货、本期期末存货、本期所有者权益、上一期所有者权益
     * @param company_id 公司id
     * @param phase 时间
     * @return
     */
    public double[] getValue(String company_id, String phase);

    /**
     * 得到本期期末总资产、流动资产、流动负债、本期期末应收帐款、本期期末存货
     * @param company_id
     * @param phase
     * @return
     */
    public double[] getValue2(String company_id, String phase);

    /**
     * 得到所有凭证数量
     * @param company_id 公司id
     * @return
     */
    public int getVoucherNumber(String company_id);

    /**
     * 得到凭证最早时间
     * @param company_id 公司id
     * @return
     */
    public String getEarliestTime(String company_id);

    /**
     * 得到凭证最晚时间
     * @param company_id 公司id
     * @return
     */
    public String getLatestTime(String company_id);

    /**
     * 得到最早及最晚时间之间的所有月份
     * @param date1 最早时间
     * @param date2 最晚时间
     * @return
     */
    public ArrayList<String> getMiddleMonth(String date1 , String date2);
}

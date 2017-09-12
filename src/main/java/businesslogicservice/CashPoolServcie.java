package businesslogicservice;

/**
 * Created by 费慧通 on 2017/9/12.
 */
public interface CashPoolServcie {
    /**
     * 得到现金流量表里的 现金流入、现金流出、现金池内留存的现金
     * @param company_id
     * @param time
     * @return
     */
    public double[] getCashFlow(String company_id, String time);

    /**
     * 得到与现金有关的财务指标
     * @param company_id
     * @param time
     * @return
     */
    public double[] getFinancialIndex(String company_id, String time);
}

package businesslogicservice;

/**
 * Created by 费慧通 on 2017/9/12.
 */
public interface FinancialWarningService {
    /**
     * 得到财务预警信息
     * @param company_id 公司id
     * @param phase 时间
     * @return
     */
    public String getWarningMessage(String company_id, String phase);
}

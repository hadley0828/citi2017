package dataservice;

import po.IndexPo;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/9/11.
 */
public interface FinancialIndexService {
    /**
     * 得到所有财务指标
     * @param industry 行业
     * @return
     */
    public ArrayList<IndexPo> getFinancialIndex(String industry);

    /**
     * 得到公司所属行业
     * @param company_id
     * @return
     */
    public String getIndustry(String company_id);
}

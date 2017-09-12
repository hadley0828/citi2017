package dataservice;

import po.IndexPo;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/9/11.
 */
public interface FinancialIndexService {
    /**
     * 得到所有财务指标
     * @return
     */
    public ArrayList<IndexPo> getFinancialIndex();
}

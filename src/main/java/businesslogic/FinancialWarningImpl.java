package businesslogic;

import businesslogicservice.BalanceSheetService;
import businesslogicservice.CashFlowTableService;
import businesslogicservice.ProfitTableService;
import data.FinancialIndexServiceImpl;
import dataservice.FinancialIndexService;
import po.IndexPo;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/9/12.
 */
public class FinancialWarningImpl {

    private ArrayList<IndexPo> list;

    /**
     * 得到财务预警信息
     * @param company_id 公司id
     * @param phase 时间
     * @return
     */
    public String getWarningMessage(String company_id, String phase){
        BalanceSheetService service1 = new BalanceSheetImpl();
        ProfitTableService service2 = new ProfitTableImpl();
        CashFlowTableService service3 = new CashFlowImpl();
        double[] data1 = service1.getValue(company_id, phase);
        double[] data2 = service2.getValue(company_id, phase);
        double data3 = service3.getValue(company_id, phase);

        FinancialIndexService service = new FinancialIndexServiceImpl();
        list = service.getFinancialIndex();

        return null;
    }


}

package businesslogic;

import businesslogicservice.BalanceSheetService;
import businesslogicservice.CashFlowTableService;
import businesslogicservice.CashPoolService;
import businesslogicservice.ProfitTableService;

/**
 * Created by 费慧通 on 2017/9/12.
 */
public class CashPoolImpl implements CashPoolService {
    @Override
    public double[] getCashFlow(String company_id, String time) {
        CashFlowTableService service = new CashFlowImpl();
        return service.getCashFlow(company_id, time);
    }

    @Override
    public double[] getFinancialIndex(String company_id, String time) {
        BalanceSheetService service1 = new BalanceSheetImpl();
        ProfitTableService service2 = new ProfitTableImpl();
        CashFlowTableService service3 = new CashFlowImpl();
        //本期期末总资产、流动资产、流动负债、本期期末应收帐款、本期期末存货
        double[] data1 = service1.getValue2(company_id, time);
        //净利润
        double data2 = service2.getProfit(company_id, time);
        //经营现金净流量
        double data3 = service3.getValue(company_id, time);

        double[] result = new double[4];
        result[0] = data3/data2;
        result[1] = data3/data1[0];
        result[2] = data3/data1[2];
        result[3] = (data1[3]+data1[4])/data1[1];
        return result;
    }
}

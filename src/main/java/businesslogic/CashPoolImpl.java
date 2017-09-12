package businesslogic;

import businesslogicservice.BalanceSheetService;
import businesslogicservice.CashFlowTableService;
import businesslogicservice.CashPoolServcie;
import businesslogicservice.ProfitTableService;

/**
 * Created by 费慧通 on 2017/9/12.
 */
public class CashPoolImpl implements CashPoolServcie {
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
        //上一期期末的总资产、本期期末总资产、总负债、流动资产、流动负债、上一期期末应收帐款、本期期末应收帐款、上期期末存货、本期期末存货、本期所有者权益、上一期所有者权益
        double[] data1 = service1.getValue(company_id, time);
        //净利润、利润总额、主营业务成本、销售费用、管理费用、财务费用、营业成本、其他业务收入、本期主营业务收入、上一期主营业务收入
        double[] data2 = service2.getValue(company_id, time);
        //经营现金净流量
        double data3 = service3.getValue(company_id, time);

        double[] result = new double[4];
        result[0] = data3/data2[0];
        result[1] = data3/data1[1];
        result[2] = data3/data1[4];
        result[3] = (data1[8]+data1[6])/data1[3];
        return result;
    }
}

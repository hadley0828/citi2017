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
        //上一期期末的总资产、本期期末总资产、总负债、流动资产、流动负债、上一期期末应收帐款、本期期末应收帐款、上期期末存货、本期期末存货、本期所有者权益、上一期所有者权益
        double[] data1 = service1.getValue(company_id, phase);
        //净利润、利润总额、主营业务成本、销售费用、管理费用、财务费用、营业成本、其他业务收入、本期主营业务收入、上一期主营业务收入
        double[] data2 = service2.getValue(company_id, phase);
        //经营现金净流量
        double data3 = service3.getValue(company_id, phase);

        FinancialIndexService service = new FinancialIndexServiceImpl();
        list = service.getFinancialIndex(service.getIndustry(company_id));

        //实际值
        double[] actual_value = new double[12];
        //净资产收益率  净利润／所有者权益
        actual_value[0] = data2[0]/data1[10];
        //总资产报酬率  利润总额／［（上一期期末的总资产＋本期期末总资产）／2］
        actual_value[1] = data2[1]/((data1[0]+data1[1])/2);
        //盈余现金保障倍数  经营现金净流量／净利润
        actual_value[2] = data3/data2[0];
        //成本费用利润率  利润总额／（主营业务成本＋销售费用＋管理费用＋财务费用）
        actual_value[3] = data2[1]/(data2[2]+data2[3]+data2[4]+data2[5]);
        //资产负债率  总负债／总资产
        actual_value[4] = data1[2]/data1[1];
        //速动比率  （流动资产－存货）／流动负债
        actual_value[5] = (data1[3]-data1[8])/data1[4];
        //现金流动负债比率  经营现金净流量／流动负债
        actual_value[6] = data3/data1[4];
        //总资产周转率  （主营业务收入＋其他业务收入）／［（上一期期末的总资产＋本期期末总资产）／2］
        actual_value[7] = (data2[8]+data2[7])/((data1[0]+data1[1])/2);
        //应收账款周转率  （主营业务收入＋其他业务收入）／［（上一期期末应收帐款＋本期期末应收帐款）／2］
        actual_value[8] = (data2[8]+data2[7])/((data1[5]+data1[6])/2);
        //存货周转率  营业成本／［（上一期期末存货＋本期期末存货）／2］
        actual_value[9] = data2[6]/((data1[7]+data1[8])/2);
        //销售增长率  （本期主营业务收入－上一期主营业务收入）／上一期主营业务收入
        actual_value[10] = (data2[8]-data2[9])/data2[9];
        //资本积累率  （本期所有者权益－上一期所有者权益）／上一期所有者权益
        actual_value[11] = (data1[9]-data1[10])/data1[10];

        double[] rate = new double[12];
        double[] average_value = new double[12];
        double[] unallowed_value = new double[12];

        //净资产收益率
        average_value[0] = getAverageIndex("净资产收益率");
        if(actual_value[0]<average_value[0]){
            unallowed_value[0] = getUnallowedIndex("净资产收益率");
            rate[0] = 40*(actual_value[0]-unallowed_value[0])/(average_value[0]-unallowed_value[0])+60;
        }else{
            rate[0] = 100;
        }

        //总资产报酬率
        average_value[1] = getAverageIndex("总资产报酬率");
        if(actual_value[1]<average_value[1]){
            unallowed_value[1] = getUnallowedIndex("总资产报酬率");
            rate[1] = 40*(actual_value[1]-unallowed_value[1])/(average_value[1]-unallowed_value[1])+60;
        }else{
            rate[1] = 100;
        }

        //盈余现金保障倍数
        average_value[2] = getAverageIndex("盈余现金保障倍数");
        if(actual_value[2]<average_value[2]){
            unallowed_value[2] = getUnallowedIndex("盈余现金保障倍数");
            rate[2] = 40*(actual_value[2]-unallowed_value[2])/(average_value[2]-unallowed_value[2])+60;
        }else{
            rate[2] = 100;
        }

        //成本费用利润率
        average_value[3] = getAverageIndex("成本费用利润率");
        if(actual_value[3]<average_value[3]){
            unallowed_value[3] = getUnallowedIndex("成本费用利润率");
            rate[3] = 40*(actual_value[3]-unallowed_value[3])/(average_value[3]-unallowed_value[3])+60;
        }else{
            rate[3] = 100;
        }

        //资产负债率
        average_value[4] = getAverageIndex("资产负债率");
        double fine_value1 = getFineIndex("资产负债率");
        double bad_value1 = getBadIndex("资产负债率");
        if(actual_value[4]<average_value[4]){
            rate[4] = 40*(actual_value[4]-bad_value1)/(average_value[4]-bad_value1)+60;
        }else if(actual_value[4>=bad_value1&&actual_value[4]<=fine_value1){
            rate[4] = 100;
        }else if(actual_value[4]>average_value[4]){
            rate[4] = 40*(actual_value[4]-fine_value1)/(average_value[4]-fine_value1)+60;
        }

        //速动比率
        average_value[5] = getAverageIndex("速动比率");
        double fine_value2 = getFineIndex("速动比率");
        double bad_value2 = getBadIndex("速动比率");
        if(actual_value[5]<average_value[5]){
            rate[5] = 40*(actual_value[5]-bad_value2)/(average_value[5]-bad_value2)+60;
        }else{
            rate[5] = 40*(actual_value[5]-fine_value2)/(average_value[5]-fine_value2)+60;
        }

        //现金流动负债比率
        average_value[6] = getAverageIndex("现金流动负债比率");
        double fine_value3 = getFineIndex("现金流动负债比率");
        double bad_value3 = getBadIndex("现金流动负债比率");
        if(actual_value[6]<average_value[6]){
            rate[6] = 40*(actual_value[6]-bad_value3)/(average_value[6]-bad_value3)+60;
        }else if(actual_value[6>=bad_value3&&actual_value[6]<=fine_value3){
            rate[6] = 100;
        }else if(actual_value[4]>average_value[4]){
            rate[6] = 40*(actual_value[6]-fine_value3)/(average_value[6]-fine_value3)+60;
        }

        //总资产周转率
        average_value[7] = getAverageIndex("总资产周转率");
        if(actual_value[7]<average_value[7]){
            unallowed_value[7] = getUnallowedIndex("总资产周转率");
            rate[7] = 40*(actual_value[7]-unallowed_value[7])/(average_value[7]-unallowed_value[7])+60;
        }else{
            rate[7] = 100;
        }

        //应收帐款周转率
        average_value[8] = getAverageIndex("应收帐款周转率");
        if(actual_value[8]<average_value[8]){
            unallowed_value[8] = getUnallowedIndex("应收帐款周转率");
            rate[8] = 40*(actual_value[8]-unallowed_value[8])/(average_value[8]-unallowed_value[8])+60;
        }else{
            rate[8] = 100;
        }

        //存货周转率
        average_value[9] = getAverageIndex("存货周转率");
        if(actual_value[9]<average_value[9]){
            unallowed_value[9] = getUnallowedIndex("存货周转率");
            rate[9] = 40*(actual_value[9]-unallowed_value[9])/(average_value[9]-unallowed_value[9])+60;
        }else{
            rate[9] = 100;
        }

        //销售增长率
        average_value[10] = getAverageIndex("销售增长率");
        if(actual_value[10]<average_value[10]){
            unallowed_value[10] = getUnallowedIndex("销售增长率");
            rate[10] = 40*(actual_value[10]-unallowed_value[10])/(average_value[10]-unallowed_value[10])+60;
        }else{
            rate[10] = 100;
        }

        //资本积累率
        average_value[11] = getAverageIndex("资本积累率");
        if(actual_value[11]<average_value[11]){
            unallowed_value[11] = getUnallowedIndex("资本积累率");
            rate[11] = 40*(actual_value[11]-unallowed_value[11])/(average_value[11]-unallowed_value[11])+60;
        }else{
            rate[11] = 100;
        }

        double Rate = rate[0]*0.16+rate[1]*0.09+rate[2]*0.07+rate[3]*0.06+rate[4]*0.12+rate[5]*0.1+rate[6]*0.12+rate[7]*0.05+rate[8]*0.08+rate[9]*0.05+rate[10]*0.05+rate[11]*0.05;

        String result = "";
        if(Rate>=90){
            result = "无警-财务状况优异，基本无风险";
        }else if(Rate>=80&&Rate<90){
            result = "轻警-财务状况良好，风险很小";
        }else if(Rate>=70&&Rate<80){
            result = "中警-财务状况一般，存在一定风险";
        }else if(Rate>=60&&Rate<70){
            result = "重警-财务状况较差，风险较大";
        }else{
            result = "巨警-财务状况恶化，风险巨大";
        }

        return result;
    }

    /**
     * 得到指标的满意值（平均值）
     * @param index_name
     * @return
     */
    public double getAverageIndex(String index_name){
        for(int i=0;i<list.size();i++){
            IndexPo po = list.get(i);
            if(po.getIndex_name().equals(index_name)){
                return po.getAverage_value();
            }
        }
        return 0;
    }

    /**
     * 得到行业的不允许值(极低值)
     * @param index_name
     * @return
     */
    public double getUnallowedIndex(String index_name){
        for(int i=0;i<list.size();i++){
            IndexPo po = list.get(i);
            if(po.getIndex_name().equals(index_name)){
                return po.getLow_value();
            }
        }
        return 0;
    }

    /**
     * 得到行业的上限不允许值(优秀值)
     * @param index_name
     * @return
     */
    public double getFineIndex(String index_name){
        for(int i=0;i<list.size();i++){
            IndexPo po = list.get(i);
            if(po.getIndex_name().equals(index_name)){
                return po.getFine_value();
            }
        }
        return 0;
    }

    /**
     * 得到行业的下限不允许值(极差值)
     * @param index_name
     * @return
     */
    public double getBadIndex(String index_name){
        for(int i=0;i<list.size();i++){
            IndexPo po = list.get(i);
            if(po.getIndex_name().equals(index_name)){
                return po.getBad_value();
            }
        }
        return 0;
    }

}

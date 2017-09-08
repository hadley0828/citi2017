package businesslogic;

import businesslogicservice.BalanceSheetService;
import data.CourseMessageServiceImpl;
import dataservice.CourseMessageService;
import javafx.beans.property.IntegerProperty;
import po.VoucherAmountPO;
import vo.BalanceSheetItemVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 费慧通 on 2017/8/7.
 *
 * 资产负债表
 */
public class BalanceSheetImpl implements BalanceSheetService {
    /**
     * 得到资产负债表数据
     * @param phase      时期
     * @return
     */
    public Map<String, ArrayList<BalanceSheetItemVo>> getBalanceSheet(String phase) {
        CourseMessageService service = new CourseMessageServiceImpl();
        ArrayList<VoucherAmountPO> polist1 = service.getCourseMessageByTime(phase);
        ArrayList<VoucherAmountPO> polist2 = service.getCourseMessageByTime(getBeginningOfYear(phase));

        Map<String, ArrayList<BalanceSheetItemVo>> result = new HashMap<>();

        //1流动资产
        ArrayList<BalanceSheetItemVo> current_asset = new ArrayList<>();

        //标题
        current_asset.add(new BalanceSheetItemVo("流动资产",0,0,0,""));

        //1.1货币资金=其他货币资金+库存现金+银行存款
        double ending_balance1_1 = getMoneyByCourseId(polist1, "1012", true) + getMoneyByCourseId(polist1, "1001", true) + getMoneyByCourseId(polist1, "1002", true);
        double beginning_balance1_1 = getMoneyByCourseId(polist2, "1012", true) + getMoneyByCourseId(polist2, "1001", true) + getMoneyByCourseId(polist2, "1002", true);
        String law1_1 = "公式：\n其他货币资金\n+库存现金\n+银行存款";
        current_asset.add(new BalanceSheetItemVo("货币资金", 1, ending_balance1_1, beginning_balance1_1, law1_1));
        //1.2短期投资
        double ending_balance1_2 = getMoneyByCourseId(polist1, "1101001", true) + getMoneyByCourseId(polist1, "1101002", true) + getMoneyByCourseId(polist1, "1101003", true);
        double beginning_balance1_2 = getMoneyByCourseId(polist2, "1101001", true) + getMoneyByCourseId(polist2, "1101002", true) + getMoneyByCourseId(polist2, "1101003", true);
        String law1_2 = "公式：\n短期投资";
        current_asset.add(new BalanceSheetItemVo("短期投资", 2, ending_balance1_2, beginning_balance1_2, law1_2));
        //1.3应收票据
        double ending_balance1_3 = getMoneyByCourseId(polist1, "1121", true);
        double beginning_balance1_3 = getMoneyByCourseId(polist2, "1121", true);
        String law1_3 = "公式：\n应收票据";
        current_asset.add(new BalanceSheetItemVo("应收票据", 3, ending_balance1_3, beginning_balance1_3, law1_3));
        //1.4应收账款=应收账款+预收账款（*余额在借方时）
        double ending_balance1_4 = getMoneyByCourseId(polist1, "1122", true) + getMoneyByCourseId(polist1, "2203", true);
        double beginning_balance1_4 = getMoneyByCourseId(polist2, "1122", true) + getMoneyByCourseId(polist2, "2203", true);
        String law1_4 = "公式：\n应收账款\n+预收账款";
        current_asset.add(new BalanceSheetItemVo("应收账款", 4, ending_balance1_4, beginning_balance1_4, law1_4));
        //1.5预付账款=预付账款+应付账款（*余额在借方时）
        double ending_balance1_5 = getMoneyByCourseId(polist1, "1123", true) + getMoneyByCourseId(polist1, "2202", true);
        double beginning_balance1_5 = getMoneyByCourseId(polist2, "1123", true) + getMoneyByCourseId(polist2, "2202", true);
        String law1_5 = "公式：\n预付账款\n+应付账款";
        current_asset.add(new BalanceSheetItemVo("预付账款", 5, ending_balance1_5, beginning_balance1_5, law1_5));
        //1.6应收股利
        double ending_balance1_6 = getMoneyByCourseId(polist1, "1131", true);
        double beginning_balance1_6 = getMoneyByCourseId(polist2, "1131", true);
        String law1_6 = "公式：\n应收股利";
        current_asset.add(new BalanceSheetItemVo("应收股利", 6, ending_balance1_6, beginning_balance1_6, law1_6));
        //1.7应收利息
        double ending_balance1_7 = getMoneyByCourseId(polist1, "1132", true);
        double beginning_balance1_7 = getMoneyByCourseId(polist2, "1132", true);
        String law1_7 = "公式：\n应收利息";
        current_asset.add(new BalanceSheetItemVo("应收利息", 7, ending_balance1_7, beginning_balance1_7, law1_7));
        //1.8其他应收款=其他应收款+其他应付款（*余额在借方时）
        double ending_balance1_8 = getMoneyByCourseId(polist1, "1121", true) + getMoneyByCourseId(polist1, "2241", true);
        double beginning_balance1_8 = getMoneyByCourseId(polist2, "1121", true) + getMoneyByCourseId(polist2, "2241", true);
        String law1_8 = "公式：\n其他应收款\n+其他应付款";
        current_asset.add(new BalanceSheetItemVo("其他应付款", 8, ending_balance1_8, beginning_balance1_8, law1_8));
        //1.9存货=在途物资+材料采购+原材料+材料成本差异+库存商品-商品进销差价+委托加工物资+周转材料+消耗性生物资产+生产成本+制造费用+工程施工+机械作业
        double ending_balance1_9 = getMoneyByCourseId(polist1, "1402", true) + getMoneyByCourseId(polist1, "1401", true) + getMoneyByCourseId(polist1, "1403", true)
                + getMoneyByCourseId(polist1, "1404", true) + getMoneyByCourseId(polist1, "1405", true) - getMoneyByCourseId(polist1, "1407", true)
                + getMoneyByCourseId(polist1, "1408", true) + getMoneyByCourseId(polist1, "1411", true) + getMoneyByCourseId(polist1, "1421", true)
                + getMoneyByCourseId(polist1, "4001", true) + getMoneyByCourseId(polist1, "4101", true) + getMoneyByCourseId(polist1, "4401", true)
                + getMoneyByCourseId(polist1, "4403", true);
        double beginning_balance1_9 = getMoneyByCourseId(polist2, "1402", true) + getMoneyByCourseId(polist2, "1401", true) + getMoneyByCourseId(polist2, "1403", true)
                + getMoneyByCourseId(polist2, "1404", true) + getMoneyByCourseId(polist2, "1405", true) - getMoneyByCourseId(polist2, "1407", true)
                + getMoneyByCourseId(polist2, "1408", true) + getMoneyByCourseId(polist2, "1411", true) + getMoneyByCourseId(polist2, "1421", true)
                + getMoneyByCourseId(polist2, "4001", true) + getMoneyByCourseId(polist2, "4101", true) + getMoneyByCourseId(polist2, "4401", true)
                + getMoneyByCourseId(polist2, "4403", true);
        String law1_9 = "公式：\n在途物资\n+材料采购\n+原材料\n+材料成本差异\n+库存商品\n-商品进销差价\n+委托加工物资\n+周转材料\n+消耗性生物资产\n+生产成本\n+制造费用\n+工程施工\n+机械作业";
        current_asset.add(new BalanceSheetItemVo("存货", 9, ending_balance1_9, beginning_balance1_9, law1_9));

        //1.9.1.1原材料
        double ending_balance1_9_1 = getMoneyByCourseId(polist1, "1403", true);
        double beginning_balance1_9_1 = getMoneyByCourseId(polist2, "1403", true);
        String law1_9_1 = "公式：\n原材料";
        current_asset.add(new BalanceSheetItemVo("原材料", 10, ending_balance1_9_1, beginning_balance1_9_1, law1_9_1));

        //1.9.1.2在产品=生产成本+制造费用+工程施工+机械作业
        double ending_balance1_9_2 = getMoneyByCourseId(polist1, "4001", true)+getMoneyByCourseId(polist1, "4101", true)
                +getMoneyByCourseId(polist1, "4401", true)+getMoneyByCourseId(polist1, "4403", true);
        double beginning_balance1_9_2 = getMoneyByCourseId(polist2, "4001", true)+getMoneyByCourseId(polist2, "4101", true)
                +getMoneyByCourseId(polist2, "4401", true)+getMoneyByCourseId(polist2, "4403", true);
        String law1_9_2 = "公式：\n生产成本\n+制造费用\n+工程施工\n+机械作业";
        current_asset.add(new BalanceSheetItemVo("在产品",11,ending_balance1_9_2, beginning_balance1_9_2, law1_9_2));

        //1.9.1.3库存商品
        double ending_balance1_9_3 = getMoneyByCourseId(polist1, "1405", true);
        double beginning_balance1_9_3 = getMoneyByCourseId(polist2, "1405", true);
        String law1_9_3 = "公式：\n库存商品";
        current_asset.add(new BalanceSheetItemVo("库存商品", 12, ending_balance1_9_3, beginning_balance1_9_3, law1_9_3));

        //1.9.1.4周转材料
        double ending_balance1_9_4 = getMoneyByCourseId(polist1, "1411", true);
        double beginning_balance1_9_4 = getMoneyByCourseId(polist2, "1411", true);
        String law1_9_4 = "公式：\n周转材料";
        current_asset.add(new BalanceSheetItemVo("周转材料", 13,ending_balance1_9_4, beginning_balance1_9_4, law1_9_4));

        //1.10其他流动资产
        double ending_balance1_10 = getMoneyByCourseId(polist1, "6000", true);
        double beginning_balance1_10 = getMoneyByCourseId(polist2, "6000", true);
        String law1_10 = "公式：\n其他流动资产";
        current_asset.add(new BalanceSheetItemVo("其他流动资产", 14, ending_balance1_10, beginning_balance1_10, law1_10));
        //1.11流动资产合计=货币资金+短期投资+应收票据+应收账款+预付账款+应收股利+应收利息+其他应收款+存货+其他流动资产
        double ending_balance1_11 = ending_balance1_1 + ending_balance1_2 + ending_balance1_3 + ending_balance1_4 + ending_balance1_5 + ending_balance1_6 + ending_balance1_7 + ending_balance1_8 + ending_balance1_9 + ending_balance1_10;
        double beginning_balance1_11 = beginning_balance1_1 + beginning_balance1_2 + beginning_balance1_3 + beginning_balance1_4 + beginning_balance1_5 + beginning_balance1_6 + beginning_balance1_7 + beginning_balance1_8 + beginning_balance1_9 + beginning_balance1_10;
        String law1_11 = "公式：\n货币资金\n+短期投资\n+应收票据\n+应收账款\n+预付账款\n+应收股利\n+应收利息\n+其他应收款\n+存货\n+其他流动资产";
        current_asset.add(new BalanceSheetItemVo("流动资产合计", 15, ending_balance1_11, beginning_balance1_11, law1_11));

        result.put("流动资产", current_asset);

        //非流动资产
        ArrayList<BalanceSheetItemVo> no_current_asset = new ArrayList<>();

        //标题
        no_current_asset.add(new BalanceSheetItemVo("非流动资产",0,0,0, ""));

        //2.1长期债券投资
        double ending_balance2_1 = getMoneyByCourseId(polist1, "1501", true);
        double beginning_balance2_1 = getMoneyByCourseId(polist2, "1501", true);
        String law2_1 = "公式：\n长期债券投资";
        no_current_asset.add(new BalanceSheetItemVo("长期债券投资", 16, ending_balance2_1, beginning_balance2_1, law2_1));
        //2.2长期股权投资
        double ending_balance2_2 = getMoneyByCourseId(polist1, "1511", true);
        double beginning_balance2_2 = getMoneyByCourseId(polist2, "1511", true);
        String law2_2 = "公式：\n长期股权投资";
        no_current_asset.add(new BalanceSheetItemVo("长期股权投资", 17, ending_balance2_2, beginning_balance2_2, law2_2));
        //2.3固定资产原价=固定资产
        double ending_balance2_3 = getMoneyByCourseId(polist1, "1601", true);
        double beginning_balance2_3 = getMoneyByCourseId(polist2, "1601", true);
        String law2_3 = "公式：\n固定资产";
        no_current_asset.add(new BalanceSheetItemVo("固定资产原价", 18, ending_balance2_3, beginning_balance2_3, law2_3));
        //2.4减：累计折旧（数值=累计折旧）
        double ending_balance2_4 = getMoneyByCourseId(polist1, "1602", true);
        double beginning_balance2_4 = getMoneyByCourseId(polist2, "1602", true);
        String law2_4 = "公式：\n累计折旧";
        no_current_asset.add(new BalanceSheetItemVo("减：累计折旧", 19, ending_balance2_4, beginning_balance2_4, law2_4));
        //2.5固定资产账面价值=固定资产原价-累计折旧
        double ending_balance2_5 = ending_balance2_3 - ending_balance2_4;
        double beginning_balance2_5 = beginning_balance2_3 - beginning_balance2_4;
        String law2_5 = "公式：\n固定资产原价\n-累计折旧";
        no_current_asset.add(new BalanceSheetItemVo("固定资产账面价值", 20, ending_balance2_5, beginning_balance2_5, law2_5));
        //2.6在建工程
        double ending_balance2_6 = getMoneyByCourseId(polist1, "1604", true);
        double beginning_balance2_6 = getMoneyByCourseId(polist2, "1604", true);
        String law2_6 = "公式：\n在建工程";
        no_current_asset.add(new BalanceSheetItemVo("在建工程", 21, ending_balance2_6, beginning_balance2_6, law2_6));
        //2.7工程物资
        double ending_balance2_7 = getMoneyByCourseId(polist1, "1605", true);
        double beginning_balance2_7 = getMoneyByCourseId(polist2, "1605", true);
        String law2_7 = "公式：\n工程物资";
        no_current_asset.add(new BalanceSheetItemVo("工程物资", 22, ending_balance2_7, beginning_balance2_7, law2_7));
        //2.8固定资产清理
        double ending_balance2_8 = getMoneyByCourseId(polist1, "1606", true);
        double beginning_balance2_8 = getMoneyByCourseId(polist2, "1606", true);
        String law2_8 = "公式：\n固定资产清理";
        no_current_asset.add(new BalanceSheetItemVo("固定资产清理", 23, ending_balance2_8, beginning_balance2_8, law2_8));
        //2.9生产性生物资产=生产性生物资产累计折旧+生产性生物资产
        double ending_balance2_9 = getMoneyByCourseId(polist1, "1622", true) + getMoneyByCourseId(polist1, "1621", true);
        double beginning_balance2_9 = getMoneyByCourseId(polist2, "1622", true) + getMoneyByCourseId(polist2, "1621", true);
        String law2_9 = "公式：\n生产性生物资产累计折旧\n+生产性生物资产";
        no_current_asset.add(new BalanceSheetItemVo("生产性生物资产", 24, ending_balance2_9, beginning_balance2_9, law2_9));
        //2.10无形资产=无形资产-累计摊销
        double ending_balance2_10 = getMoneyByCourseId(polist1, "1701", true) - getMoneyByCourseId(polist1, "1702", true);
        double beginning_balance2_10 = getMoneyByCourseId(polist2, "1701", true) - getMoneyByCourseId(polist2, "1702", true);
        String law2_10 = "公式：\n无形资产\n-累计摊销";
        no_current_asset.add(new BalanceSheetItemVo("无形资产", 25, ending_balance2_10, beginning_balance2_10, law2_10));
        //2.11开发支出=研发支出
        double ending_balance2_11 = getMoneyByCourseId(polist1, "4301", true);
        double beginning_balance2_11 = getMoneyByCourseId(polist2, "4301", true);
        String law2_11 = "公式：\n研发支出";
        no_current_asset.add(new BalanceSheetItemVo("开发支出", 26, ending_balance2_11, beginning_balance2_11, law2_11));
        //2.12长期待摊费用
        double ending_balance2_12 = getMoneyByCourseId(polist1, "1801", true);
        double beginning_balance2_12 = getMoneyByCourseId(polist2, "1801", true);
        String law2_12 = "公式：\n长期待摊费用";
        no_current_asset.add(new BalanceSheetItemVo("长期待摊费用", 27, ending_balance2_12, beginning_balance2_12, law2_12));
        //2.13其他非流动资产
        double ending_balance2_13 = getMoneyByCourseId(polist1, "6001", true);
        double beginning_balance2_13 = getMoneyByCourseId(polist2, "6001", true);
        String law2_13 = "公式：\n其他非流动资产";
        no_current_asset.add(new BalanceSheetItemVo("其他非流动资产", 28, ending_balance2_13, beginning_balance2_13, law2_13));
        //2.14非流动资产合计=长期股权投资+长期债券投资+固定资产账面价值+工程物资+在建工程+固定资产清理+生产性生物资产+开发支出+无形资产+长期待摊费用+其他非流动资产
        double ending_balance2_14 = ending_balance2_1 + ending_balance2_2 + ending_balance2_3 + ending_balance2_4 + ending_balance2_5 + ending_balance2_6 + ending_balance2_7 + ending_balance2_8 + ending_balance2_9 + ending_balance2_10 + ending_balance2_11 + ending_balance2_12 + ending_balance2_13;
        double beginning_balance2_14 = beginning_balance2_1 + beginning_balance2_2 + beginning_balance2_3 + beginning_balance2_4 + beginning_balance2_5 + beginning_balance2_6 + beginning_balance2_7 + beginning_balance2_8 + beginning_balance2_9 + beginning_balance2_10 + beginning_balance2_11 + beginning_balance2_12 + beginning_balance2_13;
        String law2_14 = "公式：\n长期股权投资\n+长期债券投资\n+固定资产账面价值\n+工程物资\n+在建工程\n+固定资产清理\n+生产性生物资产\n+开发支出\n+无形资产\n+长期待摊费用\n+其他非流动资产";
        no_current_asset.add(new BalanceSheetItemVo("非流动资产合计", 29, ending_balance2_14, beginning_balance2_14, law2_14));

        result.put("非流动资产", no_current_asset);

        //3资产合计= 非流动资产合计+流动资产合计
        ArrayList<BalanceSheetItemVo> totalasset = new ArrayList<>();

        double ending_balance3 = ending_balance1_11 + ending_balance2_14;
        double beginning_balance3 = beginning_balance1_11 + beginning_balance2_14;
        String law3 = "公式：\n非流动资产合计\n+流动资产合计";
        totalasset.add(new BalanceSheetItemVo("资产合计", 30, ending_balance3, beginning_balance3, law3));

        result.put("资产合计", totalasset);

        //4流动负债
        ArrayList<BalanceSheetItemVo> current_liabilities = new ArrayList<>();

        //标题
        current_liabilities.add(new BalanceSheetItemVo("流动负债",0,0,0, ""));

        //4.1短期借款
        double ending_balance4_1 = getMoneyByCourseId(polist1, "2001", false);
        double beginning_balance4_1 = getMoneyByCourseId(polist2, "2001", false);
        String law4_1 = "公式：\n短期借款";
        current_liabilities.add(new BalanceSheetItemVo("短期借款", 31, ending_balance4_1, beginning_balance4_1, law4_1));
        //4.2应付票据
        double ending_balance4_2 = getMoneyByCourseId(polist1, "2201", false);
        double beginning_balance4_2 = getMoneyByCourseId(polist2, "2201", false);
        String law4_2 = "公式：\n应付票据";
        current_liabilities.add(new BalanceSheetItemVo("应付票据", 32, ending_balance4_2, beginning_balance4_2, law4_2));
        //4.3应付账款=应付账款+预付账款（*余额在贷方时）
        double ending_balance4_3 = getMoneyByCourseId(polist1, "2202", false) + getMoneyByCourseId(polist1, "1123", false);
        double beginning_balance4_3 = getMoneyByCourseId(polist2, "2202", false) + getMoneyByCourseId(polist2, "1123", false);
        String law4_3 = "公式：\n应付账款\n+预付账款";
        current_liabilities.add(new BalanceSheetItemVo("应付账款", 33, ending_balance4_3, beginning_balance4_3, law4_3));
        //4.4预收账款=预收账款+应收账款（*余额在贷方时）
        double ending_balance4_4 = getMoneyByCourseId(polist1, "2203", false) + getMoneyByCourseId(polist1, "1122", false);
        double beginning_balance4_4 = getMoneyByCourseId(polist2, "2203", false) + getMoneyByCourseId(polist2, "1122", false);
        String law4_4 = "公式：\n预收账款\n+应收账款";
        current_liabilities.add(new BalanceSheetItemVo("预收账款", 34, ending_balance4_4, beginning_balance4_4, law4_4));
        //4.5应付职工薪酬
        double ending_balance4_5 = getMoneyByCourseId(polist1, "2211", false);
        double beginning_balance4_5 = getMoneyByCourseId(polist2, "2211", false);
        String law4_5 = "公式：\n应付职工薪酬";
        current_liabilities.add(new BalanceSheetItemVo("应付职工薪酬", 35, ending_balance4_5, beginning_balance4_5, law4_5));
        //4.6应交税费
        double ending_balance4_6 = getTotalTax(polist1,false);
        double beginning_balance4_6 = getTotalTax(polist2,false);
        String law4_6 = "公式：\n应交税费";
        current_liabilities.add(new BalanceSheetItemVo("应交税费", 36, ending_balance4_6, beginning_balance4_6, law4_6));
        //4.7应付利息
        double ending_balance4_7 = getMoneyByCourseId(polist1, "2231", false);
        double beginning_balance4_7 = getMoneyByCourseId(polist2, "2231", false);
        String law4_7 = "公式：\n应付利息";
        current_liabilities.add(new BalanceSheetItemVo("应付利息", 37, ending_balance4_7, beginning_balance4_7, law4_7));
        //4.8应付利润
        double ending_balance4_8 = getMoneyByCourseId(polist1, "2232", false);
        double beginning_balance4_8 = getMoneyByCourseId(polist2, "2232", false);
        String law4_8 = "公式：\n应付利润";
        current_liabilities.add(new BalanceSheetItemVo("应付利润", 38, ending_balance4_8, beginning_balance4_8, law4_8));
        //4.9其他应付款=其他应付款+其他应收款（*余额在贷方时）
        double ending_balance4_9 = getMoneyByCourseId(polist1, "2241", false) + getMoneyByCourseId(polist1, "1221", false);
        double beginning_balance4_9 = getMoneyByCourseId(polist2, "2241", false) + getMoneyByCourseId(polist2, "1221", false);
        String law4_9 = "公式：\n其他应付款\n+其他应收款";
        current_liabilities.add(new BalanceSheetItemVo("其他应付款", 39, ending_balance4_9, beginning_balance4_9, law4_9));
        //4.10其他流动负债
        double ending_balance4_10 = getMoneyByCourseId(polist1,"8000", false);
        double beginning_balance4_10 = getMoneyByCourseId(polist2, "8000", false);
        String law4_10 = "公式：\n其他流动负债";
        current_liabilities.add(new BalanceSheetItemVo("其他流动负债", 40, ending_balance4_10, beginning_balance4_10, law4_10));
        //4.11流动负债合计=短期借款+应付票据+应付账款+预收账款+应付职工薪酬+应交税费+应付利息+其他应付款+应付利润+其他流动负债
        double ending_balance4_11 = ending_balance4_1+ending_balance4_2+ending_balance4_3+ending_balance4_4+ending_balance4_5+ending_balance4_6+ending_balance4_7+ending_balance4_8+ending_balance4_9+ending_balance4_10;
        double beginning_balance4_11 = beginning_balance4_1+beginning_balance4_2+beginning_balance4_3+ending_balance4_4+beginning_balance4_5+beginning_balance4_6+beginning_balance4_7+beginning_balance4_8+beginning_balance4_9+beginning_balance4_10;
        String law4_11 = "公式：\n短期借款\n+应付票据\n+应付账款\n+预收账款\n+应付职工薪酬\n+应交税费\n+应付利息\n+其他应付款\n+应付利润\n+其他流动负债";
        current_liabilities.add(new BalanceSheetItemVo("流动负债统计", 41, ending_balance4_11, beginning_balance4_11, law4_11));

        result.put("流动负债", current_liabilities);

        //5非流动负债
        ArrayList<BalanceSheetItemVo> no_current_liabilities = new ArrayList<>();

        //标题
        no_current_liabilities.add(new BalanceSheetItemVo("非流动负债",0,0,0, ""));

        //5.1长期借款
        double ending_balance5_1 = getMoneyByCourseId(polist1, "2501", false);
        double beginning_balance5_1 = getMoneyByCourseId(polist2, "2501", false);
        String law5_1 = "公式：\n长期付款";
        no_current_liabilities.add(new BalanceSheetItemVo("长期借款", 42, ending_balance5_1, beginning_balance5_1, law5_1));
        //5.2长期应付款
        double ending_balance5_2 = getMoneyByCourseId(polist1, "2701", false);
        double beginning_balance5_2 = getMoneyByCourseId(polist2, "2701", false);
        String law5_2 = "公式：\n长期应付款";
        no_current_liabilities.add(new BalanceSheetItemVo("长期应付款", 43, ending_balance5_2, beginning_balance5_2, law5_2));
        //5.3递延收益
        double ending_balance5_3 = getMoneyByCourseId(polist1, "2401", false);
        double beginning_balance5_3 = getMoneyByCourseId(polist2, "2401", false);
        String law5_3 = "公式：\n递延收益";
        no_current_liabilities.add(new BalanceSheetItemVo("递延收益", 44, ending_balance5_3, beginning_balance5_3, law5_3));
        //5.4其他非流动负债
        double ending_balance5_4 = getMoneyByCourseId(polist1, "8001", false);
        double beginning_balance5_4 = getMoneyByCourseId(polist2, "8001", false);
        String law5_4 = "公式：\n其他非流动负债";
        no_current_liabilities.add(new BalanceSheetItemVo("其他非流动负债", 45, ending_balance5_4, beginning_balance5_4, law5_4));
        //5.5非流动负债合计
        double ending_balance5_5 = ending_balance5_1+ending_balance5_2+ending_balance5_3+ending_balance5_4;
        double beginning_balance5_5 = beginning_balance5_1+beginning_balance5_2+beginning_balance5_3+beginning_balance5_4;
        String law5_5 = "公式：\n非流动负债合计";
        no_current_liabilities.add(new BalanceSheetItemVo("非流动负债合计", 46, ending_balance5_5, beginning_balance5_5, law5_5));

        result.put("非流动负债", no_current_liabilities);

        //6负债合计=流动负债合计+非流动负债合计
        ArrayList<BalanceSheetItemVo> total_liabilities = new ArrayList<>();

        double ending_balance6 = ending_balance4_11+ending_balance5_5;
        double beginning_balance6 = beginning_balance4_11+beginning_balance5_5;
        String law6 = "公式：\n流动负债合计\n+非流动负债合计";
        total_liabilities.add(new BalanceSheetItemVo("负债合计", 47, ending_balance6, beginning_balance6, law6));

        result.put("负债合计", total_liabilities);

        //所有者权益（或股东权益）
        ArrayList<BalanceSheetItemVo> owners_equity = new ArrayList<>();

        owners_equity.add(new BalanceSheetItemVo("",0,0,0,""));
        owners_equity.add(new BalanceSheetItemVo("",0,0,0,""));
        //标题
        owners_equity.add(new BalanceSheetItemVo("所有者权益",0,0,0,""));

        //7.1实收资本（或股本）
        double ending_balance7_1 = getMoneyByCourseId(polist1, "3001", false);
        double beginning_balance7_1 = getMoneyByCourseId(polist2, "3001", false);
        String law7_1 = "公式：\n实收资本（或股本）";
        owners_equity.add(new BalanceSheetItemVo("实收资本", 48, ending_balance7_1, beginning_balance7_1, law7_1));
        //7.2资本公积
        double ending_balance7_2 = getMoneyByCourseId(polist1, "3002", false);
        double beginning_balance7_2 = getMoneyByCourseId(polist2, "3002", false);
        String law7_2 = "公式：\n资本公积";
        owners_equity.add(new BalanceSheetItemVo("资本公积", 49, ending_balance7_2, beginning_balance7_2, law7_2));
        //7.3盈余公积
        double ending_balance7_3 = getMoneyByCourseId(polist1, "3101001", false)+getMoneyByCourseId(polist1, "3101002", false)+getMoneyByCourseId(polist1, "3101002", false);
        double beginning_balance7_3 = getMoneyByCourseId(polist2, "3101001", false)+getMoneyByCourseId(polist2, "3101002", false)+getMoneyByCourseId(polist2, "3101002", false);
        String law7_3 = "公式：\n盈余公积";
        owners_equity.add(new BalanceSheetItemVo("盈余公积", 50, ending_balance7_3, beginning_balance7_3, law7_3));
        //7.4未分配利润=利润分配+本年利润
        double ending_balance7_4 = getMoneyByCourseId(polist1, "3103", false)+getTotalProfit(polist1,false);
        double beginning_balance7_4 = getMoneyByCourseId(polist2, "3103", false)+getTotalProfit(polist2,false);
        String law7_4 = "公式：\n利润分配\n+本年利润";
        owners_equity.add(new BalanceSheetItemVo("未分配利润", 51, ending_balance7_4, beginning_balance7_4, law7_4));
        //7.5所有者权益（或股东权益）合计=实收资本+资本公积+盈余公积+未分配利润
        double ending_balance7_5 = ending_balance7_1+ending_balance7_2+ending_balance7_3+ending_balance7_4;
        double beginning_balance7_5 = beginning_balance7_1+beginning_balance7_2+beginning_balance7_3+beginning_balance7_4;
        String law7_5 = "公式：\n实收资本\n+资本公积\n+盈余公积\n+未分配利润";
        owners_equity.add(new BalanceSheetItemVo("所有者权益合计", 52, ending_balance7_5, beginning_balance7_5, law7_5));

        result.put("所有者权益", owners_equity);

        //8负债和所有者权益（或股东权益）合计=负债合计+所有者权益合计
        ArrayList<BalanceSheetItemVo> total_liab_owners = new ArrayList<>();
        double ending_balance8 = ending_balance6+ending_balance7_5;
        double beginning_balance8 = beginning_balance6+beginning_balance7_5;
        String law8 = "公式：\n负债合计\n+所有者权益合计";
        total_liab_owners.add(new BalanceSheetItemVo("负债和所有者权益合计", 53, ending_balance8, beginning_balance8, law8));

        result.put("负债和所有者权益合计", total_liab_owners);
        return result;
    }

    /**
     * 得到所有凭证数量
     * @return
     */
    public int getVoucherNumber(){
        CourseMessageService service = new CourseMessageServiceImpl();
        return service.getVoucherNumber();
    }

    /**
     * 得到凭证最早时间
     * @return
     */
    public String getEarliestTime(){
        CourseMessageService service = new CourseMessageServiceImpl();
        return service.getEarliestTime();
    }

    /**
     * 得到凭证最晚时间
     * @return
     */
    public String getLatestTime(){
        CourseMessageService service = new CourseMessageServiceImpl();
        return service.getLatestTime();
    }

    /**
     * 得到最早及最晚时间之间的所有月份
     * @param date1 最早时间
     * @param date2 最晚时间
     * @return
     */
    public ArrayList<String> getMiddleMonth(String date1 , String date2){
        String[] time1 = date1.split("-");
        String[] time2 = date2.split("-");
        int year1 = Integer.valueOf(time1[0]);
        int year2 = Integer.valueOf(time2[0]);
        int month1 = Integer.valueOf(time1[1]);
        int month2 = Integer.valueOf(time2[1]);

        ArrayList<String> result = new ArrayList<>();

        if(year1<year2){
            for(int i=month1;i<=12;i++){
                result.add(String.valueOf(year1)+"-"+String.valueOf(i));
            }
            for(int i=year1+1;i<year2;i++){
                for(int j=1;j<=12;j++){
                    result.add(String.valueOf(i)+"-"+String.valueOf(j));
                }
            }
            for(int i=1;i<=month2;i++){
                result.add(String.valueOf(year2)+"-"+String.valueOf(i));
            }
        }else{
            for(int i=month1;i<=month2;i++){
                result.add(String.valueOf(year1)+"-"+String.valueOf(i));
            }
        }
        return result;
    }

    /**
     * 根据时间得到资产负债表的货币资产
     * @param time
     * @return
     */
    public double[] getDollarAssent(String time){
        CourseMessageService service = new CourseMessageServiceImpl();
        ArrayList<VoucherAmountPO> polist1 = service.getCourseMessageByTime(time);
        ArrayList<VoucherAmountPO> polist2 = service.getCourseMessageByTime(getBeginningOfYear(time));
        //期末余额
        double ending_balance = getMoneyByCourseId(polist1, "1012", true) + getMoneyByCourseId(polist1, "1001", true) + getMoneyByCourseId(polist1, "1002", true);
        //年初余额
        double beginning_balance = getMoneyByCourseId(polist2, "1012", true) + getMoneyByCourseId(polist2, "1001", true) + getMoneyByCourseId(polist2, "1002", true);
        double[] result = {ending_balance, beginning_balance};
        return result;
    }

    /**
     * 根据科目编号已经是否借方得到科目金额
     *
     * @param polist    会计科目信息
     * @param course_id 科目编号
     * @param IsDebit   是否借方
     * @return
     */
    private double getMoneyByCourseId(ArrayList<VoucherAmountPO> polist, String course_id, boolean IsDebit) {
        for (int i = 0; i < polist.size(); i++) {
            VoucherAmountPO po = polist.get(i);
            if (po.getSubject().equals(course_id)) {
                if (IsDebit) {
                    return po.getDebitAmount();
                } else {
                    return po.getCreditAmount();
                }
            }
        }
        return 0.0;
    }

    /**
     * 根据当前阶段得到年初时间
     *
     * @param phase 阶段
     * @return
     */
    private String getBeginningOfYear(String phase) {
        return phase.substring(0,5)+"1";
    }

    /**
     * 计算应交税费
     *
     * @param polist 会计科目信息
     * @param IsDebit 是否借方
     * @return
     */
    private double getTotalTax(ArrayList<VoucherAmountPO> polist, boolean IsDebit){
        String[] idlist ={"222100101", "222100102", "222100103", "222100104", "222100105", "222100106", "222100107", "222100108", "222100109", "222100110",
                "2221002", "2221003", "2221004", "2221005", "2221006", "2221007", "2221008", "2221009", "2221010", "2221011", "2221012", "2221013", "2221014",
                "2221015", "2221016", "2221017", "2221018", "2221019", "2221020", "2221021", "2221022", "2221023", "2221024", "2221025"};
        double result = 0.0;
        for(int i=0;i<idlist.length;i++){
            result = result + getMoneyByCourseId(polist, idlist[i], IsDebit);
        }
        return result;
    }

    /**
     * 计算利润分配
     *
     * @param polist 会计科目信息
     * @param IsDebit 是否借方
     * @return
     */
    private double getTotalProfit(ArrayList<VoucherAmountPO> polist, boolean IsDebit){
        String[] idlist = {"3104001", "3104002", "3104003", "3104004", "3104005", "3104006"};
        double result = 0.0;
        for(int i=0;i<idlist.length;i++){
            result = result + getMoneyByCourseId(polist, idlist[i], IsDebit);
        }
        return result;
    }
}

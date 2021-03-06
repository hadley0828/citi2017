package presentation.screenController;

/**
 * @author Molloh
 * @version 2017/9/5
 * 定义界面相关的全局变量
 */
public interface ScreensFramework {

    /*主界面*/
    String HOME_LAYOUT_SCREEN = "HomeLayout";
    String HOME_LAYOUT_SCREEN_FXML = "/view/layout/HomeLayout.fxml";

    /*还有一个主界面*/
    String NEXT_HOME_SCREEN = "NextHome";
    String NEXT_HOME_SCREEN_FXML = "/view/layout/NextHome.fxml";

    /*财务系统界面*/
    String FINANCIAL_LAYOUT_SCREEN = "FinancialLayout";
    String FINANCIAL_LAYOUT_SCREEN_FXML = "/view/layout/FinancialLayout.fxml";

    /*供应链界面*/
    String SUPPLY_LAYOUT_SCREEN = "SupplyLayout";
    String SUPPLY_LAYOUT_SCREEN_FXML = "/view/layout/SupplyLayout.fxml";

    /*设置界面*/
    String SETTINGS_LAYOUT_SCREEN = "SettingsLayout";
    String SETTINGS_LAYOUT_SCREEN_FXML = "/view/layout/SettingsLayout.fxml";

    /*新增凭证*/
    String ADD_VOUCHER_SCREEN = "AddVoucher";
    String ADD_VOUCHER_SCREEN_FXML = "/view/financialSystem/voucher/AddVoucher.fxml";

    /*查询凭证*/
    String INQUIRE_VOUCHER_SCREEN = "InquireVoucher";
    String INQUIRE_VOUCHER_SCREEN_FXML = "/view/financialSystem/voucher/InquireVoucher.fxml";

    /*修改凭证*/
    String AMEND_VOUCHER_SCREEN = "AmendVoucher";
    String AMEND_VOUCHER_SCREEN_FXML = "/view/financialSystem/voucher/AmendVoucher.fxml";

    /*明细账*/
    String DETAIL_BILL_SCREEN = "DetailBill";
    String DETAIL_BILL_SCREEN_FXML = "/view/financialSystem/bill/DetailBill.fxml";

    /*总账*/
    String GENERAL_BILL_SCREEN = "GeneralBill";
    String GENERAL_BILL_SCREEN_FXML = "/view/financialSystem/bill/GeneralBill.fxml";

    /*科目余额表*/
    String SUBJECT_BALANCE_SHEET_SCREEN = "SubjectBalanceSheet";
    String SUBJECT_BALANCE_SHEET_SCREEN_FXML = "/view/financialSystem/bill/SubjectBalanceSheet.fxml";

    /*科目汇总表*/
    String SUBJECT_SUMMARY_SHEET_SCREEN = "SubjectSummarySheet";
    String SUBJECT_SUMMARY_SHEET_SCREEN_FXML = "/view/financialSystem/bill/SubjectSummarySheet.fxml";

    /*资产负债表*/
    String BALANCESHEET_SCREEN="BalanceSheet";
    String BALANCESHEET_SCREEN_FXML="/view/financialSystem/reportForm/balanceSheet/BalanceSheet.fxml";
    /*现金流量表*/
    String CASHFLOW_SCREEN="CashFlow";
    String CASHFLOW_SCREEN_FXML="/view/financialSystem/reportForm/cashFlow/CashFlow.fxml";
    /*利润表*/
    String PROFITSHEET_SCREEN="ProfitSheet";
    String PROFITSHEET_SCREEN_FXML="/view/financialSystem/reportForm/profitSheet/ProfitSheet.fxml";

    /*财务预警*/
    String FINANCIAL_WARNING_SCREEN = "FinancialWarning";
    String FINANCIAL_WARNING_SCREEN_FXML = "/view/financialSystem/warning/FinancialWarning.fxml";

    /*账套管理*/
    String ZT_EDIT_SCREEN = "ZhangtaoAdd";
    String ZT_EDIT_SCREEN_FXML = "/view/settings/ZhangtaoEdit.fxml";

    /*科目查看*/
    String KM_CK_SCREEN = "KeMuChaKan";
    String KM_CK_SCREEN_FXML = "/view/settings/kemu.fxml";

    /*期初设置*/
    String QC_SETTINGS_SCREEN = "QiChuSheZhi";
    String QC_SETTINGS_SCREEN_FXML = "/view/settings/qichu.fxml";

    /*凭证字查看*/
    String VOUCHER_CK_SCREEN = "VoucherChaKan";
    String VOUCHER_CK_SCREEN_FXML = "/view/settings/pingzhengzi.fxml";

    /*权限设置*/
    String ROOT_SETTINGS_SCREEN = "RootSettings";
    String ROOT_SETTINGS_SCREEN_FXML = "/view/settings/RootSetting.fxml";

    /*新增用户*/
    String ROOT_ADD_USER_SCREEN = "RootAddUser";
    String ROOT_ADD_USER_SCREEN_FXML = "/view/settings/RootSettingAddUser.fxml";

    /*修改密码*/
    String CHANGE_PW_SCREEN = "ChangePassword";
    String CHANGE_PW_SCREEN_FXML = "/view/settings/ChangePassword.fxml";

    /*供应链管理*/
    String LINK_SCREEN = "Link";
    String LINK_SCREEN_FXML = "/view/settings/LinkManagement.fxml";


    String FINANCIAL_SETTING_SCREEN = "";
    String FINANCIAL_SETTING_SCREEN_FXML = "";

    /*库存管理*/
    String STOCK_MANAGEMENT_SUPPLIER_SCREEN="Supplier";
    String STOCK_MANAGEMENT_SUPPLIER_SCREEN_FXML="/view/supplyChainManagement/stock/Supplier.fxml";

    String STOCK_MANAGEMENT_PRODUCER_SCREEN="Producer";
    String STOCK_MANAGEMENT_PRODUCER_SCREEN_FXML="/view/supplyChainManagement/stock/Producer.fxml";

    String STOCK_MANAGEMENT_DISTRIBUTOR_SCREEN="Distributor";
    String STOCK_MANAGEMENT_DISTRIBUTOR_SCREEN_FXML="/view/supplyChainManagement/stock/Distributor.fxml";

    /*现金流管理*/
    String CASH_LAYOUT_SCREEN = "CashLayout";
    String CASH_LAYOUT_SCREEN_FXML = "/view/layout/CashManagementLayout.fxml";

    String CASH_FINANCE_SCREEN = "CashFinance";
    String CASH_FINANCE_SCREEN_FXML = "/view/supplyChainManagement/cashManagement/CashFinance.fxml";

    String CASH_POOL_SCREEN = "CashPool";
    String CASH_POOL_SCREEN_FXML = "/view/supplyChainManagement/cashManagement/CashPool.fxml";

    String CHARGE_STATE_SCREEN = "ChargeState";
    String CHARGE_STATE_SCREEN_FXML = "/view/supplyChainManagement/cashManagement/ChargeState.fxml";

    String PAY_STATE_SCREEN = "PayState";
    String PAY_STATE_SCREEN_FXML = "/view/supplyChainManagement/cashManagement/PayState.fxml";

    /*融资服务*/
    String FINANCING_SCREEN="Financing";
    String FINANCING_SCREEN_FXML="/view/supplyChainManagement/financing/Financing.fxml";

    /*绩效评价*/
    String PERFORMANCE_APPRAISAL_SCREEN="PerformanceAppraisal";
    String PERFORMANCE_APPRAISAL_SCREEN_FXML="/view/supplyChainManagement/performanceAppraisal/PerformanceAppraisal.fxml";

    /*金融机构*/
    String FINANCIALINSTITUTIONS_SCREEN="FinancialInstitutions";
    String FINANCIALINSTITUTIONS_SCREEN_FXML="/view/supplyChainManagement/financialInstitutions/FinancialInstitutions.fxml";

    /*试算与结账*/
    String DEAL_SCREEN="Settle";
    String DEAL_SCREEN_FXML="/view/financialSystem/trialSettlement/Settle.fxml";

}
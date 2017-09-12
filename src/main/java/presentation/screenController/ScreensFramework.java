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
    String ADD_VOUCHER_SCREEN_FXML = "/view/voucher/AddVoucher.fxml";

    /*查询凭证*/
    String INQUIRE_VOUCHER_SCREEN = "InquireVoucher";
    String INQUIRE_VOUCHER_SCREEN_FXML = "/view/voucher/InquireVoucher.fxml";

    /*明细账*/
    String DETAIL_BILL_SCREEN = "DetailBill";
    String DETAIL_BILL_SCREEN_FXML = "/view/Bill/DetailBill.fxml";

    /*总账*/
    String GENERAL_BILL_SCREEN = "GeneralBill";
    String GENERAL_BILL_SCREEN_FXML = "/view/Bill/GeneralBill.fxml";

    /*科目余额表*/
    String SUBJECT_BALANCE_SHEET_SCREEN = "SubjectBalanceSheet";
    String SUBJECT_BALANCE_SHEET_SCREEN_FXML = "/view/Bill/SubjectBalanceSheet.fxml";

    /*科目汇总表*/
    String SUBJECT_SUMMARY_SHEET_SCREEN = "SubjectSummarySheet";
    String SUBJECT_SUMMARY_SHEET_SCREEN_FXML = "/view/Bill/SubjectSummarySheet.fxml";

    String PRO_DEBT_SCREEN = "Pro_debt";
    String PRO_DEBT_SCREEN_FXML = "/view/BalanceSheet/BalanceSheet.fxml";

    /*财务预警*/
    String FINANCIAL_WARNING_SCREEN = "FinancialWarning";
    String FINANCIAL_WARNING_SCREEN_FXML = "/view/warning/FinancialWarning.fxml";

    /*新建账套*/
    String ZT_ADD_SCREEN = "ZhangtaoAdd";
    String ZT_ADD_SCREEN_FXML = "/view/Settings/ZhangtaoEdit.fxml";

    String FINANCIAL_SETTING_SCREEN = "";
    String FINANCIAL_SETTING_SCREEN_FXML = "";
}

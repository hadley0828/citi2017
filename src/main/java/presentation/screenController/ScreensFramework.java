package presentation.screenController;

/**
 * @author Molloh
 * @version 2017/9/5
 * 定义界面相关的全局变量
 */
public interface ScreensFramework {

    /*模板*/
    String TEST_SCREEN = "Test";
    String TEST_SCREEN_FXML = "/view/Test.fxml";


    /*主界面*/
    String MAIN_SCREEN = "Main";
    String MAIN_SCREEN_FXML = "Main.fxml";

    /*新增凭证*/
    String ADD_VOUCHER_SCREEN = "AddVoucher";
    String ADD_VOUCHER_SCREEN_FXML = "/view/Voucher/AddVoucher.fxml";

    /*查询凭证*/
    String INQUIRE_VOUCHER_SCREEN = "InquireVoucher";
    String INQUIRE_VOUCHER_SCREEN_FXML = "/view/Voucher/InquireVoucher.fxml";

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

}

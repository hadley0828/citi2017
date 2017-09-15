package presentation.viewController.layout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.screenController.ScreensFramework;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2017/9/6
 */
public class FinancialLayoutController implements Initializable, ControlledScreen {
    @FXML
    private Button add_voucher;
    @FXML
    private Button inquire_voucher;
    @FXML
    private Button detail_bill;
    @FXML
    private Button general_bill;
    @FXML
    private Button subject_balance;
    @FXML
    private Button subject_summary;
    @FXML
    private Button pro_debt;

    @FXML
    private StackPane missionPane;

    private ScreensController parentController;
    private ScreensController financialController = new ScreensController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        financialController.loadScreen(ScreensFramework.AMEND_VOUCHER_SCREEN, ScreensFramework.AMEND_VOUCHER_SCREEN_FXML);

//        financialController.loadScreen(ScreensFramework.FINANCIAL_SETTING_SCREEN, ScreensFramework.FINANCIAL_SETTING_SCREEN_FXML);

        missionPane.getChildren().add(financialController);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        parentController = screenPage;
    }

    @FXML
    private void missionSwitch(ActionEvent event) {
        if (event.getSource().equals(add_voucher)) {
            financialController.loadScreen(ScreensFramework.ADD_VOUCHER_SCREEN, ScreensFramework.ADD_VOUCHER_SCREEN_FXML);
            financialController.setScreen(ScreensFramework.ADD_VOUCHER_SCREEN);
        }
        else if (event.getSource().equals(inquire_voucher)) {
            financialController.loadScreen(ScreensFramework.INQUIRE_VOUCHER_SCREEN, ScreensFramework.INQUIRE_VOUCHER_SCREEN_FXML);
            financialController.setScreen(ScreensFramework.INQUIRE_VOUCHER_SCREEN);
        }
        else if (event.getSource().equals(detail_bill)) {
            financialController.loadScreen(ScreensFramework.DETAIL_BILL_SCREEN, ScreensFramework.DETAIL_BILL_SCREEN_FXML);
            financialController.setScreen(ScreensFramework.DETAIL_BILL_SCREEN);
        }
        else if (event.getSource().equals(general_bill)) {
            financialController.loadScreen(ScreensFramework.GENERAL_BILL_SCREEN, ScreensFramework.GENERAL_BILL_SCREEN_FXML);
            financialController.setScreen(ScreensFramework.GENERAL_BILL_SCREEN);
        }
        else if (event.getSource().equals(subject_balance)) {
            financialController.loadScreen(ScreensFramework.SUBJECT_BALANCE_SHEET_SCREEN, ScreensFramework.SUBJECT_BALANCE_SHEET_SCREEN_FXML);
            financialController.setScreen(ScreensFramework.SUBJECT_BALANCE_SHEET_SCREEN);
        }
        else if (event.getSource().equals(subject_summary)) {
            financialController.loadScreen(ScreensFramework.SUBJECT_SUMMARY_SHEET_SCREEN, ScreensFramework.SUBJECT_SUMMARY_SHEET_SCREEN_FXML);
            financialController.setScreen(ScreensFramework.SUBJECT_SUMMARY_SHEET_SCREEN);
        }
//        else if (event.getSource().equals(pro_debt)) {
//            financialController.loadScreen(ScreensFramework.BALANCESHEET_SCREEN,ScreensFramework.BALANCESHEET_SCREEN_FXML);
//            financialController.setScreen(ScreensFramework.BALANCESHEET_SCREEN);
//        }
//        else if (event.getSource().equals(pro_debt)) {
//            financialController.loadScreen(ScreensFramework.CASHFLOW_SCREEN,ScreensFramework.CASHFLOW_SCREEN_FXML);
//            financialController.setScreen(ScreensFramework.CASHFLOW_SCREEN);
//        }
        else if (event.getSource().equals(pro_debt)) {
            financialController.loadScreen(ScreensFramework.PROFITSHEET_SCREEN,ScreensFramework.PROFITSHEET_SCREEN_FXML);
            financialController.setScreen(ScreensFramework.PROFITSHEET_SCREEN);
        }
    }

}

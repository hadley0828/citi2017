package presentation.viewController.layout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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
    private ToggleButton add_voucher;
    @FXML
    private ToggleButton inquire_voucher;
    @FXML
    private ToggleButton detail_bill;
    @FXML
    private ToggleButton general_bill;
    @FXML
    private ToggleButton subject_balance;
    @FXML
    private ToggleButton subject_summary;
    @FXML
    private ToggleButton pro_debt;
    @FXML
    private ToggleButton warning_btn;
    @FXML
    private ToggleButton cashflow_btn;
    @FXML
    private ToggleButton profit_btn;

    @FXML
    private StackPane missionPane;

    private ScreensController parentController;
    private ScreensController financialController = new ScreensController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(add_voucher, inquire_voucher, detail_bill, general_bill, subject_balance, subject_summary, pro_debt, warning_btn, cashflow_btn, profit_btn);
//        financialController.loadScreen(ScreensFramework.AMEND_VOUCHER_SCREEN, ScreensFramework.AMEND_VOUCHER_SCREEN_FXML);

//        financialController.loadScreen(ScreensFramework.FINANCIAL_SETTING_SCREEN, ScreensFramework.FINANCIAL_SETTING_SCREEN_FXML);
        financialController.loadScreen(ScreensFramework.INQUIRE_VOUCHER_SCREEN, ScreensFramework.INQUIRE_VOUCHER_SCREEN_FXML);

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
            financialController.setScreen(ScreensFramework.INQUIRE_VOUCHER_SCREEN);
        }
        else if (event.getSource().equals(detail_bill)) {
            System.out.println("aaa");
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
        else if (event.getSource().equals(warning_btn)) {
            System.out.println("aaa");
            financialController.loadScreen(ScreensFramework.FINANCIAL_WARNING_SCREEN, ScreensFramework.FINANCIAL_WARNING_SCREEN_FXML);
            financialController.setScreen(ScreensFramework.FINANCIAL_WARNING_SCREEN);
        }
        else if (event.getSource().equals(pro_debt)) {
            financialController.loadScreen(ScreensFramework.BALANCESHEET_SCREEN,ScreensFramework.BALANCESHEET_SCREEN_FXML);
            financialController.setScreen(ScreensFramework.BALANCESHEET_SCREEN);
        }
        else if (event.getSource().equals(cashflow_btn)) {
            financialController.loadScreen(ScreensFramework.CASHFLOW_SCREEN,ScreensFramework.CASHFLOW_SCREEN_FXML);
            financialController.setScreen(ScreensFramework.CASHFLOW_SCREEN);
        }
        else if (event.getSource().equals(profit_btn)) {
            financialController.loadScreen(ScreensFramework.PROFITSHEET_SCREEN,ScreensFramework.PROFITSHEET_SCREEN_FXML);
            financialController.setScreen(ScreensFramework.PROFITSHEET_SCREEN);
        }
    }

}

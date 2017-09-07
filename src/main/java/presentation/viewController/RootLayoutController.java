package presentation.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import presentation.screenController.ScreensController;
import presentation.screenController.ScreensFramework;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2017/9/6
 */
public class RootLayoutController implements Initializable {
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

    private ScreensController myController = ScreensController.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*载入凭证界面*/
        myController.loadScreen(ScreensFramework.ADD_VOUCHER_SCREEN, ScreensFramework.ADD_VOUCHER_SCREEN_FXML);
        myController.loadScreen(ScreensFramework.INQUIRE_VOUCHER_SCREEN, ScreensFramework.INQUIRE_VOUCHER_SCREEN_FXML);
        /*载入账簿界面*/
        myController.loadScreen(ScreensFramework.DETAIL_BILL_SCREEN, ScreensFramework.DETAIL_BILL_SCREEN_FXML);
        myController.loadScreen(ScreensFramework.GENERAL_BILL_SCREEN, ScreensFramework.GENERAL_BILL_SCREEN_FXML);
        myController.loadScreen(ScreensFramework.SUBJECT_BALANCE_SHEET_SCREEN, ScreensFramework.SUBJECT_BALANCE_SHEET_SCREEN_FXML);
        myController.loadScreen(ScreensFramework.SUBJECT_SUMMARY_SHEET_SCREEN, ScreensFramework.SUBJECT_SUMMARY_SHEET_SCREEN_FXML);

        myController.loadScreen(ScreensFramework.PRO_DEBT_SCREEN,ScreensFramework.PRO_DEBT_SCREEN_FXML);
        missionPane.getChildren().add(myController);
    }

    @FXML
    private void missionSwitch(ActionEvent event) {
        if (event.getSource().equals(add_voucher))
            myController.setScreen(ScreensFramework.ADD_VOUCHER_SCREEN);
        else if (event.getSource().equals(inquire_voucher))
            myController.setScreen(ScreensFramework.INQUIRE_VOUCHER_SCREEN);
        else if (event.getSource().equals(detail_bill))
            myController.setScreen(ScreensFramework.DETAIL_BILL_SCREEN);
        else if (event.getSource().equals(general_bill))
            myController.setScreen(ScreensFramework.GENERAL_BILL_SCREEN);
        else if (event.getSource().equals(subject_balance))
            myController.setScreen(ScreensFramework.SUBJECT_BALANCE_SHEET_SCREEN);
        else if (event.getSource().equals(subject_summary))
            myController.setScreen(ScreensFramework.SUBJECT_SUMMARY_SHEET_SCREEN);
        else if (event.getSource().equals(pro_debt))
            myController.setScreen(ScreensFramework.PRO_DEBT_SCREEN);

    }

}

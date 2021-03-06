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
 * @version 2017/9/12
 */
public class CashManagementLayoutController implements Initializable, ControlledScreen {
    @FXML
    private ToggleButton pool_btn;
    @FXML
    private ToggleButton finance_btn;
    @FXML
    private ToggleButton pay_btn;
    @FXML
    private ToggleButton charge_btn;
    @FXML
    private StackPane cashStack;

    private ScreensController parentController;
    private ScreensController cashManagementController = new ScreensController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(pay_btn, pool_btn, finance_btn, charge_btn);
        cashManagementController.setScreen(ScreensFramework.CASH_POOL_SCREEN);
        cashStack.getChildren().add(cashManagementController);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        parentController = screenPage;
    }

    @FXML
    private void missionSwitch(ActionEvent event) {
        if (event.getSource().equals(pool_btn)) {
            cashManagementController.loadScreen(ScreensFramework.CASH_POOL_SCREEN, ScreensFramework.CASH_POOL_SCREEN_FXML);
            cashManagementController.setScreen(ScreensFramework.CASH_POOL_SCREEN);
        }
        else if (event.getSource().equals(finance_btn)) {
            cashManagementController.loadScreen(ScreensFramework.CASH_FINANCE_SCREEN, ScreensFramework.CASH_FINANCE_SCREEN_FXML);
            cashManagementController.setScreen(ScreensFramework.CASH_FINANCE_SCREEN);
        }
        else if (event.getSource().equals(pay_btn)) {
            cashManagementController.loadScreen(ScreensFramework.PAY_STATE_SCREEN, ScreensFramework.PAY_STATE_SCREEN_FXML);
            cashManagementController.setScreen(ScreensFramework.PAY_STATE_SCREEN);
        }
        else if (event.getSource().equals(charge_btn)) {
            cashManagementController.loadScreen(ScreensFramework.CHARGE_STATE_SCREEN, ScreensFramework.CHARGE_STATE_SCREEN_FXML);
            cashManagementController.setScreen(ScreensFramework.CHARGE_STATE_SCREEN);
        }
    }
}

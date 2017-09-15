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
 * @version 2017/9/9
 */
public class SupplyLayoutController implements Initializable, ControlledScreen {
    @FXML
    private Button inventory_btn;
    @FXML
    private Button cash_btn;
    @FXML
    private Button financing_btn;
    @FXML
    private Button performance_btn;

    @FXML
    private StackPane supplyStack;

    private ScreensController parentController;
    private ScreensController supplyController = new ScreensController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        supplyController.loadScreen(ScreensFramework.STOCK_MANAGEMENT_DISTRIBUTOR_SCREEN, ScreensFramework.STOCK_MANAGEMENT_DISTRIBUTOR_SCREEN_FXML);
//        supplyController.loadScreen();

        supplyStack.getChildren().add(supplyController);

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        parentController = screenPage;
    }

    @FXML
    private void missionSwitch(ActionEvent event) {
        if (event.getSource().equals(inventory_btn)) {
            supplyController.loadScreen(ScreensFramework.STOCK_MANAGEMENT_SUPPLIER_SCREEN, ScreensFramework.STOCK_MANAGEMENT_SUPPLIER_SCREEN_FXML);
            supplyController.setScreen(ScreensFramework.STOCK_MANAGEMENT_SUPPLIER_SCREEN);
        }
        else if (event.getSource().equals(cash_btn)) {
            supplyController.loadScreen(ScreensFramework.CASH_LAYOUT_SCREEN, ScreensFramework.CASH_LAYOUT_SCREEN_FXML);
            supplyController.setScreen(ScreensFramework.CASH_LAYOUT_SCREEN);
        }
        else if (event.getSource().equals(financing_btn)) {
            supplyController.loadScreen(ScreensFramework.FINANCING_SCREEN, ScreensFramework.FINANCING_SCREEN_FXML);
            supplyController.setScreen(ScreensFramework.FINANCING_SCREEN);
        }
        else if (event.getSource().equals(performance_btn)) {
            supplyController.loadScreen(ScreensFramework.PERFORMANCE_APPRAISAL_SCREEN, ScreensFramework.PERFORMANCE_APPRAISAL_SCREEN_FXML);
            supplyController.setScreen(ScreensFramework.PERFORMANCE_APPRAISAL_SCREEN);
        }
    }

}

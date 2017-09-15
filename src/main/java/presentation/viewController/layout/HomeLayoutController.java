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
public class HomeLayoutController implements Initializable, ControlledScreen {
    @FXML
    private Button entrance_btn;

    @FXML
    private StackPane homeStack;

    private ScreensController parentController;
    private ScreensController homeController = new ScreensController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homeStack.getChildren().add(homeController);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        parentController = screenPage;
    }

    @FXML
    private void missionSwitch(ActionEvent event) {
        if (event.getSource().equals(entrance_btn)) {
            parentController.setScreen(ScreensFramework.FINANCIAL_LAYOUT_SCREEN);
        }
    }
}

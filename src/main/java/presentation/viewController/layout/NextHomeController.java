package presentation.viewController.layout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.screenController.ScreensFramework;

import java.net.URL;
import java.util.ResourceBundle;

public class NextHomeController implements Initializable, ControlledScreen {
    @FXML
    private Button entrance_btn;
    @FXML
    private Button next_btn;

    private ScreensController parentController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        parentController = screenPage;
    }

    @FXML
    private void missionSwitch(ActionEvent event) {
        if (event.getSource().equals(entrance_btn))
            parentController.setScreen(ScreensFramework.SUPPLY_LAYOUT_SCREEN);
        else
            parentController.setScreen(ScreensFramework.HOME_LAYOUT_SCREEN);
    }
}

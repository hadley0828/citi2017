package presentation.viewController.layout;

import com.jfoenix.controls.JFXButton;
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
    private JFXButton entrance_btn;
    @FXML
    private JFXButton next_btn;

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
            parentController.setAnimatedScreen(ScreensFramework.SUPPLY_LAYOUT_SCREEN);
        else if (event.getSource().equals(next_btn))
            parentController.setAnimatedScreen(ScreensFramework.HOME_LAYOUT_SCREEN);
    }
}

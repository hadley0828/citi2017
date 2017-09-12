package presentation.viewController.layout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsLayoutController implements Initializable, ControlledScreen {

    private ScreensController parentController;
    private ScreensController settingsController = new ScreensController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //settingsController.loadScreen();

        //settingsController.setScreen();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        parentController = screenPage;
    }

    @FXML
    private void missionSwitch(ActionEvent event) {
        /*
        if(event.getSource().equals())
            settingsController.setScreen();
        else if(event.getSource().equals())
            settingsController.setScreen();
        */
    }
}

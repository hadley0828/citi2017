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
    private Button test_btn;
    @FXML
    private StackPane missionPane;

    private ScreensController myController = ScreensController.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myController.loadScreen(ScreensFramework.ADD_VOUCHER_SCREEN, ScreensFramework.ADD_VOUCHER_SCREEN_FXML);
        myController.loadScreen(ScreensFramework.TEST_SCREEN, ScreensFramework.TEST_SCREEN_FXML);
        missionPane.getChildren().add(myController);
    }

    @FXML
    private void missionSwitch(ActionEvent event) {
        if (event.getSource().equals(add_voucher))
            myController.setScreen(ScreensFramework.ADD_VOUCHER_SCREEN);
        else if (event.getSource().equals(test_btn))
            myController.setScreen(ScreensFramework.TEST_SCREEN);
    }

}

package presentation.viewController.layout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import presentation.screenController.ScreensController;
import presentation.screenController.ScreensFramework;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2017/9/9
 */
public class RootLayoutController implements Initializable {
    @FXML
    private Button logo_btn;
    @FXML
    private Button financial_btn;
    @FXML
    private Button supply_btn;
    @FXML
    private Button settings_btn;
    @FXML
    private Button signOut_btn;

    @FXML
    private StackPane rootStack;

    private ScreensController rootController = new ScreensController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rootController.loadScreen(ScreensFramework.FINANCIAL_LAYOUT_SCREEN, ScreensFramework.FINANCIAL_LAYOUT_SCREEN_FXML);

        rootController.loadScreen(ScreensFramework.HOME_LAYOUT_SCREEN, ScreensFramework.HOME_LAYOUT_SCREEN_FXML);
        rootController.setScreen(ScreensFramework.HOME_LAYOUT_SCREEN);

        rootStack.getChildren().add(rootController);
    }

    @FXML
    private void missionSwitch(ActionEvent event) {
        if (event.getSource().equals(logo_btn)) {
            rootController.setScreen(ScreensFramework.HOME_LAYOUT_SCREEN);
        }
        else if (event.getSource().equals(financial_btn)) {
            rootController.setScreen(ScreensFramework.FINANCIAL_LAYOUT_SCREEN);
        }
        else if (event.getSource().equals(supply_btn)) {
            rootController.loadScreen(ScreensFramework.SUPPLY_LAYOUT_SCREEN, ScreensFramework.SUPPLY_LAYOUT_SCREEN_FXML);
            rootController.setScreen(ScreensFramework.SUPPLY_LAYOUT_SCREEN);
        }
        else if (event.getSource().equals(settings_btn)) {
            rootController.loadScreen(ScreensFramework.SETTINGS_LAYOUT_SCREEN, ScreensFramework.SETTINGS_LAYOUT_SCREEN_FXML);
            rootController.setScreen(ScreensFramework.SETTINGS_LAYOUT_SCREEN);
        }
    }
}

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
 * @version 2017/9/12
 */
public class SettingsLayoutController implements Initializable, ControlledScreen {
    @FXML
    private Button zt_btn;
    @FXML
    private Button km_btn;
    @FXML
    private Button qc_btn;
    @FXML
    private Button pzz_btn;
    @FXML
    private Button pw_btn;
    @FXML
    private Button qx_btn;
    @FXML
    private Button link_btn;

    @FXML
    private StackPane settingsStack;

    private ScreensController parentController;
    private ScreensController settingsController = new ScreensController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        settingsController.loadScreen(ScreensFramework.ZT_EDIT_SCREEN, ScreensFramework.ZT_EDIT_SCREEN_FXML);
        settingsController.loadScreen(ScreensFramework.KM_CK_SCREEN, ScreensFramework.KM_CK_SCREEN_FXML);
        settingsController.loadScreen(ScreensFramework.QC_SETTINGS_SCREEN, ScreensFramework.QC_SETTINGS_SCREEN_FXML);
        settingsController.loadScreen(ScreensFramework.VOUCHER_CK_SCREEN, ScreensFramework.VOUCHER_CK_SCREEN_FXML);
        settingsController.loadScreen(ScreensFramework.CHANGE_PW_SCREEN, ScreensFramework.CHANGE_PW_SCREEN_FXML);
        settingsController.loadScreen(ScreensFramework.ROOT_SETTINGS_SCREEN, ScreensFramework.ROOT_SETTINGS_SCREEN_FXML);
        settingsController.loadScreen(ScreensFramework.LINK_SCREEN, ScreensFramework.LINK_SCREEN_FXML);

        //settingsController.setScreen();
        settingsStack.getChildren().add(settingsController);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        parentController = screenPage;
    }

    @FXML
    private void missionSwitch(ActionEvent event) {
        if (event.getSource().equals(zt_btn))
            settingsController.setScreen(ScreensFramework.ZT_EDIT_SCREEN);
        else if (event.getSource().equals(km_btn))
            settingsController.setScreen(ScreensFramework.KM_CK_SCREEN);
        else if (event.getSource().equals(qc_btn))
            settingsController.setScreen(ScreensFramework.QC_SETTINGS_SCREEN);
        else if (event.getSource().equals(pzz_btn))
            settingsController.setScreen(ScreensFramework.VOUCHER_CK_SCREEN);
        else if (event.getSource().equals(pw_btn))
            settingsController.setScreen(ScreensFramework.CHANGE_PW_SCREEN);
        else if (event.getSource().equals(qx_btn))
            settingsController.setScreen(ScreensFramework.ROOT_SETTINGS_SCREEN);
        else if (event.getSource().equals(link_btn))
            settingsController.setScreen(ScreensFramework.LINK_SCREEN);

    }
}

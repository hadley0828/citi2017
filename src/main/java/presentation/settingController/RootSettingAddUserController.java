package presentation.settingController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;

/**
 * Created by Chris on 2017/9/12.
 */
public class RootSettingAddUserController implements ControlledScreen{
    @FXML
    private Label id;
    @FXML
    private Label password;
    @FXML
    private Button closeButton;
    @FXML
    public void initialize(){
        //新的uservo

    }

    public void CloseClicked(){
        /**
         * 关闭当前窗口
         */
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

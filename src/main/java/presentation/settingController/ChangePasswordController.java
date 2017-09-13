package presentation.settingController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;

/**
 * Created by Chris on 2017/9/12.
 */
public class ChangePasswordController implements ControlledScreen {
    @FXML
    private PasswordField rawPassword;
    @FXML
    private  PasswordField newPassword;
    @FXML
    private  PasswordField comfirmPassword;

    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButon;

    @FXML
    public void initialize(){


    }

    public void rawConfirm(){
        /**
         * 确认原密码正确
         */
    }
    public void saveClicked(){
        /*
        调用接口
         */
    }

    public void cancelClicked(){
        /**
         * 跳转
         */
    }


    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

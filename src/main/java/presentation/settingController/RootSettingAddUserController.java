package presentation.settingController;

import businesslogic.UserManagementImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.StaticFactory;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.warningController.RunWarning;
import util.EnumPackage.ResultMessage;
import vo.userManagement.UserVO;

/**
 * Created by Chris on 2017/9/12.
 */
public class RootSettingAddUserController implements ControlledScreen{
    @FXML
    private TextField id;
    @FXML
    private PasswordField password;
    @FXML
    private Button closeButton;
    @FXML
    public void initialize(){


    }

    public void CloseClicked(){
        /**
         *
         */
        if(id.getText().isEmpty()||password.getText().isEmpty()){


        }else{
        UserManagementImpl impl=new UserManagementImpl();
        UserVO vo=StaticFactory.getUserVO();
        vo.setUserID(id.getText());
        vo.setType("normal");
        ResultMessage rm=impl.insertOneCompanyUser(vo,password.getText());
            if (rm == ResultMessage.EXIST_USERID) {
                RunWarning runWarning = new RunWarning();
                runWarning.SetWarning("用户已存在");
                runWarning.start(new Stage());

            } else if (rm == ResultMessage.SHORT_PASSWORD) {
                RunWarning runWarning = new RunWarning();
                runWarning.SetWarning("密码过短");
                runWarning.start(new Stage());

            } else if (rm == ResultMessage.FAIL ) {
                RunWarning runWarning = new RunWarning();
                runWarning.SetWarning("注册失败");
                runWarning.start(new Stage());

            } else if (rm == ResultMessage.SUCCESS ) {
                RunWarning runWarning = new RunWarning();
                runWarning.SetWarning("注册成功");
                runWarning.start(new Stage());

                //返回登陆界面

            }

    }}

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

package presentation.settingController;

import businesslogic.UserManagementImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.viewController.StaticFactory;
import presentation.warningController.RunWarning;
import util.EnumPackage.ResultMessage;

/**
 * Created by Chris on 2017/9/12.
 */
public class ChangePasswordController implements ControlledScreen {
    @FXML
    private PasswordField rawPassword;
    @FXML
    private  PasswordField newPassword;
    @FXML
    private  PasswordField confirmPassword;

    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButon;

    @FXML
    public void initialize(){
        rawPassword.setText("");
        newPassword.setText("");
        confirmPassword.setText("");


    }

//    public void rawConfirm(){
//        /**
//         * 确认原密码正确
//         */
//    }
    public void saveClicked(){

        /*
        调用接口
         */
        String raw=rawPassword.getText();
        String newp=newPassword.getText();
        String conf=confirmPassword.getText();
        if(raw.isEmpty()||newp.isEmpty()||conf.isEmpty()){
            //空
            RunWarning runWarning=new RunWarning();
            runWarning.SetWarning("请输入完整！");
            runWarning.start(new Stage());
        }else if(!newp.equals(conf)){
            RunWarning runWarning=new RunWarning();
            runWarning.SetWarning("确认密码不正确！");
            runWarning.start(new Stage());
            //两次密码不对

        }else{
            String id= StaticFactory.getUserVO().getUserID();

        UserManagementImpl impl=new UserManagementImpl();
        ResultMessage rm=impl.modifyPassword(id,raw,newp);
        if(rm==ResultMessage.FAIL){
            RunWarning runWarning=new RunWarning();
            runWarning.SetWarning("原密码不正确！");
            runWarning.start(new Stage());
        }else if(rm==ResultMessage.SUCCESS){
            RunWarning runWarning=new RunWarning();
            runWarning.SetWarning("修改成功");
            runWarning.start(new Stage());
            rawPassword.setText("");
            newPassword.setText("");
            confirmPassword.setText("");
        }
    }}

    public void cancelClicked(){
        /**
         * 跳转
         */
    }


    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

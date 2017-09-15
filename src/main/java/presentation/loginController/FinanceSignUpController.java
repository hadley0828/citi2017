package presentation.loginController;

import businesslogic.UserManagementImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.Entrance;
import presentation.warningController.RunWarning;
import util.EnumPackage.ResultMessage;
import vo.userManagement.FinancialUserVO;

/**
 * Created by Chris on 2017/9/12.
 */
public class FinanceSignUpController {
    @FXML
    private  TextField loginid;
    @FXML
    private  TextField legalperson;
    @FXML
    private  Button createButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField FinanceName;

    @FXML
    private TextField FinanceAddress;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField legalPersonCard;

    @FXML
    private TextField FinancialLicense;

    @FXML
    public void initialize(){

    }

    public void createButtonClicked(){
        /**
         * 调用接口
         */
        try{

            String logid=loginid.getText();
            String financename=FinanceName.getText();
            String address=FinanceAddress.getText();
            String legal=legalPersonCard.getText();
            String license=FinancialLicense.getText();
            String password=passwordField.getText();
            String legalp=legalperson.getText();
            FinancialUserVO Fvo=new FinancialUserVO(logid,financename,address,license,legal,legalp);
            if(logid.isEmpty()||financename.isEmpty()||address.isEmpty()||legal.isEmpty()||license.isEmpty()||password.isEmpty()||legalp.isEmpty()){
                RunWarning runWarning=new RunWarning();
                runWarning.SetWarning("请录入完整信息！");
                runWarning.start(new Stage());

            }else {

                UserManagementImpl impl = new UserManagementImpl();
                ResultMessage rm = impl.insertOneFinancialUser(Fvo, password);
                if (rm == ResultMessage.EXIST_USERID) {
                    RunWarning runWarning = new RunWarning();
                    runWarning.SetWarning("用户已存在");
                    runWarning.start(new Stage());

                } else if (rm == ResultMessage.SHORT_PASSWORD) {
                    RunWarning runWarning = new RunWarning();
                    runWarning.SetWarning("密码过短");
                    runWarning.start(new Stage());

                } else if (rm == ResultMessage.FAIL) {
                    RunWarning runWarning = new RunWarning();
                    runWarning.SetWarning("注册失败");
                    runWarning.start(new Stage());

                } else if (rm == ResultMessage.SUCCESS) {
                    RunWarning runWarning = new RunWarning();
                    runWarning.SetWarning("注册成功");
                    runWarning.start(new Stage());

                    //返回登陆界面
                    try {
                        Entrance entrance=new Entrance();
                        entrance.start(new StaticStage().GetStage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

        }catch (NullPointerException e){
            RunWarning runWarning=new RunWarning();
            runWarning.SetWarning("请录入完整信息！");
            runWarning.start(new Stage());
        }






    }

    public void cancelButtonClicked(){
        /**
         * 跳转
         */
        try {
            Entrance entrance=new Entrance();
            entrance.start(new StaticStage().GetStage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}

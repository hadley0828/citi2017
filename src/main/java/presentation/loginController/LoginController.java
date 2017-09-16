package presentation.loginController;

import businesslogic.UserManagementImpl;
import businesslogicservice.UserManagementService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.Entrance;
import presentation.RunFLayout;
import presentation.RunRootLayout;
import presentation.StaticFactory;
import presentation.warningController.RunWarning;
import util.EnumPackage.ResultMessage;
import vo.userManagement.FinancialUserVO;
import vo.userManagement.UserVO;

import java.io.IOException;

/**
 * Created by Chris on 2017/9/12.
 */
public class LoginController {
    @FXML
    private TextField CompID;

    @FXML
    private PasswordField CompPassword;

    @FXML
    private TextField FinanceID;

    @FXML
    private PasswordField FinancePassword;

    @FXML
    private Button compLoginButton;

    @FXML
    private Button compSignUpButton;

    @FXML
    private Button FinanceLoginButton;

    @FXML
    private Button FinanceSignupButton;


    private static Entrance entrance;




    @FXML
    public void initialize(){
        //好像没啥东西
    }

    public void CompLoginClicked(){
        String compID=CompID.getText();
        String compPassword=CompPassword.getText();
        /**
         * 调用接口判断
         */

//        ResultMessage resultMessage=ResultMessage.SUCCESS;
        if(CompID.getText().isEmpty()||CompPassword.getText().isEmpty()){
            RunWarning runWarning=new RunWarning();
            runWarning.SetWarning("请输入账号和密码！");
            runWarning.start(new Stage());
        }else {
            UserManagementService service=new UserManagementImpl();
            ResultMessage resultMessage=service.loginIn(compID,compPassword);
            if(!service.isCompanyUser(compID)){
            RunWarning runWarning=new RunWarning();
            runWarning.SetWarning("请从金融机构入口登陆！");
            runWarning.start(new Stage());
        }
        else if(resultMessage==ResultMessage.FAIL){
            /**
             * 弹警告窗
             */
            RunWarning runWarning=new RunWarning();
            runWarning.SetWarning("账号或密码不正确");
            runWarning.start(new Stage());

        }else if(resultMessage==ResultMessage.SUCCESS){


            UserVO vo=service.getOneCompanyUser(compID);
            StaticFactory.setUserVO(vo);
            RunRootLayout rr=new RunRootLayout();
            try {
                new StaticStage().GetStage().close();;
                Stage stage = new Stage(StageStyle.UNDECORATED);
                rr.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        /**
         * 跳转
         */


    }}

    public void CompSignupClicked() throws IOException{
        /**
         * 跳转
         */
        RunCSignUp c=new RunCSignUp();
        try {
            c.start(new StaticStage().GetStage());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void FinanceLoginClicked(){
        String Financeid=FinanceID.getText();
        String Financepassword=FinancePassword.getText();
        /**
         * 调用接口判断
         */

//        ResultMessage resultMessage=ResultMessage.SUCCESS;
        if(Financeid.isEmpty()||Financepassword.isEmpty()){
            RunWarning runWarning=new RunWarning();
            runWarning.SetWarning("请输入账号和密码！");
            runWarning.start(new Stage());
        }else {
            UserManagementService service=new UserManagementImpl();
            ResultMessage resultMessage=service.loginIn(Financeid,Financepassword);
            if(!service.isFinancialUser(Financeid)){
            RunWarning runWarning=new RunWarning();
            runWarning.SetWarning("请从企业入口登陆！");
            runWarning.start(new Stage());
        }
        else if(resultMessage==ResultMessage.FAIL){
            /**
             * 弹警告窗
             */
            RunWarning runWarning=new RunWarning();
            runWarning.SetWarning("密码或账号不正确！");
            runWarning.start(new Stage());


        }else if(resultMessage==ResultMessage.SUCCESS){
            FinancialUserVO vo=service.getOneFinancialUser(Financeid);

            StaticFactory.setFinancialUserVO(vo);
            /**
             * 跳转
             */
            RunFLayout layout=new RunFLayout();
            try {
                layout.start(new StaticStage().GetStage());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }




    }}

    public void FinanceSignupClicked()throws IOException{
        /**
         * 跳转
         */
        RunFSignUp c=new RunFSignUp();
        try {
            c.start(new StaticStage().GetStage());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    }


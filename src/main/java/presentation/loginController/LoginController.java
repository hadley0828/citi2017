package presentation.loginController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

    }

    public void CompSignupClicked(){
        /**
         * 跳转
         */

    }

    public void FinanceLoginClicked(){
        String compID=FinanceID.getText();
        String compPassword=FinancePassword.getText();
        /**
         * 调用接口判断
         */

    }

    public void FinanceSignupClicked(){
        /**
         * 跳转
         */

    }
}

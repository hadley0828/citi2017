package presentation.loginController;

import businesslogic.SettingImpl;
import businesslogic.UserManagementImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import presentation.Entrance;
import presentation.warningController.RunWarning;
import util.EnumPackage.ResultMessage;
import vo.userManagement.AccountSetVO;
import vo.userManagement.UserVO;

import java.util.ArrayList;


/**
 * Created by Chris on 2017/9/12.
 */
public class CompSignUpController {
    @FXML
    private TextField loginid;
    @FXML
    private ChoiceBox<String> chainplace;
    @FXML
    private TextField compName;

    @FXML
    private TextField compAddress;

    @FXML
    private TextField creditCode;

    @FXML
    private TextField contact;

    @FXML
    private DatePicker startTime;

    @FXML
    private ChoiceBox<String> industryType;
    @FXML
    private ChoiceBox<String> subtype;



    @FXML
    private PasswordField passwordField;

    @FXML
    private Button createButton;

    @FXML
    private Button cancelButton;

    @FXML
    public void initialize(){
        /**
         * 行业初始化
         */

        SettingImpl impl=new SettingImpl();
        ArrayList<String> superlist= impl.getAllSuperIndustry();
        industryType.getItems().addAll(superlist);
        industryType.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        String temp=industryType.getItems().get(newValue.intValue());
                        ArrayList<String> sublist=impl.getAllSubIndustry(String.valueOf(temp));
                        subtype.getItems().clear();

                        subtype.getItems().addAll(sublist);

                    }
                }
        );
        ArrayList<String> chain=new ArrayList<>();
        chain.add("生产商");
        chain.add("分销商");
        chain.add("供应商");
        chainplace.getItems().addAll(chain);


    }

    public void createButtonClicked(){
        /**
         * 调用接口
         */
        if(compName.getText().isEmpty()||compAddress.getText().isEmpty()||contact.getText().isEmpty()||
                creditCode.getText().isEmpty()||
                industryType.getValue().isEmpty()||
                subtype.getValue().isEmpty()||
                startTime.getValue().toString().isEmpty()||
                loginid.getText().isEmpty()||
                chainplace.getValue().isEmpty()||
                compName.getText().isEmpty()||
                passwordField.getText().isEmpty()){
            RunWarning runWarning=new RunWarning();
            runWarning.SetWarning("请填写完整信息！");
            runWarning.start(new Stage());


        }else {
            String logid = loginid.getText();
            String chain = chainplace.getValue();
            String name = compName.getText();
            String address = compAddress.getText();
            String contact = this.contact.getText();
            String credit = creditCode.getText();
            String supertype = industryType.getValue();
            String subType = this.subtype.getValue();
            String time = startTime.getValue().toString();
            String password = passwordField.getText();

            AccountSetVO ACCvo = new AccountSetVO(name, address, subType, chain, time, credit, contact);
            UserVO UserVO = new UserVO(logid, "admin");
            ResultMessage UserRM = new UserManagementImpl().insertOneCompanyUser(UserVO, password);
            ResultMessage AccRM = new UserManagementImpl().register(ACCvo,logid);
            if (UserRM == ResultMessage.EXIST_USERID) {
                RunWarning runWarning = new RunWarning();
                runWarning.SetWarning("用户已存在");
                runWarning.start(new Stage());

            } else if (UserRM == ResultMessage.SHORT_PASSWORD) {
                RunWarning runWarning = new RunWarning();
                runWarning.SetWarning("密码过短");
                runWarning.start(new Stage());

            } else if (UserRM == ResultMessage.FAIL || AccRM == ResultMessage.FAIL) {
                RunWarning runWarning = new RunWarning();
                runWarning.SetWarning("注册失败");
                runWarning.start(new Stage());

            } else if (UserRM == ResultMessage.SUCCESS && AccRM == ResultMessage.SUCCESS) {
                RunWarning runWarning = new RunWarning();
                runWarning.SetWarning("注册成功");
                runWarning.start(new Stage());

                //返回登陆界面

            }

        }

    }
    public void cancelButtonClicked(){
        /**
         * 跳转
         */

    }


}

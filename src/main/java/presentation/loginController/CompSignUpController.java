package presentation.loginController;

import businesslogic.SettingImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;


/**
 * Created by Chris on 2017/9/12.
 */
public class CompSignUpController {
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
    private ChoiceBox<String> subindustryType;

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

        industryType.getItems().addAll(new SettingImpl().getAllSuperIndustry());
    }

    public void createButtonClicked(){
        /**
         * 调用接口
         */
    }
    public void cancelButtonClicked(){
        /**
         * 跳转
         */
    }
    public void subindustryClicked(){
        /**
         * 二级行业
         */
    }

}

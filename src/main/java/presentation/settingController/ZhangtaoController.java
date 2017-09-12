package presentation.settingController;

import javafx.fxml.FXML;
import javafx.scene.control.*;


/**
 * Created by Chris on 2017/9/12.
 */
public class ZhangtaoController {
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
    private ChoiceBox<Object> industryType;

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
         * 显示原来账套信息
         */
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

}

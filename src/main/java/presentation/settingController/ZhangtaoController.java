package presentation.settingController;

import businesslogic.UserManagementImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.viewController.StaticFactory;
import vo.userManagement.AccountSetVO;
import vo.userManagement.UserVO;

import javax.sound.midi.ControllerEventListener;


/**
 * Created by Chris on 2017/9/12.
 */
public class ZhangtaoController implements ControlledScreen {
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
    private ChoiceBox<String> subType;



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
        UserVO uservo= StaticFactory.getUserVO();
        AccountSetVO accountvo=new UserManagementImpl().getAccountSetByCompanyID(uservo.getCompanyID());
        compName.setText(accountvo.getCompany_name());
        compAddress.setText(accountvo.getLocation());
        creditCode.setText(accountvo.getCreditCode());
        contact.setText(accountvo.getContact());
        //时间转化为datepicker？

        //两个行业初始化
        industryType.getItems().addAll();
        industryType.selectionModelProperty();
        subType.getItems().addAll();




    }

    public void createButtonClicked(){
        /**
         * 调用接口
         */
        String name=compName.getText();
        String address=compAddress.getText();
        String contact=this.contact.getText();
        String credit=creditCode.getText();
        String supertype=industryType.getValue();
        String subType=this.subType.getValue();
        String time=startTime.toString();
        //形成一个账套vo给数据库


    }
    public void cancelButtonClicked(){
        /**
         * 跳转
         */
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

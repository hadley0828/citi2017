package presentation.settingController;

import businesslogic.SettingImpl;
import businesslogic.UserManagementImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.viewController.StaticFactory;
import presentation.warningController.RunWarning;
import util.EnumPackage.ResultMessage;
import vo.userManagement.AccountSetVO;
import vo.userManagement.UserVO;

import javax.sound.midi.ControllerEventListener;
import java.util.ArrayList;


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
//        UserVO uservo= StaticFactory.getUserVO();
//        AccountSetVO accountvo=new UserManagementImpl().getAccountSetByCompanyID(uservo.getCompanyID());
//        compName.setText(accountvo.getCompany_name());
//        compAddress.setText(accountvo.getLocation());
//        creditCode.setText(accountvo.getCreditCode());
//        contact.setText(accountvo.getContact());
//        //时间转化为datepicker？
//
//        //两个行业初始化
        SettingImpl impl=new SettingImpl();
        ArrayList<String> superlist= impl.getAllSuperIndustry();
        industryType.getItems().addAll(superlist);
        industryType.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        String temp=industryType.getItems().get(newValue.intValue());
                        ArrayList<String> sublist=impl.getAllSubIndustry(String.valueOf(temp));
                        subType.getItems().clear();

                        subType.getItems().addAll(sublist);

                    }
                }
        );




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

        //形成一个账套vo给数据库

        if(name.isEmpty()||address.isEmpty()||contact.isEmpty()||credit.isEmpty()|startTime.getValue().toString().isEmpty()||subType.isEmpty()){
            RunWarning runWarning=new RunWarning();
            runWarning.SetWarning("请填写需要修改的内容！");
            runWarning.start(new Stage());

        }else {
            String time=startTime.getValue().toString();
            UserManagementImpl impl = new UserManagementImpl();
            AccountSetVO vo = new AccountSetVO(name, address, subType, time, credit, contact);
            UserVO uservo = StaticFactory.getUserVO();
            String compId = impl.getAccountSetByUserID(uservo.getUserID()).getCompany_id();
            vo.setCompany_id(compId);
            ResultMessage rm = impl.modifyAccountSet(vo);
            if (rm == ResultMessage.FAIL) {
                RunWarning runWarning = new RunWarning();
                runWarning.SetWarning("修改失败！");
                runWarning.start(new Stage());
            } else if (rm == ResultMessage.SUCCESS) {
                RunWarning runWarning = new RunWarning();
                runWarning.SetWarning("修改成功！");
                runWarning.start(new Stage());
            }
        }


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

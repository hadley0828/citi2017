package presentation.settingController;

import businesslogic.UserManagementImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import presentation.StaticFactory;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import vo.userManagement.UserVO;

import java.util.ArrayList;


/**
 * Created by Chris on 2017/9/12.
 */
public class RootSettingController implements ControlledScreen{
    @FXML
    private Button addUserButton;

    @FXML
    private TableView<UserVO> userTable;
    @FXML
    private TableColumn<UserVO,String> userInfo;
    @FXML
    private TableColumn<UserVO,String> userRoot;

    @FXML
    public void initialize(){
        UserVO vo= StaticFactory.getUserVO();
        String accId=vo.getAccountID();
        ArrayList<UserVO> list=new UserManagementImpl().getAllUserVoByAccountId(accId);
        ObservableList<UserVO> data= FXCollections.observableArrayList(list);
        userTable.setItems(data);
        userInfo.setCellValueFactory(new PropertyValueFactory("UserID"));
        userRoot.setCellValueFactory(new PropertyValueFactory("type"));


        //显示uservo数量


    }

    @FXML
    public void addUserClicked(){
        //跳转

    }


    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

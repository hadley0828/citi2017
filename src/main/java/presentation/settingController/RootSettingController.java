package presentation.settingController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;


/**
 * Created by Chris on 2017/9/12.
 */
public class RootSettingController implements ControlledScreen{
    @FXML
    private Button addUserButton;

    @FXML
    private TableView<Object> userTable;
    @FXML
    private TableColumn<Object,String> userInfo;
    @FXML
    private TableColumn<Object,String> userRoot;

    @FXML
    public void initialize(){
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

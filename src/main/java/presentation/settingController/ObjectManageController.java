package presentation.settingController;

import businesslogic.SettingImpl;
import businesslogic.UserManagementImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import vo.userManagement.SubjectsVO;

import java.util.ArrayList;

/**
 * Created by Chris on 2017/9/12.
 */
public class ObjectManageController implements ControlledScreen {
    @FXML
    private  Tab Balance;

    @FXML
    private  Tab debit;

    @FXML
    private Tab cleanBalance;

    @FXML
    private Tab income;

    @FXML
    private Tab fee;

    @FXML
    TableView<SubjectsVO> BalanceTable;

    @FXML
    TableColumn<SubjectsVO,String> code1;

    @FXML
    TableColumn<SubjectsVO,String> name1;

    @FXML
    TableColumn<SubjectsVO,String> direction1;

    @FXML
    TableColumn<SubjectsVO,String> state1;

    @FXML
    TableView<SubjectsVO> debitTable;

    @FXML
    TableColumn<SubjectsVO,String> code2;

    @FXML
    TableColumn<SubjectsVO,String> name2;

    @FXML
    TableColumn<SubjectsVO,String> direction2;

    @FXML
    TableColumn<SubjectsVO,String> state2;

    @FXML
    TableView<SubjectsVO> cleanBalanceTable;

    @FXML
    TableColumn<SubjectsVO,String> code3;

    @FXML
    TableColumn<SubjectsVO,String> name3;

    @FXML
    TableColumn<SubjectsVO,String> direction3;

    @FXML
    TableColumn<SubjectsVO,String> state3;

    @FXML
    TableView<SubjectsVO> incomeTable;

    @FXML
    TableColumn<SubjectsVO,String> code4;

    @FXML
    TableColumn<SubjectsVO,String> name4;

    @FXML
    TableColumn<SubjectsVO,String> direction4;

    @FXML
    TableColumn<SubjectsVO,String> state4;

    @FXML
    TableView<SubjectsVO> FeeTable;

    @FXML
    TableColumn<SubjectsVO,String> code5;

    @FXML
    TableColumn<SubjectsVO,String> name5;

    @FXML
    TableColumn<SubjectsVO,String> direction5;

    @FXML
    TableColumn<SubjectsVO,String> state5;

    @FXML
    public void initialize(){
        /**
         * 初始化5个表格
         *
         */
        SettingImpl impl=new SettingImpl();
        ArrayList<SubjectsVO> list=impl.getAllSubjects();

    }


    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

package presentation.settingController;

import businesslogic.SettingImpl;
import businesslogic.UserManagementImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    TableView<SubjectsVO> debitTable;

    @FXML
    TableColumn<SubjectsVO,String> code2;

    @FXML
    TableColumn<SubjectsVO,String> name2;

    @FXML
    TableColumn<SubjectsVO,String> direction2;



    @FXML
    TableView<SubjectsVO> cleanBalanceTable;

    @FXML
    TableColumn<SubjectsVO,String> code3;

    @FXML
    TableColumn<SubjectsVO,String> name3;

    @FXML
    TableColumn<SubjectsVO,String> direction3;



    @FXML
    TableView<SubjectsVO> incomeTable;

    @FXML
    TableColumn<SubjectsVO,String> code4;

    @FXML
    TableColumn<SubjectsVO,String> name4;

    @FXML
    TableColumn<SubjectsVO,String> direction4;



    @FXML
    TableView<SubjectsVO> FeeTable;

    @FXML
    TableColumn<SubjectsVO,String> code5;

    @FXML
    TableColumn<SubjectsVO,String> name5;

    @FXML
    TableColumn<SubjectsVO,String> direction5;

    @FXML
    TableView<SubjectsVO> other;
    @FXML
    TableColumn<SubjectsVO,String>code6;
    @FXML
    TableColumn<SubjectsVO,String>name6;
    @FXML
    TableColumn<SubjectsVO,String>direction6;

    @FXML
    public void initialize(){
        /**
         * 初始化5个表格
         *
         */
        try {
            SettingImpl impl = new SettingImpl();
            ArrayList<SubjectsVO> list = impl.getAllSubjects();
            ArrayList<SubjectsVO> list1=new ArrayList<>();
            ArrayList<SubjectsVO> list2=new ArrayList<>();
            ArrayList<SubjectsVO> list3=new ArrayList<>();
            ArrayList<SubjectsVO> list4=new ArrayList<>();
            ArrayList<SubjectsVO> list5=new ArrayList<>();
            ArrayList<SubjectsVO> list6=new ArrayList<>();
            for(int i=0;i<list.size();i++){
                String type=list.get(i).getType();
                if(type.equals("资产")){
                    list1.add(list.get(i));

                }else if(type.equals("负债")){
                    list2.add(list.get(i));

                }else if(type.equals("净资产")){
                    list3.add(list.get(i));

                }else if(type.equals("收入")){
                    list4.add(list.get(i));

                }else if(type.equals("费用")){
                    list5.add(list.get(i));

                }else if(type.equals("其他")){
                    list6.add(list.get(i));
                }
            }

            ObservableList<SubjectsVO> data1 = FXCollections.observableArrayList(list1);
            ObservableList<SubjectsVO> data2 = FXCollections.observableArrayList(list2);
            ObservableList<SubjectsVO> data3 = FXCollections.observableArrayList(list3);
            ObservableList<SubjectsVO> data4 = FXCollections.observableArrayList(list4);
            ObservableList<SubjectsVO> data5 = FXCollections.observableArrayList(list5);
            ObservableList<SubjectsVO> data6 = FXCollections.observableArrayList(list6);
            BalanceTable.setItems(data1);
            code1.setCellValueFactory(new PropertyValueFactory("subjectsID"));
            name1.setCellValueFactory(new PropertyValueFactory("subjectsName"));
            direction1.setCellValueFactory(new PropertyValueFactory("direction"));
            debitTable.setItems(data2);
            code2.setCellValueFactory(new PropertyValueFactory("subjectsID"));
            name2.setCellValueFactory(new PropertyValueFactory("subjectsName"));
            direction2.setCellValueFactory(new PropertyValueFactory("direction"));
            cleanBalanceTable.setItems(data3);
            code3.setCellValueFactory(new PropertyValueFactory("subjectsID"));
            name3.setCellValueFactory(new PropertyValueFactory("subjectsName"));
            direction3.setCellValueFactory(new PropertyValueFactory("direction"));
            incomeTable.setItems(data4);
            code4.setCellValueFactory(new PropertyValueFactory("subjectsID"));
            name4.setCellValueFactory(new PropertyValueFactory("subjectsName"));
            direction4.setCellValueFactory(new PropertyValueFactory("direction"));
            FeeTable.setItems(data5);
            code5.setCellValueFactory(new PropertyValueFactory("subjectsID"));
            name5.setCellValueFactory(new PropertyValueFactory("subjectsName"));
            direction5.setCellValueFactory(new PropertyValueFactory("direction"));
            other.setItems(data6);
            code6.setCellValueFactory(new PropertyValueFactory("subjectsID"));
            name6.setCellValueFactory(new PropertyValueFactory("subjectsName"));
            direction6.setCellValueFactory(new PropertyValueFactory("direction"));




        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }


    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

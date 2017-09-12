package presentation.settingController;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by Chris on 2017/9/12.
 */
public class ObjectManageController {
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
    TableView<Object> BalanceTable;

    @FXML
    TableColumn<Object,String> code1;

    @FXML
    TableColumn<Object,String> name1;

    @FXML
    TableColumn<Object,String> direction1;

    @FXML
    TableColumn<Object,String> state1;

    @FXML
    TableView<Object> debitTabl;

    @FXML
    TableColumn<Object,String> code2;

    @FXML
    TableColumn<Object,String> name2;

    @FXML
    TableColumn<Object,String> direction2;

    @FXML
    TableColumn<Object,String> state2;

    @FXML
    TableView<Object> cleanBalanceTable;

    @FXML
    TableColumn<Object,String> code3;

    @FXML
    TableColumn<Object,String> name3;

    @FXML
    TableColumn<Object,String> direction3;

    @FXML
    TableColumn<Object,String> state3;

    @FXML
    TableView<Object> incomeTable;

    @FXML
    TableColumn<Object,String> code4;

    @FXML
    TableColumn<Object,String> name4;

    @FXML
    TableColumn<Object,String> direction4;

    @FXML
    TableColumn<Object,String> state4;

    @FXML
    TableView<Object> FeeTable;

    @FXML
    TableColumn<Object,String> code5;

    @FXML
    TableColumn<Object,String> name5;

    @FXML
    TableColumn<Object,String> direction5;

    @FXML
    TableColumn<Object,String> state5;

    @FXML
    public void initialize(){
        /**
         * 初始化5个表格
         *
         */
    }



}

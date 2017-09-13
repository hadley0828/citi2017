package presentation.viewController.stockInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by YZ on 2017/9/12.
 */
public class SupplierInfoController {
    @FXML
    private TableView<RawVO> supplier_raw;
    @FXML
    private Button supplier_button;
    @FXML
    private TableColumn<RawVO,String> raw_1;
    @FXML
    private TableColumn<RawVO,String> raw_2;
    @FXML
    private TableColumn<RawVO,Timestamp> raw_3;
    @FXML
    private TableColumn<RawVO,Boolean> raw_4;
    @FXML
    private TableColumn<RawVO,Boolean> raw_5;
    @FXML
    private TableColumn<RawVO,Integer> raw_6;
    @FXML
    private TableColumn<RawVO,Double> raw_7;
    @FXML
    private TableColumn<RawVO,Double> raw_8;
    @FXML
    private TableColumn<RawVO,Integer> raw_9;
    @FXML
    private TableColumn<RawVO,Double> raw_10;
    @FXML
    private TableColumn<RawVO,Double> raw_11;
    @FXML
    private TableColumn<RawVO,Integer> raw_12;

    @FXML
    public void initialize(){
        ArrayList<RawVO> arrayList=new ArrayList<>();
        arrayList.add(new RawVO("1","1","2019-09-08 12:32:22",true,true,1,1,1,1,1,1,1));
        ObservableList<RawVO> list= FXCollections.observableArrayList(arrayList);

        supplier_raw.setEditable(true);

        raw_1.setCellValueFactory(new PropertyValueFactory<>("rawMaterial_variety"));
        raw_1.setCellFactory(TextFieldTableCell.<RawVO>forTableColumn());
        raw_1.setOnEditCommit((TableColumn.CellEditEvent<RawVO,String> t)->{
            ((RawVO) t.getTableView().getItems().get(t.getTablePosition().getRow())).setRawMaterial_variety(t.getNewValue());
        });

        raw_2.setCellValueFactory(new PropertyValueFactory<>("voucher_id"));
        raw_2.setCellFactory(TextFieldTableCell.forTableColumn());
        raw_2.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setVoucher_id(event.getNewValue()));

//        raw_3.setCellValueFactory(new PropertyValueFactory<>("datetime"));
//        raw_3.setCellFactory(TextFieldTableCell.forTableColumn());
//        raw_3.setOnEditCommit(event ->event.getTableView().getItems().get(event.getTablePosition().getRow()).setDatetime(Timestamp.valueOf(event.getNewValue()));

        raw_4.setCellValueFactory(new PropertyValueFactory<>("is_delivery_ontime"));
        raw_4.setCellFactory(TextFieldTableCell.forTableColumn());
        raw_4.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setIs_delivery_ontime(event.getNewValue()));

//        raw_5.setCellValueFactory(new PropertyValueFactory<>("is_return"));
//        raw_5.setCellFactory(TextFieldTableCell.forTableColumn());
//        raw_5.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setIs_return(event.getNewValue()));
//
//        raw_6.setCellValueFactory(new PropertyValueFactory<>("input_num"));
//        raw_6.setCellFactory(TextFieldTableCell.forTableColumn());
//        raw_6.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setInput_num(event.getNewValue()));
//
//        raw_7.setCellValueFactory(new PropertyValueFactory<>("input_price"));
//        raw_7.setCellFactory(TextFieldTableCell.forTableColumn());
//        raw_7.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setInput_price(event.getNewValue()));
//
//        raw_8.setCellValueFactory(new PropertyValueFactory<>("input_account"));
//        raw_8.setCellFactory(TextFieldTableCell.forTableColumn());
//        raw_8.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setInput_account(event.getNewValue()));
//
//        raw_9.setCellValueFactory(new PropertyValueFactory<>("out_num"));
//        raw_9.setCellFactory(TextFieldTableCell.forTableColumn());
//        raw_9.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setOut_num(event.getNewValue()));
//
//        raw_10.setCellValueFactory(new PropertyValueFactory<>("out_price"));
//        raw_10.setCellFactory(TextFieldTableCell.forTableColumn());
//        raw_10.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setOut_price(event.getNewValue()));
//
//        raw_11.setCellValueFactory(new PropertyValueFactory<>("out_account"));
//        raw_11.setCellFactory(TextFieldTableCell.forTableColumn());
//        raw_11.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setOut_account(event.getNewValue()));
//
//        raw_12.setCellValueFactory(new PropertyValueFactory<>("balance_num"));
//        raw_12.setCellFactory(TextFieldTableCell.forTableColumn());
//        raw_12.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setBalance_num(event.getNewValue()));


        supplier_raw.setItems(list);
    }
}

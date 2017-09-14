package presentation.viewController.financialSystem.stockInfo;

import businesslogic.InventoryManagementImpl;
import businesslogicservice.InventoryManagementService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import presentation.StaticFactory;
import vo.Inventory.RawMaterialInventoryItemVo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    private TableColumn<RawVO,String> raw_3;
    @FXML
    private TableColumn<RawVO,Boolean> raw_4;
    @FXML
    private TableColumn<RawVO,Boolean> raw_5;
    @FXML
    private TableColumn<RawVO,String> raw_6;
    @FXML
    private TableColumn<RawVO,String> raw_7;
    @FXML
    private TableColumn<RawVO,String> raw_8;
    @FXML
    private TableColumn<RawVO,String> raw_9;
    @FXML
    private TableColumn<RawVO,String> raw_10;
    @FXML
    private TableColumn<RawVO,String> raw_11;
    @FXML
    private TableColumn<RawVO,String> raw_12;

    InventoryManagementService service=new InventoryManagementImpl();
    ObservableList<RawVO> list;
    @FXML
    public void initialize(){
        RawVO newVO=new RawVO();
        ArrayList<RawVO> arrayList=new ArrayList<>();
        arrayList.add(new RawVO("","","自动设置",true,true,"","","","","","","自动结算"));
        arrayList.add(new RawVO("","","自动设置",true,true,"","","","","","","自动结算"));
        arrayList.add(new RawVO("","","自动设置",true,true,"","","","","","","自动结算"));
        arrayList.add(new RawVO("","","自动设置",true,true,"","","","","","","自动结算"));
        arrayList.add(new RawVO("","","自动设置",true,true,"","","","","","","自动结算"));

        list= FXCollections.observableArrayList(arrayList);
        supplier_raw.setItems(list);

        supplier_raw.setEditable(true);

        raw_1.setCellValueFactory(new PropertyValueFactory<>("rawMaterial_variety"));
        raw_1.setCellFactory(TextFieldTableCell.<RawVO>forTableColumn());
        raw_1.setOnEditCommit((TableColumn.CellEditEvent<RawVO,String> t)->{
            ((RawVO) t.getTableView().getItems().get(t.getTablePosition().getRow())).setRawMaterial_variety(t.getNewValue());
        });

        raw_2.setCellValueFactory(new PropertyValueFactory<>("voucher_id"));
        raw_2.setCellFactory(TextFieldTableCell.forTableColumn());
        raw_2.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setVoucher_id(event.getNewValue()));

        raw_3.setCellValueFactory(new PropertyValueFactory<>("datetime"));
//        raw_3.setCellFactory(TextFieldTableCell.forTableColumn());
//        raw_3.setOnEditCommit(event ->event.getTableView().getItems().get(event.getTablePosition().getRow()).setDatetime(event.getNewValue()));

        raw_4.setCellValueFactory(new PropertyValueFactory<>("is_delivery_ontime"));
        raw_4.setCellFactory(tc->new CheckBoxTableCell<>());

        raw_5.setCellValueFactory(new PropertyValueFactory<>("is_return"));
        raw_5.setCellFactory(tc->new CheckBoxTableCell<>());

        raw_6.setCellValueFactory(new PropertyValueFactory<>("input_num"));
        raw_6.setCellFactory(TextFieldTableCell.forTableColumn());
        raw_6.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setInput_num(event.getNewValue()));

        raw_7.setCellValueFactory(new PropertyValueFactory<>("input_price"));
        raw_7.setCellFactory(TextFieldTableCell.forTableColumn());
        raw_7.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setInput_price(event.getNewValue()));

        raw_8.setCellValueFactory(new PropertyValueFactory<>("input_account"));
        raw_8.setCellFactory(TextFieldTableCell.forTableColumn());
        raw_8.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setInput_account(event.getNewValue()));

        raw_9.setCellValueFactory(new PropertyValueFactory<>("out_num"));
        raw_9.setCellFactory(TextFieldTableCell.forTableColumn());
        raw_9.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setOut_num(event.getNewValue()));

        raw_10.setCellValueFactory(new PropertyValueFactory<>("out_price"));
        raw_10.setCellFactory(TextFieldTableCell.forTableColumn());
        raw_10.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setOut_price(event.getNewValue()));

        raw_11.setCellValueFactory(new PropertyValueFactory<>("out_account"));
        raw_11.setCellFactory(TextFieldTableCell.forTableColumn());
        raw_11.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setOut_account(event.getNewValue()));

        raw_12.setCellValueFactory(new PropertyValueFactory<>("balance_num"));
//        raw_12.setCellFactory(TextFieldTableCell.forTableColumn());
//        raw_12.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setBalance_num(event.getNewValue()));

    }

    @FXML
    public void addSupplierInfo() {
        ArrayList<RawMaterialInventoryItemVo> toAddVOs = new ArrayList<>();
        for (int i=0;i<list.size();i++) {
            RawVO vo = list.get(i);

            String rawMaterial_variety = vo.getRawMaterial_variety();
            if (vo.getRawMaterial_variety()!="") {

            String voucher_id = vo.getVoucher_id();
//        String datetime = vo.getDatetime();
            Timestamp datetimetc = new Timestamp(System.currentTimeMillis());
            Date date = new Date();
            date.setTime(datetimetc.getTime());
            String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

                if (vo.getInput_num()==""||vo.getInput_account()==""||vo.getInput_price()==""){
                    vo.setInput_num("0");
                    vo.setInput_account("0");
                    vo.setInput_price("0");
                }
                if (vo.getOut_num()==""||vo.getOut_account()==""||vo.getOut_price()==""){
                    vo.setOut_num("0");
                    vo.setOut_account("0");
                    vo.setOut_price("0");
                }

            boolean is_delivery_ontime = vo.isIs_delivery_ontime();
            boolean is_return = vo.isIs_return();
            int input_num = Integer.parseInt(vo.getInput_num());
            double input_price = Double.parseDouble(vo.getInput_price());
            double input_account = Double.parseDouble(vo.getInput_account());
            int out_num = Integer.parseInt(vo.getOut_num());
            double out_price = Double.parseDouble(vo.getOut_price());
            double out_account = Double.parseDouble(vo.getOut_account());

//            int balance_num;
            int balance_num = 0;
//            if (vo.getInput_num()==""){
//                balance_num=service.getRawInventory(StaticFactory.getUserVO().getCompanyID(),voucher_id,rawMaterial_variety,(-1)*out_num);
//            }else{
//                balance_num=service.getRawInventory(StaticFactory.getUserVO().getCompanyID(),voucher_id,rawMaterial_variety,input_num);
//            }
            toAddVOs.add(new RawMaterialInventoryItemVo(rawMaterial_variety, voucher_id, datetime, is_delivery_ontime, is_return, input_num, input_price, input_account, out_num, out_price, out_account, balance_num));
            }
            //        System.out.print(toAddVOs.get(0).getDatetime());
        }
        System.out.print(toAddVOs.size());
        service.SupplierInformationEntry(StaticFactory.getUserVO().getCompanyID(), toAddVOs);
    }
}

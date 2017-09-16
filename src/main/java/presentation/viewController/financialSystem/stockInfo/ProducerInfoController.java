package presentation.viewController.financialSystem.stockInfo;

import businesslogic.InventoryManagementImpl;
import businesslogicservice.InventoryManagementService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import presentation.StaticFactory;
import vo.Inventory.ProductInventoryItemVo;
import vo.Inventory.RawMaterialInventoryItemVo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by YZ on 2017/9/12.
 */
public class ProducerInfoController {
    @FXML
    private TableView<RawVO> producer_raw;
    @FXML
    private TableView<ProductVO> producer_product;
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

    @FXML
    private TableColumn<ProductVO,String> product_1;
    @FXML
    private TableColumn<ProductVO,String> product_2;
    @FXML
    private TableColumn<ProductVO,String> product_3;
    @FXML
    private TableColumn<ProductVO,Boolean> product_4;
    @FXML
    private TableColumn<ProductVO,Boolean> product_5;
    @FXML
    private TableColumn<ProductVO,String> product_6;
    @FXML
    private TableColumn<ProductVO,String> product_7;
    @FXML
    private TableColumn<ProductVO,String> product_8;
    @FXML
    private TableColumn<ProductVO,String> product_9;
    @FXML
    private TableColumn<ProductVO,String> product_10;
    @FXML
    private TableColumn<ProductVO,String> product_11;
    @FXML
    private TableColumn<ProductVO,String> product_12;

    InventoryManagementService service=new InventoryManagementImpl();
    ObservableList<RawVO> list;
    ObservableList<ProductVO> pplist;
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
        producer_raw.setItems(list);

        producer_raw.setEditable(true);

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

//        raw_5.setCellValueFactory(new PropertyValueFactory<>("is_return"));
//        raw_5.setCellFactory(tc->new CheckBoxTableCell<>());

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

        ProductVO productVO=new ProductVO();
        ArrayList<ProductVO> plist=new ArrayList<>();

        plist.add(new ProductVO("","","自动设置",false,false,"","","","","","","自动结算"));
        plist.add(new ProductVO("","","自动设置",false,false,"","","","","","","自动结算"));
        plist.add(new ProductVO("","","自动设置",false,false,"","","","","","","自动结算"));
        plist.add(new ProductVO("","","自动设置",false,false,"","","","","","","自动结算"));
        plist.add(new ProductVO("","","自动设置",false,false,"","","","","","","自动结算"));

        pplist= FXCollections.observableArrayList(plist);
        producer_product.setItems(pplist);

        producer_product.setEditable(true);

        product_1.setCellValueFactory(new PropertyValueFactory<>("product_variety"));
        product_1.setCellFactory(TextFieldTableCell.<ProductVO>forTableColumn());
        product_1.setOnEditCommit((TableColumn.CellEditEvent<ProductVO,String> t)->{
            ((ProductVO) t.getTableView().getItems().get(t.getTablePosition().getRow())).setProduct_variety(t.getNewValue());
        });

        product_2.setCellValueFactory(new PropertyValueFactory<>("voucher_id"));
        product_2.setCellFactory(TextFieldTableCell.forTableColumn());
        product_2.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setVoucher_id(event.getNewValue()));

        product_3.setCellValueFactory(new PropertyValueFactory<>("datetime"));

        product_5.setCellValueFactory(new PropertyValueFactory<>("is_return"));
        product_5.setCellFactory(tc->new CheckBoxTableCell<>());

        product_6.setCellValueFactory(new PropertyValueFactory<>("input_num"));
        product_6.setCellFactory(TextFieldTableCell.forTableColumn());
        product_6.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setInput_num(event.getNewValue()));

        product_7.setCellValueFactory(new PropertyValueFactory<>("input_price"));
        product_7.setCellFactory(TextFieldTableCell.forTableColumn());
        product_7.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setInput_price(event.getNewValue()));

        product_8.setCellValueFactory(new PropertyValueFactory<>("input_account"));
        product_8.setCellFactory(TextFieldTableCell.forTableColumn());
        product_8.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setInput_account(event.getNewValue()));

        product_9.setCellValueFactory(new PropertyValueFactory<>("out_num"));
        product_9.setCellFactory(TextFieldTableCell.forTableColumn());
        product_9.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setOut_num(event.getNewValue()));

        product_10.setCellValueFactory(new PropertyValueFactory<>("out_price"));
        product_10.setCellFactory(TextFieldTableCell.forTableColumn());
        product_10.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setOut_price(event.getNewValue()));

        product_11.setCellValueFactory(new PropertyValueFactory<>("out_account"));
        product_11.setCellFactory(TextFieldTableCell.forTableColumn());
        product_11.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setOut_account(event.getNewValue()));

        product_12.setCellValueFactory(new PropertyValueFactory<>("balance_num"));

    }

    @FXML
    public void addRaw() {
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
                boolean is_return = false;
                int input_num = Integer.parseInt(vo.getInput_num());
                double input_price = Double.parseDouble(vo.getInput_price());
                double input_account = Double.parseDouble(vo.getInput_account());
                int out_num = Integer.parseInt(vo.getOut_num());
                double out_price = Double.parseDouble(vo.getOut_price());
                double out_account = Double.parseDouble(vo.getOut_account());

            int balance_num;
            if (vo.getInput_num()==""){
                balance_num=service.getRawInventory(StaticFactory.getUserVO().getCompanyID(),voucher_id,rawMaterial_variety,(-1)*out_num);
            }else{
                balance_num=service.getRawInventory(StaticFactory.getUserVO().getCompanyID(),voucher_id,rawMaterial_variety,input_num);
            }
                toAddVOs.add(new RawMaterialInventoryItemVo(rawMaterial_variety, voucher_id, datetime, is_delivery_ontime, is_return, input_num, input_price, input_account, out_num, out_price, out_account, balance_num));
            }
            //        System.out.print(toAddVOs.get(0).getDatetime());
        }
//        System.out.print(toAddVOs.size());
//        System.out.print(StaticFactory.getUserVO().getCompanyID());
        service.SupplierInformationEntry(StaticFactory.getUserVO().getCompanyID(), toAddVOs);
        producer_product.getScene().getWindow().hide();

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("录入成功！");
        alert.showAndWait();
    }

    @FXML
    public void addProduct(){
        ArrayList<ProductInventoryItemVo> proAddVOs = new ArrayList<>();
        for (int i=0;i<pplist.size();i++) {
            ProductVO vo = pplist.get(i);

            String product_variety = vo.getProduct_variety();
            if (vo.getProduct_variety()!="") {
                String voucher_id = vo.getVoucher_id();

                Timestamp datetimetc = new Timestamp(System.currentTimeMillis());
                Date date = new Date();
                date.setTime(datetimetc.getTime());
                String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

                boolean is_delivery_ontime = false;

                boolean is_return = vo.isIs_return();

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
                int input_num = Integer.parseInt(vo.getInput_num());
                double input_price = Double.parseDouble(vo.getInput_price());
                double input_account = Double.parseDouble(vo.getInput_account());
                int out_num = Integer.parseInt(vo.getOut_num());
                double out_price = Double.parseDouble(vo.getOut_price());
                double out_account = Double.parseDouble(vo.getOut_account());

            int balance_num;
            if (vo.getInput_num()==""){
                balance_num=service.getProductInventory(StaticFactory.getUserVO().getCompanyID(),voucher_id,product_variety,(-1)*out_num);
            }else{
                balance_num=service.getProductInventory(StaticFactory.getUserVO().getCompanyID(),voucher_id,product_variety,input_num);
            }
                proAddVOs.add(new ProductInventoryItemVo(product_variety, voucher_id, datetime, is_delivery_ontime, is_return, input_num, input_price, input_account, out_num, out_price, out_account, balance_num));
            }
            //        System.out.print(toAddVOs.get(0).getDatetime());
        }
//        System.out.print(proAddVOs.size());
        service.ProducerProductInformationEntry(StaticFactory.getUserVO().getCompanyID(), proAddVOs);

        producer_product.getScene().getWindow().hide();

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("录入成功！");
        alert.showAndWait();
    }

    public void refresh(){
    }
}

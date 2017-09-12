package presentation.viewController.StockInfo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import vo.Inventory.RawMaterialInventoryItemVo;

import java.sql.Timestamp;

/**
 * Created by YZ on 2017/9/12.
 */
public class SupplierInfoController {
    @FXML
    private TableView supplier_raw;
    @FXML
    private Button supplier_button;
    @FXML
    private TableColumn raw_1;
    @FXML
    private TableColumn raw_2;
    @FXML
    private TableColumn raw_3;
    @FXML
    private TableColumn raw_4;
    @FXML
    private TableColumn raw_5;
    @FXML
    private TableColumn raw_6;
    @FXML
    private TableColumn raw_7;
    @FXML
    private TableColumn raw_8;
    @FXML
    private TableColumn raw_9;
    @FXML
    private TableColumn raw_10;
    @FXML
    private TableColumn raw_11;

    @FXML
    public void initialize(){
        raw_1.setCellValueFactory(new PropertyValueFactory<RawMaterialInventoryItemVo,TextField>("rawMaterial_variety"));
        raw_2.setCellValueFactory(new PropertyValueFactory<RawMaterialInventoryItemVo,TextField>("voucher_id"));
        raw_3.setCellValueFactory(new PropertyValueFactory<RawMaterialInventoryItemVo,TextField>("datetime"));
        raw_4.setCellValueFactory(new PropertyValueFactory<RawMaterialInventoryItemVo,CheckBox>("is_delivery_ontime"));
        raw_5.setCellValueFactory(new PropertyValueFactory<RawMaterialInventoryItemVo,CheckBox>("is_return"));
        raw_6.setCellValueFactory(new PropertyValueFactory<RawMaterialInventoryItemVo,TextField>("input_num"));
        raw_7.setCellValueFactory(new PropertyValueFactory<RawMaterialInventoryItemVo,TextField>("input_price"));
        raw_8.setCellValueFactory(new PropertyValueFactory<RawMaterialInventoryItemVo,TextField>("input_account"));
        raw_9.setCellValueFactory(new PropertyValueFactory<RawMaterialInventoryItemVo,TextField>("out_num"));
        raw_10.setCellValueFactory(new PropertyValueFactory<RawMaterialInventoryItemVo,TextField>("out_price"));
        raw_11.setCellValueFactory(new PropertyValueFactory<RawMaterialInventoryItemVo,TextField>("out_account"));


    }
}

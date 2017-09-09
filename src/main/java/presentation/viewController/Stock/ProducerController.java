package presentation.viewController.Stock;

import businesslogic.InventoryManagementImpl;
import businesslogicservice.InventoryManagementService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import vo.Inventory.ProductInventoryMonitorItemVo;
import vo.Inventory.RawMaterialInventoryMonitorItemVo;

import java.sql.Array;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by YZ on 2017/9/8.
 */
public class ProducerController {
    @FXML
    private DatePicker raw_date;
    @FXML
    private DatePicker product_date;
    @FXML
    private TableView raw_monitor_table;
    @FXML
    private TableView product_monitor_table;
    @FXML
    private TableColumn raw_material;
    @FXML
    private TableColumn raw_stock;
    @FXML
    private TableColumn raw_safe_stock;
    @FXML
    private TableColumn raw_ontime;
    @FXML
    private TableColumn product;
    @FXML
    private TableColumn product_safe_stock;
    @FXML
    private TableColumn product_back;
    @FXML
    private TableColumn product_stock;
    private InventoryManagementService service=new InventoryManagementImpl();

    DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    private ArrayList<RawMaterialInventoryMonitorItemVo> raw_tabledata=new ArrayList<>();
    private ArrayList<ProductInventoryMonitorItemVo> product_tabledata=new ArrayList<>();
    ObservableList<RawMaterialInventoryMonitorItemVo> raw_list=FXCollections.observableArrayList();
    ObservableList<ProductInventoryMonitorItemVo> product_list=FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        setTable();
    }

    public void setTable(){
        raw_date.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {

                java.util.Date d = Date.from(raw_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                raw_tabledata.add(new RawMaterialInventoryMonitorItemVo("cotton", 200, 280, "0.9", "0.02"));

                Iterator i = raw_tabledata.iterator();
                while (i.hasNext()) {
                    raw_list.add((RawMaterialInventoryMonitorItemVo) i.next());
                }
            }
        });
        raw_material.setCellValueFactory(new PropertyValueFactory("raw_material_variety"));
        raw_stock.setCellValueFactory(new PropertyValueFactory("inventory"));
        raw_safe_stock.setCellValueFactory(new PropertyValueFactory("safe_inventory"));
        raw_ontime.setCellValueFactory(new PropertyValueFactory("punctual_delivery_rate"));

        product_date.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {

                java.util.Date d = Date.from(product_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                product_tabledata.add(new ProductInventoryMonitorItemVo("cotton", 200, 280, "0.9", "0.02"));

                Iterator i = product_tabledata.iterator();
                while (i.hasNext()) {
                    product_list.add((ProductInventoryMonitorItemVo) i.next());
                }
            }
        });
        product.setCellFactory(new PropertyValueFactory("product_variety"));
        product_stock.setCellFactory(new PropertyValueFactory("inventory"));
        product_safe_stock.setCellFactory(new PropertyValueFactory("safe_inventory"));
        product_back.setCellFactory(new PropertyValueFactory("refund_rate"));


        raw_monitor_table.setItems(raw_list);
        product_monitor_table.setItems(product_list);
    }



}

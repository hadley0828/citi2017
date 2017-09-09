package presentation.viewController.Stock;

import businesslogic.InventoryManagementImpl;
import businesslogicservice.InventoryManagementService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import vo.Inventory.ProductInventoryMonitorItemVo;
import vo.Inventory.RawMaterialInventoryMonitorItemVo;

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
public class DistributorController {
    @FXML
    private TableView product_monitor;
    @FXML
    private TableColumn product;
    @FXML
    private TableColumn stock_num;
    @FXML
    private TableColumn safe_stock_num;
    @FXML
    private TableColumn ontime_delivery;
    @FXML
    private TableColumn back;
    @FXML
    private DatePicker date;

    private InventoryManagementService service=new InventoryManagementImpl();

    DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    private ArrayList<ProductInventoryMonitorItemVo> distributor_tabledata=new ArrayList<>();

    @FXML
    public void initialize(){
        setTable();
    }

    public void setTable(){
        date.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                java.util.Date d= Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                distributor_tabledata.add(new ProductInventoryMonitorItemVo("cotton",200,280,"0.9","0.02"));
                ObservableList<ProductInventoryMonitorItemVo> list= FXCollections.observableArrayList();
                Iterator i=distributor_tabledata.iterator();
                while(i.hasNext()){
                    list.add((ProductInventoryMonitorItemVo)i.next());
                }

                product.setCellValueFactory(new PropertyValueFactory("product_variety"));
                stock_num.setCellValueFactory(new PropertyValueFactory("inventory"));
                safe_stock_num.setCellValueFactory(new PropertyValueFactory("safe_inventory"));
                ontime_delivery.setCellValueFactory(new PropertyValueFactory("punctual_delivery_rate"));
                back.setCellValueFactory(new PropertyValueFactory("refund_rate"));

                product_monitor.setItems(list);
            }
        });
    }
}

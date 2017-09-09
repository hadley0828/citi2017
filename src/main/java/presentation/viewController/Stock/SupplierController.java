package presentation.viewController.Stock;

import businesslogic.InventoryManagementImpl;
import businesslogicservice.InventoryManagementService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import vo.BalanceSheetItemVo;
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
public class SupplierController {
    @FXML
    private TableView stock_monitor;
    @FXML
    private TableColumn raw_material;
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
    private ArrayList<RawMaterialInventoryMonitorItemVo> supply_tabledata=new ArrayList<>();

    @FXML
    public void initialize(){
        setTable();
    }

    public void setTable(){
        date.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {

                java.util.Date d=Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//                supply_tabledata=service.getRawMaterialInventoryMonitorItem("001",format.format(d));
                supply_tabledata.add(new RawMaterialInventoryMonitorItemVo("cotton",200,280,"0.9","0.02"));
//                                System.out.println(date.getValue());
                ObservableList<RawMaterialInventoryMonitorItemVo> list= FXCollections.observableArrayList();
                Iterator i=supply_tabledata.iterator();
                while(i.hasNext()){
                    list.add((RawMaterialInventoryMonitorItemVo)i.next());
                }


                raw_material.setCellFactory(new Callback<TableColumn<RawMaterialInventoryMonitorItemVo,String>, TableCell<RawMaterialInventoryMonitorItemVo,String>>() {
                    @Override
                    public TableCell<RawMaterialInventoryMonitorItemVo,String> call(TableColumn<RawMaterialInventoryMonitorItemVo,String> param) {
                        return new TableCell<RawMaterialInventoryMonitorItemVo,String>(){
                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);

                            }
                        };
                    }
                });
                raw_material.setCellValueFactory(new PropertyValueFactory("raw_material_variety"));

                stock_num.setCellValueFactory(new PropertyValueFactory("inventory"));
                safe_stock_num.setCellValueFactory(new PropertyValueFactory("safe_inventory"));
                ontime_delivery.setCellValueFactory(new PropertyValueFactory("punctual_delivery_rate"));
                back.setCellValueFactory(new PropertyValueFactory("refund_rate"));

                stock_monitor.setItems(list);


            }
        });
    }

    class RawControl extends TableCell<RawMaterialInventoryMonitorItemVo,String>{
        public RawControl(){
            super();
            this.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setStyle("-fx-text-fill: rgb(255,135,98)");
                }
            });
            this.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setStyle("-fx-text-fill: black");
                }
            });
            this.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //画图
                }
            });
        }
    }

}

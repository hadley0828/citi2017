package presentation.viewController.Stock;

import businesslogic.InventoryManagementImpl;
import businesslogicservice.InventoryManagementService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import presentation.viewController.StaticFactory;
import vo.Inventory.ProductInventoryMonitorItemVo;
import vo.Inventory.RawMaterialInventoryMonitorItemVo;

import java.io.IOException;
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

    static Stage chartStage;
    public String product_pro;
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

    public void setCell() {
        product.setCellFactory(tc->{
            TableCell<ProductInventoryMonitorItemVo, String> cell=new TableCell<ProductInventoryMonitorItemVo, String>(){
                @Override
                protected void updateItem(String item,boolean empty){
                    super.updateItem(item,empty);
                    setText(empty?null:item);
                }
            };
            cell.setOnMouseClicked(e->{
                if(!cell.isEmpty()){
                    product_pro=cell.getItem();
                    StaticFactory.setdistributor_product(product_pro);

                    try {
                        draw();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            cell.setOnMouseEntered(e->{
                cell.setStyle("-fx-text-fill: rgb(255,135,98);");
                cell.setCursor(Cursor.HAND);
            });
            cell.setOnMouseExited(e->{
                cell.setStyle("-fx-text-fill: black");
                cell.setCursor(Cursor.DEFAULT);
            });
            return cell;
        });
    }


    public void draw() throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("../../../view/Stock/DistributorChart.fxml"));
        chartStage=new Stage();
        Scene scene=new Scene(root,800,600);
        chartStage.setScene(scene);
        chartStage.initStyle(StageStyle.TRANSPARENT);
        chartStage.initModality(Modality.APPLICATION_MODAL);
        chartStage.show();
    }
}

package presentation.viewController.supplyChainManagement.stock;

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
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.StaticFactory;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import vo.Inventory.*;

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
public class ProducerController implements ControlledScreen {
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
    @FXML
    private StackedBarChart raw_bar;
    @FXML
    private StackedBarChart product_bar;
    private InventoryManagementService service=new InventoryManagementImpl();

    DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    private ArrayList<RawMaterialInventoryMonitorItemVo> raw_tabledata=new ArrayList<>();
    private ArrayList<ProductInventoryMonitorItemVo> product_tabledata=new ArrayList<>();
    ObservableList<RawMaterialInventoryMonitorItemVo> raw_list=FXCollections.observableArrayList();
    ObservableList<ProductInventoryMonitorItemVo> product_list=FXCollections.observableArrayList();

    public String producer_raw_pro;
    public String producer_product_pro;
    static Stage producer_raw_chartStage;
    static Stage producer_product_chartStage;

    @FXML
    public void initialize(){
        setTable();
        setCell();
        setBar();
    }

    public void setBar(){
        product_bar.getData().clear();
        raw_bar.getData().clear();
        ArrayList<ProductSafeInventoryRateVo> p=service.getProductInventoryRate(StaticFactory.getUserVO().getCompanyID());
        XYChart.Series series1=new XYChart.Series();
        XYChart.Series series2=new XYChart.Series();
        for(int i=0;i<p.size();i++){
            series1.getData().add(new XYChart.Data(p.get(i).getVariety(),p.get(i).getInventory()));
            series2.getData().add(new XYChart.Data(p.get(i).getVariety(),p.get(i).getSafe_inventory()));
        }
        series1.setName("产品库存量");
        series2.setName("安全库存量");
        product_bar.getData().add(series1);
        product_bar.getData().add(series2);

        ArrayList<RawSafeInventoryRateVo> l=service.getRawSafeInventoryRate(StaticFactory.getUserVO().getCompanyID());
        XYChart.Series series3=new XYChart.Series();
        XYChart.Series series4=new XYChart.Series();
        for(int i=0;i<l.size();i++){
            series3.getData().add(new XYChart.Data(l.get(i).getVariety(),l.get(i).getInventory()));
            series4.getData().add(new XYChart.Data(l.get(i).getVariety(),l.get(i).getSafe_inventory()));
        }
        series3.setName("原材料库存量");
        series4.setName("安全库存量");
        raw_bar.getData().add(series3);
        raw_bar.getData().add(series4);


    }

    public void setTable(){
        raw_monitor_table.getItems().clear();
        product_monitor_table.getItems().clear();
        raw_date.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {

                java.util.Date d = Date.from(raw_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                StaticFactory.setproducer_raw_date(format.format(d));
                raw_tabledata=service.getRawMaterialInventoryMonitorItem(StaticFactory.getUserVO().getCompanyID(),format.format(d));
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

                java.util.Date d1 = Date.from(product_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                StaticFactory.setproducer_product_date(format.format(d1));
                product_tabledata=service.getProductInventoryMonitorItem(StaticFactory.getUserVO().getCompanyID(),format.format(d1));
                Iterator i = product_tabledata.iterator();
                while (i.hasNext()) {
                    product_list.add((ProductInventoryMonitorItemVo) i.next());
                }
            }
        });
        product.setCellValueFactory(new PropertyValueFactory("product_variety"));
        product_stock.setCellValueFactory(new PropertyValueFactory("inventory"));
        product_safe_stock.setCellValueFactory(new PropertyValueFactory("safe_inventory"));
        product_back.setCellValueFactory(new PropertyValueFactory("refund_rate"));


        raw_monitor_table.setItems(raw_list);
        product_monitor_table.setItems(product_list);
    }

    public void setCell() {
        raw_material.setCellFactory(tc->{
            TableCell<RawMaterialInventoryMonitorItemVo, String> cell=new TableCell<RawMaterialInventoryMonitorItemVo, String>(){
                @Override
                protected void updateItem(String item,boolean empty){
                    super.updateItem(item,empty);
                    setText(empty?null:item);
                }
            };
            cell.setOnMouseClicked(e->{
                if(!cell.isEmpty()){
                    producer_raw_pro=cell.getItem();
                    StaticFactory.setProducer_Raw_material(producer_raw_pro);

                    try {
                        drawProducer_raw();
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
                    producer_product_pro=cell.getItem();
                    StaticFactory.setProducer_product(producer_product_pro);

                    try {
                        drawProducer_product();
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

    public void drawProducer_raw() throws IOException{
        Parent root= FXMLLoader.load(getClass().getResource("../../../../view/supplyChainManagement/stock/ProducerRawChart.fxml"));
        producer_raw_chartStage=new Stage();
        Scene scene=new Scene(root,900,680);
        producer_raw_chartStage.setScene(scene);
        producer_raw_chartStage.initStyle(StageStyle.TRANSPARENT);
        producer_raw_chartStage.initModality(Modality.APPLICATION_MODAL);
        producer_raw_chartStage.show();
    }

    public void drawProducer_product() throws IOException{
        Parent root= FXMLLoader.load(getClass().getResource("../../../../view/supplyChainManagement/stock/ProducerProductChart.fxml"));
        producer_product_chartStage=new Stage();
        Scene scene=new Scene(root,900,680);
        producer_product_chartStage.setScene(scene);
        producer_product_chartStage.initStyle(StageStyle.TRANSPARENT);
        producer_product_chartStage.initModality(Modality.APPLICATION_MODAL);
        producer_product_chartStage.show();
    }

    public ArrayList<InventoryChangeVo> getRawStockChart(){
        return service.getRawInventoryChange(StaticFactory.getUserVO().getCompanyID(),StaticFactory.getProducer_Raw_material(),StaticFactory.getproducer_raw_date());
    }
    public ArrayList<PunctualDeliveryRateChangeVo> getRawPunctualChart(){
        return service.getRawPunctualDeliveryRateChange(StaticFactory.getUserVO().getCompanyID(),StaticFactory.getProducer_Raw_material(),StaticFactory.getproducer_raw_date());
    }

    public ArrayList<InventoryChangeVo> getProductStockChart(){
        return service.getProductInventoryChange(StaticFactory.getUserVO().getCompanyID(),StaticFactory.getProducer_product(),StaticFactory.getproducer_product_date());
    }
    public ArrayList<RefundRateChangeVo> getProductBackChart(){
        return service.getProductRefundRateChange(StaticFactory.getUserVO().getCompanyID(),StaticFactory.getProducer_product(),StaticFactory.getproducer_product_date());
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

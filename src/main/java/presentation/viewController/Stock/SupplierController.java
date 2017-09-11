package presentation.viewController.Stock;

import businesslogic.InventoryManagementImpl;
import businesslogicservice.InventoryManagementService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import presentation.viewController.StaticFactory;
import vo.BalanceSheetItemVo;
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
    @FXML
    private StackedBarChart barChart;

    static Stage chartStage;
    private InventoryManagementService service=new InventoryManagementImpl();

    DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    public java.util.Date d;
    private ArrayList<RawMaterialInventoryMonitorItemVo> supply_tabledata=new ArrayList<>();

    public String raw_pro = "";


    @FXML
    public void initialize(){
        setTable();
        setCell();
        setBar();
    }

    public void setBar(){
        ArrayList<RawSafeInventoryRateVo> l=service.getRawSafeInventoryRate("001");
        XYChart.Series series1=new XYChart.Series();
        XYChart.Series series2=new XYChart.Series();
        for(int i=0;i<l.size();i++){
            series1.getData().add(new XYChart.Data(l.get(i).getVariety(),l.get(i).getInventory()));
            series2.getData().add(new XYChart.Data(l.get(i).getVariety(),l.get(i).getSafe_inventory()));
        }
        series1.setName("原材料库存量");
        series2.setName("安全库存量");
        barChart.getData().add(series1);
        barChart.getData().add(series2);

    }
    public StackedBarChart.Series<String,Integer> getSFL(ArrayList<RawSafeInventoryRateVo> list){
       StackedBarChart.Series<String,Integer> series=new StackedBarChart.Series<>();
        for(int i=0;i<list.size();i++){
            RawSafeInventoryRateVo vo=list.get(i);
            series.getData().add(new StackedBarChart.Data<>(vo.getVariety(),vo.getSafe_inventory()));
        }
        return series;
    }
    public XYChart.Series<String,Integer> getSeriesFromList(ArrayList<InventoryChangeVo> list){
        XYChart.Series<String,Integer> series=new XYChart.Series();
        for(int i=0;i<list.size();i++){
            InventoryChangeVo vo=list.get(i);
            series.getData().add(new XYChart.Data<>(vo.getTime(),(Integer)vo.getInventory()));
        }
        return series;
    }


    public void setTable(){
        date.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {

                d=Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                StaticFactory.setDate(format.format(d));
//                System.out.print(format.format(d));
                supply_tabledata=service.getRawMaterialInventoryMonitorItem("001",format.format(d));
//                supply_tabledata.add(new RawMaterialInventoryMonitorItemVo("cotton",200,280,"0.9","0.02"));
//                                System.out.println(date.getValue());
                ObservableList<RawMaterialInventoryMonitorItemVo> list= FXCollections.observableArrayList();
                Iterator i=supply_tabledata.iterator();
                while(i.hasNext()){
                    list.add((RawMaterialInventoryMonitorItemVo)i.next());
                }

                raw_material.setCellValueFactory(new PropertyValueFactory("raw_material_variety"));

                stock_num.setCellValueFactory(new PropertyValueFactory("inventory"));
                safe_stock_num.setCellValueFactory(new PropertyValueFactory("safe_inventory"));
                ontime_delivery.setCellValueFactory(new PropertyValueFactory("punctual_delivery_rate"));
                back.setCellValueFactory(new PropertyValueFactory("refund_rate"));

                stock_monitor.setItems(list);

            }
        });
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
                    raw_pro=cell.getItem();
                    System.out.println(raw_pro);
                    StaticFactory.setRaw_material(raw_pro);

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
//            System.out.println(raw_pro+"hhh");
            return cell;
        });
    }

//    class RawControl extends TableCell<RawMaterialInventoryMonitorItemVo,String>{
//        public RawControl(){
//            super();
//            this.setOnMouseEntered(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    setStyle("-fx-text-fill: rgb(255,135,98)");
//                }
//            });
//            this.setOnMouseExited(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    setStyle("-fx-text-fill: black");
//                }
//            });
//            this.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event){
//                    //画图
//                    try {
//                        draw();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//    }

    public void draw() throws  IOException{
        Parent root= FXMLLoader.load(getClass().getResource("../../../view/Stock/SupplierChart.fxml"));
        chartStage=new Stage();
        Scene scene=new Scene(root,800,600);
        chartStage.setScene(scene);
        chartStage.initStyle(StageStyle.TRANSPARENT);
        chartStage.initModality(Modality.APPLICATION_MODAL);
        chartStage.show();
    }


    public ArrayList<InventoryChangeVo> getStockChart(){
//        System.out.println("原材料:"+StaticFactory.getRaw_material());
//        System.out.println("时间"+format.format(d));
        return service.getRawInventoryChange("001",StaticFactory.getRaw_material(),StaticFactory.getDate());
    }

    public ArrayList<PunctualDeliveryRateChangeVo> getPunctualChart(){
        return service.getRawPunctualDeliveryRateChange("001",StaticFactory.getRaw_material(),StaticFactory.getDate());
    }

    public ArrayList<RefundRateChangeVo> getBackChart(){
        return service.getRawRefundRateChange("001",StaticFactory.getRaw_material(),StaticFactory.getDate());
    }
    public String getId(){
        return "aaaa";
    }
}

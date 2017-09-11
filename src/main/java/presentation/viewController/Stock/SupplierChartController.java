package presentation.viewController.Stock;

import businesslogic.InventoryManagementImpl;
import businesslogicservice.InventoryManagementService;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import vo.Inventory.InventoryChangeVo;
import vo.Inventory.PunctualDeliveryRateChangeVo;
import vo.Inventory.RefundRateChangeVo;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;

/**
 * Created by YZ on 2017/9/9.
 */
public class SupplierChartController {
    @FXML
    private LineChart<String,Number> stock_time;
    @FXML
    private LineChart<String,Number> ontime_time;
    @FXML
    private LineChart<String,Number> back_time;
    @FXML
    private javafx.scene.image.ImageView back;

//    InventoryManagementService service=new InventoryManagementImpl();

    SupplierController supplierController=new SupplierController();
    public SupplierChartController(){

    }

    @FXML
    public void initialize(){
        stock_time.setTitle("库存量-时间");
//        System.out.print(supplierController.getId());
        XYChart.Series stock_series=getSeriesFromList(supplierController.getStockChart());
        stock_time.getData().add(stock_series);

        ontime_time.setTitle("准时交货率-时间");
        XYChart.Series ontime_series=getPunctualSeriesFromList(supplierController.getPunctualChart());
        ontime_time.getData().add(ontime_series);

        back_time.setTitle("退货率-时间");
        XYChart.Series back_series=getBackSeriesFromList(supplierController.getBackChart());
        back_time.getData().add(back_series);

        back.setOnMouseEntered(e->{
            back.setCursor(Cursor.HAND);
        });
        back.setOnMouseExited(e->{
            back.setCursor(Cursor.DEFAULT);
        });
    }

    public void backtotable(){
        SupplierController.chartStage.close();
    }

    public XYChart.Series<String,Integer> getSeriesFromList(ArrayList<InventoryChangeVo> list){
        XYChart.Series<String,Integer> series=new XYChart.Series();
        for(int i=0;i<list.size();i++){
            InventoryChangeVo vo=list.get(i);
            series.getData().add(new XYChart.Data<>(vo.getTime(),(Integer)vo.getInventory()));
        }
        return series;
    }

    public XYChart.Series<String,Double> getPunctualSeriesFromList(ArrayList<PunctualDeliveryRateChangeVo> list){
        XYChart.Series<String,Double> series=new XYChart.Series();
        for(int i=0;i<list.size();i++){
            PunctualDeliveryRateChangeVo vo=list.get(i);
            series.getData().add(new XYChart.Data<>(vo.getTime(),(Double)vo.getPunctual_delivery_rate()));
        }
        return series;
    }

    public XYChart.Series<String,Double> getBackSeriesFromList(ArrayList<RefundRateChangeVo> list){
        XYChart.Series<String,Double> series=new XYChart.Series();
        for(int i=0;i<list.size();i++){
            RefundRateChangeVo vo=list.get(i);
            series.getData().add(new XYChart.Data<>(vo.getTime(),(Double)vo.getRefund_rate()));
        }
        return series;
    }
}


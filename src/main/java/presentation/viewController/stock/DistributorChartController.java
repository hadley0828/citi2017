package presentation.viewController.stock;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import vo.Inventory.InventoryChangeVo;
import vo.Inventory.PunctualDeliveryRateChangeVo;
import vo.Inventory.RefundRateChangeVo;

import java.util.ArrayList;

/**
 * Created by YZ on 2017/9/10.
 */
public class DistributorChartController {
    @FXML
    private LineChart<String,Number> distributor_stock_time;
    @FXML
    private LineChart<String,Number> distributor_ontime_time;
    @FXML
    private LineChart<String,Number> distributor_back_time;
    @FXML
    private javafx.scene.image.ImageView back;

    DistributorController distributorController=new DistributorController();

    @FXML
    public void initialize(){
        distributor_stock_time.setTitle("库存量-时间");
        XYChart.Series stock_series=getSeriesFromList(distributorController.getStockChart());
        distributor_stock_time.getData().add(stock_series);

        distributor_ontime_time.setTitle("准时交货率-时间");
        XYChart.Series ontime_series=getPunctualSeriesFromList(distributorController.getPunctualChart());
        distributor_ontime_time.getData().add(ontime_series);

        distributor_back_time.setTitle("退货率-时间");
        XYChart.Series back_series=getBackSeriesFromList(distributorController.getBackChart());
        distributor_back_time.getData().add(back_series);

        back.setOnMouseEntered(e->{
            back.setCursor(Cursor.HAND);
        });
        back.setOnMouseExited(e->{
            back.setCursor(Cursor.DEFAULT);
        });
    }

    public void backtotable(){
        DistributorController.chartStage.close();
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

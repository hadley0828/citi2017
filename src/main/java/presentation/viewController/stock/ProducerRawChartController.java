package presentation.viewController.stock;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import vo.Inventory.InventoryChangeVo;
import vo.Inventory.PunctualDeliveryRateChangeVo;

import java.util.ArrayList;

/**
 * Created by YZ on 2017/9/10.
 */
public class ProducerRawChartController {
    @FXML
    private LineChart<String,Number> producer_raw_stock_time;
    @FXML
    private LineChart<String,Number> producer_raw_ontime_time;
    @FXML
    private javafx.scene.image.ImageView back;

    ProducerController producerController=new ProducerController();

    public void raw_backtotable(){
        ProducerController.producer_raw_chartStage.close();
    }

    @FXML
    public void initialize(){
        producer_raw_ontime_time.setTitle("原材料准时交货率-时间");
        XYChart.Series ontime_series=getPunctualSeriesFromList(producerController.getRawPunctualChart());
        producer_raw_ontime_time.getData().add(ontime_series);

        producer_raw_stock_time.setTitle("原材料库存量-时间");
        XYChart.Series back_series=getSeriesFromList(producerController.getRawStockChart());
        producer_raw_stock_time.getData().add(back_series);

        back.setOnMouseEntered(e->{
            back.setCursor(Cursor.HAND);
        });
        back.setOnMouseExited(e->{
            back.setCursor(Cursor.DEFAULT);
        });
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

}

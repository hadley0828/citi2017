package presentation.viewController.stock;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import vo.Inventory.InventoryChangeVo;
import vo.Inventory.RefundRateChangeVo;

import java.util.ArrayList;

/**
 * Created by YZ on 2017/9/10.
 */
public class ProducerProductChartController {
    @FXML
    private LineChart<String,Number> producer_product_stock_time;
    @FXML
    private LineChart<String,Number> producer_product_refund_time;
    @FXML
    private javafx.scene.image.ImageView back;


    ProducerController producerController=new ProducerController();

    public void product_backtotable(){
        ProducerController.producer_product_chartStage.close();
    }

    @FXML
    public void initialize(){
        producer_product_stock_time.setTitle("产品库存量-时间");
        XYChart.Series product_stock__series=getSeriesFromList(producerController.getProductStockChart());
        producer_product_stock_time.getData().add(product_stock__series);

        producer_product_refund_time.setTitle("产品退货率-时间");
        XYChart.Series product_back_series=getBackSeriesFromList(producerController.getProductBackChart());
        producer_product_refund_time.getData().add(product_back_series);

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
    public XYChart.Series<String,Double> getBackSeriesFromList(ArrayList<RefundRateChangeVo> list){
        XYChart.Series<String,Double> series=new XYChart.Series();
        for(int i=0;i<list.size();i++){
            RefundRateChangeVo vo=list.get(i);
            series.getData().add(new XYChart.Data<>(vo.getTime(),(Double)vo.getRefund_rate()));
        }
        return series;
    }
}

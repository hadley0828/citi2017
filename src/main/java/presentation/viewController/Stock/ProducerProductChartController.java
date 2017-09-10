package presentation.viewController.Stock;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

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

    public void product_backtotable(){
        ProducerController.producer_product_chartStage.close();
    }

    @FXML
    public void initialize(){
        producer_product_stock_time.setTitle("产品库存量-时间");
        producer_product_refund_time.setTitle("产品退货率-时间");
    }
}

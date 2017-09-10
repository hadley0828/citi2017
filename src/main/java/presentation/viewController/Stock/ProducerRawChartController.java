package presentation.viewController.Stock;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;

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

    public void raw_backtotable(){
        ProducerController.producer_raw_chartStage.close();
    }

}

package presentation.viewController.Stock;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;

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

    public void backtotable(){
        DistributorController.chartStage.close();
    }

}

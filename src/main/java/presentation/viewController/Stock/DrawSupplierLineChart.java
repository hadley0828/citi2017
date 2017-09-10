package presentation.viewController.Stock;

import businesslogic.InventoryManagementImpl;
import businesslogicservice.InventoryManagementService;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import vo.Inventory.InventoryChangeVo;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;

/**
 * Created by YZ on 2017/9/9.
 */
public class DrawSupplierLineChart {
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
    public DrawSupplierLineChart(){

    }

    @FXML
    public void initialize(){
        stock_time.setTitle("库存量-时间");
//        System.out.print(supplierController.getId());
        XYChart.Series stock_series=getSeriesFromList(supplierController.getStockChart());

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
}


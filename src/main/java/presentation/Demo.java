package presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;


import java.io.IOException;

/**
 * Created by YZ on 2017/8/11.
 */
public class Demo extends Application {
    @Override
    public void start(Stage primaryStage){
        try{
            FXMLLoader loader=new FXMLLoader();
//            loader.setLocation(Demo.class.getResource("../view/cashFlow/cashFlow.fxml"));
//            loader.setLocation(Demo.class.getResource("../view/profitSheet/profitSheet.fxml"));
//            loader.setLocation(Demo.class.getResource("../view/financialSystem/reportForm/balanceSheet/balanceSheet.fxml"));
//              loader.setLocation(Demo.class.getResource("../view/balanceSheet/TestTree.fxml"));
//            loader.setLocation(Demo.class.getResource("../view/stock/Supplier.fxml"));
//            ScrollPane root=loader.load();
//            loader.setLocation(Demo.class.getResource("../view/supplyChainManagement/financialInstitutions/FinancialInstitutions.fxml"));
//            loader.setLocation(Demo.class.getResource("../view/supplyChainManagement/stock/Distributor.fxml"));
//            loader.setLocation(Demo.class.getResource("../view/stock/Producer.fxml"));
//            loader.setLocation(Demo.class.getResource("../view/stock/stockInfo.fxml"));
//            loader.setLocation(Demo.class.getResource("../view/performanceAppraisal/performanceAppraisal.fxml"));
//            loader.setLocation(Demo.class.getResource("../view/supplyChainManagement/financing/financing.fxml"));
            loader.setLocation(Demo.class.getResource("../view/financialSystem/trialSettlement/Settle.fxml"));
//            loader.setLocation(Demo.class.getResource("../view/financialSystem/stockInfo/SupplierInfo.fxml"));
//            loader.setLocation(Demo.class.getResource("../view/financialSystem/stockInfo/ProducerInfo.fxml"));
//            loader.setLocation(Demo.class.getResource("../view/financialSystem/stockInfo/DistributorInfo.fxml"));
            AnchorPane root=loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public static void main(String[]args) throws ClassNotFoundException{
        launch(args);
    }
}

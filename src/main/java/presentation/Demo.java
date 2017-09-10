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
//            loader.setLocation(Demo.class.getResource("../view/CashFlow/CashFlow.fxml"));
//            loader.setLocation(Demo.class.getResource("../view/ProfitSheet/ProfitSheet.fxml"));
//            loader.setLocation(Demo.class.getResource("../view/BalanceSheet/BalanceSheet.fxml"));
//              loader.setLocation(Demo.class.getResource("../view/BalanceSheet/TestTree.fxml"));
//            loader.setLocation(Demo.class.getResource("../view/Stock/Supplier.fxml"));
//            ScrollPane root=loader.load();
            loader.setLocation(Demo.class.getResource("../view/PerformanceAppraisal/PerformanceAppraisal.fxml"));
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

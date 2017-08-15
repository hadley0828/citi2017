package presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * Created by YZ on 2017/8/11.
 */
public class Demo extends Application {
    @Override
    public void start(Stage primaryStage){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(Demo.class.getResource("../view/BalanceSheet/BalanceSheet.fxml"));
            ScrollPane root=loader.load();

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

package presentation.warningController;

/**
 * Created by Chris on 2017/9/13.
 */
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RunWarning extends Application {

    private BorderPane border;
    private Stage primaryStage;
    private static String warning="提示";
    public RunWarning(){

    }

    @Override
    public void start(Stage primaryStage) {
        try{
            this.primaryStage=primaryStage;
            this.primaryStage.setTitle("提示");
            SetBorder();
            SetAnchor();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void SetAnchor(){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/warning/generalWarningAnchor.fxml"));
            AnchorPane WarningAnchor=(AnchorPane)loader.load();
            border.setCenter(WarningAnchor);

            //set controller
            WarningController controller=loader.getController();
            controller.setRunWarning(this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void SetWarning(String warning){
        RunWarning.warning=warning;
    }
    public static String GetWarning(){
        return warning;
    }

    public void SetBorder(){
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/warning/generalWarningBorder.fxml"));
            border = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(border);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public Stage getPrimaryStage() {
        return primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}

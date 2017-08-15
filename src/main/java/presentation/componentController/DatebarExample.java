package presentation.componentController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by YZ on 2017/8/12.
 */
public class DatebarExample extends Application{
    @Override
    public void start(Stage stage) throws Exception{
        Datebar datebar=new Datebar();
        stage.setScene(new Scene(datebar));
        stage.show();
    }
    public static void main(String[]args){
        launch(args);
    }

}

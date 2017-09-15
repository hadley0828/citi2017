package presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Molloh
 * @version 2017/9/6
 */
public class RunRootLayout extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/layout/RootLayout.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setMinWidth(1180);
        primaryStage.setMinHeight(790);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
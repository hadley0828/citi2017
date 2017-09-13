package presentation.loginController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Molloh
 * @version 2017/9/6
 */
public class RunCSignUp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../../view/LoginAndSignUp/SignUp.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(600);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}

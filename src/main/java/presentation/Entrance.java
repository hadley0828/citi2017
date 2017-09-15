package presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.loginController.LoginController;

/**
 * @author Molloh
 * @version 2017/9/6
 */
public class Entrance extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/loginAndSignUp/Login.fxml"));
        Scene scene = new Scene(root);

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/loginAndSignUp/Login.fxml"));
        LoginController controller=loader.getController();
        controller.SetEntrance(this);

        this.primaryStage=primaryStage;

        this.primaryStage.setMinWidth(1000);
        this.primaryStage.setMinHeight(600);
        this.primaryStage.setScene(scene);

        this.primaryStage.show();
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }
}

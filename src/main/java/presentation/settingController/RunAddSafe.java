package presentation.settingController;

/**
 * Created by Chris on 2017/9/16.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.componentController.ResizeHelper;
import presentation.loginController.LoginController;
import presentation.loginController.StaticStage;

/**
 * @author Molloh
 * @version 2017/9/6
 */
public class RunAddSafe extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("../../view/settings/AddSafe.fxml"));
        Scene scene = new Scene(root);



        this.primaryStage = primaryStage;


        new StaticStage().SetStage(this.primaryStage);
        new AddSafeController().setStage(this.primaryStage);

        this.primaryStage.setMinWidth(439);
        this.primaryStage.setMinHeight(485);
        this.primaryStage.setScene(scene);

        this.primaryStage.show();
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }
}

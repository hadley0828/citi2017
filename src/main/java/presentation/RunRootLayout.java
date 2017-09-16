package presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.componentController.ResizeHelper;


/**
 * @author Molloh
 * @version 2017/9/6
 */
public class RunRootLayout extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {

        ResizeHelper.addResizeListener(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("../view/layout/RootLayout.fxml"));
        Scene scene = new Scene(root);

        /*drag*/
        root.setOnMousePressed((MouseEvent event) -> {
            event.consume();
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();});

        root.setOnMouseDragged((MouseEvent event) -> {
            event.consume();
            primaryStage.setX(event.getScreenX() - xOffset);

            if (event.getScreenY() - yOffset < 0) {
                primaryStage.setY(0);
            } else {
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });


        primaryStage.setMinWidth(1180);
        primaryStage.setMinHeight(790);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
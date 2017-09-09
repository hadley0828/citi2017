package presentation.viewController.layout;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2017/9/9
 */
public class SupplyLayoutController implements Initializable, ControlledScreen {
    @FXML
    private StackPane supplyStack;

    private ScreensController parentController;
    private ScreensController supplyController = new ScreensController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        parentController = screenPage;
    }
}

package presentation.viewController.layout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import presentation.screenController.ScreensController;
import presentation.screenController.ScreensFramework;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2017/9/9
 */
public class RootLayoutController implements Initializable {
    @FXML
    private Button logo_btn;
    @FXML
    private Button financial_btn;
    @FXML
    private Button supply_btn;
    @FXML
    private Button settings_btn;
    @FXML
    private Button signOut_btn;

    @FXML
    private Button max_btn;
    @FXML
    private Button minus_btn;
    @FXML
    private Button close_btn;

    @FXML
    private ImageView logo;
    @FXML
    private StackPane rootStack;

    private ScreensController rootController = new ScreensController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Region icon_1 = new Region();
        icon_1.getStyleClass().add("icon");
        max_btn.setGraphic(icon_1);


        Region icon_2 = new Region();
        icon_2.getStyleClass().add("icon");
        minus_btn.setGraphic(icon_2);
        minus_btn.setOnAction(event -> {
            Stage stage = (Stage)minus_btn.getScene().getWindow();
            stage.setIconified(true);
        });

        Region icon_3 = new Region();
        icon_3.getStyleClass().add("icon");
        close_btn.setGraphic(icon_3);
        close_btn.setOnAction(event -> System.exit(0));


        rootController.loadScreen(ScreensFramework.FINANCIAL_LAYOUT_SCREEN, ScreensFramework.FINANCIAL_LAYOUT_SCREEN_FXML);

        rootController.loadScreen(ScreensFramework.HOME_LAYOUT_SCREEN, ScreensFramework.HOME_LAYOUT_SCREEN_FXML);
        rootController.setScreen(ScreensFramework.HOME_LAYOUT_SCREEN);

        rootStack.getChildren().add(rootController);

/*        Group svg = new Group(
                createPath("M860.928 286.912l-688 0c-61.76 0-112 50.24-112 112l0 424c0 61.76 50.24 112 112 112l688 0c61.744 0 112-50.24 112-112l0-424C972.928 337.136 922.672 286.912 860.928 286.912zM908.928 822.912c0 26.464-21.536 48-48 48l-688 0c-26.464 0-48-21.536-48-48l0-424c0-26.464 21.536-48 48-48l688 0c26.464 0 48 21.536 48 48L908.928 822.912z"),
                createPath("M665.84 584c13.824 0 25.072-11.232 25.072-25.072s-11.248-25.088-25.072-25.088L572 533.84l95.696-95.68c9.792-9.808 9.792-25.68 0-35.472-9.792-9.808-25.664-9.808-35.472 0l-113.936 113.968-113.968-113.968c-9.792-9.808-25.664-9.808-35.472 0-9.792 9.776-9.792 25.664 0 35.472l95.696 95.68L368 533.84c-13.856 0-25.072 11.248-25.072 25.088S354.144 584 368 584l123.84 0 0 55.264L368 639.264c-13.856 0-25.072 11.232-25.072 25.088 0 13.84 11.216 25.056 25.072 25.056l123.84 0 0 118.16c0 13.84 11.216 25.072 25.072 25.072 13.84 0 25.072-11.232 25.072-25.072l0-118.16 123.84 0c13.824 0 25.072-11.216 25.072-25.056 0-13.856-11.248-25.088-25.072-25.088l-123.84 0L541.984 584 665.84 584z"),
                createPath("M175.92 247.168 857.92 247.168c17.664 0 32-14.336 32-32 0-17.68-14.336-32-32-32L175.92 183.168c-17.664 0-32 14.32-32 32C143.92 232.832 158.256 247.168 175.92 247.168z"),
                createPath("M260.928 144.512l512 0c17.664 0 32-14.336 32-32 0-17.68-14.336-32-32-32l-512 0c-17.664 0-32 14.32-32 32C228.928 130.176 243.248 144.512 260.928 144.512z")
        );

        Bounds bounds = svg.getBoundsInParent();
        double scale = Math.min(20/bounds.getWidth(), 20 / bounds.getHeight());
        svg.setScaleX(scale);
        svg.setScaleY(scale);

        Button btn = new Button();
        btn.setGraphic(svg);
        btn.setMaxSize(30, 30);
        btn.setMinSize(30, 30);
        btn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);*/

    }

    @FXML
    private void missionSwitch(ActionEvent event) {
        if (event.getSource().equals(logo_btn)) {
            rootController.setScreen(ScreensFramework.HOME_LAYOUT_SCREEN);
        }
        else if (event.getSource().equals(financial_btn)) {
            rootController.setScreen(ScreensFramework.FINANCIAL_LAYOUT_SCREEN);
        }
        else if (event.getSource().equals(supply_btn)) {
            rootController.loadScreen(ScreensFramework.SUPPLY_LAYOUT_SCREEN, ScreensFramework.SUPPLY_LAYOUT_SCREEN_FXML);
            rootController.setScreen(ScreensFramework.SUPPLY_LAYOUT_SCREEN);
        }
        else if (event.getSource().equals(settings_btn)) {
            rootController.loadScreen(ScreensFramework.SETTINGS_LAYOUT_SCREEN, ScreensFramework.SETTINGS_LAYOUT_SCREEN_FXML);
            rootController.setScreen(ScreensFramework.SETTINGS_LAYOUT_SCREEN);
        }
    }

    private static SVGPath createPath(String d) {
        SVGPath path = new SVGPath();
        path.getStyleClass().add("svg");
        path.setContent(d);
        path.setStyle("-fill: #515151" + ';');
        return path;
    }
}

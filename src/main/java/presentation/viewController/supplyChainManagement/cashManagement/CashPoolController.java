package presentation.viewController.supplyChainManagement.cashManagement;

import businesslogic.CashPoolImpl;
import businesslogicservice.CashPoolService;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import presentation.componentController.FunLevelGauge;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.StaticFactory;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2017/9/13
 */
public class CashPoolController implements Initializable, ControlledScreen {
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label last_label;
    @FXML
    private Label currentIn_label;
    @FXML
    private Label currentOut_label;
    @FXML
    private Label reserve_label;
    @FXML
    private VBox layoutPane;

    private long lastTimerCall;
    private AnimationTimer timer;
    private FunLevelGauge gauge;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        datePicker.setOnAction(event -> {
            CashPoolService cashPool = new CashPoolImpl();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = datePicker.getValue().format(formatter);
            String company_id = StaticFactory.getUserVO().getCompanyID();

            double[] quota = cashPool.getCashFlow(company_id, date);

            last_label.setText(String.valueOf(quota[0]));
            currentIn_label.setText(String.valueOf(quota[1]));
            currentOut_label.setText(String.valueOf(quota[2]));
            reserve_label.setText(String.valueOf(quota[3]));

        });

        gauge = new FunLevelGauge();
        gauge.setPrefSize(400, 400);

        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall + 800_000_000l) {
                    gauge.setLevel(0.5);
                    lastTimerCall = now;
                }
            }
        };

        timer.start();

        layoutPane.getChildren().add(gauge);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

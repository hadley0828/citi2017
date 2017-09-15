package presentation.viewController.financialSystem.warning;

import businesslogic.FinancialWarningImpl;
import businesslogicservice.FinancialWarningService;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import eu.hansolo.medusa.Section;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.StaticFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2017/9/6
 */
public class FinancialWarningController implements Initializable, ControlledScreen {
    @FXML
    private Pane layPane;
    @FXML
    private Label message;
    @FXML
    private Label number;

    private Gauge gauge;
    private AnimationTimer timer;
    private long lastTimerCall;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String date = localDate.format(formatter);
        String company_id = StaticFactory.getUserVO().getCompanyID();

        FinancialWarningService warning = new FinancialWarningImpl();

        String warningMessage = warning.getWarningMessage2(company_id, date);
        double warningLevel = warning.getWarningMessage(company_id, date);

        message.setText(warningMessage);
        number.setText(String.valueOf(warningLevel));
        System.out.println(warningMessage);
        System.out.println(warningLevel);

//        gauge here
        gauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.SIMPLE_SECTION)
                .titleColor(Color.WHITE)
                .unitColor(Color.WHITE)
                .animated(true)
                .valueColor(Color.WHITE)
                .minValue(0)
                .maxValue(150)
                .titleColor(Color.BLACK)
                .valueColor(Color.BLACK)
                .unitColor(Color.BLACK)
                .sections(new Section(0, 25, "0", Color.web("#cc6230")),
                        new Section(25, 50, "1", Color.web("#e18c2f")),
                        new Section(50, 75, "2", Color.web("#f0c051")),
                        new Section(75, 100, "3", Color.web("#bdd156")),
                        new Section(100, 125, "4", Color.web("#89b84e")),
                        new Section(125, 160, "5", Color.web("#5ca151")))
                .build();

        gauge.setValue(0);

        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now > lastTimerCall + 1_000_000_000L) {
                    {
                        gauge.setValue(warningLevel);
                        lastTimerCall = now;
                    }
                }

            }
        };
        timer.start();

        layPane.getChildren().add(gauge);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

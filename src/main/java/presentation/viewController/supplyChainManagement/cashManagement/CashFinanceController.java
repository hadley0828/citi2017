package presentation.viewController.supplyChainManagement.cashManagement;

import businesslogic.CashPoolImpl;
import businesslogicservice.CashPoolService;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import eu.hansolo.medusa.Section;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
public class CashFinanceController implements Initializable, ControlledScreen {
    @FXML
    private DatePicker datePicker;
    @FXML
    private VBox vBox;

    private Gauge guarantee_bar;
    private Gauge recycle_bar;
    private Gauge cashFlow_bar;
    private Gauge gravity_bar;

    private AnimationTimer timer;
    private long lastTimerCall;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        guarantee_bar = getBulletChart("盈余现金保障倍数", "(%)", 600, 500, new Section(0, 100, Color.web("#cc6230")), new Section(100, 200, Color.web("#e18c2f")), new Section(200, 300, Color.web("#f0c051")),
                new Section(300, 400, Color.web("#bdd156")), new Section(400, 500, Color.web("#89b84e")), new Section(500, 600, Color.web("#5ca151")));

        recycle_bar = getBulletChart("全部资产现金回收率", "(%)", 600, 500, new Section(0, 100, Color.web("#cc6230")), new Section(100, 200, Color.web("#e18c2f")), new Section(200, 300, Color.web("#f0c051")),
                new Section(300, 400, Color.web("#bdd156")), new Section(400, 500, Color.web("#89b84e")), new Section(500, 600, Color.web("#5ca151")));

        cashFlow_bar = getBulletChart("现金流动负债率", "(%)", 600, 500, new Section(0, 100, Color.web("#cc6230")), new Section(100, 200, Color.web("#e18c2f")), new Section(200, 300, Color.web("#f0c051")),
                new Section(300, 400, Color.web("#bdd156")), new Section(400, 500, Color.web("#89b84e")), new Section(500, 600, Color.web("#5ca151")));

        gravity_bar = getBulletChart("两金占流动资产比重", "(%)", 600, 500, new Section(0, 100, Color.web("#cc6230")), new Section(100, 200, Color.web("#e18c2f")), new Section(200, 300, Color.web("#f0c051")),
                new Section(300, 400, Color.web("#bdd156")), new Section(400, 500, Color.web("#89b84e")), new Section(500, 600, Color.web("#5ca151")));

        vBox.getChildren().add(guarantee_bar);
        vBox.getChildren().add(recycle_bar);
        vBox.getChildren().add(cashFlow_bar);
        vBox.getChildren().add(gravity_bar);

        guarantee_bar.setValue(0);
        recycle_bar.setValue(0);
        cashFlow_bar.setValue(0);
        gravity_bar.setValue(0);

        datePicker.setOnAction(event -> {
            CashPoolService cashPool = new CashPoolImpl();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = datePicker.getValue().format(formatter);
            String company_id = StaticFactory.getUserVO().getCompanyID();

            double[] quota = cashPool.getFinancialIndex(company_id, date);
            for (double num : quota){
                System.out.println(num);
            }

            lastTimerCall = System.nanoTime();
            timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (now > lastTimerCall + 800_000_000L) {
                        {
                            guarantee_bar.setValue(340);
                            recycle_bar.setValue(550);
                            recycle_bar.setValue(120);
                            recycle_bar.setValue(440);
                            lastTimerCall = now;
                        }
                    }

                }
            };
            timer.start();

        });
    }

    private Gauge getBulletChart(final String TITLE, final String UNIT,
                                 final double MAX_VALUE, final double THRESHOLD,
                                 final Section... SECTIONS) {
        return GaugeBuilder.create()
                .skinType(Gauge.SkinType.BULLET_CHART)
                .animated(true)
                .thresholdColor(Color.WHITE)
                .title(TITLE)
                .unit(UNIT)
                .maxValue(MAX_VALUE)
                .threshold(THRESHOLD)
                .sectionsVisible(true)
                .sections(SECTIONS)
                .barColor(Color.SNOW)
                .build();
    }
    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

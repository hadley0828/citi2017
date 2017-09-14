package presentation.viewController.supplyChainManagement.cashManagement;

import businesslogic.CashPoolImpl;
import businesslogicservice.CashPoolService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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



    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

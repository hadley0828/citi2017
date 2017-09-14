package presentation.viewController.supplyChainManagement.cashManagement;

import businesslogic.CashPoolImpl;
import businesslogicservice.CashPoolService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressBar;
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
    private ProgressBar guarantee_bar;
    @FXML
    private ProgressBar recycle_bar;
    @FXML
    private ProgressBar cashFlow_bar;
    @FXML
    private ProgressBar gravity_bar;
    @FXML
    private DatePicker datePicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CashPoolService cashPool = new CashPoolImpl();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = datePicker.getValue().format(formatter);
        String company_id = StaticFactory.getUserVO().getCompanyID();

        double[] quota = cashPool.getFinancialIndex(date, company_id);

        guarantee_bar.setProgress(quota[0]);
        recycle_bar.setProgress(quota[1]);
        cashFlow_bar.setProgress(quota[2]);
        gravity_bar.setProgress(quota[3]);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

package presentation.viewController.financialSystem.warning;

import businesslogic.FinancialWarningImpl;
import businesslogicservice.FinancialWarningService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.viewController.StaticFactory;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2017/9/6
 */
public class FinancialWarningController implements Initializable, ControlledScreen {
    @FXML
    private DatePicker date_picker;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = date_picker.getValue().format(formatter);
        String company_id = StaticFactory.getUserVO().getCompanyID();
        FinancialWarningService warning = new FinancialWarningImpl();

        String warningMessage = warning.getWarningMessage2(company_id, date);
        double warningLevel = warning.getWarningMessage(company_id, date);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

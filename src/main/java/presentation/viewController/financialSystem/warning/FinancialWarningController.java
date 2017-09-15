package presentation.viewController.financialSystem.warning;

import businesslogic.FinancialWarningImpl;
import businesslogicservice.FinancialWarningService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.StaticFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2017/9/6
 */
public class FinancialWarningController implements Initializable, ControlledScreen {

    @FXML
    private Label message;
    @FXML
    private Label number;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = localDate.format(formatter);
        String company_id = StaticFactory.getUserVO().getCompanyID();
        FinancialWarningService warning = new FinancialWarningImpl();

        String warningMessage = warning.getWarningMessage2(company_id, date);
        double warningLevel = warning.getWarningMessage(company_id, date);

        message.setText(warningMessage);
        number.setText(String.valueOf(warningLevel));
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

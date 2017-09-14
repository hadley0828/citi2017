package presentation.componentController;

import businesslogic.VoucherBlImpl;
import businesslogicservice.VoucherBlService;
import javafx.scene.control.ComboBox;
import presentation.viewController.StaticFactory;

import java.util.ArrayList;

public class PeriodCombo extends ComboBox<String> {

    private ArrayList<String> periodArray;
    public PeriodCombo() {
        VoucherBlService voucherBl = new VoucherBlImpl();
        periodArray = voucherBl.getAllPeriod(StaticFactory.getUserVO().getCompanyID());
        for (String item: periodArray) {
            getItems().add(item);
        }
    }
}

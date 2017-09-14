package presentation.componentController;

import businesslogic.VoucherBlImpl;
import businesslogicservice.VoucherBlService;
import javafx.scene.control.ComboBox;
import presentation.viewController.StaticFactory;

import java.util.ArrayList;

public class MakerCombo extends ComboBox<String> {

    public MakerCombo() {
        getItems().add("全部");
        VoucherBlService voucherBlService = new VoucherBlImpl();
        ArrayList<String> array = voucherBlService.getAllVoucherMaker(StaticFactory.getUserVO().getCompanyID());

        for (String item: array) {
            getItems().add(item);
        }
    }
}

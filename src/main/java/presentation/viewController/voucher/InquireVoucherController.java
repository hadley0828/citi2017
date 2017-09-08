package presentation.viewController.voucher;

import businesslogic.VoucherBlImpl;
import businesslogicservice.VoucherBlService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import presentation.componentController.VoucherCard;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import vo.voucher.VoucherVo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InquireVoucherController implements Initializable, ControlledScreen {
    @FXML
    private AnchorPane rootPane;

    private VoucherCard voucherCard;
    private VoucherBlService voucherBl;
    private ArrayList<VoucherVo> voucherList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        voucherBl = new VoucherBlImpl();
        voucherList = voucherBl.getCurrentPeriodAllVoucher();
        voucherCard = new VoucherCard(voucherList.get(0).getAmountList());
        rootPane.getChildren().add(voucherCard);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

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

/**
 * @author Molloh
 * @version 2017/9/6
 */
public class InquireVoucherController implements Initializable, ControlledScreen {
    @FXML
    private AnchorPane rootPane;

    private VoucherBlService voucherBl;
    private ArrayList<VoucherVo> voucherList;
    private VoucherCard voucherCard;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        voucherBl = new VoucherBlImpl();
        voucherList = voucherBl.getCurrentPeriodAllVoucher("001");
        voucherCard = new VoucherCard(voucherList.get(0).getAmountList());
        rootPane.getChildren().add(voucherCard);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

package presentation.viewController.financialSystem.voucher;

import businesslogic.VoucherBlImpl;
import businesslogicservice.VoucherBlService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import presentation.componentController.VoucherCard;
import presentation.componentController.VoucherSearch;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import vo.voucher.VoucherSearchVo;
import vo.voucher.VoucherVo;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2017/9/6
 */
public class InquireVoucherController implements Initializable, ControlledScreen {

    @FXML
    private VBox voucher_list;
    @FXML
    private MenuItem popItem;

    private VoucherSearch voucherSearch;
    private VoucherSearchVo searchVo;
    private VoucherBlService voucherBl;
    private ArrayList<VoucherVo> voucherList;
    private ArrayList<String> voucherIdList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        voucherSearch = new VoucherSearch();
        popItem.setGraphic(voucherSearch);

        searchVo = new VoucherSearchVo();
        voucherBl = new VoucherBlImpl();
        voucherIdList = new ArrayList<>();

        updateList();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }

    public void setVoucherSearch(VoucherSearchVo searchVo) {
        this.searchVo = searchVo;
    }

    public void updateList() {
        voucherIdList.clear();
        voucherList = voucherBl.getSearchedVoucher(searchVo, "001");

        if (!voucherList.isEmpty()) {
            for (VoucherVo vo: voucherList) {
                voucherIdList.add(vo.getVoucherId());
                VoucherCard voucherCard = new VoucherCard(vo.getAmountList());
                voucher_list.getChildren().add(voucherCard);
            }
        }
    }

    @FXML
    private void OnExport() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("导出");
        File file = fileChooser.showSaveDialog(voucher_list.getScene().getWindow());
        voucherBl.exportToExcel(voucherIdList, file.getAbsolutePath(), "001");
    }

    @FXML
    private void OnImport() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("导入");
        File file = fileChooser.showOpenDialog(voucher_list.getScene().getWindow());
        voucherBl.importFromExcel(file.getAbsolutePath(), "001");
        updateList();
    }
}

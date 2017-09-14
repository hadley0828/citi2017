package presentation.viewController.financialSystem.voucher;

import businesslogic.VoucherBlImpl;
import businesslogicservice.VoucherBlService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import presentation.componentController.VoucherCard;
import presentation.componentController.VoucherSearch;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.StaticFactory;
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
    private MenuButton selectMenu;

    private VoucherSearch voucherSearch;
    private VoucherSearchVo searchVo;
    private VoucherBlService voucherBl;
    private ArrayList<VoucherVo> voucherList;
    private ArrayList<String> voucherIdList;
    private String factoryId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        voucherSearch = new VoucherSearch();
        CustomMenuItem menuItem = new CustomMenuItem(voucherSearch);
        menuItem.setHideOnClick(false);
        selectMenu.getItems().addAll(menuItem);
        selectMenu.setFocusTraversable(false);
        selectMenu.getStylesheets().add("/css/CustomSearch.css");
        voucherSearch.getCancel_btn().setOnAction(event -> {
            selectMenu.hide();
        });

        voucherSearch.getConfirm_btn().addEventHandler(ActionEvent.ACTION, (e) -> {
            setVoucherSearch(StaticFactory.getVoucherSearchVo());
            updateList();
        });


        factoryId = StaticFactory.getUserVO().getCompanyID();
        searchVo = new VoucherSearchVo();
        voucherBl = new VoucherBlImpl();
        voucherIdList = new ArrayList<>();

       /* searchVo=new VoucherSearchVo();
        searchVo.setStartPeriod("2017年第4期");
        searchVo.setEndPeriod("2017年第9期");
        searchVo.setCharacter("全部");
        searchVo.setMaker("全部");
        searchVo.setLowPrice(-1.0);
        searchVo.setHighPrice(-1.0);
        searchVo.setLowVoucherNumber(-1);
        searchVo.setHighVoucherNumber(-1);

        searchVo.setSortOrder(1);
        updateList();*/

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }

    public void setVoucherSearch(VoucherSearchVo searchVo) {
        this.searchVo = searchVo;
    }

    public void updateList() {
        voucher_list.getChildren().clear();
        voucherIdList.clear();
        voucherList = voucherBl.getSearchedVoucher(searchVo, factoryId);

        if (!voucherList.isEmpty()) {
            for (VoucherVo vo: voucherList) {
                voucherIdList.add(vo.getVoucherId());
                VoucherCard voucherCard = new VoucherCard(vo);
                voucher_list.getChildren().add(voucherCard);
            }
        }
    }

    @FXML
    private void OnExport() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("导出");
        File file = fileChooser.showSaveDialog(voucher_list.getScene().getWindow());
        voucherBl.exportToExcel(voucherIdList, file.getAbsolutePath(), factoryId);
    }

    @FXML
    private void OnImport() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("导入");
        File file = fileChooser.showOpenDialog(voucher_list.getScene().getWindow());
        voucherBl.importFromExcel(file.getAbsolutePath(), factoryId);
        updateList();
    }
}

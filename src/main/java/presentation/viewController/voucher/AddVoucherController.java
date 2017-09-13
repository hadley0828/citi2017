package presentation.viewController.voucher;

import businesslogic.VoucherBlImpl;
import businesslogicservice.VoucherBlService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import presentation.dataModel.VoucherModel;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import util.NumberToCN;
import vo.voucher.AmountTotalVo;
import vo.voucher.VoucherAmountVo;
import vo.voucher.VoucherVo;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2017/9/6
 */
public class AddVoucherController implements Initializable, ControlledScreen {
    @FXML
    private TableView<VoucherModel> voucherTable;
    @FXML
    private TableColumn<VoucherModel, String> abstractsCol;
    @FXML
    private TableColumn<VoucherModel, String> subjectCol;
    @FXML
    private TableColumn<VoucherModel, String> debitCol;
    @FXML
    private TableColumn<VoucherModel, String> creditCol;

    @FXML
    private ComboBox<String> type_combo;
    @FXML
    private TextField number_field;
    @FXML
    private DatePicker date_picker;
    @FXML
    private Label maker_label;

    private ScreensController myController;
    private VoucherBlService voucherBl;
    private VoucherVo voucher;
    private ObservableList<VoucherModel> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        voucherBl = new VoucherBlImpl();
        voucher = new VoucherVo();
        type_combo.getItems().addAll("记", "收", "付", "转");
        initialTable();


    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    private void initialTable() {
        abstractsCol.setCellValueFactory(cellData -> cellData.getValue().abstractsProperty());
        subjectCol.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        debitCol.setCellValueFactory(cellData -> cellData.getValue().debitProperty());
        creditCol.setCellValueFactory(cellData -> cellData.getValue().creditProperty());

        data.add(new VoucherModel("提现", "", "1", "2"));
        data.add(new VoucherModel("合计：", "", "", ""));
        voucherTable.setItems(data);
        voucherTable.setEditable(true);

        /*enable cell editing*/
        abstractsCol.setCellFactory(TextFieldTableCell.forTableColumn());
        abstractsCol.setOnEditCommit(
                event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setAbstracts(event.getNewValue())
        );

        subjectCol.setCellFactory(TextFieldTableCell.forTableColumn());
        subjectCol.setOnEditCommit(
                event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setSubject(event.getNewValue())
        );

        debitCol.setCellFactory(TextFieldTableCell.forTableColumn());
        debitCol.setOnEditCommit(
                event -> {
                    event.getTableView().getItems().get(
                            event.getTablePosition().getRow()).setDebit(event.getNewValue());
                    updateSum();
                }
        );

        creditCol.setCellFactory(TextFieldTableCell.forTableColumn());
        creditCol.setOnEditCommit(
                event -> {
                    event.getTableView().getItems().get(
                            event.getTablePosition().getRow()).setCredit(event.getNewValue());
                    updateSum();
                }
        );
    }

    private void updateSum() {
        data.remove(data.size() - 1);

        double sumDebit = 0;
        double sumCredit = 0;
        for (VoucherModel model: data) {
            sumDebit += Double.parseDouble(model.getDebit());
            sumCredit += Double.parseDouble(model.getCredit());
        }

        String resDebit = "";
        if (sumDebit != 0)
            resDebit = String.valueOf(sumDebit);
        String resCredit = "";
        if (sumCredit != 0)
            resCredit = String.valueOf(sumCredit);

        data.add(new VoucherModel("合计：", NumberToCN.number2CNMontrayUnit(sumCredit), resDebit, resCredit));
    }

    @FXML
    private void OnSaveAndAdd() {
        AmountTotalVo amountTotalVo = new AmountTotalVo();
        VoucherModel total = data.get(data.size() - 1);
        amountTotalVo.setChineseTotal(total.getSubject());
        amountTotalVo.setDebitAmount(Double.parseDouble(total.getDebit()));
        amountTotalVo.setCreditAmount(Double.parseDouble(total.getCredit()));
        voucher.setAmountTotalVo(amountTotalVo);

        ArrayList<VoucherAmountVo> amountList = new ArrayList<>();
        for (VoucherModel model: data) {
            VoucherAmountVo vo = new VoucherAmountVo();
            vo.setAmountId("");
            vo.setVoucherId("");
            vo.setAbstracts(model.getAbstracts());
            vo.setSubject(model.getSubject());
            vo.setDebitAmount(Double.parseDouble(model.getDebit()));
            vo.setCreditAmount(Double.parseDouble(model.getCredit()));

            amountList.add(vo);
        }
        amountList.remove(amountList.size() - 1);
        voucher.setAmountList(amountList);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String text = date_picker.getValue().format(formatter);
        voucher.setDate(text);

        voucher.setVoucherId("");
        voucher.setVoucherId(number_field.getText());
        voucher.setAddedReceipts(false);
        voucher.setVoucherMaker(maker_label.getText());
        voucher.setRemark("");

        voucherBl.saveOneVoucher(voucher, "001");
    }

    @FXML
    private void OnAddRow() {
        if (data.size() > 1)
            data.add(data.size() - 2, new VoucherModel("提现", "", "", ""));
        else
            data.add(0, new VoucherModel("提现", "", "", ""));
    }

    @FXML
    private void OnDeleteRow() {
        if (data.size() > 1)
            data.remove(data.size() - 2);
    }

}

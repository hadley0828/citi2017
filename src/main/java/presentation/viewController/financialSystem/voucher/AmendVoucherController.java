package presentation.viewController.financialSystem.voucher;

import businesslogic.VoucherBlImpl;
import businesslogicservice.VoucherBlService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import presentation.StaticFactory;
import presentation.dataModel.VoucherModel;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.screenController.ScreensFramework;
import presentation.warningController.RunWarning;
import util.NumberToCN;
import vo.userManagement.UserVO;
import vo.voucher.AmountTotalVo;
import vo.voucher.SubjectBasicVo;
import vo.voucher.VoucherAmountVo;
import vo.voucher.VoucherVo;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AmendVoucherController implements Initializable, ControlledScreen {
    @FXML
    public TableView<VoucherModel> voucherTable;
    @FXML
    public TableColumn<VoucherModel, String> abstractsCol;
    @FXML
    public TableColumn<VoucherModel, String> subjectCol;
    @FXML
    public TableColumn<VoucherModel, String> debitCol;
    @FXML
    public TableColumn<VoucherModel, String> creditCol;

    @FXML
    public ComboBox<String> type_combo;
    @FXML
    public TextField number_field;
    @FXML
    public DatePicker date_picker;
    @FXML
    public Label maker_label;

    private ScreensController parentController;
    private VoucherBlService voucherBl;
    public VoucherVo voucher;
    public ObservableList<VoucherModel> data = FXCollections.observableArrayList();
    public String factoryId;
    private String amendId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserVO userVO = StaticFactory.getUserVO();
        factoryId = userVO.getCompanyID();
        amendId = StaticFactory.getAmendId();

        voucherBl = new VoucherBlImpl();

        type_combo.getItems().addAll("记", "收", "付", "转");

        maker_label.setText(factoryId);
        initialTable();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        parentController = screenPage;
    }

    public void initialTable() {


        abstractsCol.setCellValueFactory(cellData -> cellData.getValue().abstractsProperty());
        subjectCol.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        debitCol.setCellValueFactory(cellData -> cellData.getValue().debitProperty());
        creditCol.setCellValueFactory(cellData -> cellData.getValue().creditProperty());

        data.add(new VoucherModel("", "", "", ""));
        voucherTable.setEditable(true);

        /*enable cell editing*/
        abstractsCol.setCellFactory(TextFieldTableCell.forTableColumn());
        abstractsCol.setOnEditCommit(
                event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setAbstracts(event.getNewValue())
        );

        ArrayList<SubjectBasicVo> subjectArray = voucherBl.getAllSubjectBasics(StaticFactory.getUserVO().getCompanyID());
        ObservableList<String> subjectChoice = FXCollections.observableArrayList();
        for (SubjectBasicVo vo: subjectArray) {
            subjectChoice.add(vo.getSubjectId() + " " + vo.getSubjectName());
        }

        subjectCol.setCellFactory(ComboBoxTableCell.forTableColumn(subjectChoice));
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

        voucherTable.setItems(data);

        updateTable();
    }

    private void updateTable() {
        data.clear();
        voucher = voucherBl.getOneVoucher(amendId, factoryId);
        String[] strings = voucher.getVoucherId().split("-");
        type_combo.getSelectionModel().select(strings[0]);
        number_field.setText(strings[1]);
        maker_label.setText(voucher.getVoucherMaker());
        String dateStr = voucher.getDate();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate localDate = LocalDate.parse(dateStr, formatter);
        date_picker.setValue(localDate);

        if (voucher != null) {
            for (VoucherAmountVo voucherAmountVo: voucher.getAmountList()) {
                data.add(new VoucherModel(voucherAmountVo.getAbstracts(), voucherAmountVo.getSubject(), String.valueOf(voucherAmountVo.getDebitAmount()), String.valueOf(voucherAmountVo.getCreditAmount())));
            }
            AmountTotalVo tvo = voucher.getAmountTotalVo();
            data.add(new VoucherModel("合计： ", tvo.getChineseTotal(), String.valueOf(tvo.getDebitAmount()), String.valueOf(tvo.getCreditAmount())));
        }
        voucherTable.setItems(data);
        voucherTable.refresh();
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
    public void OnSave() {
        ObservableList<VoucherModel> aid_data = voucherTable.getItems();

        AmountTotalVo amountTotalVo = new AmountTotalVo();
        VoucherModel total = data.get(data.size() - 1);
        amountTotalVo.setChineseTotal(total.getSubject());
        amountTotalVo.setDebitAmount(Double.parseDouble(total.getDebit()));
        amountTotalVo.setCreditAmount(Double.parseDouble(total.getCredit()));
        voucher.setAmountTotalVo(amountTotalVo);

        String voucherId = type_combo.getSelectionModel().getSelectedItem() + "-" + number_field.getText().trim();

        ArrayList<VoucherAmountVo> amountList = new ArrayList<>();
        for (VoucherModel model: data) {
            VoucherAmountVo vo = new VoucherAmountVo();
            vo.setVoucherId(voucherId);
            vo.setAbstracts(model.getAbstracts());
            vo.setSubject(model.getSubject().split(" ")[0]);
            vo.setDebitAmount(Double.parseDouble(model.getDebit()));
            vo.setCreditAmount(Double.parseDouble(model.getCredit()));

            amountList.add(vo);
        }
        amountList.remove(amountList.size() - 1);
        voucher.setAmountList(amountList);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String text = date_picker.getValue().format(formatter);
        voucher.setDate(text);

        voucher.setVoucherId(voucherId);
        voucher.setAddedReceipts(false);
        voucher.setVoucherMaker(maker_label.getText());
        voucher.setRemark("");

        boolean a = voucherBl.amendOneVoucher(voucherId, voucher, factoryId);
        if(a) {
            RunWarning warning = new RunWarning();
            warning.SetWarning("保存成功！");
            warning.start(new Stage());
        }
    }

    @FXML
    private void OnAddRow() {
        if (data.size() > 1)
            data.add(data.size() - 2, new VoucherModel("", "", "", ""));
        else
            data.add(0, new VoucherModel("", "", "", ""));
    }

    @FXML
    private void OnDeleteRow() {
        if (data.size() > 1)
            data.remove(data.size() - 2);
    }

    @FXML
    private void OnDelete() {
        voucherBl.deleteOneVoucher(amendId, factoryId);
        OnCancel();
    }

    @FXML
    private void OnCancel() {
        data.clear();
//        parentController.unloadScreen(ScreensFramework.INQUIRE_VOUCHER_SCREEN);
//        parentController.loadScreen(ScreensFramework.INQUIRE_VOUCHER_SCREEN, ScreensFramework.INQUIRE_VOUCHER_SCREEN_FXML);
        parentController.setScreen(ScreensFramework.INQUIRE_VOUCHER_SCREEN);
    }
}

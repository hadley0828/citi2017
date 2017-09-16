package presentation.viewController.financialSystem.voucher;

import businesslogic.CreditItemImpl;
import businesslogic.SettingImpl;
import businesslogic.UserManagementImpl;
import businesslogic.VoucherBlImpl;
import businesslogicservice.CreditItemService;
import businesslogicservice.SettingService;
import businesslogicservice.UserManagementService;
import businesslogicservice.VoucherBlService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import presentation.dataModel.VoucherModel;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.StaticFactory;
import util.NumberToCN;
import vo.CreditItemVo;
import vo.userManagement.AccountSetVO;
import vo.userManagement.SubjectsVO;
import vo.userManagement.UserVO;
import vo.voucher.AmountTotalVo;
import vo.voucher.SubjectBasicVo;
import vo.voucher.VoucherAmountVo;
import vo.voucher.VoucherVo;

import java.io.IOException;
import java.net.URL;
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
    @FXML
    private Button aid_btn;

    private ScreensController myController;
    private VoucherBlService voucherBl;
    private VoucherVo voucher;
    private ObservableList<VoucherModel> data = FXCollections.observableArrayList();
    private CreditItemService creditItemService = new CreditItemImpl();
    private String factoryId;
    private String jugder;
    private boolean bool;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        aid_btn.setVisible(false);
        UserVO userVO = StaticFactory.getUserVO();
        factoryId = userVO.getCompanyID();
        
        voucherBl = new VoucherBlImpl();
        voucher = new VoucherVo();
        type_combo.getItems().addAll("记", "收", "付", "转");
        type_combo.setEditable(false);

        initialTable();

        maker_label.setText(factoryId);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    private void initialTable() {
        type_combo.getSelectionModel().selectFirst();
        abstractsCol.setCellValueFactory(cellData -> cellData.getValue().abstractsProperty());
        subjectCol.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        debitCol.setCellValueFactory(cellData -> cellData.getValue().debitProperty());
        creditCol.setCellValueFactory(cellData -> cellData.getValue().creditProperty());

        data.add(new VoucherModel("合计：", "", "", ""));
        voucherTable.setItems(data);
        voucherTable.setEditable(true);

        /*enable cell editing*/
        abstractsCol.setCellFactory(TextFieldTableCell.forTableColumn());
        abstractsCol.setOnEditCommit(
                event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setAbstracts(event.getNewValue())
        );

        SettingService settingService = new SettingImpl();
        ArrayList<SubjectsVO> subjectArray = settingService.getAllSubjects();
        ObservableList<String> subjectChoice = FXCollections.observableArrayList();
        for (SubjectsVO vo: subjectArray) {
            subjectChoice.add(vo.getSubjectsID() + " " + vo.getSubjectsName());
        }

        subjectCol.setCellFactory(ComboBoxTableCell.forTableColumn(subjectChoice));
        subjectCol.setOnEditCommit(
                event -> {
                    event.getTableView().getItems().get(event.getTablePosition().getRow()).setSubject(event.getNewValue());
                    jugder = event.getNewValue().split(" ")[1];
                    if (jugder.equals("材料采购") || jugder.equals("在途物资") || jugder.equals("原材料") || jugder.equals("库存商品")
                            || jugder.equals("委托加工物资、工程物资") || jugder.equals("工程物资") || jugder.equals("应付账款") || jugder.equals("应收账款")) {
                        aid_btn.setVisible(true);
                        OnAid();
                        bool = true;
                    }else {
                        bool = false;
                        aid_btn.setVisible(false);
                        OnAid();
                    }
                }
        );

        debitCol.setCellFactory(TextFieldTableCell.forTableColumn());
        debitCol.setOnEditCommit(
                event -> {
                    event.getTableView().getItems().get(
                            event.getTablePosition().getRow()).setDebit(event.getNewValue());
                    updateSum();
                }
        );
        debitCol.setCellFactory(new Callback<TableColumn<VoucherModel, String>, TableCell<VoucherModel, String>>() {
            @Override
            public TableCell<VoucherModel, String> call(TableColumn<VoucherModel, String> param) {
                return null;
            }
        });

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

        boolean a = voucherBl.saveOneVoucher(voucher, factoryId);
        if(a) {
            System.out.println("yeah");
        }

        if (bool) {
            ArrayList<CreditItemVo> arrayList = StaticFactory.getAidVos();
            creditItemService.SaveCreditItem(arrayList, factoryId, type_combo.getSelectionModel().getSelectedItem() + "-" + number_field.getText());
        }
        Reset();
    }

    @FXML
    private void OnAddRow() {
        if (data.size() > 1)
            data.add(data.size() - 2, new VoucherModel("提现", "", "", ""));
        else
            data.add(0, new VoucherModel("提现", "", "", ""));

        updateSum();
    }

    @FXML
    private void OnDeleteRow() {
        if (data.size() > 1)
            data.remove(data.size() - 2);

        updateSum();
    }

    @FXML
    private void OnAid() {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);

        if (jugder.equals("应收账款")){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../../../../view/financialSystem/voucher/AidCharge.fxml"));
                Scene scene = new Scene(root);
                dialog.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
            dialog.show();
        } else if (jugder.equals("应付账款")){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../../../../view/financialSystem/voucher/AidPay.fxml"));
                Scene scene = new Scene(root);
                dialog.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
            dialog.show();
        } else if (jugder.equals("材料采购") || jugder.equals("在途物资") || jugder.equals("原材料") || jugder.equals("库存商品") || jugder.equals("委托加工物资") || jugder.equals("工程物资")) {
            String compayId = StaticFactory.getUserVO().getUserID();
            UserManagementService userManagementService = new UserManagementImpl();
            AccountSetVO accountSetVO = userManagementService.getAccountSetByCompanyID(compayId);
            String str = accountSetVO.getChainPlace();
            if (str.equals("供应商")) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../../../../view/financialSystem/stockInfo/SupplierInfo.fxml"));
                    Scene scene = new Scene(root);
                    dialog.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dialog.show();
            } else if (str.equals("生产商")) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../../../../view/financialSystem/stockInfo/ProducerInfo.fxml"));
                    Scene scene = new Scene(root);
                    dialog.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dialog.show();
            } else if (str.equals("分销商")) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../../../../view/financialSystem/stockInfo/DistributionInfo.fxml"));
                    Scene scene = new Scene(root);
                    dialog.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dialog.show();
            }

        }
    }

    private void Reset() {
        number_field.setText("");
        data.clear();
        data.add(new VoucherModel("合计：", "", "0", "0"));
        date_picker.setValue(null);

    }
}

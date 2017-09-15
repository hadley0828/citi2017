package presentation.viewController.financialSystem.voucher;

import businesslogic.UserManagementImpl;
import businesslogic.VoucherBlImpl;
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
import presentation.dataModel.VoucherModel;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.StaticFactory;
import util.NumberToCN;
import vo.userManagement.AccountSetVO;
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
    private String factoryId;
    private String judger;
        
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        aid_btn.setVisible(false);
        UserVO userVO = StaticFactory.getUserVO();
        factoryId = userVO.getCompanyID();
        
        voucherBl = new VoucherBlImpl();
        voucher = new VoucherVo();
        type_combo.getItems().addAll("记", "收", "付", "转");
        initialTable();

        maker_label.setText(factoryId);
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

        data.add(new VoucherModel("", "", "", ""));
        data.add(new VoucherModel("合计：", "", "", ""));
        voucherTable.setItems(data);
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
                event -> {
                    event.getTableView().getItems().get(event.getTablePosition().getRow()).setSubject(event.getNewValue());
                    judger = event.getNewValue().split(" ")[1];
                    if (judger.equals("材料采购") || judger.equals("在途物资") || judger.equals("原材料") || judger.equals("库存商品")
                            || judger.equals("委托加工物资、工程物资") || judger.equals("工程物资") || judger.equals("应付账款") || judger.equals("应收账款")) {
                        aid_btn.setVisible(true);
                    }else
                        aid_btn.setVisible(false);
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

    @FXML
    private void OnAid() {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);

        if (judger.equals("应收账款")){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../../../../view/financialSystem/voucher/AidCharge.fxml"));
                Scene scene = new Scene(root);
                dialog.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
            dialog.show();
        } else if (judger.equals("应付账款")){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../../../../view/financialSystem/voucher/AidPay.fxml"));
                Scene scene = new Scene(root);
                dialog.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
            dialog.show();
        } else if (judger.equals("材料采购") || judger.equals("在途物资") || judger.equals("原材料") || judger.equals("库存商品") || judger.equals("委托加工物资") || judger.equals("工程物资")) {
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
                    Parent root = FXMLLoader.load(getClass().getResource("../../../../git view/financialSystem/stockInfo/DistributionInfo.fxml"));
                    Scene scene = new Scene(root);
                    dialog.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dialog.show();
            }

        }
    }

}

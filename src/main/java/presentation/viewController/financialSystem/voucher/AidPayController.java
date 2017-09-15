package presentation.viewController.financialSystem.voucher;

import businesslogic.CreditItemImpl;
import businesslogicservice.CreditItemService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import presentation.StaticFactory;
import presentation.dataModel.DealStateModel;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import vo.CreditItemVo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AidPayController implements Initializable, ControlledScreen {
    @FXML
    private TableView<DealStateModel> table;
    @FXML
    private TableColumn<DealStateModel, String> name_col;
    @FXML
    private TableColumn<DealStateModel, String> debit_col;
    @FXML
    private TableColumn<DealStateModel, String> credit_col;
    @FXML
    private TableColumn<DealStateModel, String> money_col;
    @FXML
    private TableColumn<DealStateModel, String> discount_col;
    @FXML
    private TableColumn<DealStateModel, String> term_col;
    @FXML
    private TableColumn<DealStateModel, String> remark_col;


    private ObservableList<DealStateModel> data = FXCollections.observableArrayList();
    private CreditItemService creditItemService = new CreditItemImpl();
    private String company_id;
    private String voucher_id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        voucher_id = StaticFactory.getAmendId();
        company_id = StaticFactory.getUserVO().getCompanyID();

        table.setItems(data);
        table.setEditable(true);
        name_col.setCellValueFactory(cellValue -> cellValue.getValue().nameProperty());
        debit_col.setCellValueFactory(cellValue -> cellValue.getValue().debitTimeProperty());
        credit_col.setCellValueFactory(cellValue -> cellValue.getValue().creditTermProperty());
        money_col.setCellValueFactory(cellValue -> cellValue.getValue().moneyProperty());
        discount_col.setCellValueFactory(cellValue -> cellValue.getValue().discountProperty());
        term_col.setCellValueFactory(cellValue -> cellValue.getValue().discountTermProperty());
        remark_col.setCellValueFactory(cellValue -> cellValue.getValue().remarkProperty());

        company_id = StaticFactory.getUserVO().getCompanyID();

        /*enable editable*/

        name_col.setCellFactory(TextFieldTableCell.forTableColumn());
        name_col.setOnEditCommit(
                event -> event.getTableView().getItems().get(
                        event.getTablePosition().getRow()).setName(event.getNewValue())
        );

        debit_col.setCellFactory(TextFieldTableCell.forTableColumn());
        debit_col.setOnEditCommit(
                event -> event.getTableView().getItems().get(
                        event.getTablePosition().getRow()).setDebitTime(event.getNewValue())
        );

        credit_col.setCellFactory(TextFieldTableCell.forTableColumn());
        credit_col.setOnEditCommit(
                event -> event.getTableView().getItems().get(
                        event.getTablePosition().getRow()).setCreditTerm(event.getNewValue())
        );

        money_col.setCellFactory(TextFieldTableCell.forTableColumn());
        money_col.setOnEditCommit(
                event -> event.getTableView().getItems().get(
                        event.getTablePosition().getRow()).setMoney(event.getNewValue())
        );

        discount_col.setCellFactory(TextFieldTableCell.forTableColumn());
        discount_col.setOnEditCommit(
                event -> event.getTableView().getItems().get(
                        event.getTablePosition().getRow()).setDiscount(event.getNewValue())
        );

        term_col.setCellFactory(TextFieldTableCell.forTableColumn());
        term_col.setOnEditCommit(
                event -> event.getTableView().getItems().get(
                        event.getTablePosition().getRow()).setDiscountTerm(event.getNewValue())
        );

        remark_col.setCellFactory(TextFieldTableCell.forTableColumn());
        remark_col.setOnEditCommit(
                event -> event.getTableView().getItems().get(
                        event.getTablePosition().getRow()).setRemark(event.getNewValue())
        );

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }

    @FXML
    private void OnSave() {
        ArrayList<CreditItemVo> list = new ArrayList<>();

        for (DealStateModel model: data) {
            list.add(new CreditItemVo(model.getName(), model.getDebitTime(), model.getCreditTerm(), Double.parseDouble(model.getMoney()), Double.parseDouble(model.getDiscount()), model.getDiscountTerm(), model.getRemark()));
        }

//        creditItemService.SaveCreditItem(list, company_id, voucher_id);
        StaticFactory.setAidVos(list);
        table.getScene().getWindow().hide();
    }

    @FXML
    private void OnCancel() {
        table.getScene().getWindow().hide();
    }

    @FXML
    private void OnAddRow() {
        if (data.size() > 1)
            data.add(data.size() - 2, new DealStateModel("应付账款-", "", "", "", "", "", ""));
        else
            data.add(0, new DealStateModel("应付账款-", "", "", "", "", "", ""));
    }

    @FXML
    private void OnDeleteRow() {
        if (data.size() > 1)
            data.remove(data.size() - 2);
    }
}

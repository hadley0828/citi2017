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
    private String company_id;
    private String voucher_id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setItems(data);
        name_col.setCellValueFactory(cellValue -> cellValue.getValue().nameProperty());
        debit_col.setCellValueFactory(cellValue -> cellValue.getValue().debitTimeProperty());
        credit_col.setCellValueFactory(cellValue -> cellValue.getValue().creditTermProperty());
        money_col.setCellValueFactory(cellValue -> cellValue.getValue().moneyProperty());
        discount_col.setCellValueFactory(cellValue -> cellValue.getValue().discountProperty());
        term_col.setCellValueFactory(cellValue -> cellValue.getValue().discountTermProperty());
        remark_col.setCellValueFactory(cellValue -> cellValue.getValue().remarkProperty());

        company_id = StaticFactory.getUserVO().getCompanyID();

//        name_col.setCellFactory(TextFieldTableCell.forTableColumn());
//        name_col.setOnEditCommit(
//                event -> {
//                    event.getTableView().getItems().get(
//                            event.getTablePosition().getRow()).setCredit(event.getNewValue());
//                    updateSum();
//                }
//        );

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }

    @FXML
    private void OnSave() {

    }

    @FXML
    private void OnCancel() {

    }

    @FXML
    private void OnAddRow() {
        if (data.size() > 1)
            data.add(data.size() - 2, new DealStateModel("", "", "", "", "", "", ""));
        else
            data.add(0, new DealStateModel("", "", "", "", "", "", ""));
    }

    @FXML
    private void OnDeleteRow() {
        if (data.size() > 1)
            data.remove(data.size() - 2);
    }
}

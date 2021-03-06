package presentation.viewController.supplyChainManagement.cashManagement;

import businesslogic.CreditItemImpl;
import businesslogicservice.CreditItemService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.dataModel.DealStateModel;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.StaticFactory;
import vo.CreditItemVo;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2017/9/13
 */
public class ChargeStateController implements Initializable, ControlledScreen {
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


    private CreditItemService creditItemService;
    private ObservableList<DealStateModel> data = FXCollections.observableArrayList();
    private ArrayList<CreditItemVo> itemVoArrayList;
    private String company_id;

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

        creditItemService = new CreditItemImpl();
        company_id = StaticFactory.getUserVO().getCompanyID();
        data.add(new DealStateModel("","","","","","",""));

        for (String str: creditItemService.getOutputCredit(company_id)) {
            itemVoArrayList = creditItemService.getReceivables(company_id, str);
            for (CreditItemVo vo: itemVoArrayList) {
                data.add(new DealStateModel(vo.getCompany_name(), vo.getBorrow_time(), vo.getDeadline(), String.valueOf(vo.getPolicy() * 100) + "%", String.valueOf(vo.getMoney()), vo.getDiscount_deadline(), vo.getRemark()));
            }
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

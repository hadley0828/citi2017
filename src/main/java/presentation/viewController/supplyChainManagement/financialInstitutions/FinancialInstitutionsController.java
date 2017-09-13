package presentation.viewController.supplyChainManagement.financialInstitutions;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Created by YZ on 2017/9/10.
 */
public class FinancialInstitutionsController {
    @FXML
    private TextField company_search;
    @FXML
    private TableColumn company_name;
    @FXML
    private TableColumn financing_style;
    @FXML
    private TableColumn money_debt;
    @FXML
    private TableColumn raw_voucher;
    @FXML
    private TableColumn advice_size;
    @FXML
    private TableColumn stylesheet;
    @FXML
    private TableView financing_table;

    @FXML
    public void initialize(){

    }
}

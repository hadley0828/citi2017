package presentation.viewController.PerformanceAppraisal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by YZ on 2017/9/9.
 */
public class PerformanceAppraisalController {
    @FXML
    private TableView gain_table;
    @FXML
    private TableView gain_table_below;
    @FXML
    private TableView op_table;
    @FXML
    private TableView op_table_below;
    @FXML
    private TableView develop_table;
    @FXML
    private TableView develop_table_below;
    @FXML
    private TableView debtpay_table;
    @FXML
    private TableView debtpay_table_below;
    @FXML
    private TableColumn gain_enterprise;
    @FXML
    private TableColumn gain_sumPayback;
    @FXML
    private TableColumn gain_benefit;
    @FXML
    private TableColumn gain_chain;
    @FXML
    private TableColumn gain;
    @FXML
    private TableColumn op_enterprise;
    @FXML
    private TableColumn op_sum;
    @FXML
    private TableColumn op_stock;
    @FXML
    private TableColumn op_ontime;
    @FXML
    private TableColumn op_back;
    @FXML
    private TableColumn op;
    @FXML
    private TableColumn op_coin;
    @FXML
    private TableColumn develop_enterprise;
    @FXML
    private TableColumn develop_increase;
    @FXML
    private TableColumn develop_profit;
    @FXML
    private TableColumn develop;
    @FXML
    private TableColumn develop_profit_increase;
    @FXML
    private TableColumn debtpay_enterprise;
    @FXML
    private TableColumn debtpay_debt;
    @FXML
    private TableColumn debtpay;
    @FXML
    private TableColumn debtpay_balance;

    @FXML
    public void initialize(){
        setGain();
        setOp();
        setDevelop();
        setDebtpay();
    }

    public void setGain(){

    }

    public void setOp(){

    }

    public void setDevelop(){

    }

    public void setDebtpay(){

    }

}

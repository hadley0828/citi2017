package presentation.viewController.supplyChainManagement.performanceAppraisal;

import businesslogic.SupplyChainImpl;
import businesslogicservice.SupplyChainService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import presentation.StaticFactory;
import vo.SupplyChainPerformanceVo;

import java.util.ArrayList;

/**
 * Created by YZ on 2017/9/9.
 */
public class PerformanceAppraisalController {
    @FXML
    private TableView gain_table;
    @FXML
    private TableView op_table;
    @FXML
    private TableView develop_table;
    @FXML
    private TableView debtpay_table;
    @FXML
    private TableColumn gain_enterprise;
    @FXML
    private TableColumn gain_sumPayback;
    @FXML
    private TableColumn gain_benefit;
    @FXML
    private TextField gain_chain;
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
    private TextField op_coin;
    @FXML
    private TableColumn develop_enterprise;
    @FXML
    private TableColumn develop_increase;
    @FXML
    private TableColumn develop_profit;
    @FXML
    private TextField develop_profit_increase;
    @FXML
    private TableColumn debtpay_enterprise;
    @FXML
    private TableColumn debtpay_debt;
    @FXML
    private TextField debtpay_balance;

    private SupplyChainService service=new SupplyChainImpl();
    String sid=service.getTheCompanys(StaticFactory.getUserVO().getCompanyID())[0];
    String mid=service.getTheCompanys(StaticFactory.getUserVO().getCompanyID())[1];
    String did=service.getTheCompanys(StaticFactory.getUserVO().getCompanyID())[2];
    SupplyChainPerformanceVo vo=service.SupplyChain_Supplier(sid,mid,did, StaticFactory.getMonth());
    @FXML
    public void initialize(){
        setGain();
        setOp();
        setDevelop();
        setDebtpay();
    }

    public void setGain(){
        ArrayList<ProfitAbilityVO> profitAbilityVOS=new ArrayList<ProfitAbilityVO>();
        profitAbilityVOS.add(new ProfitAbilityVO("供应商",vo.getSupplier()[0][0],vo.getSupplier()[0][1]));
        profitAbilityVOS.add(new ProfitAbilityVO("生产商",vo.getManufacturer()[0][0],vo.getManufacturer()[0][1]));
        profitAbilityVOS.add(new ProfitAbilityVO("分销商",vo.getDistributor()[0][0],vo.getDistributor()[0][1]));

        ObservableList<ProfitAbilityVO> p_list= FXCollections.observableArrayList(profitAbilityVOS);

        gain_enterprise.setCellValueFactory(new PropertyValueFactory("enterprise"));
        gain_sumPayback.setCellValueFactory(new PropertyValueFactory("property_pay"));
        gain_benefit.setCellValueFactory(new PropertyValueFactory("market_purify"));

        gain_table.setItems(p_list);

        gain_chain.setText(vo.getSupply_chain()[0]+"");
    }

    public void setOp(){
        //准时交货率和退货率有问题
        ArrayList<OperationVO> operationVOS=new ArrayList<OperationVO>();
        operationVOS.add(new OperationVO("供应商",vo.getSupplier()[1][0],vo.getSupplier()[1][1],vo.getSupplier()[1][0],vo.getSupplier()[1][1]));
        operationVOS.add(new OperationVO("生产商",vo.getManufacturer()[1][0],vo.getManufacturer()[1][1],vo.getManufacturer()[1][0],vo.getManufacturer()[1][1]));
        operationVOS.add(new OperationVO("分销商",vo.getDistributor()[1][0],vo.getDistributor()[1][1],vo.getDistributor()[1][0],vo.getDistributor()[1][1]));

        ObservableList<OperationVO> o_list=FXCollections.observableList(operationVOS);

        op_enterprise.setCellValueFactory(new PropertyValueFactory("enterprise"));
        op_sum.setCellValueFactory(new PropertyValueFactory("property_rotate"));
        op_stock.setCellValueFactory(new PropertyValueFactory("stock_rotate"));
        op_ontime.setCellValueFactory(new PropertyValueFactory("ontime"));
        op_back.setCellValueFactory(new PropertyValueFactory("back"));

        op_table.setItems(o_list);

    }

    public void setDevelop(){
        ArrayList<DevelopVO> developVOS=new ArrayList<DevelopVO>();
        developVOS.add(new DevelopVO("供应商",vo.getSupplier()[2][0],vo.getSupplier()[2][1]));
        developVOS.add(new DevelopVO("生产商",vo.getManufacturer()[2][0],vo.getManufacturer()[2][1]));
        developVOS.add(new DevelopVO("分销商",vo.getDistributor()[2][0],vo.getDistributor()[2][1]));

        ObservableList<DevelopVO> d_list=FXCollections.observableList(developVOS);

        develop_enterprise.setCellValueFactory(new PropertyValueFactory("enterprise"));
        develop_increase.setCellValueFactory(new PropertyValueFactory("market_increase"));
        develop_profit.setCellValueFactory(new PropertyValueFactory("profit_increase"));

        develop_table.setItems(d_list);

    }

    public void setDebtpay(){
        ArrayList<PayDebtVO> payDebtVOS=new ArrayList<PayDebtVO>();
        payDebtVOS.add(new PayDebtVO("供应商",vo.getSupplier()[3][0]));
        payDebtVOS.add(new PayDebtVO("生产商",vo.getManufacturer()[3][0]));
        payDebtVOS.add(new PayDebtVO("分销商",vo.getDistributor()[3][0]));

        ObservableList<PayDebtVO> pay_list=FXCollections.observableList(payDebtVOS);

        debtpay_enterprise.setCellValueFactory(new PropertyValueFactory("enterprise"));
        debtpay_debt.setCellValueFactory(new PropertyValueFactory("balance"));

        debtpay_table.setItems(pay_list);
    }

}

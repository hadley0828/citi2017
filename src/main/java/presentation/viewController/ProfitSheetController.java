package presentation.viewController;

import businesslogic.ProfitTableImpl;
import businesslogicservice.ProfitTableService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import presentation.componentController.Datebar;
import vo.BalanceSheetItemVo;
import vo.ProfitTableVo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by YZ on 2017/9/4.
 */
public class ProfitSheetController {
    @FXML
    private Button print;
    @FXML
    private Button out;
    @FXML
    private Datebar bar;
    @FXML
    private TableColumn project_item;
    @FXML
    private TableColumn l_item;
    @FXML
    private TableColumn yearcul_item;
    @FXML
    private TableColumn month_item;
    @FXML
    private TableView profitTable;

    private ProfitTableService profitTableService=new ProfitTableImpl();
    private ProfitTableVo period;
    private ProfitTableVo year;
    @FXML
    public void initialize(){
        setProfitTable();

    }

    public void setProfitTable(){
        period=profitTableService.BusinessIncome_period(bar.getDate());
        year=profitTableService.BusinessIncome_year(bar.getDate());

        ArrayList<ProfitVO> profitTable_data=new ArrayList<ProfitVO>();
        profitTable_data.add(new ProfitVO("一、营业收入",1,year.getBusiness_income(),period.getBusiness_income()));
        profitTable_data.add(new ProfitVO("减：营业成本",2,year.getBusiness_costs(),period.getBusiness_costs()));
        profitTable_data.add(new ProfitVO("营业税金及附加",3,year.getBusiness_Taxes_and_Surcharges()[0],period.getBusiness_Taxes_and_Surcharges()[0]));
        profitTable_data.add(new ProfitVO("销售费用",11,year.getSelling_expenses()[0],period.getSelling_expenses()[0]));
        profitTable_data.add(new ProfitVO("管理费用",14,year.getManagement_expenses()[0],period.getManagement_expenses()[0]));
        profitTable_data.add(new ProfitVO("财务费用",18,year.getFinancial_expenses()[0],period.getFinancial_expenses()[0]));
        profitTable_data.add(new ProfitVO("加：投资收益（亏损以'-'号填列）",20,year.getInvestment_proceeds(),period.getInvestment_proceeds()));
        profitTable_data.add(new ProfitVO("二、营业利润（亏损以'-'号填列）",21,year.getOperating_profit(),period.getOperating_profit()));
        profitTable_data.add(new ProfitVO("加：营业外收入",22,year.getNon_operating_income()[0],period.getNon_operating_income()[0]));
        profitTable_data.add(new ProfitVO("减：营业外支出",24,year.getNon_operating_expenses()[0],period.getNon_operating_expenses()[0]));
        profitTable_data.add(new ProfitVO("三、利润总额（亏损总额以'-'号填列）",30,year.getTotal_profit(),period.getTotal_profit()));
        profitTable_data.add(new ProfitVO("减：所得税费用",31,year.getIncome_tax_expense(),period.getIncome_tax_expense()));
        profitTable_data.add(new ProfitVO("四、净利润（净亏损以'-'号填列）",32,year.getNet_profit(),period.getNet_profit()));

        ObservableList<ProfitVO> p_list= FXCollections.observableArrayList();

        project_item.setCellValueFactory(new PropertyValueFactory("project"));
        l_item.setCellValueFactory(new PropertyValueFactory("line_no"));
        yearcul_item.setCellValueFactory(new PropertyValueFactory("year"));
        month_item.setCellValueFactory(new PropertyValueFactory("period"));

        for(int i=0;i<profitTable_data.size();i++){
//            System.out.print(profitTable_data.get(i).getLine_No());
            p_list.add(profitTable_data.get(i));
        }
        profitTable.setItems(p_list);
    }

}

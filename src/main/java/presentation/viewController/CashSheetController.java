package presentation.viewController;

import businesslogic.CashFlowImpl;
import businesslogicservice.CashFlowTableService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import presentation.componentController.Datebar;
import vo.CashFlowVo;

import java.util.ArrayList;

/**
 * Created by YZ on 2017/9/5.
 */
public class CashSheetController {
    @FXML
    private Datebar bar;
    @FXML
    private Button print;
    @FXML
    private Button out;
    @FXML
    private TableColumn project_item1;
    @FXML
    private TableColumn line_item1;
    @FXML
    private TableColumn yearcul_item1;
    @FXML
    private TableColumn month_item1;
    @FXML
    private TableView CashTable;

    @FXML
    public void initialize(){
        setCashTable();
    }

    private CashFlowTableService cashService=new CashFlowImpl();
    private CashFlowVo period;
    private CashFlowVo year;

    public void setCashTable(){
        period=cashService.CashFlowTable_month(bar.getDate());
        year=cashService.CashFlowTable_year(bar.getDate());
/**
 *
 * @author hyf
 * operating_activities    经营活动产生的现金流量
 * Investment_activities   投资活动产生的现金流量
 * Financing_activities    筹资活动产生的现金流量
 * Net_cash_increase       现金净增加额
 * Final_cash_balance      期末现金余额
 */
        ArrayList<CashVO> cash_data=new ArrayList<CashVO>();
        cash_data.add(new CashVO("一、经营活动产生的现金流量：",0,0,0));
        cash_data.add(new CashVO("销售产成品、商品、提供劳务收到的现金",1,year.getOperating_activities()[0],period.getOperating_activities()[0]));
        cash_data.add(new CashVO("收到其他与经营活动有关的现金",2,year.getOperating_activities()[1],period.getOperating_activities()[1]));
        cash_data.add(new CashVO("购买原材料、商品、接受劳务支付的现金",3,year.getOperating_activities()[2],period.getOperating_activities()[2]));
        cash_data.add(new CashVO("支付的职工薪酬",4,year.getOperating_activities()[3],period.getOperating_activities()[3]));
        cash_data.add(new CashVO("支付的税费",5,year.getOperating_activities()[4],period.getOperating_activities()[4]));
        cash_data.add(new CashVO("支付其他与经营活动有关的现金",6,year.getOperating_activities()[5],period.getOperating_activities()[5]));
        cash_data.add(new CashVO("经营活动产生的现金流量净额",7,year.getOperating_activities()[6],period.getOperating_activities()[6]));
        cash_data.add(new CashVO("二、投资活动产生的现金流量：",0,0,0));
        cash_data.add(new CashVO("收回短期投资、长期债券投资和长期股权投资收到的现金",8,year.getInvestment_activities()[0],period.getInvestment_activities()[0]));
        cash_data.add(new CashVO("取得投资收益收到的现金",9,year.getInvestment_activities()[1],period.getInvestment_activities()[1]));
        cash_data.add(new CashVO("处置固定资产、无形资产和其他非流动资产收回的现金净额",10,year.getInvestment_activities()[2],period.getInvestment_activities()[2]));
        cash_data.add(new CashVO("短期投资、长期债券投资和长期股权投资支付的现金",11,year.getInvestment_activities()[3],period.getInvestment_activities()[3]));
        cash_data.add(new CashVO("购建固定资产、无形资产和其他非流动资产支付的现金",12,year.getInvestment_activities()[4],period.getInvestment_activities()[4]));
        cash_data.add(new CashVO("投资活动产生的现金流量净额",13,year.getInvestment_activities()[5],period.getInvestment_activities()[5]));
        cash_data.add(new CashVO("三、筹资活动产生的现金流量：",0,0,0));
        cash_data.add(new CashVO("取得借款收到的现金",14,year.getFinancing_activities()[0],period.getFinancing_activities()[0]));
        cash_data.add(new CashVO("吸收投资者投资收到的现金",15,year.getFinancing_activities()[1],period.getFinancing_activities()[1]));
        cash_data.add(new CashVO("偿还借款本金支付的现金",16,year.getFinancing_activities()[2],period.getFinancing_activities()[2]));
        cash_data.add(new CashVO("偿还借款利息支付的现金",17,year.getFinancing_activities()[3],period.getFinancing_activities()[3]));
        cash_data.add(new CashVO("分配利润支付的现金",18,year.getFinancing_activities()[4],period.getFinancing_activities()[4]));
        cash_data.add(new CashVO("筹资活动产生的现金流量净额",19,year.getFinancing_activities()[5],period.getFinancing_activities()[5]));
        cash_data.add(new CashVO("四、现金净增加额",20,year.getNet_cash_increase()[0],period.getNet_cash_increase()[0]));
        cash_data.add(new CashVO("加：期初现金余额",21,year.getNet_cash_increase()[1],period.getNet_cash_increase()[1]));
        cash_data.add(new CashVO("五、期末现金余额",22,year.getFinal_cash_balance(),period.getFinal_cash_balance()));

        ObservableList<CashVO> c_list= FXCollections.observableArrayList();

        project_item1.setCellValueFactory(new PropertyValueFactory("project"));
        line_item1.setCellValueFactory(new PropertyValueFactory("line_no"));
        yearcul_item1.setCellValueFactory(new PropertyValueFactory("year"));
        month_item1.setCellValueFactory(new PropertyValueFactory("period"));

        for(int i=0;i<cash_data.size();i++){
            c_list.add(cash_data.get(i));
        }

        CashTable.setItems(c_list);
    }

}

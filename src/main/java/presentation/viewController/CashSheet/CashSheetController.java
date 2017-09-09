package presentation.viewController.CashSheet;

import businesslogic.CashFlowImpl;
import businesslogicservice.CashFlowTableService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
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
        setCell();
        setTab();
    }

    public void setTab(){
        bar.getLast().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                if(bar.getMidMonths().indexOf(bar.getDate())>0) {
                    bar.getYL().setText(bar.getMidMonths().get(bar.getMidMonths().indexOf(bar.getDate()) - 1).substring(0,4));
                    bar.getML().setText(bar.getMidMonths().get(bar.getMidMonths().indexOf(bar.getDate()) - 1).split("-")[1]);
                    bar.changePro();
                }
                setCashTable();
            }
        });
        bar.getLater().setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                if(bar.getMidMonths().indexOf(bar.getDate())<bar.getMidMonths().size()-1) {
                    bar.getYL().setText(bar.getMidMonths().get(bar.getMidMonths().indexOf(bar.getDate()) + 1).substring(0,4));
                    bar.getML().setText(bar.getMidMonths().get(bar.getMidMonths().indexOf(bar.getDate()) + 1).split("-")[1]);
                    bar.changePro();
                }
                setCashTable();
            }
        });
    }
    private CashFlowTableService cashService=new CashFlowImpl();
    private CashFlowVo period;
    private CashFlowVo year;

    public void setCashTable(){
        period=cashService.CashFlowTable_month(bar.getDate(),"");
        year=cashService.CashFlowTable_year(bar.getDate(),"");
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
        cash_data.add(new CashVO("一、经营活动产生的现金流量：",0,0,0,""));
        cash_data.add(new CashVO("销售产成品、商品、提供劳务收到的现金",1,year.getOperating_activities()[0],period.getOperating_activities()[0],"公式:\n主营业务收入×（1＋17%）\n＋其他业务收入\n＋（应收票据期初余额－应收票据期末余额）\n＋（应收账款期初余额－应收账款期末余额）\n＋（预收账款期末余额－预收账款期初余额）"));
        cash_data.add(new CashVO("收到其他与经营活动有关的现金",2,year.getOperating_activities()[1],period.getOperating_activities()[1],"公式:\n营业外收入相关明细本期贷方发生额\n＋其他业务收入相关明细本期贷方发生额\n+其他应收款相关明细本期贷方发生额\n＋其他应付款相关明细本期贷方发生额\n＋银行存款利息收入"));
        cash_data.add(new CashVO("购买原材料、商品、接受劳务支付的现金",3,year.getOperating_activities()[2],period.getOperating_activities()[2],"公式:\n原材料、低值易耗品、包装物、库存商品的借方发生额×（1＋17%）\n+购买原材料、商品、接受劳务的应付账款(期初 一期末)\n+购买原材料、商品、接受劳务的应付票据(期初一期末)\n+购买原材料、商品、接受劳务的预付账款 (期末一期初)"));
        cash_data.add(new CashVO("支付的职工薪酬",4,year.getOperating_activities()[3],period.getOperating_activities()[3],"公式:\n“应付职工薪酬”科目本期借方发生额累计数\n-“应付职工薪酬”科目本期借方发生额中非现金支付的部分"));
        cash_data.add(new CashVO("支付的税费",5,year.getOperating_activities()[4],period.getOperating_activities()[4],"公式:\n“应交税费”各明细账户本期借方发生额累计数\n-“应交税费-应交增值税-进项税额”"));
        cash_data.add(new CashVO("支付其他与经营活动有关的现金",6,year.getOperating_activities()[5],period.getOperating_activities()[5],"公式:\n营业外支出（-其中的非流动资产处置净损失）\n＋管理费用(-其中的应付职工薪酬、折旧费)\n＋销售费用 (-其中的应付职工薪酬)\n＋其他应收款本期借方发生额\n＋其他应付款本期借方发生额\n＋财务费用——手续费"));
        cash_data.add(new CashVO("经营活动产生的现金流量净额",7,year.getOperating_activities()[6],period.getOperating_activities()[6],"公式:\n销售产成品、商品、提供劳务收到的现金\n+收到其他与经营活动有关的现金\n-购买原材料、商品、接受劳务支付的现金\n-支付的职工薪酬\n-支付的税费\n-支付其他与经营活动有关的现金"));
        cash_data.add(new CashVO("二、投资活动产生的现金流量：",0,0,0,""));
        cash_data.add(new CashVO("收回短期投资、长期债券投资和长期股权投资收到的现金",8,year.getInvestment_activities()[0],period.getInvestment_activities()[0],"公式:\n(短期投资期初数－短期投资期末数）（当期初数＞期末数时）\n＋（长期股权投资期初数－长期股权投资期末数）（当期初数＞期末数时）\n＋（长期债券投资期初数－长期债券投资期末数）（当期初数＞期末数时）"));
        cash_data.add(new CashVO("取得投资收益收到的现金",9,year.getInvestment_activities()[1],period.getInvestment_activities()[1],"公式:\n投资收益\n－（应收利息期末数－应收利息期初数）\n－（应收股利期末数－应收股利期初数）"));
        cash_data.add(new CashVO("处置固定资产、无形资产和其他非流动资产收回的现金净额",10,year.getInvestment_activities()[2],period.getInvestment_activities()[2],"公式:\n“固定资产清理”的贷方余额\n＋（无形资产期末数－无形资产期初数）"));
        cash_data.add(new CashVO("短期投资、长期债券投资和长期股权投资支付的现金",11,year.getInvestment_activities()[3],period.getInvestment_activities()[3],"公式:\n（短期投资期末数－短期投资期初数）（当期初数＜期末数时）\n＋〔（长期股权投资期末数－长期股权投资期初数）-长期股权投资形成的投资收益〕（当期初数＜期末数时）\n＋〔（长期债券投资期末数－长期债权投资期初数）\n-长期债券投资形成的投资收益〕（当期初数＜期末数时）"));
        cash_data.add(new CashVO("购建固定资产、无形资产和其他非流动资产支付的现金",12,year.getInvestment_activities()[4],period.getInvestment_activities()[4],"公式:\n在建工程期末数－在建工程期初数）（当期初数＜期末数时）\n＋（固定资产期末数－固定资产期初数）（当期初数＜期末数时）\n＋（无形资产期末数－无形资产期初数）（当期初数＜期末数时）"));
        cash_data.add(new CashVO("投资活动产生的现金流量净额",13,year.getInvestment_activities()[5],period.getInvestment_activities()[5],"公式:\n收回短期投资、长期债券投资和长期股权投资收到的现金\n+取得投资收益收到的现金\n+处置固定资产、无形资产和其他非流动资产收回的现金净额\n-短期投资、长期债券投资和长期股权投资支付的现金\n-购建固定资产、无形资产和其他非流动资产支付的现金"));
        cash_data.add(new CashVO("三、筹资活动产生的现金流量：",0,0,0,""));
        cash_data.add(new CashVO("取得借款收到的现金",14,year.getFinancing_activities()[0],period.getFinancing_activities()[0],"公式:\n（短期借款期末数－短期借款期初数）\n＋（长期借款期末数－长期借款期初数）"));
        cash_data.add(new CashVO("吸收投资者投资收到的现金",15,year.getFinancing_activities()[1],period.getFinancing_activities()[1],"公式:\n实收资本期末数－实收资本期初数"));
        cash_data.add(new CashVO("偿还借款本金支付的现金",16,year.getFinancing_activities()[2],period.getFinancing_activities()[2],"公式:\n（短期借款期初数－短期借款期末数）\n＋（长期借款期初数－长期借款期末数）\n-“财务费用-利息费用”"));
        cash_data.add(new CashVO("偿还借款利息支付的现金",17,year.getFinancing_activities()[3],period.getFinancing_activities()[3],"公式:\n"));
        cash_data.add(new CashVO("分配利润支付的现金",18,year.getFinancing_activities()[4],period.getFinancing_activities()[4],"公式:\n“利润分配-应付利润”本期借方发生额中以现金支付的部分"));
        cash_data.add(new CashVO("筹资活动产生的现金流量净额",19,year.getFinancing_activities()[5],period.getFinancing_activities()[5],"公式:\n取得借款收到的现金\n+吸收投资者投资收到的现金\n-偿还借款本金支付的现金\n-分配利润支付的现金"));
        cash_data.add(new CashVO("四、现金净增加额",20,year.getNet_cash_increase()[0],period.getNet_cash_increase()[0],"公式:\n经营活动产生的现金流量净额\n+投资活动产生的现金流量净额\n+筹资活动产生的现金流量净额"));
        cash_data.add(new CashVO("加：期初现金余额",21,year.getNet_cash_increase()[1],period.getNet_cash_increase()[1],"由上期期末现金余额决定"));
        cash_data.add(new CashVO("五、期末现金余额",22,year.getFinal_cash_balance(),period.getFinal_cash_balance(),"公式:\n四、现金净增加额\n+期初现金余额"));

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

    public void setCell(){
        yearcul_item1.setCellFactory(new Callback<TableColumn<CashVO,Double>, TableCell<CashVO,Double>>() {
            @Override
            public TableCell<CashVO,Double> call(TableColumn<CashVO,Double> param) {
                return new TextFieldTableCell<CashVO,Double>(){
                    public void updateItem(Double d,boolean isEmpty){
                        super.updateItem(d,isEmpty);
                        if(!isEmpty){
                            CashVO vo=getTableView().getItems().get(getTableRow().getIndex());
                            if(vo.getYear()==0){
                                setStyle("-fx-text-fill: transparent;");
                            }else if(vo.getYear()<0){
                                setStyle("-fx-text-fill: red;");
                            }
                            if(vo.line_no!=0) {
                                Tooltip tip = new Tooltip(vo.getFormula());
                                setTooltip(tip);
                            }

                        }
                    }
                };
            }
        });

        month_item1.setCellFactory(new Callback<TableColumn<CashVO,Double>, TableCell<CashVO,Double>>() {
            @Override
            public TableCell<CashVO,Double> call(TableColumn<CashVO,Double> param) {
                return new TextFieldTableCell<CashVO,Double>(){
                    public void updateItem(Double d,boolean isEmpty){
                        super.updateItem(d,isEmpty);
                        if(!isEmpty){
                            CashVO vo=getTableView().getItems().get(getTableRow().getIndex());
                            if(vo.getYear()==0){
                                setStyle("-fx-text-fill: transparent;");
                            }else if(vo.getYear()<0){
                                setStyle("-fx-text-fill: red;");
                            }
                            if(vo.line_no!=0) {
                                Tooltip tip = new Tooltip(vo.getFormula());
                                setTooltip(tip);
                            }

                        }
                    }
                };
            }
        });
        line_item1.setCellFactory(new Callback<TableColumn<CashVO,Integer>, TableCell<CashVO,Integer>>() {
            @Override
            public TableCell<CashVO,Integer> call(TableColumn<CashVO,Integer> param) {
                return new TextFieldTableCell<CashVO,Integer>(){
                    public void updateItem(Integer i,boolean isEmpty){
                        super.updateItem(i,isEmpty);
                        if(!isEmpty){
                            CashVO v=getTableView().getItems().get(getTableRow().getIndex());
                            if(v.getline_no()==0){
                                setStyle("-fx-text-fill: transparent;");
                            }
                        }
                    }
                };
            }
        });
        project_item1.setCellFactory(new Callback<TableColumn<CashVO,String>, TableCell<CashVO,String>>() {
            @Override
            public TableCell<CashVO,String> call(TableColumn<CashVO,String> param) {
                return new TextFieldTableCell<CashVO,String>(){
                    public void updateItem(String string,boolean isEmpty){
                        super.updateItem(string,isEmpty);
                        if(!isEmpty){
                            CashVO v=getTableView().getItems().get(getTableRow().getIndex());
                            if(v.getline_no()==0||v.getline_no()==20||v.getline_no()==22){
                                setStyle("-fx-font-size: 19;-fx-font-weight: bold;-fx-alignment: center-left");
                            }
                        }
                    }
                };
            }
        });
    }

}

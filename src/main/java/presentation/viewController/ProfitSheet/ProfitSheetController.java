package presentation.viewController.ProfitSheet;

import businesslogic.ProfitTableImpl;
import businesslogicservice.ProfitTableService;
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
import presentation.viewController.ProfitSheet.ProfitVO;
import vo.ProfitTableVo;

import java.util.ArrayList;

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
                setProfitTable();
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
                setProfitTable();
            }
        });
    }

    public void setProfitTable(){
        period=profitTableService.BusinessIncome_period(bar.getDate(),"");
        year=profitTableService.BusinessIncome_year(bar.getDate(),"");

        ArrayList<ProfitVO> profitTable_data=new ArrayList<ProfitVO>();
        profitTable_data.add(new ProfitVO("一、营业收入",1,year.getBusiness_income(),period.getBusiness_income(),"公式:\n主营业务收入\n+其他业务收入"));
        profitTable_data.add(new ProfitVO("减：营业成本",2,year.getBusiness_costs(),period.getBusiness_costs(),"公式:\n主营业务成本\n+其他业务成本"));
        profitTable_data.add(new ProfitVO("营业税金及附加",3,year.getBusiness_Taxes_and_Surcharges()[0],period.getBusiness_Taxes_and_Surcharges()[0],"公式:\n税金及附加"));


        profitTable_data.add(new ProfitVO("销售费用",11,year.getSelling_expenses()[0],period.getSelling_expenses()[0],"公式:\n销售费用"));


        profitTable_data.add(new ProfitVO("管理费用",14,year.getManagement_expenses()[0],period.getManagement_expenses()[0],"公式:\n销售费用"));


        profitTable_data.add(new ProfitVO("财务费用",18,year.getFinancial_expenses()[0],period.getFinancial_expenses()[0],"公式:\n销售费用"));


        profitTable_data.add(new ProfitVO("加：投资收益(亏损以'-'号填列)",20,year.getInvestment_proceeds(),period.getInvestment_proceeds(),"公式:\n投资收益"));
        profitTable_data.add(new ProfitVO("二、营业利润(亏损以'-'号填列)",21,year.getOperating_profit(),period.getOperating_profit(),"公式:\n营业收入\n+投资收益\n-营业成本\n-营业税金及附加\n-销售费用\n-管理费用\n-财务费用"));
        profitTable_data.add(new ProfitVO("加：营业外收入",22,year.getNon_operating_income()[0],period.getNon_operating_income()[0],"公式:\n营业外收入"));


        profitTable_data.add(new ProfitVO("减：营业外支出",24,year.getNon_operating_expenses()[0],period.getNon_operating_expenses()[0],"公式:\n营业外支出"));


        profitTable_data.add(new ProfitVO("三、利润总额(亏损总额以'-'号填列)",30,year.getTotal_profit(),period.getTotal_profit(),"公式:\n营业利润\n+营业外收入\n-营业外支出"));
        profitTable_data.add(new ProfitVO("减：所得税费用",31,year.getIncome_tax_expense(),period.getIncome_tax_expense(),"公式:\n所得税费用"));
        profitTable_data.add(new ProfitVO("四、净利润(净亏损以'-'号填列)",32,year.getNet_profit(),period.getNet_profit(),"公式:\n利润总额\n-所得税费用"));

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

    public void setCell(){
        yearcul_item.setCellFactory(new Callback<TableColumn<ProfitVO,Double>, TableCell<ProfitVO,Double>>() {
            @Override
            public TableCell<ProfitVO,Double> call(TableColumn<ProfitVO,Double> param) {
                return new TextFieldTableCell<ProfitVO,Double>(){
                    public void updateItem(Double d,boolean isEmpty){
                        super.updateItem(d,isEmpty);
                        if(!isEmpty){
                            ProfitVO vo=getTableView().getItems().get(getTableRow().getIndex());
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

        month_item.setCellFactory(new Callback<TableColumn<ProfitVO,Double>, TableCell<ProfitVO,Double>>() {
            @Override
            public TableCell<ProfitVO,Double> call(TableColumn<ProfitVO,Double> param) {
                return new TextFieldTableCell<ProfitVO,Double>(){
                    public void updateItem(Double d,boolean isEmpty){
                        super.updateItem(d,isEmpty);
                        if(!isEmpty){
                            ProfitVO vo=getTableView().getItems().get(getTableRow().getIndex());
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
        l_item.setCellFactory(new Callback<TableColumn<ProfitVO,Integer>, TableCell<ProfitVO,Integer>>() {
            @Override
            public TableCell<ProfitVO,Integer> call(TableColumn<ProfitVO,Integer> param) {
                return new TextFieldTableCell<ProfitVO,Integer>(){
                    public void updateItem(Integer i,boolean isEmpty){
                        super.updateItem(i,isEmpty);
                        if(!isEmpty){
                            ProfitVO v=getTableView().getItems().get(getTableRow().getIndex());
                            if(v.getline_no()==0){
                                setStyle("-fx-text-fill: transparent;");
                            }
                        }
                    }
                };
            }
        });
        project_item.setCellFactory(new Callback<TableColumn<ProfitVO,String>, TableCell<ProfitVO,String>>() {
            @Override
            public TableCell<ProfitVO,String> call(TableColumn<ProfitVO,String> param) {
                return new TextFieldTableCell<ProfitVO,String>(){
                    public void updateItem(String string,boolean isEmpty){
                        super.updateItem(string,isEmpty);
                        if(!isEmpty){
                            ProfitVO v=getTableView().getItems().get(getTableRow().getIndex());
                            if(v.getline_no()==1||v.getline_no()==21||v.getline_no()==30||v.getline_no()==32){
                                setStyle("-fx-font-size: 19;-fx-font-weight: bold;-fx-alignment: center-left");
                            }
                        }
                    }
                };
            }
        });

    }

}

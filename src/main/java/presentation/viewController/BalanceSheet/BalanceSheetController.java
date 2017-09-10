package presentation.viewController.BalanceSheet;

import businesslogic.BalanceSheetImpl;
import businesslogicservice.BalanceSheetService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import presentation.componentController.Datebar;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import vo.BalanceSheetItemVo;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by YZ on 2017/8/11.
 */
public class BalanceSheetController implements ControlledScreen{
    @FXML
    private Button print;
    @FXML
    private Button out;
    @FXML
    private Datebar bar;
    @FXML
    private TableView propertyTable;
    @FXML
    private TableView debtTable;
    @FXML
    private TableColumn property_item;
    @FXML
    private TableColumn row_item;
    @FXML
    private TableColumn endbalance_item;
    @FXML
    private TableColumn yearbegin_item;
    @FXML
    private TableColumn debt_item;
    @FXML
    private TableColumn row_itemm;
    @FXML
    private TableColumn endbalance_itemm;
    @FXML
    private TableColumn yearbegin_itemm;

    private BalanceSheetService balanceSheetService=new BalanceSheetImpl();
    private Map<String, ArrayList<BalanceSheetItemVo>> bs_data;


    @FXML
    private void initialize(){
        setBalanceTable();
        dropzero();
        setCell();
        setTab();
    }
//    private String date=bar.getDate();

    //初始化时，datebar显示当月日期，表格里显示条目以及相应的按钮提示，公式等;
    //如果有凭证的话，显示最近的一月
    //为什么不直接放在bar里

    public void setTab(){
        bar.getLast().setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                if(bar.getMidMonths().indexOf(bar.getDate())>0) {
                    bar.getYL().setText(bar.getMidMonths().get(bar.getMidMonths().indexOf(bar.getDate()) - 1).substring(0,4));
                    bar.getML().setText(bar.getMidMonths().get(bar.getMidMonths().indexOf(bar.getDate()) - 1).split("-")[1]);
                    bar.changePro();
                }
                setBalanceTable();
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
                setBalanceTable();
            }
        });
    }
    //为资产表添加数据
    public void setBalanceTable(){
//        System.out.print(bar.getDate());
        bs_data=balanceSheetService.getBalanceSheet("001",bar.getDate());
        ArrayList<BalanceSheetItemVo> p1=bs_data.get("流动资产");
        ArrayList<BalanceSheetItemVo> p2=bs_data.get("非流动资产");
        ArrayList<BalanceSheetItemVo> p3=bs_data.get("资产合计");
        ArrayList<BalanceSheetItemVo> p4=bs_data.get("流动负债");
        ArrayList<BalanceSheetItemVo> p5=bs_data.get("非流动负债");
        ArrayList<BalanceSheetItemVo> p6=bs_data.get("负债合计");
        ArrayList<BalanceSheetItemVo> p7=bs_data.get("所有者权益");
        ArrayList<BalanceSheetItemVo> p8=bs_data.get("负债和所有者权益合计");
//        System.out.print(bs_data.get("非流动资产"));
        ObservableList<BalanceSheetItemVo> p_list= FXCollections.observableArrayList();

        property_item.setCellValueFactory(new PropertyValueFactory("property_name"));
        row_item.setCellValueFactory(new PropertyValueFactory("Line_No"));
        endbalance_item.setCellValueFactory(new PropertyValueFactory("ending_balance"));
        yearbegin_item.setCellValueFactory(new PropertyValueFactory("beginning_balance"));

        Iterator i1=p1.iterator();
        while(i1.hasNext()){
            p_list.add((BalanceSheetItemVo)i1.next());
        }
        Iterator i2=p2.iterator();
        while(i2.hasNext()){
            p_list.add((BalanceSheetItemVo)i2.next());
        }
        Iterator i3=p3.iterator();
        while(i3.hasNext()){
            p_list.add((BalanceSheetItemVo)i3.next());
        }

        propertyTable.setItems(p_list);

        ObservableList<BalanceSheetItemVo> p_list2= FXCollections.observableArrayList();

        debt_item.setCellValueFactory(new PropertyValueFactory("property_name"));
        row_itemm.setCellValueFactory(new PropertyValueFactory("Line_No"));
        endbalance_itemm.setCellValueFactory(new PropertyValueFactory("ending_balance"));
        yearbegin_itemm.setCellValueFactory(new PropertyValueFactory("beginning_balance"));

        Iterator i4=p4.iterator();
        while(i4.hasNext()){
            p_list2.add((BalanceSheetItemVo)i4.next());
        }
        Iterator i5=p5.iterator();
        while(i5.hasNext()){
            p_list2.add((BalanceSheetItemVo)i5.next());
        }
        Iterator i6=p6.iterator();
        while(i6.hasNext()){
            p_list2.add((BalanceSheetItemVo)i6.next());
        }
        Iterator i7=p7.iterator();
        while(i7.hasNext()){
            p_list2.add((BalanceSheetItemVo)i7.next());
        }
        Iterator i8=p8.iterator();
        while(i8.hasNext()){
            p_list2.add((BalanceSheetItemVo)i8.next());
        }

        debtTable.setItems(p_list2);

    }
    public void dropzero(){
        for(int i=0;i<propertyTable.getItems().size();i++){
            BalanceSheetItemVo vo=(BalanceSheetItemVo)propertyTable.getItems().get(i);
            if(vo.getLine_No()==0){
            }
        }
    }

    public void setCell(){
       endbalance_item.setCellFactory(new Callback<TableColumn<BalanceSheetItemVo,Double>, TableCell<BalanceSheetItemVo,Double>>() {
           @Override
           public TableCell<BalanceSheetItemVo,Double> call(TableColumn<BalanceSheetItemVo,Double> param) {
               return new TextFieldTableCell<BalanceSheetItemVo,Double>(){
                   public void updateItem(Double d,boolean isEmpty){
                       super.updateItem(d,isEmpty);
                       if(!isEmpty){
                           BalanceSheetItemVo vo=getTableView().getItems().get(getTableRow().getIndex());
                           if(vo.getEnding_balance()==0){
                               setStyle("-fx-text-fill: transparent;");
                           }else if(vo.getEnding_balance()<0){
                               setStyle("-fx-text-fill: red;");
                           }
                           if(vo.getLine_No()!=0) {
                               Tooltip tip = new Tooltip(vo.getLaw());
                               setTooltip(tip);
                           }

                       }
                   }
               };
           }
       });
        endbalance_itemm.setCellFactory(new Callback<TableColumn<BalanceSheetItemVo,Double>, TableCell<BalanceSheetItemVo,Double>>() {
            @Override
            public TableCell<BalanceSheetItemVo,Double> call(TableColumn<BalanceSheetItemVo,Double> param) {
                return new TextFieldTableCell<BalanceSheetItemVo,Double>(){
                    public void updateItem(Double d,boolean isEmpty){
                        super.updateItem(d,isEmpty);
                        if(!isEmpty){
                            BalanceSheetItemVo vo=getTableView().getItems().get(getTableRow().getIndex());
                            if(vo.getEnding_balance()==0){
                                setStyle("-fx-text-fill: transparent;");
                            }else if(vo.getEnding_balance()<0){
                                setStyle("-fx-text-fill: red;");
                            }
                            if(vo.getLine_No()!=0) {
                                Tooltip tip = new Tooltip(vo.getLaw());
                                setTooltip(tip);
                            }

                        }
                    }
                };
            }
        });
       property_item.setCellFactory(new Callback<TableColumn<BalanceSheetItemVo,String>, TableCell<BalanceSheetItemVo,String>>() {
           @Override
           public TableCell<BalanceSheetItemVo,String> call(TableColumn<BalanceSheetItemVo,String> param) {
               return new TextFieldTableCell<BalanceSheetItemVo,String>(){
                   public void updateItem(String string,boolean isEmpty){
                       super.updateItem(string,isEmpty);
                       if(!isEmpty){
                           BalanceSheetItemVo v=getTableView().getItems().get(getTableRow().getIndex());

                           if(v.getLine_No()==0||v.getLine_No()==30){
                               setStyle("-fx-font-size: 19;-fx-font-weight: bold;-fx-alignment: center-left");
                           }
//                           if(v.getLine_No()==10||v.getLine_No()==11||v.getLine_No()==12||v.getLine_No()==13){
//                               setStyle("-fx-alignment: center-right");
//                           }
//                           if(v.getLine_No()==9){
//                               setStyle("-fx-background-image: url('images/down.png');-fx-background-position: left;-fx-background-repeat: no-repeat;-fx-background-size: 28 28;");
//                               setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
//                                   @Override
//                                   public void handle(javafx.scene.input.MouseEvent event) {
////                                       getTableView().getItems().add(new BalanceSheetItemVo("  其中：原材料",10,0,0,"公式：\n原材料"));
//
//                                   }
//                               });
//
//                           }

                       }
                   }
               };
           }
       });

        debt_item.setCellFactory(new Callback<TableColumn<BalanceSheetItemVo,String>, TableCell<BalanceSheetItemVo,String>>() {
            @Override
            public TableCell<BalanceSheetItemVo,String> call(TableColumn<BalanceSheetItemVo,String> param) {
                return new TextFieldTableCell<BalanceSheetItemVo,String>(){
                    public void updateItem(String string,boolean isEmpty){
                        super.updateItem(string,isEmpty);
                        if(!isEmpty){
                            BalanceSheetItemVo v=getTableView().getItems().get(getTableRow().getIndex());
                            if(v.getLine_No()==0||v.getLine_No()==47||v.getLine_No()==53){
                                setStyle("-fx-font-size: 19;-fx-font-weight: bold;-fx-alignment: center-left");
                            }


                        }
                    }
                };
            }
        });
       row_item.setCellFactory(new Callback<TableColumn<BalanceSheetItemVo,Integer>, TableCell<BalanceSheetItemVo,Integer>>() {
           @Override
           public TableCell<BalanceSheetItemVo,Integer> call(TableColumn<BalanceSheetItemVo,Integer> param) {
               return new TextFieldTableCell<BalanceSheetItemVo,Integer>(){
                   public void updateItem(Integer i,boolean isEmpty){
                       super.updateItem(i,isEmpty);
                       if(!isEmpty){
                           BalanceSheetItemVo v=getTableView().getItems().get(getTableRow().getIndex());
                           if(v.getLine_No()==0){
                               setStyle("-fx-text-fill: transparent;");
                           }
                       }
                   }
               };
           }
       });
       yearbegin_item.setCellFactory(new Callback<TableColumn<BalanceSheetItemVo,Double>, TableCell<BalanceSheetItemVo,Double>>() {
            @Override
            public TableCell<BalanceSheetItemVo,Double> call(TableColumn<BalanceSheetItemVo,Double> param) {
                return new TextFieldTableCell<BalanceSheetItemVo,Double>(){
                    public void updateItem(Double d,boolean isEmpty){
                        super.updateItem(d,isEmpty);
                        if(!isEmpty){
                            BalanceSheetItemVo vo=getTableView().getItems().get(getTableRow().getIndex());
                            if(vo.getBeginning_balance()==0){
                                setStyle("-fx-text-fill: transparent;");
                            }else if(vo.getBeginning_balance()<0){
                                setStyle("-fx-text-fill: red;");
                            }
                            if(vo.getLine_No()!=0) {
                                Tooltip tip = new Tooltip(vo.getLaw());
                                setTooltip(tip);
                            }

                        }
                    }
                };
            }
        });
        row_itemm.setCellFactory(new Callback<TableColumn<BalanceSheetItemVo,Integer>, TableCell<BalanceSheetItemVo,Integer>>() {
            @Override
            public TableCell<BalanceSheetItemVo,Integer> call(TableColumn<BalanceSheetItemVo,Integer> param) {
                return new TextFieldTableCell<BalanceSheetItemVo,Integer>(){
                    public void updateItem(Integer i,boolean isEmpty){
                        super.updateItem(i,isEmpty);
                        if(!isEmpty){
                            BalanceSheetItemVo v=getTableView().getItems().get(getTableRow().getIndex());
                            if(v.getLine_No()==0){
                                setStyle("-fx-text-fill: transparent;");
                            }
                        }
                    }
                };
            }
        });
        yearbegin_itemm.setCellFactory(new Callback<TableColumn<BalanceSheetItemVo,Double>, TableCell<BalanceSheetItemVo,Double>>() {
            @Override
            public TableCell<BalanceSheetItemVo,Double> call(TableColumn<BalanceSheetItemVo,Double> param) {
                return new TextFieldTableCell<BalanceSheetItemVo,Double>(){
                    public void updateItem(Double d,boolean isEmpty){
                        super.updateItem(d,isEmpty);
                        if(!isEmpty){
                            BalanceSheetItemVo vo=getTableView().getItems().get(getTableRow().getIndex());
                            if(vo.getBeginning_balance()==0){
                                setStyle("-fx-text-fill: transparent;");
                            }else if(vo.getBeginning_balance()<0){
                                setStyle("-fx-text-fill: red;");
                            }
                            if(vo.getLine_No()!=0) {
                                Tooltip tip = new Tooltip(vo.getLaw());
                                setTooltip(tip);
                            }

                        }
                    }
                };
            }
        });
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }

}

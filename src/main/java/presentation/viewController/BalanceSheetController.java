package presentation.viewController;

import businesslogic.BalanceSheetImpl;
import businesslogicservice.BalanceSheetService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import presentation.componentController.Datebar;
import vo.BalanceSheetItemVo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by YZ on 2017/8/11.
 */
public class BalanceSheetController {
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
    }
//    private String date=bar.getDate();

    //初始化时，datebar显示当月日期，表格里显示条目以及相应的按钮提示，公式等;
    //如果有凭证的话，显示最近的一月
    //为什么不直接放在bar里

    //为资产表添加数据
    public void setBalanceTable(){
//        System.out.print(bar.getDate());
        bs_data=balanceSheetService.getBalanceSheet(bar.getDate());
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

}

package presentation.viewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

/**
 * Created by YZ on 2017/9/7.
 */
public class TreeController {
    @FXML
    TreeTableColumn<CashVO,String> pro;
    @FXML
    TreeTableView<CashVO> table;
    @FXML
    public void initialize(){
        setTable();
    }

    public void setTable(){
        ArrayList<CashVO> cash_data=new ArrayList<CashVO>();
        TreeItem<CashVO> root=new TreeItem<>(new CashVO("一、经营活动产生的现金流量：",0,0,0,""));
        cash_data.add(new CashVO("一、经营活动产生的现金流量：",0,0,0,""));
        cash_data.add(new CashVO("一、经营活动产生的现金流量：",0,0,0,""));
        cash_data.add(new CashVO("一、经营活动产生的现金流量：",0,0,0,""));
        cash_data.add(new CashVO("一、经营活动产生的现金流量：",0,0,0,""));
        cash_data.add(new CashVO("一、经营活动产生的现金流量：",0,0,0,""));
        ObservableList<CashVO> c_list= FXCollections.observableArrayList();

        pro.setCellValueFactory(new PropertyValueFactory("project"));

        for(int i=0;i<cash_data.size();i++){
            c_list.add(cash_data.get(i));
        }
        table=new TreeTableView<>(root);
        table.getColumns().setAll(pro);


    }

}

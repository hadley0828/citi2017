package presentation.viewController.supplyChainManagement.financialInstitutions;

import businesslogic.SupplyChainImpl;
import businesslogicservice.SupplyChainService;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import po.SupplyChainPO;
import presentation.viewController.StaticFactory;
import vo.CompanyVo;
import vo.Inventory.ProductInventoryMonitorItemVo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    static Stage chartStage;
    public String com_name;
    SupplyChainService service=new SupplyChainImpl();
    @FXML
    public void initialize(){
        ArrayList<SupplyChainPO> list=(ArrayList)service.GetSupplyChains();
        ObservableList<SupplyChainPO> observableList= FXCollections.observableList(list);
        company_name.setCellValueFactory(new PropertyValueFactory("company"));
        financing_style.setCellValueFactory(new PropertyValueFactory("FinacingType"));
        money_debt.setCellValueFactory(new PropertyValueFactory("Net"));
        advice_size.setCellValueFactory(new PropertyValueFactory("ProposedFinancingScale"));
//        stylesheet.setCellValueFactory(new PropertyValueFactory("company"));
        financing_table.setItems(observableList);

        setCell();
    }
    public void setCell() {
        company_name.setCellFactory(tc->{
            TableCell<SupplyChainPO, String> cell=new TableCell<SupplyChainPO, String>(){
                @Override
                protected void updateItem(String item,boolean empty){
                    super.updateItem(item,empty);
                    setText(empty?null:item);
                }
            };
            cell.setOnMouseClicked(e->{
                if(!cell.isEmpty()){
                    com_name=cell.getItem();
                    StaticFactory.setCompany_name(com_name);

                    try {
                        draw();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            cell.setOnMouseEntered(e->{
                cell.setStyle("-fx-text-fill: rgb(255,135,98);");
                cell.setCursor(Cursor.HAND);
            });
            cell.setOnMouseExited(e->{
                cell.setStyle("-fx-text-fill: black");
                cell.setCursor(Cursor.DEFAULT);
            });
            return cell;
        });
    }


    public void draw() throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("../../../../view/supplyChainManagement/financialInstitutions/CompanyInfo.fxml"));
        chartStage=new Stage();
        Scene scene=new Scene(root,350,250);
        chartStage.setScene(scene);
        chartStage.initStyle(StageStyle.TRANSPARENT);
        chartStage.initModality(Modality.APPLICATION_MODAL);
        chartStage.show();
    }

    public CompanyVo getCompany(){
        return service.getCompanyInfo(StaticFactory.getCompany_name());
    }
}

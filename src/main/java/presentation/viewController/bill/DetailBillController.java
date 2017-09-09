package presentation.viewController.bill;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.dataModel.DetailBillModel;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2017/9/6
 */
public class DetailBillController implements Initializable, ControlledScreen {
    @FXML
    private TableView<DetailBillModel> billTable;
    @FXML
    private TableColumn<DetailBillModel, String> dateCol;
    @FXML
    private TableColumn<DetailBillModel, String> idCol;
    @FXML
    private TableColumn<DetailBillModel, String> subjectCol;
    @FXML
    private TableColumn<DetailBillModel, String> abstractsCol;
    @FXML
    private TableColumn<DetailBillModel, Number> debitCol;
    @FXML
    private TableColumn<DetailBillModel, Number> creditCol;
    @FXML
    private TableColumn<DetailBillModel, String> directionCol;
    @FXML
    private TableColumn<DetailBillModel, Number> balanceCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }

    private void initialTable() {

        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        subjectCol.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        abstractsCol.setCellValueFactory(cellData -> cellData.getValue().abstractsProperty());
        debitCol.setCellValueFactory(cellData -> cellData.getValue().debitProperty());
        creditCol.setCellValueFactory(cellData -> cellData.getValue().creditProperty());
        directionCol.setCellValueFactory(cellData -> cellData.getValue().directionProperty());
        balanceCol.setCellValueFactory(cellData -> cellData.getValue().balanceProperty());
    }
}

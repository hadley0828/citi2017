package presentation.viewController.bill;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.dataModel.GeneralBillModel;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class GeneralBillController implements Initializable, ControlledScreen {
    @FXML
    private TableView<GeneralBillModel> billTable;
    @FXML
    private TableColumn<GeneralBillModel, String> periodCol;
    @FXML
    private TableColumn<GeneralBillModel, String> idCol;
    @FXML
    private TableColumn<GeneralBillModel, String> subjectCol;
    @FXML
    private TableColumn<GeneralBillModel, String> abstractsCol;
    @FXML
    private TableColumn<GeneralBillModel, Number> debitCol;
    @FXML
    private TableColumn<GeneralBillModel, Number> creditCol;
    @FXML
    private TableColumn<GeneralBillModel, String> directionCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }

    private void initialTable() {

        periodCol.setCellValueFactory(cellData -> cellData.getValue().periodProperty());
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        subjectCol.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        abstractsCol.setCellValueFactory(cellData -> cellData.getValue().abstractsProperty());
        debitCol.setCellValueFactory(cellData -> cellData.getValue().debitProperty());
        creditCol.setCellValueFactory(cellData -> cellData.getValue().creditProperty());
        directionCol.setCellValueFactory(cellData -> cellData.getValue().directionProperty());
    }
}

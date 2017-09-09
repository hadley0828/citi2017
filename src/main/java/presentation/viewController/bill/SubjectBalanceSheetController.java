package presentation.viewController.bill;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.dataModel.SubjectBalanceModel;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2017/9/6
 */
public class SubjectBalanceSheetController implements Initializable, ControlledScreen {
    @FXML
    private TableView<SubjectBalanceModel> billTable;
    @FXML
    private TableColumn<SubjectBalanceModel, String> idCol;
    @FXML
    private TableColumn<SubjectBalanceModel, String> subjectCol;
    @FXML
    private TableColumn<SubjectBalanceModel, Number> beginningDebitCol;
    @FXML
    private TableColumn<SubjectBalanceModel, Number> beginningCreditCol;
    @FXML
    private TableColumn<SubjectBalanceModel, Number> occurredDebitCol;
    @FXML
    private TableColumn<SubjectBalanceModel, Number> occurredCreditCol;
    @FXML
    private TableColumn<SubjectBalanceModel, Number> endingDebitCol;
    @FXML
    private TableColumn<SubjectBalanceModel, Number> endingCreditCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }

    private void initialTable() {
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        subjectCol.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        beginningDebitCol.setCellValueFactory(cellData -> cellData.getValue().beginningDebitProperty());
        beginningCreditCol.setCellValueFactory(cellData -> cellData.getValue().beginningCreditProperty());
        occurredDebitCol.setCellValueFactory(cellData -> cellData.getValue().occurredDebitProperty());
        occurredCreditCol.setCellValueFactory(cellData -> cellData.getValue().occurredCreditProperty());
        endingDebitCol.setCellValueFactory(cellData -> cellData.getValue().endingDebitProperty());
        endingCreditCol.setCellValueFactory(cellData -> cellData.getValue().endingCreditProperty());

    }
}

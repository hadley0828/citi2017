package presentation.viewController.bill;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.dataModel.GeneralBillModel;
import presentation.dataModel.SubjectSummaryModel;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2017/9/6
 */
public class SubjectSummarySheetController implements Initializable, ControlledScreen {
    @FXML
    private TableView<SubjectSummaryModel> billTable;
    @FXML
    private TableColumn<SubjectSummaryModel, String> idCol;
    @FXML
    private TableColumn<SubjectSummaryModel, String> subjectCol;
    @FXML
    private TableColumn<SubjectSummaryModel, Number> debitSumCol;
    @FXML
    private TableColumn<SubjectSummaryModel, Number> creditSumCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }

    private void initialTable() {
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        subjectCol.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        debitSumCol.setCellValueFactory(cellData -> cellData.getValue().debitSumProperty());
        creditSumCol.setCellValueFactory(cellData -> cellData.getValue().creditSumProperty());
    }

}

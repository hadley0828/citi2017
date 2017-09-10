package presentation.viewController.bill;

import businesslogic.AccountBooksBlImpl;
import businesslogicservice.AccountBooksBlService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.dataModel.GeneralBillModel;
import presentation.dataModel.SubjectBalanceModel;
import presentation.dataModel.SubjectSummaryModel;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import vo.accountBook.GatherTableOneClause;

import java.net.URL;
import java.util.ArrayList;
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

    private AccountBooksBlService accountBooksBl;
    private ObservableList<SubjectSummaryModel> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountBooksBl = new AccountBooksBlImpl();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }

    private void initialTable() {
        ArrayList<GatherTableOneClause> gatherTableOneClauses = accountBooksBl.getGatherTableAllClauses(null, "001");
        for (GatherTableOneClause clause: gatherTableOneClauses) {
            data.add(new SubjectSummaryModel(clause.getSubjectId(), clause.getSubjectName(), clause.getDebitTotal(), clause.getCreditTotal()));
        }

        billTable.setItems(data);
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        subjectCol.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        debitSumCol.setCellValueFactory(cellData -> cellData.getValue().debitSumProperty());
        creditSumCol.setCellValueFactory(cellData -> cellData.getValue().creditSumProperty());
    }

}

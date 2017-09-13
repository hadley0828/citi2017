package presentation.viewController.financialSystem.bill;

import businesslogic.AccountBooksBlImpl;
import businesslogicservice.AccountBooksBlService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.componentController.BookSearch;
import presentation.dataModel.SubjectSummaryModel;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.viewController.StaticFactory;
import vo.accountBook.BookSearchVo;
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

    @FXML
    private MenuButton select_menu;

    private BookSearch bookSearch = new BookSearch();

    private String factoryId;
    private BookSearchVo bookSearchVo;
    private AccountBooksBlService accountBooksBl;
    private ObservableList<SubjectSummaryModel> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountBooksBl = new AccountBooksBlImpl();
        bookSearchVo = new BookSearchVo();
        factoryId = StaticFactory.getUserVO().getCompanyID();

        bookSearch.getConfirm_btn().setOnAction(event -> {
            bookSearchVo.setStartPeriod(bookSearch.getStartPeriod_item().getValue());
            bookSearchVo.setEndPeriod(bookSearch.getEndPeriod_item().getValue());
            bookSearchVo.setStartSubjectId(bookSearch.getStartSubject_item().getValue());
            bookSearchVo.setEndSubjectId(bookSearch.getEndSubject_item().getValue());
            bookSearchVo.setLowLevel(Integer.parseInt(bookSearch.getStartLevel_item().getText()));
            bookSearchVo.setHighLevel(Integer.parseInt(bookSearch.getEndLevel_item().getText()));
            updateTable();
        });

        MenuItem popItem = new MenuItem();
        popItem.setGraphic(bookSearch);
        select_menu.getItems().setAll(popItem);

        initialTable();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }

    private void initialTable() {


        billTable.setItems(data);
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        subjectCol.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        debitSumCol.setCellValueFactory(cellData -> cellData.getValue().debitSumProperty());
        creditSumCol.setCellValueFactory(cellData -> cellData.getValue().creditSumProperty());
    }

    private void updateTable() {
        data.clear();
        ArrayList<GatherTableOneClause> gatherTableOneClauses = accountBooksBl.getGatherTableAllClauses(bookSearchVo, factoryId);
        for (GatherTableOneClause clause: gatherTableOneClauses) {
            data.add(new SubjectSummaryModel(clause.getSubjectId(), clause.getSubjectName(), clause.getDebitTotal(), clause.getCreditTotal()));
        }
    }

}

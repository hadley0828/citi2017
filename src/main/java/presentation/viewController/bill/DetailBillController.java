package presentation.viewController.bill;

import businesslogic.AccountBooksBlImpl;
import businesslogicservice.AccountBooksBlService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import presentation.componentController.BookSearch;
import presentation.dataModel.DetailBillModel;
import presentation.dataModel.SubjectSummaryModel;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import vo.accountBook.BookSearchVo;
import vo.accountBook.DetailAccountAmountVo;
import vo.accountBook.DetailAccountVo;
import vo.accountBook.TotalAccountVo;

import java.net.URL;
import java.util.ArrayList;
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

    @FXML
    private HBox topControl;
    @FXML
    private VBox rightSubjects;

    @FXML
    private MenuButton select_menu;

    private BookSearch bookSearch = new BookSearch();

    private BookSearchVo bookSearchVo;
    private AccountBooksBlService accountBooksBl;
    private ObservableList<DetailBillModel> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountBooksBl = new AccountBooksBlImpl();
        bookSearchVo = new BookSearchVo();

        bookSearch.getConfirm_btn().setOnAction(event -> {
            bookSearchVo.setStartPeriod(bookSearch.getStartPeriod_item().getValue());
            bookSearchVo.setEndPeriod(bookSearch.getEndPeriod_item().getValue());
            bookSearchVo.setStartSubjectId(bookSearch.getStartSubject_item().getValue());
            bookSearchVo.setEndSubjectId(bookSearch.getEndSubject_item().getValue());
            bookSearchVo.setLowLevel(Integer.parseInt(bookSearch.getStartLevel_item().getText()));
            bookSearchVo.setHighLevel(Integer.parseInt(bookSearch.getEndLevel_item().getText()));
        });

        MenuItem popItem = new MenuItem();
        popItem.setGraphic(bookSearch);
        select_menu.getItems().setAll(popItem);

        initialSubjectsList();

        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        subjectCol.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        abstractsCol.setCellValueFactory(cellData -> cellData.getValue().abstractsProperty());
        debitCol.setCellValueFactory(cellData -> cellData.getValue().debitProperty());
        creditCol.setCellValueFactory(cellData -> cellData.getValue().creditProperty());
        directionCol.setCellValueFactory(cellData -> cellData.getValue().directionProperty());
        balanceCol.setCellValueFactory(cellData -> cellData.getValue().balanceProperty());
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }

    private void initialSubjectsList() {
        ArrayList<String> subjectsList = accountBooksBl.getAllExistedSubjectId("001");
        for (String sub: subjectsList) {
            Button btn = new Button(sub);
            btn.setOnAction((ActionEvent e) -> {
                updateTable(sub);
            });
            rightSubjects.getChildren().add(new Button(sub));
        }
    }

    private void updateTable(String subjectId) {
        DetailAccountVo detailAccountVo = accountBooksBl.getOneSubjectDetail(subjectId, bookSearchVo, "001");
        ArrayList<DetailAccountAmountVo> amountVoArrayList = detailAccountVo.getAmountVoArrayList();
        for (DetailAccountAmountVo vo: amountVoArrayList) {
            data.add(new DetailBillModel(vo.getDate(), vo.getVoucherId(), vo.getSubject(), vo.getAbstracts(), vo.getDebitAmount(), vo.getCreditAmount(), vo.getDirection(), vo.getBalance()));
        }

        billTable.setItems(data);
    }
}

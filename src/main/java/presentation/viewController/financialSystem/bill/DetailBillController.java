package presentation.viewController.financialSystem.bill;

import businesslogic.AccountBooksBlImpl;
import businesslogicservice.AccountBooksBlService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import presentation.componentController.BookSearch;
import presentation.dataModel.DetailBillModel;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.StaticFactory;
import vo.accountBook.BookSearchVo;
import vo.accountBook.DetailAccountAmountVo;
import vo.accountBook.DetailAccountVo;

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
    private VBox rightSubjects;

    @FXML
    private MenuButton select_menu;

    private BookSearch bookSearch = new BookSearch();

    private String factoryId;
    private BookSearchVo bookSearchVo;
    private AccountBooksBlService accountBooksBl;
    private ObservableList<DetailBillModel> data = FXCollections.observableArrayList();
    private ArrayList<String> subjectsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountBooksBl = new AccountBooksBlImpl();
        bookSearchVo = new BookSearchVo();
        factoryId = StaticFactory.getUserVO().getCompanyID();

        bookSearch.getConfirm_btn().setOnAction(event -> {
            bookSearchVo.setStartPeriod(bookSearch.getStartPeriod_item().getSelectionModel().getSelectedItem());
            bookSearchVo.setEndPeriod(bookSearch.getEndPeriod_item().getSelectionModel().getSelectedItem());
            bookSearchVo.setStartSubjectId(bookSearch.getStartSubject_item().getSelectionModel().getSelectedItem().split(" ")[0]);
            bookSearchVo.setEndSubjectId(bookSearch.getEndSubject_item().getSelectionModel().getSelectedItem().split(" ")[0]);
            bookSearchVo.setLowLevel(Integer.parseInt(bookSearch.getStartLevel_item().getText()));
            bookSearchVo.setHighLevel(Integer.parseInt(bookSearch.getEndLevel_item().getText()));
            updateTable(subjectsList.get(0));
        });

        CustomMenuItem menuItem = new CustomMenuItem(bookSearch);
        menuItem.setHideOnClick(false);
        select_menu.getItems().addAll(menuItem);
        select_menu.setFocusTraversable(false);

        initialSubjectsList();

        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        subjectCol.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        abstractsCol.setCellValueFactory(cellData -> cellData.getValue().abstractsProperty());
        debitCol.setCellValueFactory(cellData -> cellData.getValue().debitProperty());
        creditCol.setCellValueFactory(cellData -> cellData.getValue().creditProperty());
        directionCol.setCellValueFactory(cellData -> cellData.getValue().directionProperty());
        balanceCol.setCellValueFactory(cellData -> cellData.getValue().balanceProperty());
        billTable.setItems(data);

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }

    private void initialSubjectsList() {
        subjectsList = accountBooksBl.getAllExistedSubjectId(factoryId);
            for (String sub: subjectsList) {
                Button btn = new Button(sub);
                btn.setOnAction((ActionEvent e) -> {
                    updateTable(btn.getText());
                    System.out.println(sub);
                });
                rightSubjects.getChildren().add(new Button(sub));
            }
    }

    private void updateTable(String subjectId) {
        data.clear();
        DetailAccountVo detailAccountVo = accountBooksBl.getOneSubjectDetail(subjectId, bookSearchVo, factoryId);
        ArrayList<DetailAccountAmountVo> amountVoArrayList = detailAccountVo.getAmountVoArrayList();
        for (DetailAccountAmountVo vo: amountVoArrayList) {
            data.add(new DetailBillModel(vo.getDate(), vo.getVoucherId(), vo.getSubject(), vo.getAbstracts(), vo.getDebitAmount(), vo.getCreditAmount(), vo.getDirection(), vo.getBalance()));
        }

        billTable.setItems(data);
    }
}

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
import presentation.dataModel.GeneralBillModel;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.viewController.StaticFactory;
import vo.accountBook.BookSearchVo;
import vo.accountBook.TotalAccountAmountVo;
import vo.accountBook.TotalAccountVo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2017/9/6
 */
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
    @FXML
    private TableColumn<GeneralBillModel, Number> balanceCol;

    @FXML
    private MenuButton select_menu;

    private BookSearch bookSearch = new BookSearch();

    private String factoryId;
    private BookSearchVo bookSearchVo;
    private AccountBooksBlService accountBooksBl;
    private ObservableList<GeneralBillModel> data = FXCollections.observableArrayList();

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
        ArrayList<TotalAccountVo> totalAccountVos = accountBooksBl.getAllSubjectTotal(bookSearchVo, factoryId);

        for (TotalAccountVo vo: totalAccountVos) {
            ArrayList<TotalAccountAmountVo> amountVoArrayList = vo.getAmountVoArrayList();
            for (TotalAccountAmountVo amountVo: amountVoArrayList) {
                data.add(new GeneralBillModel(amountVo.getSubjectId(), amountVo.getSubjectName(), amountVo.getPeriod(), amountVo.getAbstracts(), amountVo.getDebitAmount(), amountVo.getDebitAmount(), amountVo.getDirection(), amountVo.getBalance()));
            }
        }

        billTable.setItems(data);
        periodCol.setCellValueFactory(cellData -> cellData.getValue().periodProperty());
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        subjectCol.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        abstractsCol.setCellValueFactory(cellData -> cellData.getValue().abstractsProperty());
        debitCol.setCellValueFactory(cellData -> cellData.getValue().debitProperty());
        creditCol.setCellValueFactory(cellData -> cellData.getValue().creditProperty());
        directionCol.setCellValueFactory(cellData -> cellData.getValue().directionProperty());
        balanceCol.setCellValueFactory(cellData -> cellData.getValue().balanceProperty());
    }
}

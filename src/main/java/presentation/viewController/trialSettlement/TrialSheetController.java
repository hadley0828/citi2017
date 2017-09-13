package presentation.viewController.trialSettlement;

import businesslogic.TrialSettlementImpl;
import businesslogicservice.TrialSettlementService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import vo.TrialTableItemVo;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/9/11.
 */
public class TrialSheetController {
    @FXML
    private TableView<TrialTableItemVo> tableView;
    @FXML
    private TableColumn<TrialTableItemVo, String> course_id;
    @FXML
    private TableColumn<TrialTableItemVo, String> course_name;
    @FXML
    private TableColumn<TrialTableItemVo, Number> debit;
    @FXML
    private TableColumn<TrialTableItemVo, Number> credit;

    @FXML
    private void initialize(){
        TrialSettlementService service = new TrialSettlementImpl();
        //此处要修改
        ArrayList<TrialTableItemVo> list = service.getTrialTable("001");
        ObservableList<TrialTableItemVo> data = FXCollections.observableArrayList(list);
        course_id.setCellValueFactory(new PropertyValueFactory("id"));
        course_name.setCellValueFactory(new PropertyValueFactory("name"));
        debit.setCellValueFactory(new PropertyValueFactory("debit"));
        credit.setCellValueFactory(new PropertyValueFactory("credit"));
    }
}

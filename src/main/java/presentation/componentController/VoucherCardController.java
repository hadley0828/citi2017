package presentation.componentController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.util.converter.DoubleStringConverter;
import presentation.dataModel.VoucherModel;
import vo.voucher.VoucherAmountVo;

import java.io.IOException;
import java.util.ArrayList;


public class VoucherCardController extends Pane{
    @FXML
    private TableColumn<VoucherModel, String> abstractsCol;
    @FXML
    private TableColumn<VoucherModel, String> subjectCol;
    @FXML
    private TableColumn<VoucherModel, String> debitCol;
    @FXML
    private TableColumn<VoucherModel, String> creditCol;
    @FXML
    private TableView<VoucherModel> cardTable;

    private ArrayList<VoucherAmountVo> singleVoucher;

    public VoucherCardController(ArrayList<VoucherAmountVo> singleVoucher) {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../../component/VoucherCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.singleVoucher = singleVoucher;
        initialCard();
    }

    private void initialCard() {
        ObservableList<VoucherModel> data = FXCollections.observableArrayList();
        for(VoucherAmountVo vo: singleVoucher) {
            data.add(new VoucherModel(vo.getAbstracts(), vo.getSubject(), String.valueOf(vo.getDebitAmount()), String.valueOf(vo.getCreditAmount())));
        }
        cardTable.setItems(data);

        abstractsCol.setCellValueFactory(cellData -> cellData.getValue().abstractsProperty());
        subjectCol.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        debitCol.setCellValueFactory(cellData -> cellData.getValue().debitProperty());
        creditCol.setCellValueFactory(cellData -> cellData.getValue().creditProperty());
    }

}

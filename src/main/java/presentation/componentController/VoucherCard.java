package presentation.componentController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.dataModel.voucherModel;
import vo.voucher.VoucherAmountVo;

import java.util.ArrayList;


public class VoucherCard extends TableView{

    private ArrayList<VoucherAmountVo> singleVoucher;

    public VoucherCard(ArrayList<VoucherAmountVo> singleVoucher) {
            this.singleVoucher = singleVoucher;
            initialCard();
    }

    private void initialCard() {
        TableColumn<voucherModel, String> abstractsCol = new TableColumn<>("摘要");
        TableColumn<voucherModel, String> subjectCol = new TableColumn<>("会计科目");
        TableColumn<voucherModel, Number> debitCol = new TableColumn<>("借方金额");
        TableColumn<voucherModel, Number> creditCol = new TableColumn<>("贷方金额");
        this.getColumns().addAll(abstractsCol, subjectCol, debitCol, creditCol);

        ObservableList<voucherModel> data = FXCollections.observableArrayList();
        for(VoucherAmountVo vo: singleVoucher) {
            data.add(new voucherModel(vo.getAbstracts(), vo.getSubject(), vo.getDebitAmount(), vo.getCreditAmount()));
        }
        this.setItems(data);

        abstractsCol.setCellValueFactory(cellData -> cellData.getValue().abstractsProperty());
        subjectCol.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        debitCol.setCellValueFactory(cellData -> cellData.getValue().debitProperty());
        creditCol.setCellValueFactory(cellData -> cellData.getValue().creditProperty());
    }

}

package presentation.componentController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import presentation.dataModel.VoucherModel;
import vo.voucher.AmountTotalVo;
import vo.voucher.VoucherAmountVo;
import vo.voucher.VoucherVo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2017/9/10
 */
public class VoucherCard extends BorderPane {

    private ArrayList<VoucherAmountVo> singleVoucher;
    private AmountTotalVo amountTotalVo;
    private String date;
    private String voucherId;
    private Button modify_btn;
    private Button delete_btn;

    public VoucherCard(VoucherVo voucher) {
        singleVoucher = voucher.getAmountList();
        amountTotalVo = voucher.getAmountTotalVo();
        date = voucher.getDate();
        voucherId = voucher.getVoucherId();
        initialCard();
        initControl();
    }

    private void initControl() {
        modify_btn = new Button("修改");
        delete_btn = new Button("删除");
        Label date_label = new Label("日期： " + date);
        Label voucher_label = new Label("凭证字号：" + voucherId);

        HBox label_box = new HBox(date_label, voucher_label);
        HBox btn_box = new HBox(modify_btn, delete_btn);

        BorderPane subPane = new BorderPane();
        subPane.setLeft(label_box);
        subPane.setRight(btn_box);
        setTop(subPane);
    }

    private void initialCard() {

        TableView<VoucherModel> cardTable = new TableView<>();
        TableColumn<VoucherModel, String> abstractsCol = new TableColumn<>("摘要");
        TableColumn<VoucherModel, String> subjectCol = new TableColumn<>("科目");
        TableColumn<VoucherModel, String> debitCol = new TableColumn<>("借方金额");
        TableColumn<VoucherModel, String> creditCol = new TableColumn<>("贷方金额");

        cardTable.getColumns().add(abstractsCol);
        cardTable.getColumns().add(subjectCol);
        cardTable.getColumns().add(debitCol);
        cardTable.getColumns().add(creditCol);

        cardTable.setMaxSize(1000, 400);

        abstractsCol.setCellValueFactory(cellData -> cellData.getValue().abstractsProperty());
        subjectCol.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        debitCol.setCellValueFactory(cellData -> cellData.getValue().debitProperty());
        creditCol.setCellValueFactory(cellData -> cellData.getValue().creditProperty());

        ObservableList<VoucherModel> data = FXCollections.observableArrayList();

        for (VoucherAmountVo vo: singleVoucher) {
            data.add(new VoucherModel(vo.getAbstracts(), vo.getSubject(), String.valueOf(vo.getDebitAmount()), String.valueOf(vo.getCreditAmount())));
        }
        data.add(new VoucherModel("合计： ", amountTotalVo.getChineseTotal(), String.valueOf(amountTotalVo.getDebitAmount()), String.valueOf(amountTotalVo.getCreditAmount())));

        cardTable.setItems(data);

        setAlignment(cardTable, Pos.CENTER);
        setCenter(cardTable);
    }

    public Button getDelete_btn() {
        return delete_btn;
    }

    public Button getModify_btn() {
        return modify_btn;
    }
}

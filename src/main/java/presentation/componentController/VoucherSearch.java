package presentation.componentController;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import presentation.screenController.ScreensFramework;
import presentation.viewController.voucher.InquireVoucherController;
import vo.voucher.VoucherSearchVo;

import java.io.IOException;

/**
 * @author Molloh
 * @version 2017/9/13
 */
public class VoucherSearch extends GridPane {
    private Button confirm_btn;
    private Button cancel_btn;
    private Button reset_btn;

    private ComboBox<String> startPeriod_item;
    private ComboBox<String> endPeriod_item;
    private ComboBox<String> voucherKey_item;
    private ComboBox<String> maker_item;
    private ComboBox<String> subject_item;

    private TextField abstracts_item;
    private TextField lowAmount_item;
    private TextField highAmount_item;
    private TextField lowVoucher_item;
    private TextField highVoucher_item;

    private RadioButton id_radio;
    private RadioButton date_radio;

    public VoucherSearch(){
        confirm_btn = new Button("确认");
        cancel_btn = new Button("取消");
        reset_btn = new Button("重置");

        confirm_btn.setOnAction(event -> {
            try {
                OnConfirm();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        reset_btn.setOnAction(event -> OnReset());

        subject_item = new ComboBox<>();
        startPeriod_item = new ComboBox<>();
        endPeriod_item = new ComboBox<>();
        voucherKey_item = new ComboBox<>();
        maker_item = new ComboBox<>();

        abstracts_item = new TextField();
        lowAmount_item = new TextField();
        highAmount_item = new TextField();
        lowVoucher_item = new TextField();
        highVoucher_item = new TextField();

        id_radio = new RadioButton("凭证号排序");
        date_radio = new RadioButton("凭证日期排序");

        Label label_0 = new Label("会计期间：");
        Label label_1 = new Label("凭证字：");
        Label label_2 = new Label("制单人：");
        Label label_3 = new Label("摘要：");
        Label label_4 = new Label("科目：");
        Label label_5 = new Label("金额：");
        Label label_6 = new Label("凭证号：");
        Label label_7 = new Label("排序方式：");
        Label label_8 = new Label("至");
        Label label_9 = new Label("至");
        Label label_10 = new Label("至");

        add(label_0, 1, 0);
        add(label_1, 1, 1);
        add(label_2, 1, 2);
        add(label_3, 1, 3);
        add(label_4, 1, 4);
        add(label_5, 1, 5);
        add(label_6, 1, 6);
        add(label_7, 1, 7);

        add(startPeriod_item, 2, 0);
        add(label_8, 3, 0);
        add(endPeriod_item, 4, 0);
        add(voucherKey_item, 2, 1);
        add(maker_item, 2, 2);
        add(abstracts_item, 2, 3);
        add(subject_item, 2, 4);
        add(lowAmount_item, 2, 5);
        add(label_9, 3,5);
        add(highAmount_item, 4, 5);
        add(lowVoucher_item, 2, 6);
        add(label_10, 3, 6);
        add(highVoucher_item, 4, 6);
        add(id_radio, 2, 7);
        add(date_radio, 3, 7);

        add(confirm_btn, 1, 8);
        add(cancel_btn, 2, 8);
        add(reset_btn, 3, 8);
    }

    private void OnReset() {
        startPeriod_item.setValue("");
        endPeriod_item.setValue("");
        voucherKey_item.setValue("");
        maker_item.setValue("");
        subject_item.setValue("");
        abstracts_item.setText("");
        lowAmount_item.setText("");
        highAmount_item.setText("");
        lowVoucher_item.setText("");
        highVoucher_item.setText("");
        id_radio.setFocusTraversable(true);
        date_radio.setFocusTraversable(false);
    }

    private void OnConfirm() throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource(ScreensFramework.INQUIRE_VOUCHER_SCREEN_FXML));
        InquireVoucherController myScreenController = myLoader.getController();

        VoucherSearchVo searchVo = new VoucherSearchVo();
        searchVo.setStartPeriod(startPeriod_item.getValue());
        searchVo.setEndPeriod(endPeriod_item.getValue());
        searchVo.setCharacter(voucherKey_item.getValue());
        searchVo.setMaker(maker_item.getValue());
        searchVo.setAbstracts(abstracts_item.getText());
        searchVo.setSubjectId(subject_item.getValue());
        searchVo.setLowPrice(Double.parseDouble(lowAmount_item.getText()));
        searchVo.setHighPrice(Double.parseDouble(highAmount_item.getText()));
        searchVo.setLowVoucherNumber(Integer.parseInt(lowVoucher_item.getText()));
        searchVo.setHighVoucherNumber(Integer.parseInt(highVoucher_item.getText()));

        myScreenController.setVoucherSearch(searchVo);
        myScreenController.updateList();
    }

}

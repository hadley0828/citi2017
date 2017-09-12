package presentation.componentController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import vo.accountBook.BookSearchVo;

import java.io.IOException;

public class BookSearch extends GridPane {
    private Button confirm_btn;
    private Button cancel_btn;
    private Button reset_btn;

    private ComboBox<String> startPeriod_item;
    private ComboBox<String> endPeriod_item;
    private ComboBox<String> startSubject_item;
    private ComboBox<String> endSubject_item;

    private TextField startLevel_item;
    private TextField endLevel_item;

    public BookSearch(){
        confirm_btn = new Button("确认");
        cancel_btn = new Button("取消");
        reset_btn = new Button("重置");

        startPeriod_item = new ComboBox<>();
        endSubject_item = new ComboBox<>();
        startPeriod_item = new ComboBox<>();
        endPeriod_item = new ComboBox<>();
        startSubject_item = new ComboBox<>();

        startLevel_item = new TextField();
        endLevel_item = new TextField();

        Label label_0 = new Label("会计期间：");
        Label label_1 = new Label("起始科目：");
        Label label_2 = new Label("结账科目：");
        Label label_3 = new Label("");
    }

    public Button getConfirm_btn() {
        return confirm_btn;
    }

    public ComboBox<String> getStartPeriod_item() {
        return startPeriod_item;
    }

    public ComboBox<String> getEndPeriod_item() {
        return endPeriod_item;
    }

    public ComboBox<String> getStartSubject_item() {
        return startSubject_item;
    }

    public ComboBox<String> getEndSubject_item() {
        return endSubject_item;
    }

    public TextField getStartLevel_item() {
        return startLevel_item;
    }

    public TextField getEndLevel_item() {
        return endLevel_item;
    }

    @FXML
    private void OnReset() {
        startPeriod_item.setValue("");
        endPeriod_item.setValue("");
        startSubject_item.setValue("");
        endSubject_item.setValue("");
        startLevel_item.setText("");
        endLevel_item.setText("");
    }
}

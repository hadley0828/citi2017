package presentation.componentController;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * @author Molloh
 * @version 2017/9/11
 */
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

        reset_btn.setOnAction(event -> OnReset());

        startPeriod_item = new PeriodCombo();
        endPeriod_item = new PeriodCombo();
        startSubject_item = new SubjectsCombo();
        endSubject_item = new SubjectsCombo();

        startLevel_item = new TextField();
        endLevel_item = new TextField();

        Label label_0 = new Label("会计期间：");
        Label label_1 = new Label("起始科目：");
        Label label_2 = new Label("结账科目：");
        Label label_3 = new Label("科目级别：");
        Label label_4 = new Label("至");
        Label label_5 = new Label("至");

        add(label_0, 1, 0);
        add(label_1, 1, 1);
        add(label_2, 1, 2);
        add(label_3, 1, 3);
        add(label_4, 3, 0);
        add(label_5, 3, 3);

        add(startSubject_item, 2, 1);
        add(endSubject_item, 2, 2);
        add(startPeriod_item, 2, 0);
        add(endPeriod_item, 4, 0);
        add(startLevel_item, 2, 3);
        add(endLevel_item, 4, 3);


        add(confirm_btn, 1, 4);
        add(cancel_btn, 2, 4);
        add(reset_btn, 3, 4);
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

    private void OnReset() {
        startPeriod_item.setValue("");
        endPeriod_item.setValue("");
        startSubject_item.setValue("");
        endSubject_item.setValue("");
        startLevel_item.setText("");
        endLevel_item.setText("");
    }
}

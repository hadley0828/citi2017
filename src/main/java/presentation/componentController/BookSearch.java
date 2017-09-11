package presentation.componentController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import vo.accountBook.BookSearchVo;

import java.io.IOException;

public class BookSearch extends GridPane {
    @FXML
    private Button confirm_btn;
    @FXML
    private Button cancel_btn;

    @FXML
    private ComboBox<String> startPeriod_item;
    @FXML
    private ComboBox<String> endPeriod_item;
    @FXML
    private ComboBox<String> startSubject_item;
    @FXML
    private ComboBox<String> endSubject_item;

    @FXML
    private TextField startLevel_item;
    @FXML
    private TextField endLevel_item;

    public BookSearch(){
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../../component/BookSearch.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

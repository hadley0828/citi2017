package presentation.viewController.trialSettlement;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;


/**
 * Created by 费慧通 on 2017/9/11.
 */
public class SettleController {
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;

    @FXML
    private void initialize(){
        label1.setText("2017年第6期");
        label2.setText("2017年第7期");
        label3.setText("2017年第8期");
        label4.setText("2017年第9期");
        label1.setDisable(true);
        label2.setDisable(true);
        label3.setDisable(true);
        label4.setCursor(Cursor.CLOSED_HAND);
    }
}

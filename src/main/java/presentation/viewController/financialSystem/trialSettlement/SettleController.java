package presentation.viewController.financialSystem.trialSettlement;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import presentation.StaticFactory;
import vo.userManagement.UserVO;

import java.time.LocalDate;


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
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        label1.setText(getTime(year,month-3));
        label2.setText(getTime(year,month-2));
        label3.setText(getTime(year,month-1));
        label4.setText(getTime(year,month));
        UserVO vo = StaticFactory.getUserVO();
        if(vo.getType().equals("admin")){
            label1.setDisable(true);
            label2.setDisable(true);
            label3.setDisable(true);
            label4.setCursor(Cursor.CLOSED_HAND);
        }else if(vo.getType().equals("normal")){
            label1.setDisable(true);
            label2.setDisable(true);
            label3.setDisable(true);
            label4.setDisable(true);
        }
    }

    private String getTime(int year, int month){
        return year+"年第"+month+"期";
    }
}

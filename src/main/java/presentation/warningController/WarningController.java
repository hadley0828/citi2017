package presentation.warningController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * Created by Chris on 2017/9/13.
 */
public class WarningController {
    @FXML
    Text text;
    @FXML
    Button close;

    String warning;

    RunWarning runWarning;

    public WarningController(){

    }

    public void CloseClicked(){
        this.runWarning.getPrimaryStage().close();
    }

    public void initialize(){

        warning=RunWarning.GetWarning();
        text.setText(warning);
    }
    public void setRunWarning(RunWarning runWarning){
        this.runWarning=runWarning;
        initialize();
    }


}

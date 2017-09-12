package presentation.settingController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.screenController.ScreensFramework;

/**
 * Created by Chris on 2017/9/9.
 */
public class ZhangtaoController implements ControlledScreen{
    @FXML
    Button NewZT;
    @FXML
    Label NumberOfZt;
    @FXML
    TableView<Object> tableview;
    @FXML
    TableColumn<Object,String> ZTname;

    private ScreensController myController;

    @FXML
    public void initialize(){



    }

//    public void newZTClicked(){
//        myController.setScreen(ScreensFramework.)
//
//    }


    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

}

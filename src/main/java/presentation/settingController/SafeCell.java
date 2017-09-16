package presentation.settingController;

import businesslogic.SettingImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.StaticFactory;
import presentation.warningController.RunWarning;
import vo.Inventory.SafeInventoryVo;
import vo.userManagement.SubjectsInitialVO;
import vo.userManagement.SubjectsVO;

import java.util.ArrayList;

//import static presentation.settingController.QCController.isSet;

/**
 * Created by Chris on 2017/9/14.
 */
public class SafeCell extends TableCell<SafeInventoryVo,Boolean> {
    final TextField cellText = new TextField();


    SafeCell(){


        cellText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                SafeInventoryVo vo=getTableView().getItems().get(getIndex());
                vo.setVariety(cellText.getText());
                if(vo.getVariety().isEmpty()||vo.getName().isEmpty()){

                }else{

                    SettingImpl impl=new SettingImpl();
                    ArrayList<SafeInventoryVo> list=new ArrayList<>();
                    list.add(vo);
                    impl.setSafetyInventory(list,StaticFactory.getUserVO().getCompanyID());


                }

            }


    });
    }


    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {

        super.updateItem(t, empty);
        if(!empty) {
                setGraphic(cellText);
        }
    }
}

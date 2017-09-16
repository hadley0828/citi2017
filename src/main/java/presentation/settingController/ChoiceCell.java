package presentation.settingController;

/**
 * Created by Chris on 2017/9/16.
 */
import businesslogic.SettingImpl;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.StaticFactory;
import presentation.warningController.RunWarning;
import vo.Inventory.SafeInventoryVo;
import vo.userManagement.SubjectsInitialVO;
import vo.userManagement.SubjectsVO;

import java.util.ArrayList;

/**
 * Created by Chris on 2017/9/14.
 */
public class ChoiceCell extends TableCell<SafeInventoryVo,Boolean> {
    final ChoiceBox<String> cellBox = new ChoiceBox<>();

    ChoiceCell() {
        SafeInventoryVo svo=getTableView().getItems().get(getIndex());
        cellBox.setValue(svo.getName());

        ArrayList<String> list = new ArrayList<>();
        list.add("原材料");
        list.add("产品");
        cellBox.getItems().addAll(list);
        cellBox.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        SafeInventoryVo vo=getTableView().getItems().get(getIndex());
                        vo.setName(cellBox.getSelectionModel().getSelectedItem());
                        if(vo.getVariety().isEmpty()||vo.getName().isEmpty()){

                        }else{

                            SettingImpl impl=new SettingImpl();
                            ArrayList<SafeInventoryVo> list=new ArrayList<>();
                            list.add(vo);
                            impl.setSafetyInventory(list,StaticFactory.getUserVO().getCompanyID());


                        }
                    }
                }
        );


    }







                //调用接口










                //调用接口




    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            setGraphic(cellBox);
        }
    }
}

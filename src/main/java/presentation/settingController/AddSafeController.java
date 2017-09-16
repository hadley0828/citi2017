package presentation.settingController;

import businesslogic.SettingImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.StaticFactory;
import presentation.warningController.RunWarning;
import vo.Inventory.SafeInventoryVo;

import java.util.ArrayList;

/**
 * Created by Chris on 2017/9/16.
 */
public class AddSafeController {
    @FXML
    private  ChoiceBox<String> name;
    @FXML
    private TextField variety;
    @FXML
    private TextField sum;
    @FXML
    private Button save;
    @FXML
    private Button cancel;

    private  static Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize(){
        name.getItems().add("原材料");
        name.getItems().add("产品");



    }

    public void saveClicked(){
        if(name.getSelectionModel().isEmpty()||variety.getText().isEmpty()||sum.getText().isEmpty()){
            RunWarning rw=new RunWarning();
            rw.SetWarning("请输入完整信息！");
            rw.start(new Stage());
        }else{
            SettingImpl impl=new SettingImpl();
            SafeInventoryVo vo=new SafeInventoryVo(name.getSelectionModel().getSelectedItem(),variety.getText(),Integer.parseInt(sum.getText()));
            ArrayList<SafeInventoryVo> list=new ArrayList<>();
            list.add(vo);
            Boolean rm=impl.setSafetyInventory(list, StaticFactory.getUserVO().getCompanyID());
            if(rm){
                RunWarning rw=new RunWarning();
                rw.SetWarning("成功！");
                rw.start(new Stage());
                stage.close();
                new QCController().getList().add(vo);
            }
        }

    }

    public void cancelClicked(){
        stage.close();

    }
}

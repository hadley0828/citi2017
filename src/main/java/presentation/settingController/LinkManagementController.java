package presentation.settingController;

import businesslogic.SettingImpl;
import businesslogic.SupplyChainImpl;
import businesslogic.UserManagementImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.StaticFactory;
import presentation.warningController.RunWarning;

import vo.userManagement.AccountSetVO;
import vo.userManagement.UserVO;

/**
 * Created by Chris on 2017/9/14.
 */
public class LinkManagementController implements ControlledScreen {
    @FXML
    private RadioButton supply;
    @FXML
    private RadioButton product;
    @FXML
    private RadioButton sale;
    @FXML
    private TextField up;
    @FXML
    private TextField down;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;




    @FXML
    public void initialize(){
        UserVO vo= StaticFactory.getUserVO();
        String[] companys=new SupplyChainImpl().getTheCompanys(vo.getCompanyID());
        AccountSetVO accountvo = new UserManagementImpl().getAccountSetByCompanyID(vo.getCompanyID());
        String place=accountvo.getChainPlace();
        if(place.equals("生产商")){
            product.setSelected(true);
            sale.setDisable(true);
            supply.setDisable(true);
            up.setText(companys[0]);
            down.setText(companys[2]);
        }else if(place.equals("分销商")){
            sale.setSelected(true);
            supply.setDisable(true);
            product.setDisable(true);
            up.setText(companys[1]);
            down.setDisable(true);



        }else if (place.equals("供应商")){
            supply.setSelected(true);
            sale.setDisable(true);
            product.setDisable(true);
            down.setText(companys[1]);
            up.setDisable(true);

        }








    }

    public void saveClicked(){
        String  type="";
        if(supply.isSelected()){
            type="供应商";
        }else if(product.isSelected()){
            type="生产商";
        }else if(sale.isSelected()){
            type="分销商";
        }
        String upper=up.getText();
        String downner=down.getText();
        if(upper.isEmpty()){
            upper.equals("");
        }
        if(downner.isEmpty()){
            downner.equals("");
        }
        UserVO vo= StaticFactory.getUserVO();
        SettingImpl ipl=new SettingImpl();
        Boolean rm=ipl.setSupplyChain(vo.getCompanyID(),type,upper,downner);
        if(rm==false){
            RunWarning runWarning=new RunWarning();
            runWarning.SetWarning("修改失败！");
            runWarning.start(new Stage());
        }else if(rm==true){
            RunWarning runWarning=new RunWarning();
            runWarning.SetWarning("修改成功！");
            runWarning.start(new Stage());

        }





    }

    public void cancelClicked(){

    }

    public void supplyClicked(){
        product.setSelected(true);
        sale.setSelected(true);
        up.setDisable(true);
        down.setDisable(false);

    }
    public void productClicked(){
        sale.setSelected(true);
        supply.setSelected(true);
        up.setDisable(false);
        down.setDisable(false);
    }
    public void saleClicked(){
        supply.setSelected(true);
        product.setSelected(true);
        up.setDisable(false);
        down.setDisable(true);

    }
    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

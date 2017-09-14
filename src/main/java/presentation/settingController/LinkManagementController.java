package presentation.settingController;

import businesslogic.SupplyChainImpl;
import businesslogic.UserManagementImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import presentation.StaticFactory;
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
            up.setText(companys[0]);
            down.setText(companys[2]);
        }else if(place.equals("分销商")){
            sale.setSelected(true);



        }else if (place.equals("供应商")){
            supply.setSelected(true);

        }








    }

    public void saveClicked(){

    }

    public void cancelClicked(){

    }

    public void supplyClicked(){
        product.setSelected(false);
        sale.setSelected(false);

    }
    public void productClicked(){
        sale.setSelected(false);
        supply.setSelected(false);
    }
    public void saleClicked(){
        supply.setSelected(false);
        product.setSelected(false);

    }
    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}

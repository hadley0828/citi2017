package presentation.viewController.supplyChainManagement.financialInstitutions;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import presentation.viewController.supplyChainManagement.stock.DistributorController;
import vo.CompanyVo;

/**
 * Created by YZ on 2017/9/14.
 */
public class CompanyInfoController {
    @FXML
    private TextField name;
    @FXML
    private TextField industry;
    @FXML
    private TextField locationat;
    @FXML
    private TextField code;
    @FXML
    private TextField phone;
    @FXML
    private javafx.scene.image.ImageView back;

    FinancialInstitutionsController controller=new FinancialInstitutionsController();

    CompanyVo thisvo=controller.getCompany();

    @FXML
    public void initialize(){
//        System.out.print(thisvo);
        name.setText(thisvo.getCompanyName());
        industry.setText(thisvo.getHangye());
        locationat.setText(thisvo.getSuoZaiDi());
        code.setText(thisvo.getXinYongDaiMa());
        phone.setText(thisvo.getDianHua());

        back.setOnMouseEntered(e->{
            back.setCursor(Cursor.HAND);
        });
        back.setOnMouseExited(e->{
            back.setCursor(Cursor.DEFAULT);
        });
    }
    public void backtotable(){
        FinancialInstitutionsController.chartStage.close();
    }
}

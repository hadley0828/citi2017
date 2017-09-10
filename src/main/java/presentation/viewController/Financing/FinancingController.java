package presentation.viewController.Financing;

import javafx.fxml.FXML;

import java.awt.*;

/**
 * Created by YZ on 2017/9/9.
 */
public class FinancingController {
    @FXML
    private TextField getDebtNum;
    @FXML
    private TextField getDebtMortgage;
    @FXML
    private TextField advice;
    @FXML
    private TextField stockNum;
    @FXML
    private TextField stockMortgage;
    @FXML
    private TextField advice2;


    @FXML
    public void initialize(){
        getDebtNum.setText("123");
    }

}

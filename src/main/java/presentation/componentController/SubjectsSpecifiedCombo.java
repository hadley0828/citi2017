package presentation.componentController;

import businesslogic.UserManagementImpl;
import businesslogic.VoucherBlImpl;
import businesslogicservice.UserManagementService;
import businesslogicservice.VoucherBlService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.StaticFactory;
import vo.userManagement.AccountSetVO;
import vo.voucher.SubjectBasicVo;

import java.io.IOException;
import java.util.ArrayList;


public class SubjectsSpecifiedCombo extends ComboBox<String> {

    private ArrayList<SubjectBasicVo> subjectArray;

    public SubjectsSpecifiedCombo() {
        VoucherBlService voucherBl = new VoucherBlImpl();
        subjectArray = voucherBl.getAllSubjectBasics(StaticFactory.getUserVO().getCompanyID());
        for (SubjectBasicVo vo: subjectArray) {
            getItems().add(vo.getSubjectId() + " " + vo.getSubjectName());
        }

        getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String judger = newValue.split(" ")[1];
                Stage dialog = new Stage();
                dialog.initStyle(StageStyle.UTILITY);
                if (judger.equals("应收账款")){
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("../../view/voucher/AidCharge.fxml"));
                        Scene scene = new Scene(root);
                        dialog.setScene(scene);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    dialog.show();
                } else if (judger.equals("应付账款")){
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("../../view/voucher/AidPay.fxml"));
                        Scene scene = new Scene(root);
                        dialog.setScene(scene);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    dialog.show();
                } else if (judger.equals("材料采购") || judger.equals("在途物资") || judger.equals("原材料") || judger.equals("库存商品") || judger.equals("委托加工物资") || judger.equals("工程物资")) {
                    String compayId = StaticFactory.getUserVO().getUserID();
                    UserManagementService userManagementService = new UserManagementImpl();
                    AccountSetVO accountSetVO = userManagementService.getAccountSetByCompanyID(compayId);
                    String str = accountSetVO.getChainPlace();
                    if (str.equals("供应商")) {
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("../../view/stockInfo/SupplierInfo.fxml"));
                            Scene scene = new Scene(root);
                            dialog.setScene(scene);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        dialog.show();
                    } else if (str.equals("生产商")) {
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("../../view/stockInfo/ProducerInfo.fxml"));
                            Scene scene = new Scene(root);
                            dialog.setScene(scene);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        dialog.show();
                    } else if (str.equals("分销商")) {
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("../../view/stockInfo/DistributionInfo.fxml"));
                            Scene scene = new Scene(root);
                            dialog.setScene(scene);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        dialog.show();
                    }

                }
            }
        });
    }
}

package presentation.componentController;

import businesslogic.VoucherBlImpl;
import businesslogicservice.VoucherBlService;
import javafx.scene.control.ComboBox;
import presentation.viewController.StaticFactory;
import vo.voucher.SubjectBasicVo;

import java.util.ArrayList;

public class SubjectsCombo extends ComboBox<String> {

    private ArrayList<SubjectBasicVo> subjectArray;

    public SubjectsCombo() {
        VoucherBlService voucherBl = new VoucherBlImpl();
        subjectArray = voucherBl.getAllSubjectBasics(StaticFactory.getUserVO().getCompanyID());
        for (SubjectBasicVo vo: subjectArray) {
            getItems().add(vo.getSubjectId() + " " + vo.getSubjectName());
        }
    }

}

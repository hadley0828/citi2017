package presentation.componentController;

import businesslogic.SettingImpl;
import businesslogic.VoucherBlImpl;
import businesslogicservice.SettingService;
import businesslogicservice.VoucherBlService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import presentation.StaticFactory;
import vo.userManagement.SubjectsVO;
import vo.voucher.SubjectBasicVo;

import java.util.ArrayList;

public class SubjectsCombo extends ComboBox<String> {

    public SubjectsCombo() {
        SettingService settingService = new SettingImpl();
        ArrayList<SubjectsVO> subjectArray = settingService.getAllSubjects();
        for (SubjectsVO vo: subjectArray) {
            getItems().add(vo.getSubjectsID() + " " + vo.getSubjectsName());
        }
    }

}

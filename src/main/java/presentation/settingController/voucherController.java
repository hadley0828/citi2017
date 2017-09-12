package presentation.settingController;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


/**
 * Created by Chris on 2017/9/12.
 */
public class voucherController {
    @FXML
   private TableView<Object> voucherTable;

    @FXML
    private TableColumn<Object,String> Voucher;

    @FXML
    private TableColumn<Object,String>object;

    @FXML
    private TableColumn<Object,String> IsDefault;

    @FXML
    public void initialize(){
        /**
         * 初始化表格
         */
    }
}

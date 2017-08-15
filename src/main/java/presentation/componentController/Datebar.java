package presentation.componentController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * Created by YZ on 2017/8/10.
 */
public class Datebar extends HBox{
    @FXML
    private Label year;
    @FXML
    private Label month;
    @FXML
    private ImageView last_month;
    @FXML
    private ImageView later_month;


    public Datebar(){
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../../component/Datebar/Datebar.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

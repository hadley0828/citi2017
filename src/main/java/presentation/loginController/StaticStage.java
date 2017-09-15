package presentation.loginController;

import javafx.stage.Stage;

/**
 * Created by Chris on 2017/9/15.
 */
public class StaticStage {
    private static Stage stage;

    public Stage GetStage(){
        return stage;
    }

    public void SetStage(Stage stage){
        StaticStage.stage=stage;
    }
}

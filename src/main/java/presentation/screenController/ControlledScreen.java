package presentation.screenController;

/**
 * @author Molloh
 * @version 2017/9/5
 * 定义接口，提供上层界面节点注入的方法
 */
public interface ControlledScreen {

    //allow the injection of the Parent ScreenPane
    void setScreenParent(ScreensController screenPage);

}

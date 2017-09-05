package po.Inventory;

/**
 * Created by loohaze on 2017/9/5 下午9:04
 *
 * 各个商库存父类
 * sender --> 发出方
 * receiver --> 收入方
 */
public class InventorySuperClass {

    private String sender;

    private String receiver;

    public InventorySuperClass(String sender, String receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public InventorySuperClass() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}

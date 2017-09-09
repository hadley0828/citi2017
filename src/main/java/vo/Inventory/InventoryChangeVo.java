package vo.Inventory;

/**
 * Created by 费慧通 on 2017/9/9.
 *
 * 折线图——库存量随着时间的变化的数据
 */
public class InventoryChangeVo {
    private String time;
    private int inventory;

    public InventoryChangeVo(String time, int inventory){
        this.time = time;
        this.inventory = inventory;
    }

    public String getTime() {
        return time;
    }

    public int getInventory() {
        return inventory;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}

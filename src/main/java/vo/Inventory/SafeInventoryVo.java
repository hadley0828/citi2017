package vo.Inventory;

/**
 * Created by 费慧通 on 2017/9/9.
 *
 * 原材料安全库存量表格的元组
 */
public class SafeInventoryVo {
    private String name;    //只有两个值："原材料"和"产品"
    private String variety;       //种类
    private int safe_inventory=0;     //安全库存量

    public SafeInventoryVo(String name, String variety, int safe_inventory){
        this.name = name;
        this.variety = variety;
        this.safe_inventory = safe_inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public int getSafe_inventory() {
        return safe_inventory;
    }

    public void setSafe_inventory(int safe_inventory) {
        this.safe_inventory = safe_inventory;
    }
}

package po;

/**
 * Created by loohaze on 2017/9/5 下午8:47
 * 分销商PO
 *
 * id --> 分销商id
 * name --> 分销商名称
 * productInventory --> 产品库存
 */
public class DistributorPO {

    private String id;

    private String name;

    private int productInventory;

    public DistributorPO(String id, String name, int productInventory) {
        this.id = id;
        this.name = name;
        this.productInventory = productInventory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(int productInventory) {
        this.productInventory = productInventory;
    }
}

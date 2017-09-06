package po;

/**
 * Created by loohaze on 2017/9/5 下午8:45
 * 生产商PO
 *
 * id --> 生产商id
 * name --> 生产商名称
 * rawMaterialInventory --> 原材料库存
 * productInventory --> 产品库存
 */
public class ProducerPO {

    private String id;

    private String name;

    private int rawMaterialInventory;

    private int productInventory;

    public ProducerPO(String id, String name, int rawMaterialInventory, int productInventory) {
        this.id = id;
        this.name = name;
        this.rawMaterialInventory = rawMaterialInventory;
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

    public int getRawMaterialInventory() {
        return rawMaterialInventory;
    }

    public void setRawMaterialInventory(int rawMaterialInventory) {
        this.rawMaterialInventory = rawMaterialInventory;
    }

    public int getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(int productInventory) {
        this.productInventory = productInventory;
    }
}

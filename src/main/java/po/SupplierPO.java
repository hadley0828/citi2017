package po;

/**
 * Created by loohaze on 2017/9/5 下午8:43
 * 供应商PO
 *
 * id --> 供应商id
 * name --> 供应商民称
 * rawMaterialInventory --> 原材料库存
 */
public class SupplierPO {

    private String id;

    private String name;

    private int rawMaterialInventory;

    public SupplierPO(String id, String name, int rawMaterialInventory) {
        this.id = id;
        this.name = name;
        this.rawMaterialInventory = rawMaterialInventory;
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
}

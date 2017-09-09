package dataTest;

import data.InventoryServiceImpl;
import dataservice.InventoryService;
import org.junit.Before;

/**
 * Created by loohaze on 2017/9/6 上午10:14
 */
public class InventoryDataServiceImplTest {

    private InventoryService inventoryService;

    @Before
    public void init(){
        inventoryService = new InventoryServiceImpl();
    }


}

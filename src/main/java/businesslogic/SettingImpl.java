package businesslogic;

import businesslogicservice.InventoryManagementService;
import businesslogicservice.SettingService;
import data.InventoryServiceImpl;
import data.SettingDataServiceImpl;
import data.UserManagementServiceImpl;
import dataservice.InventoryService;
import dataservice.SettingDataService;
import dataservice.UserManagementService;
import po.Inventory.ProductSafeInventoryPo;
import po.Inventory.RawMaterialSafeInventoryPo;
import vo.Inventory.SafeInventoryVo;
import vo.userManagement.SubjectsInitialVO;
import vo.userManagement.SubjectsVO;
import vo.userManagement.UserVO;

import java.util.ArrayList;

/**
 * Created by loohaze on 2017/9/13 上午11:00
 */
public class SettingImpl implements SettingService{

    UserManagementService userdataservice;
    SettingDataService settingDataService;
    InventoryManagementService inventoryservice;

    public SettingImpl() {
        userdataservice = new UserManagementServiceImpl();
        settingDataService = new SettingDataServiceImpl();
        inventoryservice = new InventoryManagementImpl();
    }


    @Override
    public ArrayList<String> getAllSuperIndustry() {
        return settingDataService.getAllSuperIndustry();
    }

    @Override
    public ArrayList<String> getAllSubIndustry(String superIndustry) {
        return settingDataService.getAllSubIndustry(superIndustry);
    }

    @Override
    public ArrayList<SubjectsVO> getAllSubjects() {
        return settingDataService.getAllSubjects();
    }

    @Override
    public ArrayList<UserVO> getAllUserVoByAccountId(String account_id) {
        return userdataservice.getAllUserVoByAccountId(account_id);
    }

    @Override
    public boolean setInitialSubjects(ArrayList<SubjectsInitialVO> list, String company_id) {
        return settingDataService.setInitialSubjects(list,company_id);
    }

    @Override
    public boolean setSafetyInventory(ArrayList<SafeInventoryVo> list, String company_id) {
        try{
            inventoryservice.SaveSafeInventory(company_id,list);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean setSupplyChain(String company_id, String chainindex, String upper, String down) {
        return settingDataService.setSupplyChain(company_id,chainindex,upper,down);
    }

    @Override
    public ArrayList<SafeInventoryVo> getSafeInventory(String company_id) {
        InventoryService service = new InventoryServiceImpl();
        ArrayList<RawMaterialSafeInventoryPo> list1 = service.getAllRawMaterialSafeInventory(company_id);
        ArrayList<ProductSafeInventoryPo> list2 = service.getAllProductSafeInventory(company_id);

        ArrayList<SafeInventoryVo> result = new ArrayList<>();
        for(int i=0;i<list1.size();i++){
            RawMaterialSafeInventoryPo po = list1.get(i);
            result.add(new SafeInventoryVo("原材料", po.getRaw_material_variety(), po.getSafe_inventory()));
        }
        for(int i=0;i<list2.size();i++){
            ProductSafeInventoryPo po = list2.get(i);
            result.add(new SafeInventoryVo("产品", po.getProduct_variety(), po.getSafe_inventory()));
        }
        return result;
    }
}

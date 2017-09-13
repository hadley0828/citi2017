package businesslogic;

import businesslogicservice.UserManagementService;
import data.UserManagementServiceImpl;
import po.AccountSetPO;
import po.UserCompanyPO;
import po.UserFinancialPO;
import util.EnumPackage.ResultMessage;
import vo.userManagement.AccountSetVO;
import vo.userManagement.FinancialUserVO;
import vo.userManagement.UserVO;

import java.sql.Date;

/**
 * Created by loohaze on 2017/9/12 上午1:28
 */
public class UserManagementImpl implements UserManagementService{

    private dataservice.UserManagementService dataservice;

    public UserManagementImpl() {
        dataservice = new UserManagementServiceImpl();
    }



    @Override
    public ResultMessage insertOneCompanyUser(UserVO vo, String password) {
        UserCompanyPO po = UserCompanyVO2PO(vo);
        ResultMessage message = dataservice.insertPassword(vo.getUserID(),password);
        if (message == ResultMessage.SHORT_PASSWORD){
            return message;
        }
        ResultMessage message2 = dataservice.insertOneCompanyUser(po);
        if (message2 == ResultMessage.EXIST_USERID){
            return message2;
        }
        if (message == ResultMessage.SUCCESS && message2 == ResultMessage.SUCCESS){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAIL;
        }
    }

    @Override
    public ResultMessage insertOneFinancialUser(FinancialUserVO vo, String password) {
        UserFinancialPO po = UserFinancialVO2PO(vo);
        ResultMessage message = dataservice.insertPassword(vo.getId(),password);
        if (message == ResultMessage.SHORT_PASSWORD){
            return message;
        }
        ResultMessage message2 = dataservice.insertOneFinancialUser(po);
        if (message2 == ResultMessage.EXIST_USERID){
            return message2;
        }
        if (message == ResultMessage.SUCCESS && message2 == ResultMessage.SUCCESS){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAIL;
        }
    }

    @Override
    public ResultMessage modifyPassword(String id, String rawpassword, String newpassword) {
        return dataservice.modifyPassword(id,rawpassword,newpassword);
    }

    @Override
    public UserVO getOneCompanyUser(String id) {
//        UserCompanyPO po = dataservice.getOneCompanyUser(id,company_id);
//        return UserCompanyPO2VO(po);
        return null;
    }

    @Override
    public FinancialUserVO getOneFinancialUser(String id) {
        UserFinancialPO po = dataservice.getOneFinancialUser(id);
        return FinancialUserPO2VO(po);
    }

    @Override
    public ResultMessage register(AccountSetVO vo) {
        AccountSetPO po = AccountSetVO2PO(vo);
        return dataservice.insertOneAccountSet(po);
    }

    @Override
    public ResultMessage modifyAccountSet(AccountSetVO vo) {
        AccountSetPO po = AccountSetVO2PO(vo);
        return dataservice.modifyOneAccountSet(po);
    }

    @Override
    public AccountSetVO getAccountSetByUserID(String userID) {
        AccountSetPO po = dataservice.getAccountSetByUserID(userID);
        return AccountSetPO2VO(po);
    }

    @Override
    public AccountSetVO getAccountSetByCompanyID(String companyID) {
        AccountSetPO po = dataservice.getAccountSetByCompanyID(companyID);
        return AccountSetPO2VO(po);
    }



    @Override
    public ResultMessage loginIn(String id, String password) {
        return dataservice.loginIn(id,password);
    }

    @Override
    public Boolean isCompanyUser(String id) {
        return dataservice.isCompanyUser(id);
    }

    @Override
    public Boolean isFinancialUser(String id) {
        return dataservice.isFinancialUser(id);
    }



    private UserCompanyPO UserCompanyVO2PO(UserVO vo){
        UserCompanyPO po = new UserCompanyPO();
        po.setUserID(vo.getUserID());
        po.setCompanyID(vo.getCompanyID());
        po.setType(vo.getType());
        po.setAccountID(vo.getAccountID());

        return po;
    }

    private UserVO UserCompanyPO2VO(UserCompanyPO po){
        UserVO vo = new UserVO();
        vo.setAccountID(po.getAccountID());
        vo.setCompanyID(po.getCompanyID());
        vo.setType(po.getType());
        vo.setUserID(po.getUserID());
        return vo;
    }

    private UserFinancialPO UserFinancialVO2PO(FinancialUserVO vo){
        UserFinancialPO po = new UserFinancialPO();
        po.setLegalPersonQualification(vo.getLegalPersonQualification());
        po.setLegalPerson(vo.getLegalPerson());
        po.setFinancialKey(vo.getFinancialKey());
        po.setAddress(vo.getAddress());
        po.setName(vo.getName());
        po.setUserID(vo.getId());
        return po;
    }

    private FinancialUserVO FinancialUserPO2VO(UserFinancialPO po){
        FinancialUserVO vo = new FinancialUserVO();
        vo.setAddress(po.getAddress());
        vo.setFinancialKey(po.getFinancialKey());
        vo.setId(po.getUserID());
        vo.setLegalPerson(po.getLegalPerson());
        vo.setLegalPersonQualification(po.getLegalPersonQualification());
        vo.setName(po.getName());
        return vo;
    }


    private AccountSetVO AccountSetPO2VO(AccountSetPO po){
        AccountSetVO vo = new AccountSetVO();
        vo.setAccount_id(po.getAccountID());
        vo.setChainPlace(po.getChainPlace());
        vo.setCompany_id(po.getCompanyID());
        vo.setCompany_name(po.getCompanyName());
        vo.setContact(po.getContact());
        vo.setCreditCode(po.getCreditCode());
        vo.setDate(po.getDate().toString());
        vo.setIndustry(po.getIndustry());
        vo.setLocation(po.getLocation());
        return vo;
    }

    private AccountSetPO  AccountSetVO2PO(AccountSetVO vo){
        AccountSetPO po = new AccountSetPO();
        po.setChainPlace(vo.getChainPlace());
        po.setContact(vo.getContact());
        po.setCreditCode(vo.getCreditCode());
        po.setDate(Date.valueOf(vo.getDate()));
        po.setIndustry(vo.getIndustry());
        po.setLocation(vo.getLocation());
        po.setCompanyName(vo.getCompany_name());
        po.setAccountID(vo.getAccount_id());
        po.setCompanyID(vo.getCompany_id());
        return po;
    }
}

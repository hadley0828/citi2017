package data;

import dataservice.VoucherDataService;
import po.VoucherAmountPO;
import po.VoucherPO;
import po.VoucherTemplateAmountPO;
import po.VoucherTemplatePO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by loohaze on 2017/8/13.
 */
public class VoucherDataServiceImpl implements VoucherDataService {

    SqlManager sqlManager = SqlManager.getSqlManager();

    @Override
    public boolean addVoucher(VoucherPO voucherPO,String factoryId) {
        if(voucherPO == null){
            return false;
        }

        try{
            sqlManager.getConnection();
            List<Object> params = new ArrayList<>();
            params.add(factoryId);
            params.add(voucherPO.getId());
            params.add(voucherPO.getDate());
            params.add(voucherPO.isAddReceipts());
            params.add(voucherPO.getVoucher_maker());
            params.add(voucherPO.getRemark());
            String sql = sqlManager.appendSQL("INSERT INTO voucher VALUES",params.size());
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteOneVoucher(String voucherId,String factoryId) {
        try{
            sqlManager.getConnection();

            List<Object> params = new ArrayList<>();
            params.add(voucherId);
            params.add(factoryId);
            String sql = "DELETE FROM voucher WHERE v_id=? AND company_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();

            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean deleteAllVoucher(String factoryId) {
        try{
            sqlManager.getConnection();
            List<Object> params = new ArrayList<>();
            params.add(factoryId);

            String sql = "DELETE FROM voucher WHERE company_id=?";

            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean modifyOneVoucher(String voucherId, VoucherPO voucherPO,String factoryId) {
        try{
            ArrayList<String> vidList = getVIdList();
            if(!vidList.contains(voucherId)){
                addVoucher(voucherPO,factoryId);
                return true;
            }else if(voucherId.equals(voucherPO.getId())){
                sqlManager.getConnection();
                List<Object> params = new ArrayList<>();
                params.add(voucherPO.getId());
                params.add(voucherPO.getDate());
                params.add(voucherPO.isAddReceipts());
                params.add(voucherPO.getVoucher_maker());
                params.add(voucherPO.getRemark());
                params.add(voucherId);
                params.add(factoryId);

                String sql = "UPDATE voucher SET v_id=?, date=?, is_addedreceipts=?, voucher_maker=?, remark=? WHERE v_id=? AND company_id=?";
                sqlManager.executeUpdateByList(sql,params);
                sqlManager.releaseAll();
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public VoucherPO findOneVoucher(String voucherId,String factoryId) {
        try{
            ArrayList<String> vidList = getVIdList();
            if(!vidList.contains(voucherId)){
                return null;
            }else {
                sqlManager.getConnection();
                Map<String,Object> map = new HashMap<>();
                String sql = "SELECT * FROM voucher WHERE v_id=? AND company_id=?";
                map = sqlManager.querySimple(sql,new Object[]{voucherId,factoryId});

                VoucherPO po = getVoucherPOByMap(map);
                sqlManager.releaseAll();
                return po;
            }
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public ArrayList<VoucherPO> findSeveralVoucher(ArrayList<String> idList,String factoryId) {
        ArrayList<VoucherPO> list = new ArrayList<>();
        VoucherPO po = new VoucherPO();
        for (String id : idList){
            try {
                po = findOneVoucher(id,factoryId);
            }catch (Exception e){
                System.out.println("Find SeveralVoucher Fail");
            }
            list.add(po);
        }
        return list;
    }

    @Override
    public ArrayList<VoucherPO> findAllVoucher(String factoryId) {
        ArrayList<VoucherPO> list = new ArrayList<>();
        try{
            sqlManager.getConnection();

            String sql = "SELECT * FROM voucher WHERE company_id=?";
            ArrayList<Map<String,Object>> maplist = sqlManager.queryMulti(sql,new Object[]{factoryId});
            for (Map<String,Object> map : maplist){
                list.add(getVoucherPOByMap(map));
            }

        }catch (Exception e){
        }
        return list;
    }

    @Override
    public boolean addOneVoucherAllAmount(String voucherId, ArrayList<VoucherAmountPO> amountList,String factoryId) {
        try{
            sqlManager.getConnection();
            for (VoucherAmountPO po : amountList){
                if(voucherId.equals(po.getV_id())){
                    addOneVoucherAmount(po,factoryId);
                }else{
                    return false;
                }
            }
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteOneVoucherAllAmount(String voucherId,String factoryId) {
        try{
            sqlManager.getConnection();

            List<Object> params = new ArrayList<>();
            params.add(voucherId);
            params.add(factoryId);

            String sql = "DELETE FROM voucher_amount WHERE v_id=? AND company_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();

            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteOneAmount(String amountId,String factoryId) {
        try{
            sqlManager.getConnection();

            List<Object> params = new ArrayList<>();
            params.add(amountId);
            params.add(factoryId);

            String sql = "DELETE FROM voucher_amount WHERE a_id=? AND company_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();

            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteSeveralVoucherAllAmount(ArrayList<String> voucherIdList,String factoryId) {
        try{
            for (String id : voucherIdList){
                deleteOneVoucherAllAmount(id,factoryId);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteAllAmount(String factoryId) {
        try{
            sqlManager.getConnection();

            List<Object> params = new ArrayList<>();
            params.add(factoryId);

            String sql = "DELETE FROM voucher_amount WHERE company_id=?";
            sqlManager.executeUpdateByList(sql,params);

            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean modifyOneAmount(String amountId, VoucherAmountPO voucherAmountPO,String factoryId) {
        try{
            sqlManager.getConnection();

            List<Object> params = new ArrayList<>();
            params.add(voucherAmountPO.getV_id());
            params.add(voucherAmountPO.getDigest());
            params.add(voucherAmountPO.getSubject());
            params.add(voucherAmountPO.getDebitAmount());
            params.add(voucherAmountPO.getCreditAmount());
            params.add(amountId);
            params.add(factoryId);

            String sql = "UPDATE voucher_amount SET v_id=?, abstract=?, subject=?, debit_amount=?, credit_amount=? WHERE a_id=? AND company_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public ArrayList<VoucherAmountPO> findOneVoucherAllAmount(String voucherId,String factoryId) {
        ArrayList<VoucherAmountPO> list = new ArrayList<>();

        try{
            sqlManager.getConnection();


            String sql = "SELECT * FROM voucher_amount WHERE v_id=? AND company_id=?";

            ArrayList<Map<String,Object>> maplist = sqlManager.queryMulti(sql,new Object[]{voucherId,factoryId});
            for (Map<String,Object> map : maplist){
                list.add(getVoucherAmountPOByMap(map));
            }
        }catch (Exception e){
        }
        return list;
    }

    @Override
    public HashMap<String, ArrayList<VoucherAmountPO>> findSeveralVoucherAllAmount(ArrayList<String> voucherIdList,String factoryId) {
        HashMap<String,ArrayList<VoucherAmountPO>> map = new HashMap<>();
        try{
            for (String vid : voucherIdList){
                ArrayList<VoucherAmountPO> list = findOneVoucherAllAmount(vid,factoryId);
                map.put(vid,list);
            }
        }catch (Exception e){
        }
        return map;
    }

    @Override
    public ArrayList<VoucherAmountPO> findAllVoucherAllAmount(String factoryId) {
        sqlManager.getConnection();
        ArrayList<VoucherAmountPO> list = new ArrayList<>();

        String sql = "SELECT * FROM voucher_amount WHERE company_id=?";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{factoryId});
        for (Map<String,Object> map : maps){
            list.add(getVoucherAmountPOByMap(map));
        }
        sqlManager.releaseAll();
        return list;
    }

    @Override
    public boolean addOneSubjectBalance(String subjectId, double number,String factoryId) {
        try{
            sqlManager.getConnection();

            List<Object> params = new ArrayList<>();
            params.add(subjectId);
            params.add(number);

            String sql = sqlManager.appendSQL("INSERT INTO subjects_balance VALUES",params.size());
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean deleteOneSubjectBalance(String subjectId,String factoryId) {
        try{
            sqlManager.getConnection();

            List<Object> params = new ArrayList<>();
            params.add(subjectId);

            String sql = "DELETE FROM subjects_balance WHERE subjects_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean modifyOneSubjectBalance(String subjectId, double number,String factoryId) {
        try{
            sqlManager.getConnection();

            List<Object> params = new ArrayList<>();
            params.add(number);
            params.add(subjectId);

            String sql = "UPDATE subjects_balance SET balances=? WHERE subjects_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public double findOneSubjectBalance(String subjectId,String factoryId) {
        try{
            sqlManager.getConnection();

            String sql = "SELECT balances FROM subjects_balance WHERE subjects_id=?";
            Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{subjectId});
            double balances = Double.valueOf(map.get("balances").toString());
            sqlManager.releaseAll();
            return balances;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public boolean addOneTemplate(VoucherTemplatePO voucherTemplatePO,String factoryId) {
        if(voucherTemplatePO == null){
            return false;
        }
        try {
            sqlManager.getConnection();

            List<Object> params = new ArrayList<>();
            params.add(voucherTemplatePO.getTemplateId());
            params.add(voucherTemplatePO.getCatagory());
            params.add(voucherTemplatePO.getTemplateName());
            params.add(factoryId);

            String sql = sqlManager.appendSQL("INSERT INTO voucher_template VALUES",params.size());
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }
    }



    @Override
    public boolean deleteOneTemplate(String templateId,String factoryId) {
        try {
            sqlManager.getConnection();
            List<Object> params = new ArrayList<>();
            params.add(templateId);
            params.add(factoryId);


            String sql = "DELETE FROM voucher_template WHERE template_id=? AND company_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteOneTemplateAmounts(String templateId,String factoryId) {
        try {
            sqlManager.getConnection();
            List<Object> params = new ArrayList<>();
            params.add(templateId);
            params.add(factoryId);

            String sql = "DELETE FROM voucher_template_amount WHERE template_id=? AND company_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean modifyOneTemplate(String templateId, VoucherTemplatePO voucherTemplatePO,String factoryId) {
        if(!templateId.equals(voucherTemplatePO.getTemplateId())){
            return false;
        }
        try {
            sqlManager.getConnection();
            List<Object> params = new ArrayList<>();
            params.add(voucherTemplatePO.getTemplateId());
            params.add(voucherTemplatePO.getCatagory());
            params.add(voucherTemplatePO.getTemplateName());
            params.add(templateId);
            params.add(factoryId);

            String sql = "UPDATE voucher_template SET template_id=?, catagory=?, template_name=? WHERE template_id=? AND company_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();

            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean addOneTemplateAmount(VoucherTemplateAmountPO templateAmountPO,String factoryId) {
        if (templateAmountPO == null){
            return false;
        }
        try{
            sqlManager.getConnection();

            List<Object> params = new ArrayList<>();
            params.add(templateAmountPO.getTemplateId());
            params.add(templateAmountPO.getA_id());
            params.add(templateAmountPO.getDigest());
            params.add(templateAmountPO.getSubject());
            params.add(templateAmountPO.getDebitAmount());
            params.add(templateAmountPO.getCreditAmount());
            params.add(factoryId);

            String sql = sqlManager.appendSQL("INSERT INTO voucher_template_amount VALUES",params.size());
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean addOneTemplateAmounts(String templateId, ArrayList<VoucherTemplateAmountPO> templateAmountPOArrayList,String factoryId) {
        try{
            for (VoucherTemplateAmountPO po : templateAmountPOArrayList){
                if(templateId.equals(po.getTemplateId())){
                    addOneTemplateAmount(po,factoryId);
                }else{
                    return false;
                }
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean modifyTemplateAmount(VoucherTemplateAmountPO templateAmountPO,String factoryId) {
        try {
            sqlManager.getConnection();
            List<Object> params = new ArrayList<>();
            params.add(templateAmountPO.getTemplateId());
            params.add(templateAmountPO.getDigest());
            params.add(templateAmountPO.getSubject());
            params.add(templateAmountPO.getDebitAmount());
            params.add(templateAmountPO.getCreditAmount());
            params.add(templateAmountPO.getA_id());
            params.add(factoryId);

            String sql = "UPDATE voucher_template_amount SET template_id=?, abstract=?, subject=?, debit_amount=?, credit_amount=? WHERE a_id=? AND company_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();

            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public VoucherTemplatePO getOneTemplate(String templateId,String factoryId) {
        try{
            VoucherTemplatePO po = new VoucherTemplatePO();
            sqlManager.getConnection();

            String sql = "SELECT * FROM voucher_template WHERE template_id=? AND company_id=?";
            Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{templateId,factoryId});
            po = getVoucherTemplatePOByMap(map);
            sqlManager.releaseAll();
            return po;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public ArrayList<VoucherTemplatePO> getAllTemplate(String factoryId) {
        sqlManager.getConnection();
        ArrayList<VoucherTemplatePO> list = new ArrayList<>();

        String sql = "SELECT * FROM voucher_template WHERE company_id=?";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{factoryId});

        for (Map<String, Object> map : maps){
            list.add(getVoucherTemplatePOByMap(map));
        }
        sqlManager.releaseAll();
        return list;
    }

    @Override
    public boolean deleteTemplateAmount(String amountId,String factoryId) {
        try {
            sqlManager.getConnection();

            List<Object> params = new ArrayList<>();
            params.add(amountId);
            params.add(factoryId);

            String sql = "DELETE FROM voucher_template_amount WHERE a_id=? AND company_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public VoucherTemplateAmountPO getOneTemplateAmount(String amountId,String factoryId) {

        try{
            VoucherTemplateAmountPO po = new VoucherTemplateAmountPO();
            sqlManager.getConnection();

            String sql = "SELECT * FROM voucher_template_amount WHERE a_id=? AND company_id=?";
            Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{amountId,factoryId});
            po = getVoucherTemplateAmountPOByMap(map);
            sqlManager.releaseAll();
            return po;
        }catch (Exception e){
            return null;
        }
    }

    public void intialSubjectsBalance(String factoryId){
        sqlManager.getConnection();

        ArrayList<String> idList = new ArrayList<>();

        String sql = "SELECT * FROM subjects";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{});
        for (Map<String,Object> map : maps){
            idList.add(map.get("subjects_id").toString());
        }

        for (String s : idList){

            List<Object> params = new ArrayList<>();
            params.add(s);
            params.add(factoryId);

            String sql2 = sqlManager.appendSQL("INSERT INTO subjects_balance(subjects_id,company_id) VALUES",params.size());
            sqlManager.executeUpdateByList(sql2,params);
        }

        sqlManager.commit();
        sqlManager.releaseAll();
    }

    @Override
    public ArrayList<VoucherTemplateAmountPO> getOneTemplateAllAmount(String templateId,String factoryId) {
        ArrayList<VoucherTemplateAmountPO> list = new ArrayList<>();
        sqlManager.getConnection();

        String sql = "SELECT * FROM voucher_template_amount WHERE template_id=? AND company_id=?";
        ArrayList<Map<String,Object>> maplist = sqlManager.queryMulti(sql,new Object[]{templateId,factoryId});

        for (Map<String,Object> map : maplist){
            list.add(getVoucherTemplateAmountPOByMap(map));
        }
        return list;
    }

    /**
     * 获得所有凭证id
     * @return
     */
    private ArrayList<String> getVIdList(){
        sqlManager.getConnection();

        ArrayList<String> vidList = new ArrayList<>();
        String sql = "SELECT v_id FROM voucher";
        List<Map<String,Object>> vidRawList = sqlManager.queryMulti(sql,new Object[]{});
        for(Map<String,Object> map : vidRawList){
            vidList.add(map.get("v_id").toString());
        }
        return vidList;
    }

    /**
     * 获得所有模板id
     * @return
     */
    private ArrayList<String> getTemplateIdList(){
        sqlManager.getConnection();
        ArrayList<String> tidList = new ArrayList<>();
        String sql = "SELECT template_id FROM voucher_template";
        List<Map<String,Object>> tidRawList = sqlManager.queryMulti(sql,new Object[]{});
        for (Map<String,Object> map : tidRawList){
            tidList.add(map.get("template_id").toString());
        }
        return tidList;
    }

    private VoucherPO getVoucherPOByMap(Map<String,Object> map){
        VoucherPO po = new VoucherPO();

        po.setId(map.get("v_id").toString());
        po.setDate(Date.valueOf(map.get("date").toString()));
        po.setAddReceipts(Boolean.valueOf(map.get("is_addedreceipts").toString()));
        po.setVoucher_maker(map.get("voucher_maker").toString());
        po.setRemark(map.get("remark").toString());

        return po;
    }

    private VoucherAmountPO getVoucherAmountPOByMap(Map<String,Object> map){
        VoucherAmountPO po = new VoucherAmountPO();

        po.setV_id(map.get("v_id").toString());
        po.setA_id(map.get("a_id").toString());
        po.setDigest(map.get("abstract").toString());
        po.setSubject(map.get("subject").toString());
        po.setDebitAmount(Double.valueOf(map.get("debit_amount").toString()));
        po.setCreditAmount(Double.valueOf(map.get("credit_amount").toString()));

        return po;
    }

    private VoucherTemplatePO getVoucherTemplatePOByMap(Map<String,Object> map){
        VoucherTemplatePO po = new VoucherTemplatePO();

        po.setTemplateId(map.get("template_id").toString());
        po.setCatagory(map.get("catagory").toString());
        po.setTemplateName(map.get("template_name").toString());

        return po;
    }

    private VoucherTemplateAmountPO getVoucherTemplateAmountPOByMap(Map<String,Object> map){
        VoucherTemplateAmountPO po = new VoucherTemplateAmountPO();

        po.setTemplateId(map.get("template_id").toString());
        po.setA_id(map.get("a_id").toString());
        po.setDigest(map.get("abstract").toString());
        po.setSubject(map.get("subject").toString());
        po.setDebitAmount(!map.get("debit_amount").equals("") ? Double.valueOf(map.get("debit_amount").toString()): 0);
        po.setCreditAmount(!map.get("credit_amount").equals("")? Double.valueOf(map.get("credit_amount").toString()) : 0);

        return po;

    }

    private VoucherTemplateAmountPO getOneTemplateAmountByTid(String t_id){
        VoucherTemplateAmountPO po = new VoucherTemplateAmountPO();
        sqlManager.getConnection();

        String sql = "SELECT * FROM voucher_template_amount WHERE template_id=?";
        Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{t_id});
        po = getVoucherTemplateAmountPOByMap(map);


        return po;
    }

    private void addOneVoucherAmount(VoucherAmountPO po,String company_id){
        if(po == null){
            return;
        }
        sqlManager.getConnection();
        List<Object> params = new ArrayList<>();
        params.add(company_id);
        params.add(po.getV_id());
        params.add(po.getA_id());
        params.add(po.getDigest());
        params.add(po.getSubject());
        params.add(po.getDebitAmount());
        params.add(po.getCreditAmount());

        String sql = sqlManager.appendSQL("INSERT INTO voucher_amount VALUES",params.size());
        sqlManager.executeUpdateByList(sql,params);
        sqlManager.commit();
    }
}

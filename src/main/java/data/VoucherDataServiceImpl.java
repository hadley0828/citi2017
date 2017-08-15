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
    public boolean addVoucher(VoucherPO voucherPO) {
        if(voucherPO == null){
            return false;
        }

        try{
            sqlManager.getConnection();
            List<Object> params = new ArrayList<>();
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
    public boolean deleteOneVoucher(String voucherId) {
        try{
            sqlManager.getConnection();

            List<Object> params = new ArrayList<>();
            params.add(voucherId);

            String sql = "DELETE FROM voucher WHERE v_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();

            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean deleteAllVoucher() {
        try{
            sqlManager.getConnection();
            String sql = "DELETE FROM voucher";

            sqlManager.executeUpdate(sql);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean modifyOneVoucher(String voucherId, VoucherPO voucherPO) {
        try{
            ArrayList<String> vidList = getVIdList();
            if(!vidList.contains(voucherId)){
                addVoucher(voucherPO);
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

                String sql = "UPDATE voucher SET v_id=?, date=?, is_addedreceipts=?, voucher_maker=?, remark=? WHERE v_id=?";
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
    public VoucherPO findOneVoucher(String voucherId) {
        try{
            ArrayList<String> vidList = getVIdList();
            if(!vidList.contains(voucherId)){
                return null;
            }else {
                sqlManager.getConnection();
                Map<String,Object> map = new HashMap<>();
                String sql = "SELECT * FROM voucher WHERE v_id=?";
                map = sqlManager.querySimple(sql,new Object[]{voucherId});

                VoucherPO po = getVoucherPOByMap(map);
                sqlManager.releaseAll();
                return po;
            }
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public ArrayList<VoucherPO> findSeveralVoucher(ArrayList<String> idList) {
        ArrayList<VoucherPO> list = new ArrayList<>();
        VoucherPO po = new VoucherPO();
        for (String id : idList){
            try {
                po = findOneVoucher(id);
            }catch (Exception e){
                System.out.println("Fing SeveralVoucher Fail");
            }
            list.add(po);
        }
        return list;
    }

    @Override
    public ArrayList<VoucherPO> findAllVoucher() {
        ArrayList<VoucherPO> list = new ArrayList<>();
        try{
            sqlManager.getConnection();

            String sql = "SELECT * FROM voucher";
            ArrayList<Map<String,Object>> maplist = sqlManager.queryMulti(sql,new Object[]{});
            for (Map<String,Object> map : maplist){
                list.add(getVoucherPOByMap(map));
            }

        }catch (Exception e){
        }
        return list;
    }

    @Override
    public boolean addOneVoucherAllAmount(String voucherId, ArrayList<VoucherAmountPO> amountList) {
        try{
            sqlManager.getConnection();
            for (VoucherAmountPO po : amountList){
                if(voucherId.equals(po.getV_id())){
                    addOneVoucherAmount(po);
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
    public boolean deleteOneVoucherAllAmount(String voucherId) {
        try{
            sqlManager.getConnection();

            List<Object> params = new ArrayList<>();
            params.add(voucherId);

            String sql = "DELETE FROM voucher_amount WHERE v_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();

            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteOneAmount(String amountId) {
        try{
            sqlManager.getConnection();

            List<Object> params = new ArrayList<>();
            params.add(amountId);

            String sql = "DELETE FROM voucher_amount WHERE a_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();

            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteSeveralVoucherAllAmount(ArrayList<String> voucherIdList) {
        try{
            for (String id : voucherIdList){
                deleteOneVoucherAllAmount(id);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteAllAmount() {
        try{
            sqlManager.getConnection();

            String sql = "DELETE FROM voucher_amount";
            sqlManager.executeUpdate(sql);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean modifyOneAmount(String amountId, VoucherAmountPO voucherAmountPO) {
        try{
            sqlManager.getConnection();

            List<Object> params = new ArrayList<>();
            params.add(voucherAmountPO.getV_id());
            params.add(voucherAmountPO.getDigest());
            params.add(voucherAmountPO.getSubject());
            params.add(voucherAmountPO.getDebitAmount());
            params.add(voucherAmountPO.getCreditAmount());
            params.add(amountId);

            String sql = "UPDATE voucher_amount SET v_id=?, abstract=?, subject=?, debit_amount=?, credit_amount=? WHERE a_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public ArrayList<VoucherAmountPO> findOneVoucherAllAmount(String voucherId) {
        ArrayList<VoucherAmountPO> list = new ArrayList<>();

        try{
            sqlManager.getConnection();


            String sql = "SELECT * FROM voucher_amount WHERE v_id=?";

            ArrayList<Map<String,Object>> maplist = sqlManager.queryMulti(sql,new Object[]{voucherId});
            for (Map<String,Object> map : maplist){
                list.add(getVoucherAmountPOByMap(map));
            }
            sqlManager.releaseAll();

        }catch (Exception e){
        }
        return list;
    }

    @Override
    public HashMap<String, ArrayList<VoucherAmountPO>> findSeveralVoucherAllAmount(ArrayList<String> voucherIdList) {
        HashMap<String,ArrayList<VoucherAmountPO>> map = new HashMap<>();
        try{
            for (String vid : voucherIdList){
                ArrayList<VoucherAmountPO> list = findOneVoucherAllAmount(vid);
                map.put(vid,list);
            }
        }catch (Exception e){
        }
        return map;
    }

    @Override
    public HashMap<String, ArrayList<VoucherAmountPO>> findAllVoucherAllAmount() {
        HashMap<String,ArrayList<VoucherAmountPO>> map = new HashMap<>();
        try {
            ArrayList<String> vidList = getVIdList();
            map = findSeveralVoucherAllAmount(vidList);
        }catch (Exception e){
        }
        return map;
    }

    @Override
    public boolean addOneSubjectBalance(String subjectId, double number) {
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
    public boolean deleteOneSubjectBalance(String subjectId) {
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
    public boolean modifyOneSubjectBalance(String subjectId, double number) {
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
    public double findOneSubjectBalance(String subjectId) {
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
    public boolean addOneTemplate(VoucherTemplatePO voucherTemplatePO) {
        if(voucherTemplatePO == null){
            return false;
        }
        try {
            sqlManager.getConnection();

            List<Object> params = new ArrayList<>();
            params.add(voucherTemplatePO.getTemplateId());
            params.add(voucherTemplatePO.getCatagory());
            params.add(voucherTemplatePO.getTemplateName());

            String sql = sqlManager.appendSQL("INSERT INTO voucher_template VALUES",params.size());
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }
    }



    @Override
    public boolean deleteOneTemplate(String templateId) {
        try {
            sqlManager.getConnection();
            List<Object> params = new ArrayList<>();
            params.add(templateId);

            String sql = "DELETE FROM voucher_template WHERE template_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteOneTemplateAmounts(String templateId) {
        try {
            sqlManager.getConnection();
            List<Object> params = new ArrayList<>();
            params.add(templateId);

            String sql = "DELETE FROM voucher_template_amount WHERE template_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean modifyOneTemplate(String templateId, VoucherTemplatePO voucherTemplatePO) {
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

            String sql = "UPDATE voucher_template SET template_id=?, catagory=?, template_name=? WHERE template_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();

            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean addOneTemplateAmount(VoucherTemplateAmountPO templateAmountPO) {
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

            String sql = sqlManager.appendSQL("INSERT INTO voucher_template_amount VALUES",params.size());
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean addOneTemplateAmounts(String templateId, ArrayList<VoucherTemplateAmountPO> templateAmountPOArrayList) {
        try{
            for (VoucherTemplateAmountPO po : templateAmountPOArrayList){
                if(templateId.equals(po.getTemplateId())){
                    addOneTemplateAmount(po);
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
    public boolean modifyTemplateAmount(VoucherTemplateAmountPO templateAmountPO) {
        try {
            sqlManager.getConnection();
            List<Object> params = new ArrayList<>();
            params.add(templateAmountPO.getTemplateId());
            params.add(templateAmountPO.getDigest());
            params.add(templateAmountPO.getSubject());
            params.add(templateAmountPO.getDebitAmount());
            params.add(templateAmountPO.getCreditAmount());
            params.add(templateAmountPO.getA_id());
            String sql = "UPDATE voucher_template_amount SET template_id=?, abstract=?, subject=?, debit_amount=?, credit_amount=? WHERE a_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();

            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public VoucherTemplatePO getOneTemplate(String templateId) {
        try{
            VoucherTemplatePO po = new VoucherTemplatePO();
            sqlManager.getConnection();

            String sql = "SELECT * FROM voucher_template WHERE template_id=?";
            Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{templateId});
            po = getVoucherTemplatePOByMap(map);
            sqlManager.releaseAll();
            return po;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public ArrayList<VoucherTemplatePO> getAllTemplate() {
        ArrayList<String> tidList = getTemplateIdList();
        ArrayList<VoucherTemplatePO> list = new ArrayList<>();
        for (String tid : tidList){
            list.add(getOneTemplate(tid));
        }
        return list;
    }

    @Override
    public boolean deleteTemplateAmount(String amountId) {
        try {
            sqlManager.getConnection();

            List<Object> params = new ArrayList<>();
            params.add(amountId);

            String sql = "DELETE FROM voucher_template_amount WHERE a_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public VoucherTemplateAmountPO getOneTemplateAmount(String amountId) {

        try{
            VoucherTemplateAmountPO po = new VoucherTemplateAmountPO();
            sqlManager.getConnection();

            String sql = "SELECT * FROM voucher_template_amount WHERE a_id=?";
            Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{amountId});
            po = getVoucherTemplateAmountPOByMap(map);
            sqlManager.releaseAll();
            return po;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public ArrayList<VoucherTemplateAmountPO> getOneTemplateAllAmount(String templateId) {
        ArrayList<VoucherTemplateAmountPO> list = new ArrayList<>();
        sqlManager.getConnection();

        String sql = "SELECT * FROM voucher_template_amount WHERE template_id=?";
        ArrayList<Map<String,Object>> maplist = sqlManager.queryMulti(sql,new Object[]{templateId});

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
        sqlManager.releaseAll();
        return vidList;
    }

    private ArrayList<String> getTemplateIdList(){
        sqlManager.getConnection();
        ArrayList<String> tidList = new ArrayList<>();
        String sql = "SELECT template_id FROM voucher_template";
        List<Map<String,Object>> tidRawList = sqlManager.queryMulti(sql,new Object[]{});
        for (Map<String,Object> map : tidRawList){
            tidList.add(map.get("template_id").toString());
        }
        sqlManager.releaseAll();
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

        sqlManager.releaseAll();
        return po;
    }

    private void addOneVoucherAmount(VoucherAmountPO po){
        if(po == null){
            return;
        }
        sqlManager.getConnection();
        List<Object> params = new ArrayList<>();
        params.add(po.getV_id());
        params.add(po.getA_id());
        params.add(po.getDigest());
        params.add(po.getSubject());
        params.add(po.getDebitAmount());
        params.add(po.getCreditAmount());

        String sql = sqlManager.appendSQL("INSERT INTO voucher_amount VALUES",params.size());
        sqlManager.executeUpdateByList(sql,params);
        sqlManager.releaseAll();

    }
}

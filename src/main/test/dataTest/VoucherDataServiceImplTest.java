package dataTest;

import data.VoucherDataServiceImpl;
import dataservice.VoucherDataService;
import org.junit.Before;
import org.junit.Test;
import po.VoucherAmountPO;
import po.VoucherPO;
import po.VoucherTemplateAmountPO;
import po.VoucherTemplatePO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by loohaze on 2017/8/13.
 */
public class VoucherDataServiceImplTest {

    private VoucherDataService voucherDataService;

    @Before
    public void setUp(){
        voucherDataService = new VoucherDataServiceImpl();
    }

    @Test
    public void testAddVoucher(){
        VoucherPO po = new VoucherPO();
        po.setId("借-2");
        po.setDate(Date.valueOf("2012-04-12"));
        po.setAddReceipts(false);
        po.setVoucher_maker("loohaze");
        po.setRemark("TEST");

        voucherDataService.addVoucher(po);
    }

    @Test
    public void testDeleteOneVoucher(){
        String v_id = "借-2";
        voucherDataService.deleteOneVoucher(v_id);
    }

    @Test
    public void testDeleteAllVoucher(){
        voucherDataService.deleteAllVoucher();
    }

    @Test
    public void testModifyOneVoucher(){
        String vid = "借-2";
        VoucherPO po = new VoucherPO();
        po.setId("借-2");
        po.setDate(Date.valueOf("2012-02-07"));
        po.setAddReceipts(false);
        po.setVoucher_maker("loohaze");
        po.setRemark("TEST");

        System.out.println(voucherDataService.modifyOneVoucher(vid,po));
    }

    @Test
    public void testFindOneVoucher(){
        String id = "转-1";
        VoucherPO po = voucherDataService.findOneVoucher(id);
        print(po);
    }


    @Test
    public void testFindSeveralVoucher(){
        ArrayList<String> idlist = new ArrayList<>();
        idlist.add("借-1");
        idlist.add("借-2");
        idlist.add("借-3");
        idlist.add("转-4");

        ArrayList<VoucherPO> polist = voucherDataService.findSeveralVoucher(idlist);
        for (VoucherPO po : polist){
            print(po);
        }
    }

    @Test
    public void testFindAllVoucher(){
       ArrayList<VoucherPO> list = voucherDataService.findAllVoucher();
       for (VoucherPO po : list){
           print(po);
       }
    }

    @Test
    public void testAddOneVoucherAllAmount(){
        String v_id = "借-3";
        ArrayList<VoucherAmountPO> list = new ArrayList<>();
        VoucherAmountPO po1 = new VoucherAmountPO();
        po1.setV_id("借-3");
        po1.setDigest("TEST");
        po1.setSubject("1001");
        po1.setCreditAmount(500);
        po1.setDebitAmount(0);

        VoucherAmountPO po2= new VoucherAmountPO();
        po2.setV_id("借-3");
        po2.setDigest("TEST");
        po2.setSubject("1002");
        po2.setCreditAmount(0);
        po2.setDebitAmount(500);

        list.add(po1);
        list.add(po2);

        System.out.println(voucherDataService.addOneVoucherAllAmount(v_id,list));
    }

    @Test
    public void testDeleteOneVoucherAllAmount(){
        String v_id = "借-3";
        System.out.println(voucherDataService.deleteOneVoucherAllAmount(v_id));
    }

    @Test
    public void testDeleteOneAmount(){
        String a_id = "16";
        System.out.println(voucherDataService.deleteOneAmount(a_id));
    }

    @Test
    public void testDeleteSeveralVoucherAllAmount(){
        ArrayList<String> vidlist = new ArrayList<>();
        vidlist.add("转-1");
        vidlist.add("借-1");
        System.out.println(voucherDataService.deleteSeveralVoucherAllAmount(vidlist));

    }


    @Test
    public void testDeleteAllAmount(){
        System.out.println(voucherDataService.deleteAllAmount());
    }

    @Test
    public void testModifyOneAmount(){
        String a_id = "10";
        VoucherAmountPO po = new VoucherAmountPO();
        po.setV_id("转-4");
        po.setDigest("MODIFY TEST");
        po.setDebitAmount(100);
        po.setSubject("1001");
        System.out.println(voucherDataService.modifyOneAmount(a_id,po));
    }

    @Test
    public void testFindOneVoucherAllAmount(){
        String v_id = "转-2";
        ArrayList<VoucherAmountPO> list = voucherDataService.findOneVoucherAllAmount(v_id);
        for (VoucherAmountPO po : list){
            print(po);
        }
    }

    @Test
    public void testFindSeveralVoucherAllAmount(){
        ArrayList<String> list = new ArrayList<>();
        list.add("转-2");
        list.add("转-3");

        HashMap<String,ArrayList<VoucherAmountPO>> map = voucherDataService.findSeveralVoucherAllAmount(list);
        System.out.println(map.size());
    }

    @Test
    public void testfindAllVoucherAllAmount(){
        HashMap<String,ArrayList<VoucherAmountPO>> map = voucherDataService.findAllVoucherAllAmount();
        System.out.println(map.size());
        System.out.println(map.get("记-1").size());
    }


    @Test
    public void testAddOneSubjectBalance(){
        String s_id = "1001";
        double num = 1000;
        System.out.println(voucherDataService.addOneSubjectBalance(s_id,num));
    }

    @Test
    public void testDeleteOneSubjectBalance(){
        String s_id = "1001";
        System.out.println(voucherDataService.deleteOneSubjectBalance(s_id));
    }

    @Test
    public void testModifyOneSubjectBalance(){
        String s_id = "1001";
        double num = 2000;
        System.out.println(voucherDataService.modifyOneSubjectBalance(s_id,num));
    }


    @Test
    public void testFindOneSubjectBalance(){
        String s_id = "1001";
        double balances = voucherDataService.findOneSubjectBalance(s_id);
        System.out.println(balances);
    }

    @Test
    public void testAddOneTemplate(){
        VoucherTemplatePO po = new VoucherTemplatePO();
        po.setTemplateId("t001");
        po.setCatagory("TEST");
        po.setTemplateName("TEST NAME");
        System.out.println(voucherDataService.addOneTemplate(po));
    }

    @Test
    public void testAddOneTemplateAmount(){
        VoucherTemplateAmountPO po = new VoucherTemplateAmountPO();
        po.setTemplateId("t001");
        po.setDigest(" ");
        po.setSubject("1001");
        po.setCreditAmount(1000);
        System.out.println(voucherDataService.addOneTemplateAmount(po));

    }

    @Test
    public void testDeleteOneTemplate(){
        String t_id = "t001";
        System.out.println(voucherDataService.deleteOneTemplate(t_id));
    }

    @Test
    public void testDeleteOneTemplateAmounts(){
        String t_id = "t001";
        System.out.println(voucherDataService.deleteOneTemplateAmounts(t_id));
    }


    @Test
    public void testModifyOneTemplate(){
        String t_id = "t001";
        VoucherTemplatePO po = new VoucherTemplatePO();
        po.setTemplateId("t001");
        po.setCatagory("TEST");
        po.setTemplateName("TEST NAME");
        System.out.println(voucherDataService.modifyOneTemplate(t_id,po));
    }

    @Test
    public void testModifyTemplateAmount(){
        VoucherTemplateAmountPO po = new VoucherTemplateAmountPO();
        po.setTemplateId("t001");
        po.setA_id("3");
        po.setDigest("TEST");
        po.setSubject("1001");
        po.setCreditAmount(1223);
        po.setDebitAmount(99);
        System.out.println(voucherDataService.modifyTemplateAmount(po));
    }

    @Test
    public void testGetOneTemplate(){
        VoucherTemplatePO po = voucherDataService.getOneTemplate("t001");
        print(po);
    }

    @Test
    public void testGetAllTemplate(){
        ArrayList<VoucherTemplatePO> list = voucherDataService.getAllTemplate();
        for (VoucherTemplatePO po : list){
            print(po);
        }
    }

    @Test
    public void testDeleteTemplateAmount(){
        String a_id = "3";
        System.out.println(voucherDataService.deleteTemplateAmount(a_id));

    }

    @Test
    public void testGetOneTemplateAmount(){
        String a_id = "4";
        VoucherTemplateAmountPO po = voucherDataService.getOneTemplateAmount(a_id);

        print(po);
    }

    @Test
    public void testGetOneTemplateAllAmount(){
        String t_id = "t002";
        ArrayList<VoucherTemplateAmountPO> list = voucherDataService.getOneTemplateAllAmount(t_id);
        for (VoucherTemplateAmountPO po : list){
            print(po);
        }
    }

    private void print(VoucherPO po){
        if(po == null){
            System.out.println("NULL");
        }else{
            System.out.println(po.getId() + " " + po.getDate() + " " + po.isAddReceipts() + " " + po.getVoucher_maker()+ " " + po.getRemark());
        }
    }


    private void print(VoucherAmountPO po){
        if(po == null){
            System.out.println("NULL");
        }else{
            System.out.println(po.getV_id() +" " + po.getA_id() + " " + po.getDigest() + " " + po.getSubject() + " " + po.getDebitAmount() + " " + po.getCreditAmount());
        }
    }

    private void print(VoucherTemplatePO po){
        if(po == null){
            System.out.println("NULL");
        }else {
            System.out.println(po.getTemplateId()+ " " + po.getCatagory() + " " + po.getTemplateName());
        }
    }

    private void print(VoucherTemplateAmountPO po){
        if(po == null){
            System.out.println("NULL");
        }else {
            System.out.println(po.getTemplateId() + " " + po.getA_id() + " " + po.getDigest() + " " + po.getSubject() + " " + po.getCreditAmount()+ " " + po.getDebitAmount());
        }
    }

}



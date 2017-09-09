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
        po.setDate(Date.valueOf("2017-09-08"));
        po.setAddReceipts(false);
        po.setVoucher_maker("loohaze");
        po.setRemark("TEST");

        voucherDataService.addVoucher(po,"003");
    }

    @Test
    public void testDeleteOneVoucher(){
        String v_id = "借-1";
        voucherDataService.deleteOneVoucher(v_id,"002");
    }

    @Test
    public void testDeleteAllVoucher(){
        voucherDataService.deleteAllVoucher("003");
    }

    @Test
    public void testModifyOneVoucher(){
        String vid = "借-2";
        VoucherPO po = new VoucherPO();
        po.setId("借-2");
        po.setDate(Date.valueOf("2017-08-07"));
        po.setAddReceipts(false);
        po.setVoucher_maker("loohaze");
        po.setRemark("TEST");

        System.out.println(voucherDataService.modifyOneVoucher(vid,po,"003"));
    }

    @Test
    public void testFindOneVoucher(){
        String id = "借-2";
        VoucherPO po = voucherDataService.findOneVoucher(id,"001");
        print(po);
    }


    @Test
    public void testFindSeveralVoucher(){
        ArrayList<String> idlist = new ArrayList<>();
        idlist.add("借-1");
        idlist.add("借-2");
        idlist.add("借-3");
        idlist.add("转-4");

        ArrayList<VoucherPO> polist = voucherDataService.findSeveralVoucher(idlist,"001");
        for (VoucherPO po : polist){
            print(po);
        }
    }

    @Test
    public void testFindAllVoucher(){
       ArrayList<VoucherPO> list = voucherDataService.findAllVoucher("001");
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

        System.out.println(voucherDataService.addOneVoucherAllAmount(v_id,list,"003"));
    }

    @Test
    public void testDeleteOneVoucherAllAmount(){
        String v_id = "借-3";
        System.out.println(voucherDataService.deleteOneVoucherAllAmount(v_id,"003"));
    }

    @Test
    public void testDeleteOneAmount(){
        String a_id = "16";
        System.out.println(voucherDataService.deleteOneAmount(a_id,"001"));
    }

    @Test
    public void testDeleteSeveralVoucherAllAmount(){
        ArrayList<String> vidlist = new ArrayList<>();
        vidlist.add("转-1");
        vidlist.add("借-1");
        System.out.println(voucherDataService.deleteSeveralVoucherAllAmount(vidlist,"001"));

    }


    @Test
    public void testDeleteAllAmount(){
        System.out.println(voucherDataService.deleteAllAmount("001"));
    }

    @Test
    public void testModifyOneAmount(){
        String a_id = "10";
        VoucherAmountPO po = new VoucherAmountPO();
        po.setV_id("转-4");
        po.setDigest("MODIFY TEST");
        po.setDebitAmount(100);
        po.setSubject("1001");
        System.out.println(voucherDataService.modifyOneAmount(a_id,po,"001"));
    }

    @Test
    public void testFindOneVoucherAllAmount(){
        String v_id = "转-1";
        ArrayList<VoucherAmountPO> list = voucherDataService.findOneVoucherAllAmount(v_id,"001");
        for (VoucherAmountPO po : list){
            print(po);
        }
    }

    @Test
    public void testFindSeveralVoucherAllAmount(){
        ArrayList<String> list = new ArrayList<>();
        list.add("转-1");
        list.add("借-3");

        HashMap<String,ArrayList<VoucherAmountPO>> map = voucherDataService.findSeveralVoucherAllAmount(list,"001");
        System.out.println(map.size());
    }

    @Test
    public void testfindAllVoucherAllAmount(){
        ArrayList<VoucherAmountPO> list = voucherDataService.findAllVoucherAllAmount("001");
        for (VoucherAmountPO po : list){
            print(po);
        }
    }

    @Test
    public void testAddOneTemplate(){
        VoucherTemplatePO po = new VoucherTemplatePO();
        po.setTemplateId("t002");
        po.setCatagory("TEST");
        po.setTemplateName("TEST NAME");
        System.out.println(voucherDataService.addOneTemplate(po,"002"));
    }

    @Test
    public void testAddOneTemplateAmount(){
        VoucherTemplateAmountPO po = new VoucherTemplateAmountPO();
        po.setTemplateId("t001");
        po.setDigest(" ");
        po.setSubject("1001");
        po.setCreditAmount(1000);
        System.out.println(voucherDataService.addOneTemplateAmount(po,"001"));

    }

    @Test
    public void testDeleteOneTemplate(){
        String t_id = "t001";
        System.out.println(voucherDataService.deleteOneTemplate(t_id,"001"));
    }

    @Test
    public void testDeleteOneTemplateAmounts(){
        String t_id = "t001";
        System.out.println(voucherDataService.deleteOneTemplateAmounts(t_id,"001"));
    }


    @Test
    public void testModifyOneTemplate(){
        String t_id = "t001";
        VoucherTemplatePO po = new VoucherTemplatePO();
        po.setTemplateId("t001");
        po.setCatagory("TEST");
        po.setTemplateName("TEST NAME");
        System.out.println(voucherDataService.modifyOneTemplate(t_id,po,"001"));
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
        System.out.println(voucherDataService.modifyTemplateAmount(po,"001"));
    }

    @Test
    public void testGetOneTemplate(){
        VoucherTemplatePO po = voucherDataService.getOneTemplate("t001","001");
        print(po);
    }

    @Test
    public void testGetAllTemplate(){
        ArrayList<VoucherTemplatePO> list = voucherDataService.getAllTemplate("001");
        for (VoucherTemplatePO po : list){
            print(po);
        }
    }

    @Test
    public void testDeleteTemplateAmount(){
        String a_id = "3";
        System.out.println(voucherDataService.deleteTemplateAmount(a_id,"001"));

    }

    @Test
    public void testGetOneTemplateAmount(){
        String a_id = "4";
        VoucherTemplateAmountPO po = voucherDataService.getOneTemplateAmount(a_id,"001");

        print(po);
    }

    @Test
    public void testGetOneTemplateAllAmount(){
        String t_id = "t002";
        ArrayList<VoucherTemplateAmountPO> list = voucherDataService.getOneTemplateAllAmount(t_id,"001");
        for (VoucherTemplateAmountPO po : list){
            print(po);
        }
    }

    @Test
    public void testIntialSubjectsBalance(){
        voucherDataService.intialSubjectsBalance("001");
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



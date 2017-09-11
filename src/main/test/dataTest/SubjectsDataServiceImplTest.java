package dataTest;

import data.SubjectDataServiceImpl;
import dataservice.SubjectDataService;
import org.junit.Before;
import org.junit.Test;
import po.SubjectNumberPO;
import po.SubjectsPO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by loohaze on 2017/9/9 下午2:59
 */
public class SubjectsDataServiceImplTest {

    private SubjectDataService subjectDataService;

    @Before
    public void setUp(){
        subjectDataService = new SubjectDataServiceImpl();
    }


    @Test
    public void testGetSubjectIdToNameMap(){
        HashMap<String,String> hashMap = subjectDataService.getSubjectIdToNameMap();

        for (Map.Entry<String,String> entry : hashMap.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    @Test
    public void testAddOneSubject(){
        SubjectsPO po = new SubjectsPO();
        po.setId("1001");
        po.setDate(java.sql.Date.valueOf("2017-09-09"));
        po.setBalances(0);
        po.setCreditAmount(0);
        po.setDebitAmount(0);
        po.setVoucher_id("借-1");
        subjectDataService.addOneSubject(po,"001");
    }

    @Test
    public void testDeleteOneSubject(){
        String subjects_id = "1001";
        String voucher_id = "借-1";
        double balance = 0;
        String factory_id = "001";

        subjectDataService.deleteOneSubject(subjects_id,voucher_id,balance,factory_id);
    }

    @Test
    public void testGetAllExistedSubjectId(){
        ArrayList<String> list = subjectDataService.getAllExistedSubjectId("001");
        for (String id : list){
            System.out.println(id);
        }
    }


    @Test
    public void testFindOneYearAllSubjects(){
        ArrayList<SubjectsPO> list = subjectDataService.findOneYearAllSubjects("2015","001");
        for (SubjectsPO po : list){
            print(po);
        }
    }

    @Test
    public void testFindOneMonthAllSubjects(){
        ArrayList<SubjectsPO> list = subjectDataService.findOneMonthAllSubjects("2015-02","001");
        for (SubjectsPO po : list){
            print(po);
        }
    }

    @Test
    public void testFindMonthsAllSubjects(){
        ArrayList<String> month = new ArrayList<>();
        month.add("2015-01");
        month.add("2015-02");
        ArrayList<SubjectsPO> list = subjectDataService.findMonthsAllSubjects(month,"001");
        for (SubjectsPO po : list){
            print(po);
        }
    }

    @Test
    public void testGetNewestSubjectBalance(){
        SubjectNumberPO po = subjectDataService.getNewestSubjectBalance("1001","001");
        print(po);
    }

    @Test
    public void testgetAllSubjects(){
        ArrayList<SubjectsPO> list = subjectDataService.getAllSubjects("001");
        for (SubjectsPO po : list){
            print(po);
        }
    }

    @Test
    public void testgetOneSubjectAllRecords(){
        ArrayList<SubjectsPO> list = subjectDataService.getOneSubjectAllRecords("1002","001");
        for (SubjectsPO po : list){
            print(po);
        }
    }


    private void print(SubjectsPO po){
        System.out.print(po.getId() + "\t");
        System.out.print(po.getName() + "\t");
        System.out.print(po.getDate() + "\t");
        System.out.print(po.getBalances() + "\t");
        System.out.print(po.getDebitAmount() + "\t");
        System.out.print(po.getCreditAmount()+ "\t");
        System.out.println(po.getVoucher_id());
    }

    private void print(SubjectNumberPO po){
        System.out.print(po.getSubjectId() + " ");
        System.out.print(po.getBalance() +  " ");
        System.out.print(po.getDebitAmount()+ " ");
        System.out.println(po.getCreditAmount());
    }
}


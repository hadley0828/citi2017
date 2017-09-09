package data;

import dataservice.SubjectDataService;
import po.SubjectNumberPO;
import po.SubjectsPO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhangzy on 2017/9/9 下午1:14
 */
public class SubjectDataServiceImpl implements SubjectDataService{

    @Override
    public HashMap<String, String> getSubjectIdToNameMap(String factoryId) {
        return null;
    }

    @Override
    public boolean addOneSubject(SubjectsPO po, String factoryId) {
        return false;
    }

    @Override
    public boolean deleteOneSubject(String subjectId, String voucherId, double balance, String factoryId) {
        return false;
    }

    @Override
    public boolean updateOneSubject(SubjectsPO subjectPO, String factoryId) {
        return false;
    }

    @Override
    public SubjectNumberPO getNewestSubjectBalance(String subjectId,String factoryId) {
        return null;
    }

    @Override
    public ArrayList<SubjectsPO> findOneMonthAllSubjects(String month, String factoryId) {
        return null;
    }

    @Override
    public ArrayList<SubjectsPO> findMonthsAllSubjects(ArrayList<String> monthList, String factoryId) {
        return null;
    }

    @Override
    public ArrayList<SubjectsPO> findOneYearAllSubjects(String year, String factoryId) {
        return null;
    }
}

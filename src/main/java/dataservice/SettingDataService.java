package dataservice;

import vo.userManagement.SubjectsInitialVO;
import vo.userManagement.SubjectsVO;

import java.util.ArrayList;

/**
 * Created by loohaze on 2017/9/13 下午6:47
 */
public interface SettingDataService {



    /**
     * 获得所有行业
     * @return
     */
    public ArrayList<String> getAllSuperIndustry();

    /**
     *
     * @param superIndustry
     * @return
     */
    public ArrayList<String> getAllSubIndustry(String superIndustry);

    /**
     * 获得所有科目
     * @return
     */
    public ArrayList<SubjectsVO> getAllSubjects();


    public boolean setInitialSubjects(ArrayList<SubjectsInitialVO> list, String company_id);


}

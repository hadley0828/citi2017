package dataTest;

import data.CourseMessageServiceImpl;
import dataservice.CourseMessageService;
import org.junit.Before;
import org.junit.Test;
import po.SubjectsPO;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by loohaze on 2017/8/9.
 */
public class CourseMessageServiceImplTest {

    private CourseMessageService courseMessageService;


    @Before
    public void setUp() throws Exception{

        courseMessageService = new CourseMessageServiceImpl();
    }


    @Test
    public void testGetCurrentCouseMessage(){
        ArrayList<SubjectsPO> list = courseMessageService.getCurrentCouseMessage("001");
        System.out.println(list.size());
    }

    @Test
    public void testGetYearEndCourseMessage(){
        ArrayList<SubjectsPO> list = courseMessageService.getYearEndCourseMessage("001","1991");
        System.out.println(list.size());
    }

    @Test
    public void testHasYear(){
        assertEquals(true,courseMessageService.HasYear("001","1991"));
        assertEquals(false,courseMessageService.HasYear("001","1992"));

    }

    @Test
    public void testgetBeginCourseMessage(){

    }

    @Test
    public void testGetCourseNameById(){
        String id = "1122";
        System.out.println(courseMessageService.getCourseNameById(id));
    }

    @Test
    public void testGetVoucherNumber(){
        System.out.println(courseMessageService.getVoucherNumber("001"));
    }

    @Test
    public void testGetEarliestTime(){
        System.out.println(courseMessageService.getEarliestTime("001"));
    }

    @Test
    public void testGetLatestTime(){
        System.out.println(courseMessageService.getLatestTime("001"));
    }
}

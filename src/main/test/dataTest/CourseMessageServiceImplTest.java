package dataTest;

import data.CourseMessageServiceImpl;
import dataservice.CourseMessageService;
import org.junit.Before;
import org.junit.Test;
import po.VoucherAmountPO;

import java.util.ArrayList;

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
    public void testGetCourseMessageById(){
        String id = "转-1";
        ArrayList<VoucherAmountPO> list = courseMessageService.getCourseMessageById(id);

        for(VoucherAmountPO po : list){
            System.out.println(po.getId());
        }
    }
}

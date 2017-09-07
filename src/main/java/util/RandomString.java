package util;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Created by loohaze on 2017/9/7 上午9:51
 */
public class RandomString {

    public static String getRandomString(int num){
        return RandomStringUtils.randomAlphanumeric(10);
    }

}

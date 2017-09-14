package util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by loohaze on 2017/9/14 上午11:32
 */
public class Encrypt {

    public static String encryptByMD5(String rawpassword){
        String md5str = DigestUtils.md5Hex(rawpassword);
        return md5str;
    }

    public static void main(String[] args) {
        System.out.println(Encrypt.encryptByMD5("000000"));
        System.out.println(Encrypt.encryptByMD5("123123"));
        System.out.println(Encrypt.encryptByMD5("0000000"));
    }
}

package cn.zhangxd.oak.core;

import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

/**
 * 密钥生成
 *
 * @author zhangxd
 */
public class SecretGenerator {

    private StringEncryptor stringEncryptor;

    @Before
    public void setup() {
        MockEnvironment e = new MockEnvironment();
        e.setProperty("jasypt.encryptor.password", "fg1YAyCQHHU52xdA");
        stringEncryptor = new DefaultLazyEncryptor(e);
    }


    @Test
    public void generateSecret() {
        System.out.println("-------------------------------------------------");
        System.out.println(stringEncryptor.encrypt("root"));
        System.out.println(stringEncryptor.encrypt("313JYBUjbfkwfX58"));
        System.out.println("-------------------------------------------------");
    }

}

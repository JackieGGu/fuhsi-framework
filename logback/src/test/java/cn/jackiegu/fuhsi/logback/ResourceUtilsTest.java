package cn.jackiegu.fuhsi.logback;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.net.URL;

/**
 * @author JackieGu
 * @date 2022/4/2
 */
public class ResourceUtilsTest {

    @Test
    public void getURLTest() {
        try {
            URL url = ResourceUtils.getURL("classpath:cn/jackiegu/fuhsi/logback/config/logback-default.xml");
            Assert.assertFalse(url.getPath().isEmpty());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

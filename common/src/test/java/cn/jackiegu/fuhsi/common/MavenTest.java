package cn.jackiegu.fuhsi.common;

import org.junit.Test;

import java.io.File;
import java.util.Objects;

/**
 * Maven单元测试
 *
 * @author gujie
 * @date 2025/6/30
 */
public class MavenTest {

    @Test
    public void deleteInvalidFile() {
        String path = "/usr/local/apache/repository";
        File file = new File(path);
        this.deleteExecute(file);
    }

    private void deleteExecute(File file) {
        if (file.isFile() && file.getName().contains(".lastUpdated")) {
            file.delete();
            System.out.println("Deleted: " + file.getPath());
            return;
        }
        for (File f : Objects.requireNonNull(file.listFiles())) {
            if (f.isDirectory()) {
                this.deleteExecute(f);
            } else {
                if (f.getName().contains(".lastUpdated")) {
                    f.delete();
                    System.out.println("Deleted: " + f.getPath());
                }
            }
        }
    }
}

package cn.jackiegu.fuhsi.common;

import org.junit.Test;

import java.io.File;
import java.util.Objects;

/**
 * MacOS单元测试
 *
 * @author gujie
 * @date 2025/1/6
 */
public class MacOSTest {

    @Test
    public void deleteStoreFile() {
        String path = "D:\\02_Work";
        File file = new File(path);
        this.deleteExecute(file);
    }

    private void deleteExecute(File file) {
        if (file.isFile() && (file.getName().equals(".DS_Store") || file.getName().startsWith("._"))) {
            file.delete();
            System.out.println("Deleted: " + file.getPath());
            return;
        }
        for (File f : Objects.requireNonNull(file.listFiles())) {
            if (f.isDirectory()) {
                this.deleteExecute(f);
            } else {
                if (f.getName().equals(".DS_Store") || f.getName().startsWith("._")) {
                    f.delete();
                    System.out.println("Deleted: " + f.getPath());
                }
            }
        }
    }
}

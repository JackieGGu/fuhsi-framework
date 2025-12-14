package cn.jackiegu.fuhsi.common;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * WPS单元测试
 *
 * @author gujie
 * @date 2025/12/14
 * @since 1.0.0
 */
public class WpsTest {

    @Test
    public void deleteRepeatDirectory() {
        String path = "C:\\Users\\gujie\\AppData\\Roaming\\kingsoft\\wps\\addons\\pool\\win-i386";
        File file = new File(path);
        Map<String, List<File>> repeatDirectoryMap = Arrays.stream(Optional.ofNullable(file.listFiles()).orElse(new File[0]))
            .collect(Collectors.groupingBy(item -> this.splitFileName(item.getName()).get(0)));
        repeatDirectoryMap.forEach((fileName, files) -> {
            if (files.size() == 1) {
                return;
            }
            files = files.stream()
                .sorted((f1, f2) -> {
                    String version1 = this.splitFileName(f1.getName()).get(1);
                    String version2 = this.splitFileName(f2.getName()).get(1);
                    return Integer.parseInt(version2) - Integer.parseInt(version1);
                })
                .collect(Collectors.toList());
            for (int i = 1; i < files.size(); i++) {
                FileUtil.del(files.get(i));
                System.out.println("Deleted: " + files.get(i).getPath());
            }
        });
    }

    /**
     * 以最后一个下划线分割文件名
     *
     * @param fileName 文件名
     * @return 分割后的文件名
     */
    private List<String> splitFileName(String fileName) {
        if (StrUtil.isBlank(fileName)) {
            return CollUtil.newArrayList();
        }
        int i = fileName.lastIndexOf("_");
        return CollUtil.newArrayList(
            fileName.substring(0, i),
            fileName.substring(i + 1).replaceAll("\\.", "")
        );
    }
}

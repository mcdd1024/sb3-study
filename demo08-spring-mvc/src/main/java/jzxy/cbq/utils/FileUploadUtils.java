package jzxy.cbq.utils;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author: cbq1024
 * @description: FileUploadUtils
 * @since 2024/7/11 下午6:22
 */
@Slf4j
@Component
public class FileUploadUtils {
    // 使用静态属性，通过 set 方法注入值，类上需要加 @Component 注解才能完成值注入
    private static String uploadPath;

    @Value("${cbq.upload_path}")
    public void setUploadPath(String uploadPath) {
        FileUploadUtils.uploadPath = uploadPath;
    }

    /**
     * 文件上传
     */
    public static final String upload(MultipartFile file)
            throws IOException {

        String fileName = extractFilename(file);
        String absPath = getAbsoluteFile(fileName).getAbsolutePath();
        file.transferTo(Paths.get(absPath));
        return fileName;
    }

    /**
     * 编码文件名
     */
    public static final String extractFilename(MultipartFile file) {
        return StrUtil.format("{}.{}", UUID.randomUUID().toString(), getExtension(file));
    }

    public static final File getAbsoluteFile(String fileName) throws IOException {
        File desc = new File(uploadPath + File.separator + fileName);
        if (!desc.exists()) {
            if (!desc.getParentFile().exists()) {
                desc.getParentFile().mkdirs();
            }
        }
        return desc;
    }

    /**
     * 获取文件名的后缀
     */
    public static final String getExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    }
}

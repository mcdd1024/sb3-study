package jzxy.cbq.controller;

import jzxy.cbq.entity.CommonResult;
import jzxy.cbq.utils.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * @author: cbq1024
 * @description: LocalFileController
 * @since 2024/7/11 下午6:13
 */
@RequestMapping("local-files")
@RestController
@Slf4j
public class LocalFileController {

    @PostMapping("single-file")
    public CommonResult singleFile(@RequestParam("file") MultipartFile file) {
        showInfo(file);
        return CommonResult.success();
    }

    /**
     * 多文件上传
     */
    @PostMapping("method2")
    public CommonResult method2(@RequestParam("fileList") MultipartFile[] fileList) {
        for (MultipartFile file : fileList) {
            showInfo(file);
            log.info("============================");
        }

        return CommonResult.success();
    }

    @PostMapping("/s_upload")
    public CommonResult uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(file);
            HashMap<String, String> map = new HashMap<>();
            // 返回值根据需求返回就可以了
            map.put("fileName", fileName);
            map.put("url", "http://localhost:8080/file/" + fileName);
            return CommonResult.success(map);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
    }

    private static void showInfo(MultipartFile file) {
        log.info("文件大小 ==========> {}", file.getSize());
        log.info("文件文件名 ==========> {}", file.getName());
        log.info("文件文本类型 ==========> {}", file.getContentType());
        log.info("文件原始名 ==========> {}", file.getOriginalFilename());
    }
}

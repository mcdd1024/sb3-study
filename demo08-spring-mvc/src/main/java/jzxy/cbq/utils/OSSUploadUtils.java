package jzxy.cbq.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static jzxy.cbq.utils.FileUploadUtils.extractFilename;

/**
 * @author: cbq1024
 * @description: OSSUploadUtils
 * @since 2024/7/11 下午6:53
 */
@Slf4j
public class OSSUploadUtils {
    public static String uploadOSS(MultipartFile file) {
        // Endpoint 以华东 1（杭州）为例，其它 Region 请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        String keyId = "换成自己的 key";
        String keySecret = "换成自己的 key";
        // 填写 Bucket 名称，例如 example bucket
        String bucketName = "stt-study";
        // 设置文件名
        String fileName = extractFilename(file);

        // 创建 OSSClient 实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyId, keySecret);

        try {
            // 创建 PutObjectRequest 对象
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file.getInputStream());

            // 上传文件
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            log.info("result======>{}", result);
            return fileName;
        } catch (OSSException oe) {
            log.info("OSSException===》{}", oe.getMessage());
            throw new ServiceException("文件上传失败");
        } catch (ClientException ce) {
            log.info("ClientException===》{}", ce.getMessage());
            throw new ServiceException("文件上传失败");
        } catch (IOException e) {
            log.info("IOException===》{}", e.getMessage());
            throw new ServiceException("文件上传失败");
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}

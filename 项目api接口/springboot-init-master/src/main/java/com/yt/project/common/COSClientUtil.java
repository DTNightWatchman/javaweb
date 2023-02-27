package com.yt.project.common;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import com.yt.project.component.RedisIdWorker;
import com.yt.project.component.SpringBeanUtils;
import com.yt.project.config.COSConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.IdGenerator;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 腾讯云COS文件上传工具类
 */
@Slf4j
public class COSClientUtil {
 
    /**
     * 获取配置信息
     */
    private static COSConfig cosConfig = SpringBeanUtils.getBean(COSConfig.class);

    private static RedisIdWorker redisIdWorker = SpringBeanUtils.getBean(RedisIdWorker.class);
 
    /**
     * 初始化用户身份信息
     */
    private static COSCredentials cred = new BasicCOSCredentials(cosConfig.getAccessKey(), cosConfig.getSecretKey());
 
    /**
     * 设置bucket的区域
     */
    private static ClientConfig clientConfig = new ClientConfig(new Region(cosConfig.getRegionName()));
 
    /**
     * 生成COS客户端
     */
    private static COSClient cosClient = new COSClient(cred, clientConfig);

    private static ExecutorService threadPool = Executors.newFixedThreadPool(16);
    private static TransferManager transferManager = new TransferManager(cosClient, threadPool);



    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static String upload(MultipartFile file) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String originalFilename = file.getOriginalFilename();
        long nextId = redisIdWorker.nextId("upload:");
        String name = nextId + originalFilename.substring(originalFilename.lastIndexOf("."));
        String folderName = cosConfig.getFolderPrefix() + "/" + date + "/";
        String key = folderName + name;
        File localFile = null;
        try {
            localFile = transferToFile(file);
            System.out.println(localFile.getAbsolutePath());
            String filePath = uploadFileToCOS(localFile, key);
            log.info("upload COS successful: {}", filePath);
            return filePath;
        } catch (Exception e) {
            throw new Exception("文件上传失败");
        } finally {
            if (localFile != null) {
                localFile.delete();
            }
        }
    }
 
    /**
     * 上传文件到COS
     *
     * @param localFile
     * @param key
     * @return
     */
    private static String uploadFileToCOS(File localFile, String key) throws InterruptedException {
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosConfig.getBucketName(), key, localFile);
        // 传入一个threadPool, 若不传入线程池, 默认TransferManager中会生成一个单线程的线程池

        // 返回一个异步结果Upload, 可同步的调用waitForUploadResult等待upload结束, 成功返回UploadResult, 失败抛出异常
        Upload upload = transferManager.upload(putObjectRequest);
        UploadResult uploadResult = upload.waitForUploadResult();
        String filePath = cosConfig.getBaseUrl() + uploadResult.getKey();
        return filePath;
    }
 
    /**
     * 用缓冲区来实现这个转换, 即创建临时文件
     * 使用 MultipartFile.transferTo()
     *
     * @param multipartFile
     * @return
     */
    private static File transferToFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String prefix = prefix = UUID.randomUUID().toString();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        File file = File.createTempFile(prefix, suffix);
        multipartFile.transferTo(file);
        return file;
    }
 
}
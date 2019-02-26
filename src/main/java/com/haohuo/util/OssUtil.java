package com.haohuo.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

/**
 * @ClassName OssUtil
 * @Description TODO
 * @Author Zhang Peike
 * @Date 2019/1/31 9:39
 **/
public class OssUtil {

    // Endpoint以杭州为例，其它Region请按实际情况填写。
    public static String endpoint = "oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    public static String accessKeyId = "";
    public static String accessKeySecret = "";
    public static String bucketName = "server-image";
    public static String objectName = "haohuo_space";

    // 创建OSSClient实例。
    public static OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

    public static String SimpleUpload(MultipartFile file){
        StringBuffer url = new StringBuffer();
        try {
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            //自定义新文件名
            String newName = Calendar.getInstance().toString() + "-img" + suffix;

            InputStream inputStream = file.getInputStream();
            ossClient.putObject(bucketName, objectName + newName, inputStream);
            url.append("http://").append(bucketName)
                    .append(".")
                    .append(endpoint)
                    .append("/")
                    .append(objectName)
                    .append("/")
                    .append(newName);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ossClient.shutdown();
        return url.toString();
    }
}

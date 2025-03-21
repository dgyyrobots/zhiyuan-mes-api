package com.dofast.framework.common.util.io;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.dofast.framework.common.config.MinioConfig;
import com.dofast.framework.common.util.date.DateUtils;
import com.dofast.framework.common.util.spring.SpringUtils;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class MinioUtil {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioConfig minioConfig;

    /**
     * 判断桶是否存在
     */
    @SneakyThrows(Exception.class)
    public boolean bucketExists(String bucketName) {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }


    /**
     * 创建桶
     * @param bucketName
     * 获取全部的桶  minioClient.listBuckets();
     */
    @SneakyThrows(Exception.class)
    public void createBucket(String bucketName) {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }


    /**
     * 根据bucketName获取信息
     * @param bucketName bucket名称
     */
    @SneakyThrows(Exception.class)
    public Optional<Bucket> getBucket(String bucketName) {
        return minioClient.listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
    }

    /**
     * 根据bucketName删除信息
     * @param bucketName bucket名称
     */
    @SneakyThrows(Exception.class)
    public  void removeBucket(String bucketName) {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
    }

    /**
     * 获取文件流
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 二进制流
     */
    @SneakyThrows(Exception.class)
    public InputStream getObject(String bucketName, String objectName) {
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }


    /**
     * 上传本地文件
     * @param bucketName 存储桶
     * @param objectName 对象名称
     * @param fileName   本地文件路径
     */
    @SneakyThrows(Exception.class)
    public ObjectWriteResponse putObject(String bucketName, String objectName, String fileName) {
        if(!bucketExists(bucketName)){
            createBucket(bucketName);
        }
        return minioClient.uploadObject(UploadObjectArgs.builder().bucket(bucketName).object(objectName).filename(fileName).build());
    }

    /**
     * 通过流上传文件
     * @param bucketName  存储桶
     * @param objectName  文件对象
     * @param inputStream 文件流
     */
    @SneakyThrows(Exception.class)
    public  ObjectWriteResponse putObject(String bucketName, String objectName, InputStream inputStream) {
        if(!bucketExists(bucketName)){
            createBucket(bucketName);
        }
        return minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(inputStream, inputStream.available(), -1).build());
    }


    /**
     * 单文件上传
     * @param bucketName
     * @param multipartFile
     * @return
     */
    /*@SneakyThrows(Exception.class)
    public String uploadFileSingle(String prefix , String bucketName, MultipartFile multipartFile) {
        if (!bucketExists(bucketName)) {
            createBucket(bucketName);
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        // 生成5位随机数
        String number = RandomUtil.randomNumbers(5);
        // 先判断originalFilename是否含有".", 没有就使用默认的userBlob加上5位随机数
        String name = null;
        if (originalFilename.contains(".")) {
            name = Optional.ofNullable(originalFilename.substring(0,originalFilename.lastIndexOf("."))).orElse("userBlob"+number);
        }else{
            name = "userBlob"+number;
        }

        String fileName = prefix + "_" + name + "_" + IdUtil.simpleUUID() + "." + extension;InputStream in = null;
        try {
            in = multipartFile.getInputStream();
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(in, multipartFile.getSize(), -1)
                    .contentType(multipartFile.getContentType())
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 只返回文件名，而不是完整的URL
        return fileName;
    }*/

    @SneakyThrows(Exception.class)
    public String uploadFileSingle(String prefix, String bucketName, MultipartFile multipartFile) {
        // 参数校验
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new IllegalArgumentException("上传文件为空, 请检查!");
        }

        if (!bucketExists(bucketName)) {
            createBucket(bucketName);
        }

        // 文件名处理
        String originalFilename = StringUtils.defaultString(multipartFile.getOriginalFilename(), "");
        String extension = StringUtils.defaultIfBlank(FilenameUtils.getExtension(originalFilename), "");
        String baseName = buildBaseName(originalFilename);

        // 构建文件名
        String fileName = buildFileName(prefix, baseName, extension);

        // 文件上传
        uploadToMinio(bucketName, fileName, multipartFile);

        // 返回完整的文件名
        fileName = minioConfig.getEndpoint()+"/"+bucketName+"/"+fileName;
        return fileName;
    }

    private String buildBaseName(String originalFilename) {
        String number = RandomUtil.randomNumbers(5);
        int lastDotIndex = originalFilename.lastIndexOf('.');

        if (lastDotIndex == -1) {
            return "userBlob" + number;
        }

        String namePart = originalFilename.substring(0, lastDotIndex);
        return StringUtils.isNotBlank(namePart) ? namePart : "userBlob" + number;
    }

    private String buildFileName(String prefix, String baseName, String extension) {
        return String.join("_",
                prefix,
                baseName,
                IdUtil.simpleUUID()
        ) + "." + extension;
    }

    private void uploadToMinio(String bucketName, String fileName, MultipartFile file) {
        try (InputStream in = file.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(in, file.getSize(), -1)
                            .contentType(StringUtils.defaultString(file.getContentType(), "application/octet-stream"))
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("上传MinIO失败", e);
        }
    }

    /**
     * 获取文件外链
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param expires    过期时间 <=7 秒级
     * @return url
     */
    @SneakyThrows(Exception.class)
    public  String getUploadObjectUrl(String bucketName, String objectName, Integer expires)  {
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(bucketName).object(objectName).expiry(expires).build());
    }

    /**
     * 下载文件
     * bucketName:桶名
     * @param fileName: 文件名
     */
    @SneakyThrows(Exception.class)
    public  void download(String  bucketName,String fileName, HttpServletResponse response) {
        // 获取对象的元数据
        StatObjectResponse  stat = minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(fileName).build());
        response.setContentType(stat.contentType());
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        InputStream is = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
        IOUtils.copy(is, response.getOutputStream());
        is.close();
    }

    /**
     * 删除文件
     * @param bucketName
     * @throws Exception
     */
    public void deleteFile(String bucketName, String fileName) throws Exception {
        if (StringUtils.isBlank(bucketName)){
            // 存储桶名字不能为空
            System.out.println("存储桶名字不能为空");
            return;
        }
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(fileName).build());
        } catch (Exception e) {
            System.out.println("文件删除失败" + e.getMessage());
        }
    }

}


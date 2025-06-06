package com.dofast.module.infra.controller.admin.file;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.util.io.FileUploadUtils;
import com.dofast.framework.common.util.io.MinioUtil;
import com.dofast.framework.common.util.servlet.ServletUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.infra.controller.admin.file.vo.file.FilePageReqVO;
import com.dofast.module.infra.controller.admin.file.vo.file.FileRespVO;
import com.dofast.module.infra.controller.admin.file.vo.file.FileUploadReqVO;
import com.dofast.module.infra.convert.file.FileConvert;
import com.dofast.module.infra.dal.dataobject.file.FileDO;
import com.dofast.module.infra.service.file.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import static com.dofast.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 文件存储")
@RestController
@RequestMapping("/infra/file")
@Validated
@Slf4j
public class FileController {

    @Resource
    private FileService fileService;

    @Resource
    private MinioUtil minioUtil;


    @PostMapping("/upload")
    @Operation(summary = "上传文件")
    @OperateLog(logArgs = false) // 上传文件，没有记录操作日志的必要
    public CommonResult<String> uploadFile(FileUploadReqVO uploadReqVO) throws Exception {
        MultipartFile file = uploadReqVO.getFile();
        System.out.println(file.getOriginalFilename());

        String path = uploadReqVO.getPath();
        //String fileAddr =  FileUploadUtils.upload(file); // 服务器上传 -> 局域网内保存
        String filUr = minioUtil.uploadFileSingle("uploadFile", "ammes", file);
        //fileService.createFile(file.getOriginalFilename(), path, IoUtil.readBytes(file.getInputStream()))
        return success(filUr);
    }

    @PostMapping("/getFullUrl")
    @Operation(summary = "获取文件完整Url")
    @OperateLog(logArgs = false) // 上传文件，没有记录操作日志的必要
    public CommonResult<String> getFullUrl(@RequestBody String fileName) {
        String filUr = minioUtil.getUploadObjectUrl( "ammes", fileName, 7*24*60*60);
        return success(filUr);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除文件")
    @Parameter(name = "id", description = "编号", required = true)
    /*@PreAuthorize("@ss.hasPermission('infra:file:delete')")*/
    public CommonResult<Boolean> deleteFile(@RequestParam("fileName") String fileName) throws Exception {
        System.out.println(fileName);
        // 判定当前文件是否包含?, 若不不包含则默认截取至文件末尾
        if (!fileName.contains("?")) {
            //fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.lastIndexOf("/"));
            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        }else{
            fileName = Optional.ofNullable(fileName.substring(fileName.lastIndexOf("/") + 1, fileName.indexOf("?"))).orElse(fileName);
        }
        minioUtil.deleteFile("ammes", fileName);
        return success(true);
    }

    @GetMapping("/{configId}/get/**")
    @PermitAll
    @Operation(summary = "下载文件")
    @Parameter(name = "configId", description = "配置编号",  required = true)
    public void getFileContent(HttpServletRequest request,
                               HttpServletResponse response,
                               @PathVariable("configId") Long configId) throws Exception {
        // 获取请求的路径
        String path = StrUtil.subAfter(request.getRequestURI(), "/get/", false);
        if (StrUtil.isEmpty(path)) {
            throw new IllegalArgumentException("结尾的 path 路径必须传递");
        }

        // 读取内容
        byte[] content = fileService.getFileContent(configId, path);
        if (content == null) {
            log.warn("[getFileContent][configId({}) path({}) 文件不存在]", configId, path);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }
        ServletUtils.writeAttachment(response, path, content);
    }

    @GetMapping("/page")
    @Operation(summary = "获得文件分页")
    @PreAuthorize("@ss.hasPermission('infra:file:query')")
    public CommonResult<PageResult<FileRespVO>> getFilePage(@Valid FilePageReqVO pageVO) {
        PageResult<FileDO> pageResult = fileService.getFilePage(pageVO);
        return success(FileConvert.INSTANCE.convertPage(pageResult));
    }



}

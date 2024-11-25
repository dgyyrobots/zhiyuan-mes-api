package com.dofast.module.system.controller.admin.user;

import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.common.enums.UserTypeEnum;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.util.io.FileUploadUtils;
import com.dofast.framework.common.util.io.MinioUtil;
import com.dofast.framework.datapermission.core.annotation.DataPermission;
import com.dofast.module.system.controller.admin.user.vo.profile.UserProfileRespVO;
import com.dofast.module.system.controller.admin.user.vo.profile.UserProfileUpdatePasswordReqVO;
import com.dofast.module.system.controller.admin.user.vo.profile.UserProfileUpdateReqVO;
import com.dofast.module.system.controller.admin.user.vo.usersFace.*;
import com.dofast.module.system.convert.user.UserConvert;
import com.dofast.module.system.dal.dataobject.dept.DeptDO;
import com.dofast.module.system.dal.dataobject.dept.PostDO;
import com.dofast.module.system.dal.dataobject.permission.RoleDO;
import com.dofast.module.system.dal.dataobject.social.SocialUserDO;
import com.dofast.module.system.dal.dataobject.user.AdminUserDO;
import com.dofast.module.system.dal.dataobject.user.*;
import com.dofast.module.system.service.dept.DeptService;
import com.dofast.module.system.service.dept.PostService;
import com.dofast.module.system.service.permission.PermissionService;
import com.dofast.module.system.service.permission.RoleService;
import com.dofast.module.system.service.social.SocialUserService;
import com.dofast.module.system.service.user.AdminUserService;
import com.dofast.module.system.service.user.UsersFaceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static com.dofast.module.infra.enums.ErrorCodeConstants.FILE_IS_EMPTY;

@Tag(name = "管理后台 - 用户个人中心")
@RestController
@RequestMapping("/system/user/profile")
@Validated
@Slf4j
public class UserProfileController {

    @Resource
    private AdminUserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private PostService postService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RoleService roleService;
    @Resource
    private SocialUserService socialService;

    @Resource
    private UsersFaceService usersFaceService;

    @Resource
    private MinioUtil minioUtil;


    @GetMapping("/get")
    @Operation(summary = "获得登录用户信息")
    @DataPermission(enable = false) // 关闭数据权限，避免只查看自己时，查询不到部门。
    public CommonResult<UserProfileRespVO> profile() {
        // 获得用户基本信息
        AdminUserDO user = userService.getUser(getLoginUserId());
        UserProfileRespVO resp = UserConvert.INSTANCE.convert03(user);
        // 获得用户角色
        List<RoleDO> userRoles = roleService.getRoleListFromCache(permissionService.getUserRoleIdListByUserId(user.getId()));
        resp.setRoles(UserConvert.INSTANCE.convertList(userRoles));
        // 获得部门信息
        if (user.getDeptId() != null) {
            DeptDO dept = deptService.getDept(user.getDeptId());
            resp.setDept(UserConvert.INSTANCE.convert02(dept));
        }
        // 获得岗位信息
        if (CollUtil.isNotEmpty(user.getPostIds())) {
            List<PostDO> posts = postService.getPostList(user.getPostIds());
            resp.setPosts(UserConvert.INSTANCE.convertList02(posts));
        }
        // 获得社交用户信息
        /*List<SocialUserDO> socialUsers = socialService.getSocialUserList(user.getId(), UserTypeEnum.ADMIN.getValue());
        resp.setSocialUsers(UserConvert.INSTANCE.convertList03(socialUsers));*/
        return success(resp);
    }

    @PutMapping("/update")
    @Operation(summary = "修改用户个人信息")
    public CommonResult<Boolean> updateUserProfile(@Valid @RequestBody UserProfileUpdateReqVO reqVO) {
        userService.updateUserProfile(getLoginUserId(), reqVO);
        return success(true);
    }

    @PutMapping("/update-password")
    @Operation(summary = "修改用户个人密码")
    public CommonResult<Boolean> updateUserProfilePassword(@Valid @RequestBody UserProfileUpdatePasswordReqVO reqVO) {
        userService.updateUserPassword(getLoginUserId(), reqVO);
        return success(true);
    }

    @RequestMapping(value = "/update-avatar", method = {RequestMethod.POST, RequestMethod.PUT})
    // 解决 uni-app 不支持 Put 上传文件的问题
    @Operation(summary = "上传用户个人头像")
    public CommonResult<String> updateUserAvatar(@RequestParam("avatarFile") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw exception(FILE_IS_EMPTY);
        }
        String avatar = userService.updateUserAvatar(getLoginUserId(), file.getInputStream());
        return success(avatar);
    }

   /*
   功能已实现, 开始测试minio上传
   @RequestMapping(value = "/update-face", method = {RequestMethod.POST, RequestMethod.PUT}) // 解决 uni-app 不支持 Put 上传文件的问题
    @Operation(summary = "上传用户人脸识别数据")
    public CommonResult<String> updateUserFaceInfo(@RequestParam("facesInfo") MultipartFile[] files) throws Exception {
        if (files.length== 0 ) {
            throw exception(FILE_IS_EMPTY);
        }
        // 遍历人脸数据与当前用户进行绑定
        Long userId = getLoginUserId();
        System.out.println(userId);
        // 根据当前用户Id, 绑定拍照的人脸信息
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                throw exception(FILE_IS_EMPTY);
            }
            UsersFaceCreateReqVO createReqVO = new UsersFaceCreateReqVO();
            createReqVO.setUserId(userId);
            createReqVO.setFaceImg(file.getBytes());
            usersFaceService.createUsersFace(createReqVO);
        }
        return success("上传成功!");
    }*/

    @RequestMapping(value = "/update-face", method = {RequestMethod.POST, RequestMethod.PUT})
    // 解决 uni-app 不支持 Put 上传文件的问题
    @Operation(summary = "上传用户人脸识别数据")
    public CommonResult<String> updateUserFaceInfo(@RequestParam("facesInfo") MultipartFile[] files) throws Exception {
        if (files.length == 0) {
            throw exception(FILE_IS_EMPTY);
        }
        // 遍历人脸数据与当前用户进行绑定
        Long userId = getLoginUserId();
        System.out.println(userId);
        // 根据当前用户Id, 绑定拍照的人脸信息
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                throw exception(FILE_IS_EMPTY);
            }
            UsersFaceCreateReqVO createReqVO = new UsersFaceCreateReqVO();
            createReqVO.setUserId(userId);
            // 开始上传minio, 数据库仅保存图片路径
            String pathFileName = minioUtil.uploadFileSingle("ammes", file);
            createReqVO.setFaceImg(pathFileName);
            usersFaceService.createUsersFace(createReqVO);
        }
        return success("上传成功!");
    }

    @GetMapping("/get-face")
    @Operation(summary = "获得登录用户人脸信息")
    @DataPermission(enable = false) // 关闭数据权限，避免只查看自己时，查询不到部门。
    public CommonResult<List<String>> getFaces() {
        // 获得用户人脸信息
        UsersFaceExportReqVO exportReqVO = new UsersFaceExportReqVO();
        exportReqVO.setUserId(getLoginUserId());
        List<UsersFaceDO> resp = usersFaceService.getUsersFaceList(exportReqVO);
        List<String> faceImgList = new ArrayList<>();
        for (int i = 0; i < resp.size(); i++) {
            String url = minioUtil.getUploadObjectUrl("ammes", resp.get(i).getFaceImg(), 3600);
            System.out.println(url);
            faceImgList.add(url);
        }
        return success(faceImgList);
    }


}

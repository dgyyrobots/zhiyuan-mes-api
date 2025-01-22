package com.dofast.module.system.controller.admin.auth;

import cn.hutool.core.util.StrUtil;
import com.dofast.framework.common.enums.CommonStatusEnum;
import com.dofast.framework.common.exception.ErrorCode;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.util.collection.SetUtils;
import com.dofast.framework.common.util.io.MinioUtil;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.framework.security.config.SecurityProperties;
import com.dofast.framework.tenant.core.context.TenantContextHolder;
import com.dofast.module.system.controller.admin.auth.vo.*;
import com.dofast.module.system.controller.admin.wechat.WechatGetDept;
import com.dofast.module.system.controller.admin.wechat.vo.WechatDepartment;
import com.dofast.module.system.convert.auth.AuthConvert;
import com.dofast.module.system.dal.dataobject.permission.MenuDO;
import com.dofast.module.system.dal.dataobject.permission.RoleDO;
import com.dofast.module.system.dal.dataobject.tenant.TenantDO;
import com.dofast.module.system.dal.dataobject.user.AdminUserDO;
import com.dofast.module.system.enums.logger.LoginLogTypeEnum;
import com.dofast.module.system.enums.permission.MenuTypeEnum;
import com.dofast.module.system.service.auth.AdminAuthService;
import com.dofast.module.system.service.permission.PermissionService;
import com.dofast.module.system.service.permission.RoleService;
import com.dofast.module.system.service.social.SocialUserService;
import com.dofast.module.system.service.tenant.TenantService;
import com.dofast.module.system.service.user.AdminUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static com.dofast.framework.security.core.util.SecurityFrameworkUtils.obtainAuthorization;
import static java.util.Collections.singleton;

@Tag(name = "管理后台 - 认证")
@RestController
@RequestMapping("/system/auth")
@Validated
@Slf4j
public class AuthController {

    @Resource
    private AdminAuthService authService;
    @Resource
    private AdminUserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private SocialUserService socialUserService;
    @Resource
    private SecurityProperties securityProperties;
    @Resource
    private TenantService tenantService;
    @Resource
    private WechatGetDept wechatGetDept;

    @Resource
    private MinioUtil minioUtil;

    @GetMapping("/get-wechat-department")
    @PermitAll
    @Parameter(name = "parentId", description = "部门ID", required = false)
    @Operation(summary = "获取企业微信部门信息")
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<List<WechatDepartment>> WechatDepartment(@RequestParam(name = "parentId", required = false) Long parentId) throws IOException {
        return success(wechatGetDept.getDepartmentList(parentId));
    }

    @PostMapping("/login")
    @PermitAll
    @Operation(summary = "使用账号密码登录")
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<AuthLoginRespVO> login(@RequestBody @Valid AuthLoginReqVO reqVO) {
        return success(authService.login(reqVO));
    }

    @PostMapping("/logout")
    @PermitAll
    @Operation(summary = "登出系统")
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<Boolean> logout(HttpServletRequest request) {
        String token = obtainAuthorization(request, securityProperties.getTokenHeader());
        if (StrUtil.isNotBlank(token)) {
            authService.logout(token, LoginLogTypeEnum.LOGOUT_SELF.getType());
        }
        return success(true);
    }

    @PostMapping("/refresh-token")
    @PermitAll
    @Operation(summary = "刷新令牌")
    @Parameter(name = "refreshToken", description = "刷新令牌", required = true)
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<AuthLoginRespVO> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return success(authService.refreshToken(refreshToken));
    }



    @GetMapping("/get-permission-info")
    @Operation(summary = "获取登录用户的权限信息")
    public CommonResult<AuthPermissionInfoRespVO> getPermissionInfo() {
        // 获得用户信息
        AdminUserDO user = userService.getUser(getLoginUserId());
        if (user == null) {
            return null;
        }
        // 基于头像名称获取访问地址
        String finUrl =  minioUtil.getUploadObjectUrl("ammes", user.getAvatar(), 3600);
        user.setAvatar(finUrl);

        // 获得角色列表
        Set<Long> roleIds = permissionService.getUserRoleIdsFromCache(getLoginUserId(), singleton(CommonStatusEnum.ENABLE.getStatus()));
        List<RoleDO> roleList = roleService.getRoleListFromCache(roleIds);
        // 获得菜单列表
        List<MenuDO> menuList = permissionService.getRoleMenuListFromCache(roleIds,
                SetUtils.asSet(MenuTypeEnum.DIR.getType(), MenuTypeEnum.MENU.getType(), MenuTypeEnum.BUTTON.getType()),
                singleton(CommonStatusEnum.ENABLE.getStatus())); // 只要开启的
        TenantDO info =  tenantService.getTenant(TenantContextHolder.getTenantId());

        //获取菜单
        // 获得角色列表
        Set<Long> roleIds1 = permissionService.getUserRoleIdsFromCache(getLoginUserId(), singleton(CommonStatusEnum.ENABLE.getStatus()));
        // 获得用户拥有的菜单列表
        List<MenuDO> menuList1 = permissionService.getRoleMenuListFromCache(roleIds1,
                SetUtils.asSet(MenuTypeEnum.DIR.getType(), MenuTypeEnum.MENU.getType()), // 只要目录和菜单类型
                singleton(CommonStatusEnum.ENABLE.getStatus())); // 只要开启的
        List<AuthMenuRespVO> authMenuRespVOS = AuthConvert.INSTANCE.buildMenuTree(menuList1);

        // 拼接结果返回
        return success(AuthConvert.INSTANCE.convert(user, roleList, menuList, info).setMenus(authMenuRespVOS));
    }

    @GetMapping("/list-menus")
    @Operation(summary = "获得登录用户的菜单列表")
    public CommonResult<List<AuthMenuRespVO>> getMenuList() {
        // 获得角色列表
        Set<Long> roleIds = permissionService.getUserRoleIdsFromCache(getLoginUserId(), singleton(CommonStatusEnum.ENABLE.getStatus()));
        // 获得用户拥有的菜单列表
        List<MenuDO> menuList = permissionService.getRoleMenuListFromCache(roleIds,
                SetUtils.asSet(MenuTypeEnum.DIR.getType(), MenuTypeEnum.MENU.getType()), // 只要目录和菜单类型
                singleton(CommonStatusEnum.ENABLE.getStatus())); // 只要开启的
        // 转换成 Tree 结构返回
        return success(AuthConvert.INSTANCE.buildMenuTree(menuList));
    }

    // ========== 短信登录相关 ==========

    @PostMapping("/sms-login")
    @PermitAll
    @Operation(summary = "使用短信验证码登录")
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<AuthLoginRespVO> smsLogin(@RequestBody @Valid AuthSmsLoginReqVO reqVO) {
        return success(authService.smsLogin(reqVO));
    }

    @PostMapping("/send-sms-code")
    @PermitAll
    @Operation(summary = "发送手机验证码")
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<Boolean> sendLoginSmsCode(@RequestBody @Valid AuthSmsSendReqVO reqVO) {
        authService.sendSmsCode(reqVO);
        return success(true);
    }

    // ========== 社交登录相关 ==========

    @GetMapping("/social-auth-redirect")
    @PermitAll
    @Operation(summary = "社交授权的跳转")
    @Parameters({
            @Parameter(name = "type", description = "社交类型", required = true),
            @Parameter(name = "redirectUri", description = "回调路径")
    })
    public CommonResult<String> socialLogin(@RequestParam("type") Integer type,
                                                    @RequestParam("redirectUri") String redirectUri) {
        return CommonResult.success(socialUserService.getAuthorizeUrl(type, redirectUri));
    }

    @PostMapping("/social-login")
    @PermitAll
    @Operation(summary = "社交快捷登录，使用 code 授权码", description = "适合未登录的用户，但是社交账号已绑定用户")
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<AuthLoginRespVO> socialQuickLogin(@RequestBody @Valid AuthSocialLoginReqVO reqVO) {
        return success(authService.socialLogin(reqVO));
    }

    @PostMapping("/new-login")
    @PermitAll
    @Operation(summary = "转换租户二次登录")
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<AuthLoginRespVO> newLogin(@RequestBody @Valid AuthNewLoginReqVO reqVO) {
        return success(authService.newLogin(reqVO));
    }

    @PostMapping("/one-click-login")
    @PermitAll
    @Operation(summary = "一键登录")
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<AuthLoginRespVO> oneClickLogin(@RequestBody @Valid OneClickLoginReqVO reqVO) {
        try {
            return success(authService.oneClickLogin(reqVO));
        } catch (Exception e) {
            throw exception(new ErrorCode(500,"获取手机号失败"));
        }
    }

}

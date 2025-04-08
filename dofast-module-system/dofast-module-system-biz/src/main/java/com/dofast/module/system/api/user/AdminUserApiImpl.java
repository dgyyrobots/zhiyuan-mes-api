package com.dofast.module.system.api.user;

import com.dofast.module.system.api.user.dto.AdminUserRespDTO;
import com.dofast.module.system.convert.user.UserConvert;
import com.dofast.module.system.dal.dataobject.user.AdminUserDO;
import com.dofast.module.system.service.user.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * Admin 用户 API 实现类
 *
 * @author 芋道源码
 */
@Service
public class AdminUserApiImpl implements AdminUserApi {

    @Resource
    private AdminUserService userService;

    @Override
    public AdminUserRespDTO getUser(Long id) {
        AdminUserDO user = userService.getUser(id);
        return UserConvert.INSTANCE.convert4(user);
    }

    @Override
    public AdminUserRespDTO getUser(String userName){
        AdminUserDO user = userService.getUserByUsername(userName);
        return UserConvert.INSTANCE.convert4(user);
    }


    @Override
    public List<AdminUserRespDTO> getUserList(Collection<Long> ids) {
        List<AdminUserDO> users = userService.getUserList(ids);
        return UserConvert.INSTANCE.convertList4(users);
    }

    @Override
    public List<AdminUserRespDTO> getUserListByDeptIds(Collection<Long> deptIds) {
        List<AdminUserDO> users = userService.getUserListByDeptIds(deptIds);
        return UserConvert.INSTANCE.convertList4(users);
    }

    @Override
    public List<AdminUserRespDTO> getUserListByPostIds(Collection<Long> postIds) {
        List<AdminUserDO> users = userService.getUserListByPostIds(postIds);
        return UserConvert.INSTANCE.convertList4(users);
    }

    @Override
    public void validateUserList(Collection<Long> ids) {
        userService.validateUserList(ids);
    }

}

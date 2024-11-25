package com.dofast.module.system.service.user;

import com.dofast.module.system.controller.admin.user.vo.usersFace.UsersFaceCreateReqVO;
import com.dofast.module.system.controller.admin.user.vo.usersFace.UsersFaceExportReqVO;
import com.dofast.module.system.controller.admin.user.vo.usersFace.UsersFacePageReqVO;
import com.dofast.module.system.controller.admin.user.vo.usersFace.UsersFaceUpdateReqVO;
import com.dofast.module.system.dal.dataobject.user.UsersFaceDO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.system.convert.user.UsersFaceConvert;
import com.dofast.module.system.dal.mysql.user.UsersFaceMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.system.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 用户人脸数据 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class UsersFaceServiceImpl implements UsersFaceService {

    @Resource
    private UsersFaceMapper usersFaceMapper;

    @Override
    public Long createUsersFace(UsersFaceCreateReqVO createReqVO) {
        // 插入
        UsersFaceDO usersFace = UsersFaceConvert.INSTANCE.convert(createReqVO);
        usersFace.setCreateTime(LocalDateTime.now());
        usersFaceMapper.insert(usersFace);
        // 返回
        return usersFace.getId();
    }

    @Override
    public void updateUsersFace(UsersFaceUpdateReqVO updateReqVO) {
        // 校验存在
        validateUsersFaceExists(updateReqVO.getId());
        // 更新
        UsersFaceDO updateObj = UsersFaceConvert.INSTANCE.convert(updateReqVO);
        usersFaceMapper.updateById(updateObj);
    }

    @Override
    public void deleteUsersFace(Long id) {
        // 校验存在
        validateUsersFaceExists(id);
        // 删除
        usersFaceMapper.deleteById(id);
    }

    private void validateUsersFaceExists(Long id) {
        if (usersFaceMapper.selectById(id) == null) {
            throw exception(USERS_FACE_NOT_EXISTS);
        }
    }

    @Override
    public UsersFaceDO getUsersFace(Long id) {
        return usersFaceMapper.selectById(id);
    }

    @Override
    public List<UsersFaceDO> getUsersFaceList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return usersFaceMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<UsersFaceDO> getUsersFacePage(UsersFacePageReqVO pageReqVO) {
        return usersFaceMapper.selectPage(pageReqVO);
    }

    @Override
    public List<UsersFaceDO> getUsersFaceList(UsersFaceExportReqVO exportReqVO) {
        return usersFaceMapper.selectList(exportReqVO);
    }

}

package com.dofast.module.mes.service.userworkstation;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.userworkstation.vo.*;
import com.dofast.module.mes.dal.dataobject.userworkstation.UserWorkstationDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.userworkstation.UserWorkstationConvert;
import com.dofast.module.mes.dal.mysql.userworkstation.UserWorkstationMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;

/**
 * 用户工作站绑定关系 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class UserWorkstationServiceImpl implements UserWorkstationService {

    @Resource
    private UserWorkstationMapper userWorkstationMapper;

    @Override
    public Long createUserWorkstation(UserWorkstationCreateReqVO createReqVO) {
        // 插入
        UserWorkstationDO userWorkstation = UserWorkstationConvert.INSTANCE.convert(createReqVO);
        userWorkstationMapper.insert(userWorkstation);
        // 返回
        return userWorkstation.getId();
    }

    @Override
    public void updateUserWorkstation(UserWorkstationUpdateReqVO updateReqVO) {
        // 校验存在
        validateUserWorkstationExists(updateReqVO.getId());
        // 更新
        UserWorkstationDO updateObj = UserWorkstationConvert.INSTANCE.convert(updateReqVO);
        userWorkstationMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserWorkstation(Long id) {
        // 校验存在
        validateUserWorkstationExists(id);
        // 删除
        userWorkstationMapper.deleteById(id);
    }

    @Override
    public void deleteUserWorkstation(String username) {
        UserWorkstationDO userWorkstationDO = userWorkstationMapper.selectOne(UserWorkstationDO::getUserName, username);
        userWorkstationMapper.deleteById(userWorkstationDO.getId());
    }

    private void validateUserWorkstationExists(Long id) {
        if (userWorkstationMapper.selectById(id) == null) {
            throw exception(USER_WORKSTATION_NOT_EXISTS);
        }
    }

    @Override
    public UserWorkstationDO getUserWorkstation(Long id) {
        return userWorkstationMapper.selectById(id);
    }

    @Override
    public List<UserWorkstationDO> getUserWorkstationList(Collection<Long> ids) {
        return userWorkstationMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<UserWorkstationDO> getUserWorkstationPage(UserWorkstationPageReqVO pageReqVO) {
        return userWorkstationMapper.selectPage(pageReqVO);
    }

    @Override
    public List<UserWorkstationDO> getUserWorkstationList(UserWorkstationExportReqVO exportReqVO) {
        return userWorkstationMapper.selectList(exportReqVO);
    }

}

package com.dofast.module.system.dal.mysql.systemconfig;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.system.dal.dataobject.systemconfig.SystemConfigDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.system.controller.admin.systemconfig.vo.*;

/**
 * 参数配置 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface SystemConfigMapper extends BaseMapperX<SystemConfigDO> {

    default PageResult<SystemConfigDO> selectPage(SystemConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SystemConfigDO>()
                .eqIfPresent(SystemConfigDO::getAppModule, reqVO.getAppModule())
                .eqIfPresent(SystemConfigDO::getConfigKey, reqVO.getConfigKey())
                .eqIfPresent(SystemConfigDO::getValue, reqVO.getValue())
                .eqIfPresent(SystemConfigDO::getConfigDesc, reqVO.getConfigDesc())
                .eqIfPresent(SystemConfigDO::getIsUse, reqVO.getIsUse())
                .betweenIfPresent(SystemConfigDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SystemConfigDO::getId));
    }

    default List<SystemConfigDO> selectList(SystemConfigExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SystemConfigDO>()
                .eqIfPresent(SystemConfigDO::getAppModule, reqVO.getAppModule())
                .eqIfPresent(SystemConfigDO::getConfigKey, reqVO.getConfigKey())
                .eqIfPresent(SystemConfigDO::getValue, reqVO.getValue())
                .eqIfPresent(SystemConfigDO::getConfigDesc, reqVO.getConfigDesc())
                .eqIfPresent(SystemConfigDO::getIsUse, reqVO.getIsUse())
                .betweenIfPresent(SystemConfigDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SystemConfigDO::getId));
    }

    default void updateAllConfig(Collection<SystemConfigDO> systemConfigUpdateReqVOS){
        updateBatch(systemConfigUpdateReqVOS,systemConfigUpdateReqVOS.size());
    }

    default SystemConfigDO selectOneByConfigKey(String configKey){
        return selectOne(new LambdaQueryWrapperX<SystemConfigDO>()
                .eq(SystemConfigDO::getConfigKey,configKey));
    }
    default void inserAllConfig(Collection<SystemConfigDO> systemConfigDOS){
        insertBatch(systemConfigDOS);
    }
}

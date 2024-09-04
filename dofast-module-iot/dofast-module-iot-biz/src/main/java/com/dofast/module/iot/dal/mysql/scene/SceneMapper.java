package com.dofast.module.iot.dal.mysql.scene;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.iot.dal.dataobject.scene.SceneDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.iot.controller.admin.scene.vo.*;

/**
 * 场景联动 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface SceneMapper extends BaseMapperX<SceneDO> {

    default PageResult<SceneDO> selectPage(ScenePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SceneDO>()
                .likeIfPresent(SceneDO::getSceneName, reqVO.getSceneName())
                .eqIfPresent(SceneDO::getUserId, reqVO.getUserId())
                .likeIfPresent(SceneDO::getUserName, reqVO.getUserName())
                .eqIfPresent(SceneDO::getTriggers, reqVO.getTriggers())
                .eqIfPresent(SceneDO::getActions, reqVO.getActions())
                .eqIfPresent(SceneDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(SceneDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SceneDO::getId));
    }

    default List<SceneDO> selectList(SceneExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SceneDO>()
                .likeIfPresent(SceneDO::getSceneName, reqVO.getSceneName())
                .eqIfPresent(SceneDO::getUserId, reqVO.getUserId())
                .likeIfPresent(SceneDO::getUserName, reqVO.getUserName())
                .eqIfPresent(SceneDO::getTriggers, reqVO.getTriggers())
                .eqIfPresent(SceneDO::getActions, reqVO.getActions())
                .eqIfPresent(SceneDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(SceneDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SceneDO::getId));
    }

}

package com.dofast.module.iot.dal.mysql.group;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.iot.dal.dataobject.group.GroupDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.iot.controller.admin.group.vo.*;

/**
 * 设备分组 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface GroupMapper extends BaseMapperX<GroupDO> {

    default PageResult<GroupDO> selectPage(GroupPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GroupDO>()
                .likeIfPresent(GroupDO::getGroupName, reqVO.getGroupName())
                .eqIfPresent(GroupDO::getGroupOrder, reqVO.getGroupOrder())
                .eqIfPresent(GroupDO::getUserId, reqVO.getUserId())
                .likeIfPresent(GroupDO::getUserName, reqVO.getUserName())
                .eqIfPresent(GroupDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(GroupDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GroupDO::getId));
    }

    default List<GroupDO> selectList(GroupExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<GroupDO>()
                .likeIfPresent(GroupDO::getGroupName, reqVO.getGroupName())
                .eqIfPresent(GroupDO::getGroupOrder, reqVO.getGroupOrder())
                .eqIfPresent(GroupDO::getUserId, reqVO.getUserId())
                .likeIfPresent(GroupDO::getUserName, reqVO.getUserName())
                .eqIfPresent(GroupDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(GroupDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GroupDO::getId));
    }

}

package com.dofast.module.pro.dal.mysql.andonrecord;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.pro.dal.dataobject.andonrecord.AndonRecordDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.pro.controller.admin.andonrecord.vo.*;

/**
 * 安灯呼叫记录 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface AndonRecordMapper extends BaseMapperX<AndonRecordDO> {

    default PageResult<AndonRecordDO> selectPage(AndonRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AndonRecordDO>()
                .eqIfPresent(AndonRecordDO::getWorkstationId, reqVO.getWorkstationId())
                .eqIfPresent(AndonRecordDO::getWorkstationCode, reqVO.getWorkstationCode())
                .likeIfPresent(AndonRecordDO::getWorkstationName, reqVO.getWorkstationName())
                .eqIfPresent(AndonRecordDO::getUserId, reqVO.getUserId())
                .likeIfPresent(AndonRecordDO::getUserName, reqVO.getUserName())
                .likeIfPresent(AndonRecordDO::getNickName, reqVO.getNickName())
                .eqIfPresent(AndonRecordDO::getWorkorderId, reqVO.getWorkorderId())
                .eqIfPresent(AndonRecordDO::getWorkorderCode, reqVO.getWorkorderCode())
                .likeIfPresent(AndonRecordDO::getWorkorderName, reqVO.getWorkorderName())
                .eqIfPresent(AndonRecordDO::getProcessId, reqVO.getProcessId())
                .eqIfPresent(AndonRecordDO::getProcessCode, reqVO.getProcessCode())
                .likeIfPresent(AndonRecordDO::getProcessName, reqVO.getProcessName())
                .eqIfPresent(AndonRecordDO::getAndonReason, reqVO.getAndonReason())
                .eqIfPresent(AndonRecordDO::getAndonLevel, reqVO.getAndonLevel())
                .betweenIfPresent(AndonRecordDO::getOperationTime, reqVO.getOperationTime())
                .eqIfPresent(AndonRecordDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AndonRecordDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AndonRecordDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(AndonRecordDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(AndonRecordDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(AndonRecordDO::getAttr4, reqVO.getAttr4())
                .eqIfPresent(AndonRecordDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(AndonRecordDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(AndonRecordDO::getUpdateBy, reqVO.getUpdateBy())
                .orderByDesc(AndonRecordDO::getRecordId));
    }

    default List<AndonRecordDO> selectList(AndonRecordExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AndonRecordDO>()
                .eqIfPresent(AndonRecordDO::getWorkstationId, reqVO.getWorkstationId())
                .eqIfPresent(AndonRecordDO::getWorkstationCode, reqVO.getWorkstationCode())
                .likeIfPresent(AndonRecordDO::getWorkstationName, reqVO.getWorkstationName())
                .eqIfPresent(AndonRecordDO::getUserId, reqVO.getUserId())
                .likeIfPresent(AndonRecordDO::getUserName, reqVO.getUserName())
                .likeIfPresent(AndonRecordDO::getNickName, reqVO.getNickName())
                .eqIfPresent(AndonRecordDO::getWorkorderId, reqVO.getWorkorderId())
                .eqIfPresent(AndonRecordDO::getWorkorderCode, reqVO.getWorkorderCode())
                .likeIfPresent(AndonRecordDO::getWorkorderName, reqVO.getWorkorderName())
                .eqIfPresent(AndonRecordDO::getProcessId, reqVO.getProcessId())
                .eqIfPresent(AndonRecordDO::getProcessCode, reqVO.getProcessCode())
                .likeIfPresent(AndonRecordDO::getProcessName, reqVO.getProcessName())
                .eqIfPresent(AndonRecordDO::getAndonReason, reqVO.getAndonReason())
                .eqIfPresent(AndonRecordDO::getAndonLevel, reqVO.getAndonLevel())
                .betweenIfPresent(AndonRecordDO::getOperationTime, reqVO.getOperationTime())
                .eqIfPresent(AndonRecordDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AndonRecordDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AndonRecordDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(AndonRecordDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(AndonRecordDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(AndonRecordDO::getAttr4, reqVO.getAttr4())
                .eqIfPresent(AndonRecordDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(AndonRecordDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(AndonRecordDO::getUpdateBy, reqVO.getUpdateBy())
                .orderByDesc(AndonRecordDO::getRecordId));
    }

}

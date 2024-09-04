package com.dofast.module.trade.dal.mysql.calculate;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateRecordExportReqVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateRecordPageReqVO;
import com.dofast.module.trade.dal.dataobject.calculate.CalculateRecordDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 计价记录 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface CalculateRecordMapper extends BaseMapperX<CalculateRecordDO> {

    default PageResult<CalculateRecordDO> selectPage(CalculateRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CalculateRecordDO>()
                .eqIfPresent(CalculateRecordDO::getTypeId, reqVO.getTypeId())
                .eqIfPresent(CalculateRecordDO::getResult, reqVO.getResult())
                .eqIfPresent(CalculateRecordDO::getOs, reqVO.getOs())
                .eqIfPresent(CalculateRecordDO::getClient, reqVO.getClient())
                .eqIfPresent(CalculateRecordDO::getDevice, reqVO.getDevice())
                .eqIfPresent(CalculateRecordDO::getMobile, reqVO.getMobile())
                .eqIfPresent(CalculateRecordDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CalculateRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CalculateRecordDO::getId));
    }

    default List<CalculateRecordDO> selectList(CalculateRecordExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CalculateRecordDO>()
                .eqIfPresent(CalculateRecordDO::getTypeId, reqVO.getTypeId())
                .eqIfPresent(CalculateRecordDO::getResult, reqVO.getResult())
                .eqIfPresent(CalculateRecordDO::getOs, reqVO.getOs())
                .eqIfPresent(CalculateRecordDO::getClient, reqVO.getClient())
                .eqIfPresent(CalculateRecordDO::getDevice, reqVO.getDevice())
                .eqIfPresent(CalculateRecordDO::getMobile, reqVO.getMobile())
                .eqIfPresent(CalculateRecordDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CalculateRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CalculateRecordDO::getId));
    }

}

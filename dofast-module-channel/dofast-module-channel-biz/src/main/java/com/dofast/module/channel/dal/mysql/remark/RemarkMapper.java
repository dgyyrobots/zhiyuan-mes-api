package com.dofast.module.channel.dal.mysql.remark;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.channel.dal.dataobject.remark.RemarkDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.channel.controller.admin.remark.vo.*;

/**
 * 订单备注 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface RemarkMapper extends BaseMapperX<RemarkDO> {

    default PageResult<RemarkDO> selectPage(RemarkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RemarkDO>()
                .eqIfPresent(RemarkDO::getTradeOrderId, reqVO.getTradeOrderId())
                .eqIfPresent(RemarkDO::getSortCode, reqVO.getSortCode())
                .eqIfPresent(RemarkDO::getRemark, reqVO.getRemark())
                .eqIfPresent(RemarkDO::getType, reqVO.getType())
                .eqIfPresent(RemarkDO::getSalId, reqVO.getSalId())
                .betweenIfPresent(RemarkDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RemarkDO::getSortCode));
    }

    default List<RemarkDO> selectList(RemarkExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<RemarkDO>()
                .eqIfPresent(RemarkDO::getTradeOrderId, reqVO.getTradeOrderId())
                .eqIfPresent(RemarkDO::getSortCode, reqVO.getSortCode())
                .eqIfPresent(RemarkDO::getRemark, reqVO.getRemark())
                .eqIfPresent(RemarkDO::getType, reqVO.getType())
                .eqIfPresent(RemarkDO::getSalId, reqVO.getSalId())
                .betweenIfPresent(RemarkDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RemarkDO::getId));
    }

    default Integer getTopSortCode() {
        RemarkDO remark = selectOne(new LambdaQueryWrapperX<RemarkDO>()
                .orderByDesc(RemarkDO::getSortCode)
                .last("limit 1")
        );
        if (remark== null || remark.getSortCode() <= 0) {
            return 1;
        }
        return remark.getSortCode() + 1;
    }
}

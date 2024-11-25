package com.dofast.module.mes.dal.mysql.mdunitconverse;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.mes.dal.dataobject.mdunitconverse.MdUnitConverseDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.mes.controller.admin.mdunitconverse.vo.*;

/**
 * 单位换算 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface MdUnitConverseMapper extends BaseMapperX<MdUnitConverseDO> {

    default PageResult<MdUnitConverseDO> selectPage(MdUnitConversePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MdUnitConverseDO>()
                .eqIfPresent(MdUnitConverseDO::getMeasureCode, reqVO.getMeasureCode())
                .eqIfPresent(MdUnitConverseDO::getOriginCount, reqVO.getOriginCount())
                .eqIfPresent(MdUnitConverseDO::getConverseCode, reqVO.getConverseCode())
                .eqIfPresent(MdUnitConverseDO::getConverseCount, reqVO.getConverseCount())
                .betweenIfPresent(MdUnitConverseDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MdUnitConverseDO::getId));
    }

    default List<MdUnitConverseDO> selectList(MdUnitConverseExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MdUnitConverseDO>()
                .eqIfPresent(MdUnitConverseDO::getMeasureCode, reqVO.getMeasureCode())
                .eqIfPresent(MdUnitConverseDO::getOriginCount, reqVO.getOriginCount())
                .eqIfPresent(MdUnitConverseDO::getConverseCode, reqVO.getConverseCode())
                .eqIfPresent(MdUnitConverseDO::getConverseCount, reqVO.getConverseCount())
                .betweenIfPresent(MdUnitConverseDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MdUnitConverseDO::getId));
    }

}

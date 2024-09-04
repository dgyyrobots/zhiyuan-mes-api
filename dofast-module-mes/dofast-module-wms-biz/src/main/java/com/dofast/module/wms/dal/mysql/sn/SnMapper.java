package com.dofast.module.wms.dal.mysql.sn;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.QueryWrapperX;
import com.dofast.module.wms.dal.dataobject.sn.SnDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.wms.controller.admin.sn.vo.*;

/**
 * SN码 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface SnMapper extends BaseMapperX<SnDO> {
    default PageResult<SnDO> selectPageSn(SnPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<SnDO>()
                .eqIfPresent("sn_code", reqVO.getSnCode())
                .eqIfPresent("item_id", reqVO.getItemId())
                .eqIfPresent("item_code", reqVO.getItemCode())
                .likeIfPresent("item_name", reqVO.getItemName())
                .eqIfPresent("specification", reqVO.getSpecification())
                .eqIfPresent("unit_of_measure", reqVO.getUnitOfMeasure())
                .eqIfPresent("batch_code", reqVO.getBatchCode())
                .groupBy("item_id", "item_code", "item_name", "specification", "unit_of_measure", "batch_code", "gen_date")
                .select("item_id", "item_code", "item_name", "specification", "unit_of_measure", "batch_code","gen_date", "max(create_time) as createTime", "count(*) as snNum")
                .orderByDesc("item_id","batch_code" ));
    }

    default PageResult<SnDO> selectPage(SnPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SnDO>()
                .eqIfPresent(SnDO::getSnCode, reqVO.getSnCode())
                .eqIfPresent(SnDO::getItemId, reqVO.getItemId())
                .eqIfPresent(SnDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(SnDO::getItemName, reqVO.getItemName())
                .eqIfPresent(SnDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(SnDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(SnDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(SnDO::getRemark, reqVO.getRemark())
                .eqIfPresent(SnDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(SnDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(SnDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(SnDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(SnDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SnDO::getId));
    }

    default List<SnDO> selectList(SnExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SnDO>()
                .eqIfPresent(SnDO::getSnCode, reqVO.getSnCode())
                .eqIfPresent(SnDO::getItemId, reqVO.getItemId())
                .eqIfPresent(SnDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(SnDO::getItemName, reqVO.getItemName())
                .eqIfPresent(SnDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(SnDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(SnDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(SnDO::getRemark, reqVO.getRemark())
                .eqIfPresent(SnDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(SnDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(SnDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(SnDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(SnDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SnDO::getId));
    }

}

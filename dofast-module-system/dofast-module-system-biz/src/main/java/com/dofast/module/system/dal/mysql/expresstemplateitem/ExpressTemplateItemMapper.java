package com.dofast.module.system.dal.mysql.expresstemplateitem;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.system.controller.admin.expresstemplateitem.vo.ExpressTemplateItemExportReqVO;
import com.dofast.module.system.controller.admin.expresstemplateitem.vo.ExpressTemplateItemPageReqVO;
import com.dofast.module.system.dal.dataobject.expresstemplateitem.ExpressTemplateItemDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 运费模板细节 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface ExpressTemplateItemMapper extends BaseMapperX<ExpressTemplateItemDO> {

    default PageResult<ExpressTemplateItemDO> selectPage(ExpressTemplateItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ExpressTemplateItemDO>()
                .eqIfPresent(ExpressTemplateItemDO::getTemplateId, reqVO.getTemplateId())
                .eqIfPresent(ExpressTemplateItemDO::getAreaIds, reqVO.getAreaIds())
                .eqIfPresent(ExpressTemplateItemDO::getStartUnit, reqVO.getStartUnit())
                .eqIfPresent(ExpressTemplateItemDO::getStartUnitPrice, reqVO.getStartUnitPrice())
                .eqIfPresent(ExpressTemplateItemDO::getPlusPerUnit, reqVO.getPlusPerUnit())
                .eqIfPresent(ExpressTemplateItemDO::getPlusPerUnitPrice, reqVO.getPlusPerUnitPrice())
                .eqIfPresent(ExpressTemplateItemDO::getPriceType, reqVO.getPriceType())
                .betweenIfPresent(ExpressTemplateItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ExpressTemplateItemDO::getId));
    }

    default List<ExpressTemplateItemDO> selectList(ExpressTemplateItemExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ExpressTemplateItemDO>()
                .eqIfPresent(ExpressTemplateItemDO::getTemplateId, reqVO.getTemplateId())
                .eqIfPresent(ExpressTemplateItemDO::getAreaIds, reqVO.getAreaIds())
                .eqIfPresent(ExpressTemplateItemDO::getStartUnit, reqVO.getStartUnit())
                .eqIfPresent(ExpressTemplateItemDO::getStartUnitPrice, reqVO.getStartUnitPrice())
                .eqIfPresent(ExpressTemplateItemDO::getPlusPerUnit, reqVO.getPlusPerUnit())
                .eqIfPresent(ExpressTemplateItemDO::getPlusPerUnitPrice, reqVO.getPlusPerUnitPrice())
                .eqIfPresent(ExpressTemplateItemDO::getPriceType, reqVO.getPriceType())
                .betweenIfPresent(ExpressTemplateItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ExpressTemplateItemDO::getId));
    }

}

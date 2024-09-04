package com.dofast.module.system.dal.mysql.expresstemplate;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.system.controller.admin.expresstemplate.vo.ExpressTemplateExportReqVO;
import com.dofast.module.system.controller.admin.expresstemplate.vo.ExpressTemplatePageReqVO;
import com.dofast.module.system.dal.dataobject.expresstemplate.ExpressTemplateDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 运费模板 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface ExpressTemplateMapper extends BaseMapperX<ExpressTemplateDO> {

    default PageResult<ExpressTemplateDO> selectPage(ExpressTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ExpressTemplateDO>()
                .likeIfPresent(ExpressTemplateDO::getName, reqVO.getName())
                .eqIfPresent(ExpressTemplateDO::getPriceType, reqVO.getPriceType())
                .eqIfPresent(ExpressTemplateDO::getDefaulted, reqVO.getDefaulted())
                .betweenIfPresent(ExpressTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ExpressTemplateDO::getId));
    }

    default List<ExpressTemplateDO> selectList(ExpressTemplateExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ExpressTemplateDO>()
                .likeIfPresent(ExpressTemplateDO::getName, reqVO.getName())
                .eqIfPresent(ExpressTemplateDO::getPriceType, reqVO.getPriceType())
                .eqIfPresent(ExpressTemplateDO::getDefaulted, reqVO.getDefaulted())
                .betweenIfPresent(ExpressTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ExpressTemplateDO::getId));
    }

}

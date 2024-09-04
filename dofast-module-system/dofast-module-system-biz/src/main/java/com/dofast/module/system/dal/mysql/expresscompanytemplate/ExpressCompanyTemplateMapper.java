package com.dofast.module.system.dal.mysql.expresscompanytemplate;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.system.controller.admin.expresscompanytemplate.vo.ExpressCompanyTemplateExportReqVO;
import com.dofast.module.system.controller.admin.expresscompanytemplate.vo.ExpressCompanyTemplatePageReqVO;
import com.dofast.module.system.dal.dataobject.expresscompanytemplate.ExpressCompanyTemplateDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统物流公司 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface ExpressCompanyTemplateMapper extends BaseMapperX<ExpressCompanyTemplateDO> {

    default PageResult<ExpressCompanyTemplateDO> selectPage(ExpressCompanyTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ExpressCompanyTemplateDO>()
                .eqIfPresent(ExpressCompanyTemplateDO::getSiteId, reqVO.getSiteId())
                .likeIfPresent(ExpressCompanyTemplateDO::getCompanyName, reqVO.getCompanyName())
                .eqIfPresent(ExpressCompanyTemplateDO::getLogo, reqVO.getLogo())
                .eqIfPresent(ExpressCompanyTemplateDO::getUrl, reqVO.getUrl())
                .eqIfPresent(ExpressCompanyTemplateDO::getSort, reqVO.getSort())
                .eqIfPresent(ExpressCompanyTemplateDO::getExpressNo, reqVO.getExpressNo())
                .eqIfPresent(ExpressCompanyTemplateDO::getExpressNoKd100, reqVO.getExpressNoKd100())
                .eqIfPresent(ExpressCompanyTemplateDO::getExpressNoCainiao, reqVO.getExpressNoCainiao())
                .eqIfPresent(ExpressCompanyTemplateDO::getContentJson, reqVO.getContentJson())
                .eqIfPresent(ExpressCompanyTemplateDO::getBackgroundImage, reqVO.getBackgroundImage())
                .eqIfPresent(ExpressCompanyTemplateDO::getFontSize, reqVO.getFontSize())
                .eqIfPresent(ExpressCompanyTemplateDO::getWidth, reqVO.getWidth())
                .eqIfPresent(ExpressCompanyTemplateDO::getHeight, reqVO.getHeight())
                .eqIfPresent(ExpressCompanyTemplateDO::getScale, reqVO.getScale())
                .eqIfPresent(ExpressCompanyTemplateDO::getIsElectronicsheet, reqVO.getIsElectronicsheet())
                .eqIfPresent(ExpressCompanyTemplateDO::getPrintStyle, reqVO.getPrintStyle())
                .betweenIfPresent(ExpressCompanyTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ExpressCompanyTemplateDO::getCompanyId));
    }

    default List<ExpressCompanyTemplateDO> selectList(ExpressCompanyTemplateExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ExpressCompanyTemplateDO>()
                .eqIfPresent(ExpressCompanyTemplateDO::getSiteId, reqVO.getSiteId())
                .likeIfPresent(ExpressCompanyTemplateDO::getCompanyName, reqVO.getCompanyName())
                .eqIfPresent(ExpressCompanyTemplateDO::getLogo, reqVO.getLogo())
                .eqIfPresent(ExpressCompanyTemplateDO::getUrl, reqVO.getUrl())
                .eqIfPresent(ExpressCompanyTemplateDO::getSort, reqVO.getSort())
                .eqIfPresent(ExpressCompanyTemplateDO::getExpressNo, reqVO.getExpressNo())
                .eqIfPresent(ExpressCompanyTemplateDO::getExpressNoKd100, reqVO.getExpressNoKd100())
                .eqIfPresent(ExpressCompanyTemplateDO::getExpressNoCainiao, reqVO.getExpressNoCainiao())
                .eqIfPresent(ExpressCompanyTemplateDO::getContentJson, reqVO.getContentJson())
                .eqIfPresent(ExpressCompanyTemplateDO::getBackgroundImage, reqVO.getBackgroundImage())
                .eqIfPresent(ExpressCompanyTemplateDO::getFontSize, reqVO.getFontSize())
                .eqIfPresent(ExpressCompanyTemplateDO::getWidth, reqVO.getWidth())
                .eqIfPresent(ExpressCompanyTemplateDO::getHeight, reqVO.getHeight())
                .eqIfPresent(ExpressCompanyTemplateDO::getScale, reqVO.getScale())
                .eqIfPresent(ExpressCompanyTemplateDO::getIsElectronicsheet, reqVO.getIsElectronicsheet())
                .eqIfPresent(ExpressCompanyTemplateDO::getPrintStyle, reqVO.getPrintStyle())
                .betweenIfPresent(ExpressCompanyTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ExpressCompanyTemplateDO::getCompanyId));
    }

}

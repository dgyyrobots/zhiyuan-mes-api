package com.dofast.module.system.dal.mysql.expresscompany;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.system.controller.admin.expresscompany.vo.ExpressCompanyExportReqVO;
import com.dofast.module.system.controller.admin.expresscompany.vo.ExpressCompanyPageReqVO;
import com.dofast.module.system.dal.dataobject.expresscompany.ExpressCompanyDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 租户物流公司 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface ExpressCompanyMapper extends BaseMapperX<ExpressCompanyDO> {

    default PageResult<ExpressCompanyDO> selectPage(ExpressCompanyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ExpressCompanyDO>()
                .eqIfPresent(ExpressCompanyDO::getCompanyId, reqVO.getCompanyId())
                .likeIfPresent(ExpressCompanyDO::getCompanyName, reqVO.getCompanyName())
                .eqIfPresent(ExpressCompanyDO::getLogo, reqVO.getLogo())
                .eqIfPresent(ExpressCompanyDO::getContentJson, reqVO.getContentJson())
                .eqIfPresent(ExpressCompanyDO::getBackgroundImage, reqVO.getBackgroundImage())
                .eqIfPresent(ExpressCompanyDO::getFontSize, reqVO.getFontSize())
                .eqIfPresent(ExpressCompanyDO::getWidth, reqVO.getWidth())
                .eqIfPresent(ExpressCompanyDO::getHeight, reqVO.getHeight())
                .eqIfPresent(ExpressCompanyDO::getScale, reqVO.getScale())
                .betweenIfPresent(ExpressCompanyDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ExpressCompanyDO::getIsElectronicsheet, reqVO.getIsElectronicsheet())
                .eqIfPresent(ExpressCompanyDO::getExpressNo, reqVO.getExpressNo())
                .eqIfPresent(ExpressCompanyDO::getExpressNoDiansan, reqVO.getExpressNoDiansan())
                .eqIfPresent(ExpressCompanyDO::getExpressNoKd100, reqVO.getExpressNoKd100())
                .eqIfPresent(ExpressCompanyDO::getExpressNoCainiao, reqVO.getExpressNoCainiao())
                .eqIfPresent(ExpressCompanyDO::getPrintStyle, reqVO.getPrintStyle())
                .orderByDesc(ExpressCompanyDO::getId));
    }

    default List<ExpressCompanyDO> selectList(ExpressCompanyExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ExpressCompanyDO>()
                .eqIfPresent(ExpressCompanyDO::getCompanyId, reqVO.getCompanyId())
                .likeIfPresent(ExpressCompanyDO::getCompanyName, reqVO.getCompanyName())
                .eqIfPresent(ExpressCompanyDO::getLogo, reqVO.getLogo())
                .eqIfPresent(ExpressCompanyDO::getContentJson, reqVO.getContentJson())
                .eqIfPresent(ExpressCompanyDO::getBackgroundImage, reqVO.getBackgroundImage())
                .eqIfPresent(ExpressCompanyDO::getFontSize, reqVO.getFontSize())
                .eqIfPresent(ExpressCompanyDO::getWidth, reqVO.getWidth())
                .eqIfPresent(ExpressCompanyDO::getHeight, reqVO.getHeight())
                .eqIfPresent(ExpressCompanyDO::getScale, reqVO.getScale())
                .betweenIfPresent(ExpressCompanyDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ExpressCompanyDO::getIsElectronicsheet, reqVO.getIsElectronicsheet())
                .eqIfPresent(ExpressCompanyDO::getExpressNo, reqVO.getExpressNo())
                .eqIfPresent(ExpressCompanyDO::getExpressNoDiansan, reqVO.getExpressNoDiansan())
                .eqIfPresent(ExpressCompanyDO::getExpressNoKd100, reqVO.getExpressNoKd100())
                .eqIfPresent(ExpressCompanyDO::getExpressNoCainiao, reqVO.getExpressNoCainiao())
                .eqIfPresent(ExpressCompanyDO::getPrintStyle, reqVO.getPrintStyle())
                .orderByDesc(ExpressCompanyDO::getId));
    }

    default ExpressCompanyDO selectOne(String code){
        return selectOne(new LambdaQueryWrapperX<ExpressCompanyDO>()
                .eqIfPresent(ExpressCompanyDO::getExpressNo,code));
    }

}

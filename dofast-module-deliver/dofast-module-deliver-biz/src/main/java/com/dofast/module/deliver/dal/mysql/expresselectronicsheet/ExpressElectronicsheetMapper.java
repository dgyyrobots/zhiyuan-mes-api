package com.dofast.module.deliver.dal.mysql.expresselectronicsheet;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.deliver.dal.dataobject.expresselectronicsheet.ExpressElectronicsheetDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.deliver.controller.admin.expresselectronicsheet.vo.*;

/**
 * 电子面单 Mapper
 *
 * @author a1
 */
@Mapper
public interface ExpressElectronicsheetMapper extends BaseMapperX<ExpressElectronicsheetDO> {

    default PageResult<ExpressElectronicsheetDO> selectPage(ExpressElectronicsheetPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ExpressElectronicsheetDO>()
                .likeIfPresent(ExpressElectronicsheetDO::getCompanyName, reqVO.getCompanyName())
                .eqIfPresent(ExpressElectronicsheetDO::getType, reqVO.getType())
                .eqIfPresent(ExpressElectronicsheetDO::getInfo, reqVO.getInfo())
                .eqIfPresent(ExpressElectronicsheetDO::getKdnCode, reqVO.getKdnCode())
                .eqIfPresent(ExpressElectronicsheetDO::getTemplate, reqVO.getTemplate())
                .eqIfPresent(ExpressElectronicsheetDO::getConfig, reqVO.getConfig())
                .betweenIfPresent(ExpressElectronicsheetDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ExpressElectronicsheetDO::getId));
    }

    default List<ExpressElectronicsheetDO> selectList(ExpressElectronicsheetExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ExpressElectronicsheetDO>()
                .likeIfPresent(ExpressElectronicsheetDO::getCompanyName, reqVO.getCompanyName())
                .eqIfPresent(ExpressElectronicsheetDO::getType, reqVO.getType())
                .eqIfPresent(ExpressElectronicsheetDO::getInfo, reqVO.getInfo())
                .eqIfPresent(ExpressElectronicsheetDO::getKdnCode, reqVO.getKdnCode())
                .eqIfPresent(ExpressElectronicsheetDO::getTemplate, reqVO.getTemplate())
                .eqIfPresent(ExpressElectronicsheetDO::getConfig, reqVO.getConfig())
                .betweenIfPresent(ExpressElectronicsheetDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ExpressElectronicsheetDO::getId));
    }

}

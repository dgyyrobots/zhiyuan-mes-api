package com.dofast.module.wms.dal.mysql.barcodeconfig;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.wms.dal.dataobject.barcodeconfig.BarcodeConfigDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.wms.controller.admin.barcodeconfig.vo.*;

/**
 * 条码配置 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface BarcodeConfigMapper extends BaseMapperX<BarcodeConfigDO> {
    default List<BarcodeConfigDO> selectList(BarcodeConfigListVO listVO){
        return selectList(new LambdaQueryWrapperX<BarcodeConfigDO>()
                .eqIfPresent(BarcodeConfigDO::getBarcodeFormart,listVO.getBarcodeFormart())
                .eqIfPresent(BarcodeConfigDO::getBarcodeType,listVO.getBarcodeType())
                .eqIfPresent(BarcodeConfigDO::getContentFormart,listVO.getContentFormart())
                .eqIfPresent(BarcodeConfigDO::getContentExample,listVO.getContentFormart())
                .eqIfPresent(BarcodeConfigDO::getAutoGenFlag,listVO.getAutoGenFlag())
                .eqIfPresent(BarcodeConfigDO::getEnableFlag,listVO.getEnableFlag())
                .eqIfPresent(BarcodeConfigDO::getAttr1,listVO.getAttr1())
                .eqIfPresent(BarcodeConfigDO::getAttr2,listVO.getAttr2())
                .eqIfPresent(BarcodeConfigDO::getAttr3,listVO.getAttr3())
                .eqIfPresent(BarcodeConfigDO::getAttr4,listVO.getAttr4())
        );
    }
    default PageResult<BarcodeConfigDO> selectPage(BarcodeConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BarcodeConfigDO>()
                .eqIfPresent(BarcodeConfigDO::getBarcodeFormart, reqVO.getBarcodeFormart())
                .eqIfPresent(BarcodeConfigDO::getBarcodeType, reqVO.getBarcodeType())
                .eqIfPresent(BarcodeConfigDO::getContentFormart, reqVO.getContentFormart())
                .eqIfPresent(BarcodeConfigDO::getContentExample, reqVO.getContentExample())
                .eqIfPresent(BarcodeConfigDO::getAutoGenFlag, reqVO.getAutoGenFlag())
                .eqIfPresent(BarcodeConfigDO::getDefaultTemplate, reqVO.getDefaultTemplate())
                .eqIfPresent(BarcodeConfigDO::getEnableFlag, reqVO.getEnableFlag())
                .eqIfPresent(BarcodeConfigDO::getRemark, reqVO.getRemark())
                .eqIfPresent(BarcodeConfigDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(BarcodeConfigDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(BarcodeConfigDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(BarcodeConfigDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(BarcodeConfigDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BarcodeConfigDO::getId));
    }

    default List<BarcodeConfigDO> selectList(BarcodeConfigExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<BarcodeConfigDO>()
                .eqIfPresent(BarcodeConfigDO::getBarcodeFormart, reqVO.getBarcodeFormart())
                .eqIfPresent(BarcodeConfigDO::getBarcodeType, reqVO.getBarcodeType())
                .eqIfPresent(BarcodeConfigDO::getContentFormart, reqVO.getContentFormart())
                .eqIfPresent(BarcodeConfigDO::getContentExample, reqVO.getContentExample())
                .eqIfPresent(BarcodeConfigDO::getAutoGenFlag, reqVO.getAutoGenFlag())
                .eqIfPresent(BarcodeConfigDO::getDefaultTemplate, reqVO.getDefaultTemplate())
                .eqIfPresent(BarcodeConfigDO::getEnableFlag, reqVO.getEnableFlag())
                .eqIfPresent(BarcodeConfigDO::getRemark, reqVO.getRemark())
                .eqIfPresent(BarcodeConfigDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(BarcodeConfigDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(BarcodeConfigDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(BarcodeConfigDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(BarcodeConfigDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BarcodeConfigDO::getId));
    }

}

package com.dofast.module.cmms.dal.mysql.dvsubject;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.cmms.dal.dataobject.dvsubject.DvSubjectDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.cmms.controller.admin.dvsubject.vo.*;

/**
 * 设备点检保养项目 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DvSubjectMapper extends BaseMapperX<DvSubjectDO> {
    default DvSubjectDO checkSubjectCodeUnique(String  dvSubjectCode){
        return selectOne(new LambdaQueryWrapperX<DvSubjectDO>().eq(DvSubjectDO::getSubjectCode,dvSubjectCode));
    };

    default PageResult<DvSubjectDO> selectPage(DvSubjectPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DvSubjectDO>()
                .eqIfPresent(DvSubjectDO::getSubjectCode, reqVO.getSubjectCode())
                .likeIfPresent(DvSubjectDO::getSubjectName, reqVO.getSubjectName())
                .eqIfPresent(DvSubjectDO::getSubjectType, reqVO.getSubjectType())
                .eqIfPresent(DvSubjectDO::getSubjectContent, reqVO.getSubjectContent())
                .eqIfPresent(DvSubjectDO::getSubjectStandard, reqVO.getSubjectStandard())
                .eqIfPresent(DvSubjectDO::getEnableFlag, reqVO.getEnableFlag())
                .eqIfPresent(DvSubjectDO::getRemark, reqVO.getRemark())
                .eqIfPresent(DvSubjectDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(DvSubjectDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(DvSubjectDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(DvSubjectDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(DvSubjectDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DvSubjectDO::getId));
    }

    default List<DvSubjectDO> selectList(DvSubjectExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DvSubjectDO>()
                .eqIfPresent(DvSubjectDO::getSubjectCode, reqVO.getSubjectCode())
                .likeIfPresent(DvSubjectDO::getSubjectName, reqVO.getSubjectName())
                .eqIfPresent(DvSubjectDO::getSubjectType, reqVO.getSubjectType())
                .eqIfPresent(DvSubjectDO::getSubjectContent, reqVO.getSubjectContent())
                .eqIfPresent(DvSubjectDO::getSubjectStandard, reqVO.getSubjectStandard())
                .eqIfPresent(DvSubjectDO::getEnableFlag, reqVO.getEnableFlag())
                .eqIfPresent(DvSubjectDO::getRemark, reqVO.getRemark())
                .eqIfPresent(DvSubjectDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(DvSubjectDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(DvSubjectDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(DvSubjectDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(DvSubjectDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DvSubjectDO::getId));
    }

}

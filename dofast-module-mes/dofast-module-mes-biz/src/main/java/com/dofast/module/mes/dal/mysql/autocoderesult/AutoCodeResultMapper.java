package com.dofast.module.mes.dal.mysql.autocoderesult;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.mes.controller.admin.Autocodepart.vo.AutoCodePartListVO;
import com.dofast.module.mes.dal.dataobject.Autocodepart.AutoCodePartDO;
import com.dofast.module.mes.dal.dataobject.autocoderesult.AutoCodeResultDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.mes.controller.admin.autocoderesult.vo.*;

/**
 * 编码生成记录 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface AutoCodeResultMapper extends BaseMapperX<AutoCodeResultDO> {
//    default List<AutoCodeResultDO> selectResultList(AutoCodeResultListVO listVO){
//        return selectList(new LambdaQueryWrapperX<AutoCodeResultDO>()
//                .eqIfPresent(AutoCodeResultDO::getId,listVO.getId())
//                .eqIfPresent(AutoCodeResultDO::getRuleId,listVO.getRuleId())
//                .eqIfPresent(AutoCodeResultDO::getLastInputChar,listVO.getLastInputChar())
//                .likeIfPresent(AutoCodeResultDO::getGenDate,listVO.getGenDate()+"%")
//                .orderByAsc(AutoCodeResultDO::getGenDate)
//        );
//    }
    public List<AutoCodeResultDO> selectAutoCodeResultList(AutoCodeResultListVO listVO);

    default PageResult<AutoCodeResultDO> selectPage(AutoCodeResultPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AutoCodeResultDO>()
                .eqIfPresent(AutoCodeResultDO::getRuleId, reqVO.getRuleId())
                .eqIfPresent(AutoCodeResultDO::getGenIndex, reqVO.getGenIndex())
                .eqIfPresent(AutoCodeResultDO::getLastResult, reqVO.getLastResult())
                .eqIfPresent(AutoCodeResultDO::getLastSerialNo, reqVO.getLastSerialNo())
                .eqIfPresent(AutoCodeResultDO::getLastInputChar, reqVO.getLastInputChar())
                .eqIfPresent(AutoCodeResultDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AutoCodeResultDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(AutoCodeResultDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(AutoCodeResultDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(AutoCodeResultDO::getAttr4, reqVO.getAttr4())
                .orderByDesc(AutoCodeResultDO::getId));
    }

    default List<AutoCodeResultDO> selectList(AutoCodeResultExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AutoCodeResultDO>()
                .eqIfPresent(AutoCodeResultDO::getRuleId, reqVO.getRuleId())
                .eqIfPresent(AutoCodeResultDO::getGenIndex, reqVO.getGenIndex())
                .eqIfPresent(AutoCodeResultDO::getLastResult, reqVO.getLastResult())
                .eqIfPresent(AutoCodeResultDO::getLastSerialNo, reqVO.getLastSerialNo())
                .eqIfPresent(AutoCodeResultDO::getLastInputChar, reqVO.getLastInputChar())
                .eqIfPresent(AutoCodeResultDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AutoCodeResultDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(AutoCodeResultDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(AutoCodeResultDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(AutoCodeResultDO::getAttr4, reqVO.getAttr4())
                .orderByDesc(AutoCodeResultDO::getId));
    }

}

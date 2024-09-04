package com.dofast.module.qms.convert.defect;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.qms.controller.admin.defect.vo.*;
import com.dofast.module.qms.dal.dataobject.defect.DefectDO;

/**
 * 常见缺陷 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface DefectConvert {

    DefectConvert INSTANCE = Mappers.getMapper(DefectConvert.class);

    DefectDO convert(DefectCreateReqVO bean);

    default DefectDO convert(DefectUpdateReqVO bean){
        if ( bean == null ) {
            return null;
        }

        DefectDO.DefectDOBuilder defectDO = DefectDO.builder();

        defectDO.id( bean.getId() );
        defectDO.defectCode( bean.getDefectCode() );
        defectDO.defectName( bean.getDefectName() );
        defectDO.indexType( bean.getIndexType() );
        defectDO.defectLevel( bean.getDefectLevel() );
        defectDO.remark( bean.getRemark() );
        defectDO.attr1( bean.getAttr1() );
        defectDO.attr2( bean.getAttr2() );
        defectDO.attr3( bean.getAttr3() );
        defectDO.attr4( bean.getAttr4() );

        return defectDO.build();
    }

    DefectRespVO convert(DefectDO bean);

    List<DefectRespVO> convertList(List<DefectDO> list);

    PageResult<DefectRespVO> convertPage(PageResult<DefectDO> page);

    List<DefectExcelVO> convertList02(List<DefectDO> list);

}

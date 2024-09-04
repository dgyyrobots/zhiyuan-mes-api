package com.dofast.module.qms.convert.defectrecord;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.qms.controller.admin.defectrecord.vo.*;
import com.dofast.module.qms.dal.dataobject.defectrecord.DefectRecordDO;

/**
 * 检验单缺陷记录 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface DefectRecordConvert {

    DefectRecordConvert INSTANCE = Mappers.getMapper(DefectRecordConvert.class);

    default DefectRecordDO convert(DefectRecordCreateReqVO bean){
        if ( bean == null ) {
            return null;
        }

        DefectRecordDO.DefectRecordDOBuilder defectRecordDO = DefectRecordDO.builder();

        defectRecordDO.qcType( bean.getQcType() );
        defectRecordDO.qcId( bean.getQcId() );
        defectRecordDO.lineId( bean.getLineId() );
        defectRecordDO.defectName( bean.getDefectName() );
        defectRecordDO.defectLevel( bean.getDefectLevel() );
        defectRecordDO.defectQuantity( bean.getDefectQuantity() );
        defectRecordDO.remark( bean.getRemark() );
        defectRecordDO.attr1( bean.getAttr1() );
        defectRecordDO.attr2( bean.getAttr2() );
        defectRecordDO.attr3( bean.getAttr3() );
        defectRecordDO.attr4( bean.getAttr4() );

        return defectRecordDO.build();
    }

    List<DefectRecordDO> coverList(List<DefectRecordUpdateReqVO> been);

    default DefectRecordDO convert(DefectRecordUpdateReqVO bean){
        if ( bean == null ) {
            return null;
        }

        DefectRecordDO.DefectRecordDOBuilder defectRecordDO = DefectRecordDO.builder();

        defectRecordDO.id( bean.getId() );
        defectRecordDO.qcType( bean.getQcType() );
        defectRecordDO.qcId( bean.getQcId() );
        defectRecordDO.lineId( bean.getLineId() );
        defectRecordDO.defectName( bean.getDefectName() );
        defectRecordDO.defectLevel( bean.getDefectLevel() );
        defectRecordDO.defectQuantity( bean.getDefectQuantity() );
        defectRecordDO.remark( bean.getRemark() );
        defectRecordDO.attr1( bean.getAttr1() );
        defectRecordDO.attr2( bean.getAttr2() );
        defectRecordDO.attr3( bean.getAttr3() );
        defectRecordDO.attr4( bean.getAttr4() );

        return defectRecordDO.build();
    }

    DefectRecordRespVO convert(DefectRecordDO bean);

    List<DefectRecordRespVO> convertList(List<DefectRecordDO> list);

    default PageResult<DefectRecordRespVO> convertPage(PageResult<DefectRecordDO> page){
        if ( page == null ) {
            return null;
        }

        PageResult<DefectRecordRespVO> pageResult = new PageResult<DefectRecordRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    List<DefectRecordExcelVO> convertList02(List<DefectRecordDO> list);

}

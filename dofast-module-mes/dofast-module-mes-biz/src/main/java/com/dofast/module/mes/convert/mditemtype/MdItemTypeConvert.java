package com.dofast.module.mes.convert.mditemtype;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.mditemtype.vo.*;
import com.dofast.module.mes.dal.dataobject.mditemtype.MdItemTypeDO;

/**
 * 物料产品分类 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface MdItemTypeConvert {

    MdItemTypeConvert INSTANCE = Mappers.getMapper(MdItemTypeConvert.class);

    MdItemTypeDO convert(MdItemTypeCreateReqVO bean);

    MdItemTypeDO convert(MdItemTypeUpdateReqVO bean);

    MdItemTypeRespVO convert(MdItemTypeDO bean);

    List<MdItemTypeRespVO> convertList(List<MdItemTypeDO> list);

    PageResult<MdItemTypeRespVO> convertPage(PageResult<MdItemTypeDO> page);

    List<MdItemTypeExcelVO> convertList02(List<MdItemTypeDO> list);

}

package com.dofast.module.mes.convert.mdproductsop;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.mdproductsop.vo.*;
import com.dofast.module.mes.dal.dataobject.mdproductsop.MdProductSopDO;

/**
 * 产品SOP Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface MdProductSopConvert {

    MdProductSopConvert INSTANCE = Mappers.getMapper(MdProductSopConvert.class);

    MdProductSopDO convert(MdProductSopCreateReqVO bean);

    MdProductSopDO convert(MdProductSopUpdateReqVO bean);

    MdProductSopRespVO convert(MdProductSopDO bean);

    List<MdProductSopRespVO> convertList(List<MdProductSopDO> list);

    PageResult<MdProductSopRespVO> convertPage(PageResult<MdProductSopDO> page);

    List<MdProductSopExcelVO> convertList02(List<MdProductSopDO> list);

}

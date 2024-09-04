package com.dofast.module.wms.convert.transferline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.transferline.vo.*;
import com.dofast.module.wms.dal.dataobject.transferline.TransferLineDO;

/**
 * 转移单行 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface TransferLineConvert {

    TransferLineConvert INSTANCE = Mappers.getMapper(TransferLineConvert.class);

    TransferLineDO convert(TransferLineCreateReqVO bean);

    TransferLineDO convert(TransferLineUpdateReqVO bean);

    TransferLineRespVO convert(TransferLineDO bean);

    List<TransferLineRespVO> convertList(List<TransferLineDO> list);

    PageResult<TransferLineRespVO> convertPage(PageResult<TransferLineDO> page);

    List<TransferLineExcelVO> convertList02(List<TransferLineDO> list);

}

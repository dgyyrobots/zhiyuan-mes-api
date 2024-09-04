package com.dofast.module.wms.convert.transfer;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.transfer.vo.*;
import com.dofast.module.wms.dal.dataobject.transfer.TransferDO;

/**
 * 转移单 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface TransferConvert {

    TransferConvert INSTANCE = Mappers.getMapper(TransferConvert.class);

    TransferDO convert(TransferCreateReqVO bean);

    TransferDO convert(TransferUpdateReqVO bean);

    TransferRespVO convert(TransferDO bean);
    TransferUpdateReqVO convert01(TransferDO bean);

    List<TransferRespVO> convertList(List<TransferDO> list);

    PageResult<TransferRespVO> convertPage(PageResult<TransferDO> page);

    List<TransferExcelVO> convertList02(List<TransferDO> list);

}

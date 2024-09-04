package com.dofast.module.wms.convert.transaction;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.transaction.vo.*;
import com.dofast.module.wms.dal.dataobject.transaction.TransactionDO;

/**
 * 库存事务 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface TransactionConvert {

    TransactionConvert INSTANCE = Mappers.getMapper(TransactionConvert.class);

    TransactionDO convert(TransactionCreateReqVO bean);

    TransactionDO convert(TransactionUpdateReqVO bean);

    TransactionRespVO convert(TransactionDO bean);

    TransactionUpdateReqVO convert01(TransactionDO bean);

    List<TransactionRespVO> convertList(List<TransactionDO> list);

    PageResult<TransactionRespVO> convertPage(PageResult<TransactionDO> page);

    List<TransactionExcelVO> convertList02(List<TransactionDO> list);



}

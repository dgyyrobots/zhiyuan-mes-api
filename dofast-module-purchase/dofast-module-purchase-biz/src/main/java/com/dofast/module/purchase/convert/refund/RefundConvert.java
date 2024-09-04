package com.dofast.module.purchase.convert.refund;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.purchase.controller.admin.bpm.refund.vo.RefundBpmCreateReqVO;
import com.dofast.module.purchase.controller.admin.bpm.refund.vo.RefundBpmRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.purchase.controller.admin.refund.vo.*;
import com.dofast.module.purchase.dal.dataobject.refund.RefundDO;

/**
 * 采购退货 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface RefundConvert {

    RefundConvert INSTANCE = Mappers.getMapper(RefundConvert.class);

    RefundDO convert(RefundCreateReqVO bean);

    RefundDO convert(RefundUpdateReqVO bean);

    RefundDO convert(RefundBpmCreateReqVO bpmCreateReqVO);

    RefundRespVO convert(RefundDO bean);

    List<RefundRespVO> convertList(List<RefundDO> list);

    PageResult<RefundRespVO> convertPage(PageResult<RefundDO> page);

    List<RefundExcelVO> convertList02(List<RefundDO> list);

    RefundBpmRespVO convertBpm(RefundDO refundDO);

    PageResult<RefundBpmRespVO> convertPageBpm(PageResult<RefundDO> pageResult);
}

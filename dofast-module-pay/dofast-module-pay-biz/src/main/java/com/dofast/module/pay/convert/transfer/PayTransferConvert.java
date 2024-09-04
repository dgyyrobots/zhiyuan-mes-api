package com.dofast.module.pay.convert.transfer;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.pay.core.client.dto.transfer.PayTransferUnifiedReqDTO;
import com.dofast.module.pay.api.transfer.dto.PayTransferCreateReqDTO;
import com.dofast.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferCreateReqVO;
import com.dofast.module.pay.controller.admin.transfer.vo.PayTransferCreateReqVO;
import com.dofast.module.pay.controller.admin.transfer.vo.PayTransferPageItemRespVO;
import com.dofast.module.pay.controller.admin.transfer.vo.PayTransferRespVO;
import com.dofast.module.pay.dal.dataobject.transfer.PayTransferDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayTransferConvert {

    PayTransferConvert INSTANCE = Mappers.getMapper(PayTransferConvert.class);

    PayTransferDO convert(PayTransferCreateReqDTO dto);

    PayTransferUnifiedReqDTO convert2(PayTransferCreateReqDTO dto);

    PayTransferCreateReqDTO convert(PayTransferCreateReqVO vo);

    PayTransferCreateReqDTO convert(PayDemoTransferCreateReqVO vo);

    PayTransferRespVO convert(PayTransferDO bean);

    PageResult<PayTransferPageItemRespVO> convertPage(PageResult<PayTransferDO> pageResult);

}

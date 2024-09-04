package com.dofast.module.pay.api.transfer;

import com.dofast.module.pay.api.transfer.dto.PayTransferCreateReqDTO;


import com.dofast.module.pay.service.transfer.PayTransferService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**

import com.dofast.module.pay.service.transfer.PayTransferService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * 转账单 API 实现类
 *


import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**

 * @author jason
 */
@Service
@Validated
public class PayTransferApiImpl implements PayTransferApi {


    @Resource
    private PayTransferService payTransferService;

    @Override
    public Long createTransfer(PayTransferCreateReqDTO reqDTO) {
        return payTransferService.createTransfer(reqDTO);
    }




}

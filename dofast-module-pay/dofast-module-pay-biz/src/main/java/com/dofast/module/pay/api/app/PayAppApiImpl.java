package com.dofast.module.pay.api.app;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.module.pay.app.PayAppApi;
import com.dofast.module.pay.app.dto.PayAppResDTO;
import com.dofast.module.pay.controller.admin.merchant.vo.app.PayAppExportReqVO;
import com.dofast.module.pay.dal.dataobject.app.PayAppDO;
import com.dofast.module.pay.service.app.PayAppService;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PayAppApiImpl implements PayAppApi {

    @Resource
    PayAppService payAppService;

    @Override
    public PayAppResDTO getApp() {
        PayAppExportReqVO payAppExportReqVO = new PayAppExportReqVO();
        List<PayAppDO> appList = payAppService.getAppList10(payAppExportReqVO);
        if (appList == null || appList.isEmpty()) return null;
        PayAppResDTO payAppResDTO = BeanUtil.toBean(appList.get(0), PayAppResDTO.class);
        return payAppResDTO;
    }
}

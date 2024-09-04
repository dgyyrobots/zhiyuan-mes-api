package com.dofast.module.qms.api.OqcApi;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.module.pro.api.FeedbackApi.dto.FeedbackDTO;
import com.dofast.module.pro.api.WorkorderApi.dto.WorkorderDTO;
import com.dofast.module.qms.api.oqcApi.OqcApi;
import com.dofast.module.qms.api.oqcApi.dto.OqcDTO;
import com.dofast.module.qms.dal.dataobject.oqc.OqcDO;
import com.dofast.module.qms.service.oqc.OqcService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OqcApiImpl implements OqcApi {
    @Resource
    private OqcService oqcService;

    @Override
    public OqcDTO generateOqc(FeedbackDTO feedback, WorkorderDTO workorderDTO) {
        OqcDO oqcDO = oqcService.generateOqc(feedback, workorderDTO);
        OqcDTO oqcDTO = BeanUtil.toBean(oqcDO, OqcDTO.class);
        return oqcDTO;
    }
}

package com.dofast.module.pro.api.ProcessApi;

import com.dofast.module.pro.api.ProcessApi.dto.ProcessDTO;
import com.dofast.module.pro.convert.process.ProcessConvert;
import com.dofast.module.pro.dal.dataobject.process.ProcessDO;
import com.dofast.module.pro.service.process.ProcessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProcessApiImpl implements ProcessApi{
    @Resource
    private ProcessService processService;


    @Override
    public ProcessDTO getcess(Long id) {
        ProcessDO processDO = processService.getcess(id);
        ProcessDTO dto = ProcessConvert.INSTANCE.convert01(processDO);
        return dto;
    }
}

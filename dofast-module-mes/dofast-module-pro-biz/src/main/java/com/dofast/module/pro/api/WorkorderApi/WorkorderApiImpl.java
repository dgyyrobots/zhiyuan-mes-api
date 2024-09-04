package com.dofast.module.pro.api.WorkorderApi;

import com.dofast.module.pro.api.WorkorderApi.dto.WorkorderDTO;
import com.dofast.module.pro.convert.workorder.WorkorderConvert;
import com.dofast.module.pro.dal.dataobject.workorder.WorkorderDO;
import com.dofast.module.pro.dal.mysql.workorder.WorkorderMapper;
import com.dofast.module.pro.service.workorder.WorkorderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WorkorderApiImpl implements WorkorderApi{

    @Resource
    private WorkorderService workorderService;

    @Resource
    private WorkorderMapper workorderMapper;

    @Override
    public WorkorderDTO getWorkorder(Long workorderId) {
        WorkorderDO workorderDO = workorderService.getWorkorder(workorderId);
        WorkorderDTO workorderDTO = WorkorderConvert.INSTANCE.convert01(workorderDO);
        return workorderDTO;
    }

    @Override
    public WorkorderDTO getWorkorder(String workorderCode) {
        WorkorderDO workorderDO = workorderMapper.selectOne(WorkorderDO::getWorkorderCode, workorderCode);
        WorkorderDTO workorderDTO = WorkorderConvert.INSTANCE.convert01(workorderDO);
        return workorderDTO;
    }
}

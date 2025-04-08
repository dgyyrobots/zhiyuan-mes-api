package com.dofast.module.pro.api.WorkorderApi;

import com.dofast.module.pro.api.WorkorderApi.dto.WorkorderBomDTO;
import com.dofast.module.pro.api.WorkorderApi.dto.WorkorderDTO;
import com.dofast.module.pro.controller.admin.workorderbom.vo.WorkorderBomExportReqVO;
import com.dofast.module.pro.convert.workorder.WorkorderConvert;
import com.dofast.module.pro.convert.workorderbom.WorkorderBomConvert;
import com.dofast.module.pro.dal.dataobject.workorder.WorkorderDO;
import com.dofast.module.pro.dal.dataobject.workorderbom.WorkorderBomDO;
import com.dofast.module.pro.dal.mysql.workorder.WorkorderMapper;
import com.dofast.module.pro.dal.mysql.workorderbom.WorkorderBomMapper;
import com.dofast.module.pro.service.workorder.WorkorderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WorkorderApiImpl implements WorkorderApi{

    @Resource
    private WorkorderService workorderService;

    @Resource
    private WorkorderMapper workorderMapper;

    @Resource
    private WorkorderBomMapper workorderBomMapper;

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
    @Override
    public List<WorkorderBomDTO> getWorkorderBom(Long workorderId){
        WorkorderBomExportReqVO reqVO = new WorkorderBomExportReqVO();
        reqVO.setWorkorderId(workorderId);
        List<WorkorderBomDO> workorderBomDTOList = workorderBomMapper.selectList(reqVO);
        return WorkorderBomConvert.INSTANCE.convertList01(workorderBomDTOList);
    }

    @Override
    public List<WorkorderBomDTO> getWorkorderBom(String workorderCode){
        WorkorderDO workorderDO = workorderService.getWorkorder(workorderCode);
        WorkorderBomExportReqVO reqVO = new WorkorderBomExportReqVO();
        reqVO.setWorkorderId(workorderDO.getId());
        List<WorkorderBomDO> workorderBomDTOList = workorderBomMapper.selectList(reqVO);
        return WorkorderBomConvert.INSTANCE.convertList01(workorderBomDTOList);
    }


}

package com.dofast.module.pro.api.WorkorderApi;

import com.dofast.module.pro.api.WorkorderApi.dto.WorkorderBomDTO;
import com.dofast.module.pro.api.WorkorderApi.dto.WorkorderDTO;

import java.util.List;

public interface WorkorderApi {

    public WorkorderDTO getWorkorder(Long workorderId);

    public WorkorderDTO getWorkorder(String workorderCode);

    public List<WorkorderBomDTO> getWorkorderBom(Long workorderId);

    public List<WorkorderBomDTO> getWorkorderBom(String workorderCode);

}

package com.dofast.module.pro.api.WorkorderApi;

import com.dofast.module.pro.api.WorkorderApi.dto.WorkorderDTO;

public interface WorkorderApi {

    public WorkorderDTO getWorkorder(Long workorderId);

    public WorkorderDTO getWorkorder(String workorderCode);
}

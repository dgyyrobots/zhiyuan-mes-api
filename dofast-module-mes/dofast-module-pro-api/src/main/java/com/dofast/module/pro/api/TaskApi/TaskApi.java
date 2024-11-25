package com.dofast.module.pro.api.TaskApi;

import com.dofast.module.pro.api.TaskApi.dto.TaskDTO;

public interface TaskApi {
    TaskDTO getTask(Long id);

    void updateTask(TaskDTO taskDTO);
}

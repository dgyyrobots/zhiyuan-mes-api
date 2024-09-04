package com.dofast.module.pro.api.TaskApi;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.module.pro.api.TaskApi.dto.TaskDTO;
import com.dofast.module.pro.dal.dataobject.task.TaskDO;
import com.dofast.module.pro.service.task.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TaskApiImpl implements TaskApi{
    @Resource
    private TaskService taskService;

    @Override
    public TaskDTO getTask(Long id) {
        TaskDO taskDO = taskService.getTask(id);
        TaskDTO taskDTO = BeanUtil.toBean(taskDO, TaskDTO.class);
        return taskDTO;
    }
}

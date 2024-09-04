package com.dofast.module.pro.convert.task;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.pro.controller.pad.protask.vo.PadTaskDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.dofast.module.pro.controller.admin.task.vo.*;
import com.dofast.module.pro.dal.dataobject.task.TaskDO;

/**
 * 生产任务 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface TaskConvert {

    TaskConvert INSTANCE = Mappers.getMapper(TaskConvert.class);

    TaskDO convert(TaskCreateReqVO bean);

    TaskDO convert(TaskUpdateReqVO bean);

    TaskRespVO convert(TaskDO bean);

    @Mappings({
            @Mapping(source = "id", target = "taskId")
    })
    PadTaskDetail convert03(TaskDO bean);

    List<TaskRespVO> convertList(List<TaskDO> list);

    PageResult<TaskRespVO> convertPage(PageResult<TaskDO> page);

    List<TaskExcelVO> convertList02(List<TaskDO> list);

    List<PadTaskDetail> convertList03(List<TaskDO> list);


}

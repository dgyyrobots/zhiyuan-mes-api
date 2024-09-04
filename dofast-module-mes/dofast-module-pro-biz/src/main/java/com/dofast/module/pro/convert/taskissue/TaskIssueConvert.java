package com.dofast.module.pro.convert.taskissue;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.pro.controller.admin.taskissue.vo.*;
import com.dofast.module.pro.dal.dataobject.taskissue.TaskIssueDO;

/**
 * 生产任务投料 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface TaskIssueConvert {

    TaskIssueConvert INSTANCE = Mappers.getMapper(TaskIssueConvert.class);

    TaskIssueDO convert(TaskIssueCreateReqVO bean);

    TaskIssueDO convert(TaskIssueUpdateReqVO bean);

    TaskIssueRespVO convert(TaskIssueDO bean);

    List<TaskIssueRespVO> convertList(List<TaskIssueDO> list);

    PageResult<TaskIssueRespVO> convertPage(PageResult<TaskIssueDO> page);

    List<TaskIssueExcelVO> convertList02(List<TaskIssueDO> list);

}

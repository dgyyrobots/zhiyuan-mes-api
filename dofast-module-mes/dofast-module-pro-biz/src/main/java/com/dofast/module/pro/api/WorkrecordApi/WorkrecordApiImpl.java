package com.dofast.module.pro.api.WorkrecordApi;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.module.pro.api.WorkrecordApi.dto.WorkrecordDTO;
import com.dofast.module.pro.controller.admin.workrecord.vo.WorkrecordCreateReqVO;
import com.dofast.module.pro.service.workrecord.WorkrecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WorkrecordApiImpl implements WorkrecordApi {

    @Resource
    private WorkrecordService workrecordService;

    @Override
    public void insertWorkrecord(WorkrecordDTO workrecordDTO) {
        WorkrecordCreateReqVO workrecordCreateReqVO = BeanUtil.toBean(workrecordDTO, WorkrecordCreateReqVO.class);
        workrecordService.createWorkrecord(workrecordCreateReqVO);
    }
}

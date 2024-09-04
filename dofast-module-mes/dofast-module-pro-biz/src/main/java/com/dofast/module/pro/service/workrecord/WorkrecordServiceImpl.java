package com.dofast.module.pro.service.workrecord;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.pro.controller.admin.workrecord.vo.*;
import com.dofast.module.pro.dal.dataobject.workrecord.WorkrecordDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.pro.convert.workrecord.WorkrecordConvert;
import com.dofast.module.pro.dal.mysql.workrecord.WorkrecordMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;

/**
 * 上下工记录 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class WorkrecordServiceImpl implements WorkrecordService {

    @Resource
    private WorkrecordMapper workrecordMapper;

    @Override
    public Long createWorkrecord(WorkrecordCreateReqVO createReqVO) {
        // 插入
        WorkrecordDO workrecord = WorkrecordConvert.INSTANCE.convert(createReqVO);
        workrecordMapper.insert(workrecord);
        // 返回
        return workrecord.getRecordId();
    }

    @Override
    public void updateWorkrecord(WorkrecordUpdateReqVO updateReqVO) {
        // 校验存在
        validateWorkrecordExists(updateReqVO.getRecordId());
        // 更新
        WorkrecordDO updateObj = WorkrecordConvert.INSTANCE.convert(updateReqVO);
        workrecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteWorkrecord(Long id) {
        // 校验存在
        validateWorkrecordExists(id);
        // 删除
        workrecordMapper.deleteById(id);
    }

    private void validateWorkrecordExists(Long id) {
        if (workrecordMapper.selectById(id) == null) {
            throw exception(WORKRECORD_NOT_EXISTS);
        }
    }

    @Override
    public WorkrecordDO getWorkrecord(Long id) {
        return workrecordMapper.selectById(id);
    }

    @Override
    public List<WorkrecordDO> getWorkrecordList(Collection<Long> ids) {
        return workrecordMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<WorkrecordDO> getWorkrecordPage(WorkrecordPageReqVO pageReqVO) {
        return workrecordMapper.selectPage(pageReqVO);
    }

    @Override
    public List<WorkrecordDO> getWorkrecordList(WorkrecordExportReqVO exportReqVO) {
        return workrecordMapper.selectList(exportReqVO);
    }

}

package com.dofast.module.pro.service.workorderbom;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.pro.controller.admin.workorderbom.vo.*;
import com.dofast.module.pro.dal.dataobject.workorderbom.WorkorderBomDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.pro.convert.workorderbom.WorkorderBomConvert;
import com.dofast.module.pro.dal.mysql.workorderbom.WorkorderBomMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;

/**
 * 生产工单BOM组成 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class WorkorderBomServiceImpl implements WorkorderBomService {

    @Resource
    private WorkorderBomMapper workorderBomMapper;

    @Override
    public int deleteProWorkorderBomByWorkorderId(Long wordId) {
        return workorderBomMapper.deleteProWorkorderBomByWorkorderId(wordId);
    }

    @Override
    public Long createWorkorderBom(WorkorderBomCreateReqVO createReqVO) {
        // 插入
        WorkorderBomDO workorderBom = WorkorderBomConvert.INSTANCE.convert(createReqVO);
        workorderBomMapper.insert(workorderBom);
        // 返回
        return workorderBom.getId();
    }

    @Override
    public void updateWorkorderBom(WorkorderBomUpdateReqVO updateReqVO) {
        // 校验存在
        validateWorkorderBomExists(updateReqVO.getId());
        // 更新
        WorkorderBomDO updateObj = WorkorderBomConvert.INSTANCE.convert(updateReqVO);
        workorderBomMapper.updateById(updateObj);
    }

    @Override
    public void deleteWorkorderBom(Long id) {
        // 校验存在
        validateWorkorderBomExists(id);
        // 删除
        workorderBomMapper.deleteById(id);
    }

    private void validateWorkorderBomExists(Long id) {
        if (workorderBomMapper.selectById(id) == null) {
            throw exception(WORKORDER_BOM_NOT_EXISTS);
        }
    }

    @Override
    public WorkorderBomDO getWorkorderBom(Long id) {
        return workorderBomMapper.selectById(id);
    }

    @Override
    public List<WorkorderBomDO> getWorkorderBomList(Collection<Long> ids) {
        return workorderBomMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<WorkorderBomDO> getWorkorderBomPage(WorkorderBomPageReqVO pageReqVO) {
        return workorderBomMapper.selectPage(pageReqVO);
    }

    @Override
    public List<WorkorderBomDO> getWorkorderBomList(WorkorderBomExportReqVO exportReqVO) {
        return workorderBomMapper.selectList(exportReqVO);
    }

}

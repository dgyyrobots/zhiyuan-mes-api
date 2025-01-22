package com.dofast.module.pro.service.processdefect;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.pro.controller.admin.processdefect.vo.*;
import com.dofast.module.pro.dal.dataobject.processdefect.ProcessDefectDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.pro.convert.processdefect.ProcessDefectConvert;
import com.dofast.module.pro.dal.mysql.processdefect.ProcessDefectMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 工序异常缺陷名称 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class ProcessDefectServiceImpl implements ProcessDefectService {

    @Resource
    private ProcessDefectMapper cessDefectMapper;

    @Override
    public Long createcessDefect(ProcessDefectCreateReqVO createReqVO) {
        // 插入
        ProcessDefectDO cessDefect = ProcessDefectConvert.INSTANCE.convert(createReqVO);
        cessDefectMapper.insert(cessDefect);
        // 返回
        return cessDefect.getId();
    }

    @Override
    public void updatecessDefect(ProcessDefectUpdateReqVO updateReqVO) {
        // 校验存在
        validatecessDefectExists(updateReqVO.getId());
        // 更新
        ProcessDefectDO updateObj = ProcessDefectConvert.INSTANCE.convert(updateReqVO);
        cessDefectMapper.updateById(updateObj);
    }

    @Override
    public void deletecessDefect(Long id) {
        // 校验存在
        validatecessDefectExists(id);
        // 删除
        cessDefectMapper.deleteById(id);
    }

    private void validatecessDefectExists(Long id) {
        if (cessDefectMapper.selectById(id) == null) {
            throw exception(CESS_DEFECT_NOT_EXISTS);
        }
    }

    @Override
    public ProcessDefectDO getcessDefect(Long id) {
        return cessDefectMapper.selectById(id);
    }

    @Override
    public List<ProcessDefectDO> getcessDefectByCode(String processCode){
        List<ProcessDefectDO> processDefectList = cessDefectMapper.selectList(ProcessDefectDO::getProcessCode, processCode);
        List<ProcessDefectDO> otherList = cessDefectMapper.selectList(ProcessDefectDO::getProcessCode, "OTHER");
        processDefectList.addAll(otherList);
        return processDefectList;
    }


    @Override
    public List<ProcessDefectDO> getcessDefectList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return cessDefectMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProcessDefectDO> getcessDefectPage(ProcessDefectPageReqVO pageReqVO) {
        return cessDefectMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProcessDefectDO> getcessDefectList(ProcessDefectExportReqVO exportReqVO) {
        return cessDefectMapper.selectList(exportReqVO);
    }

}

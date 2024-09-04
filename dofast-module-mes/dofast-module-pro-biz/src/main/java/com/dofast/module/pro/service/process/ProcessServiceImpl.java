package com.dofast.module.pro.service.process;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.pro.controller.admin.process.vo.*;
import com.dofast.module.pro.dal.dataobject.process.ProcessDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.pro.convert.process.ProcessConvert;
import com.dofast.module.pro.dal.mysql.process.ProcessMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;

/**
 * 生产工序 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProcessServiceImpl implements ProcessService {

    @Resource
    private ProcessMapper cessMapper;

    @Override
    public String checkProcessCodeUnique(ProcessBaseVO baseVO) {
        ProcessDO process = cessMapper.checkProcessCodeUnique(baseVO);
        Long processId = baseVO.getId()==null?-1L:baseVO.getId();
        if(StrUtils.isNotNull(process) && process.getId().longValue() != processId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public String checkProcessNameUnique(ProcessBaseVO baseVO) {
        ProcessDO process = cessMapper.checkProcessNameUnique(baseVO);
        Long processId = baseVO.getId()==null?-1L:baseVO.getId();
        if(StrUtils.isNotNull(process) && process.getId().longValue() != processId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public List<ProcessDO> selectAll(ProcessListVO listVO) {
        return cessMapper.selectList(listVO);
    }

    @Override
    public Long createcess(ProcessCreateReqVO createReqVO) {
        // 插入
        ProcessDO cess = ProcessConvert.INSTANCE.convert(createReqVO);
        cessMapper.insert(cess);
        // 返回
        return cess.getId();
    }

    @Override
    public void updatecess(ProcessUpdateReqVO updateReqVO) {
        // 校验存在
        validatecessExists(updateReqVO.getId());
        // 更新
        ProcessDO updateObj = ProcessConvert.INSTANCE.convert(updateReqVO);
        cessMapper.updateById(updateObj);
    }

    @Override
    public void deletecess(Long id) {
        // 校验存在
        validatecessExists(id);
        // 删除
        cessMapper.deleteById(id);
    }

    private void validatecessExists(Long id) {
        if (cessMapper.selectById(id) == null) {
            throw exception(CESS_NOT_EXISTS);
        }
    }

    @Override
    public ProcessDO getcess(Long id) {
        return cessMapper.selectById(id);
    }

    @Override
    public List<ProcessDO> getcessList(Collection<Long> ids) {
        return cessMapper.selectBatchIds(ids);
    }

    @Override
    public List<ProcessSimpleVO> getcessSimpleList() {
        return cessMapper.selectSimpleList();
    }

    @Override
    public PageResult<ProcessDO> getcessPage(ProcessPageReqVO pageReqVO) {
        return cessMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProcessDO> getcessList(ProcessExportReqVO exportReqVO) {
        return cessMapper.selectList(exportReqVO);
    }

}

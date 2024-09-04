package com.dofast.module.mes.service.autocoderesult;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.autocoderesult.vo.*;
import com.dofast.module.mes.dal.dataobject.autocoderesult.AutoCodeResultDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.autocoderesult.AutoCodeResultConvert;
import com.dofast.module.mes.dal.mysql.autocoderesult.AutoCodeResultMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;

/**
 * 编码生成记录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AutoCodeResultServiceImpl implements AutoCodeResultService {

    @Resource
    private AutoCodeResultMapper autoCodeResultMapper;

    @Override
    public List<AutoCodeResultDO> getAutoCodeResultList(AutoCodeResultListVO listVO) {
        return autoCodeResultMapper.selectAutoCodeResultList(listVO);
    }

    @Override
    public Long createAutoCodeResult(AutoCodeResultCreateReqVO createReqVO) {
        // 插入
        AutoCodeResultDO autoCodeResult = AutoCodeResultConvert.INSTANCE.convert(createReqVO);
        autoCodeResultMapper.insert(autoCodeResult);
        // 返回
        return autoCodeResult.getId();
    }

    @Override
    public void updateAutoCodeResult(AutoCodeResultUpdateReqVO updateReqVO) {
        // 校验存在
        validateAutoCodeResultExists(updateReqVO.getId());
        // 更新
        AutoCodeResultDO updateObj = AutoCodeResultConvert.INSTANCE.convert(updateReqVO);
        autoCodeResultMapper.updateById(updateObj);
    }

    @Override
    public void deleteAutoCodeResult(Long id) {
        // 校验存在
        validateAutoCodeResultExists(id);
        // 删除
        autoCodeResultMapper.deleteById(id);
    }

    private void validateAutoCodeResultExists(Long id) {
        if (autoCodeResultMapper.selectById(id) == null) {
            throw exception(AUTO_CODE_RESULT_NOT_EXISTS);
        }
    }

    @Override
    public AutoCodeResultDO getAutoCodeResult(Long id) {
        return autoCodeResultMapper.selectById(id);
    }

    @Override
    public List<AutoCodeResultDO> getAutoCodeResultList(Collection<Long> ids) {
        return autoCodeResultMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<AutoCodeResultDO> getAutoCodeResultPage(AutoCodeResultPageReqVO pageReqVO) {
        return autoCodeResultMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AutoCodeResultDO> getAutoCodeResultList(AutoCodeResultExportReqVO exportReqVO) {
        return autoCodeResultMapper.selectList(exportReqVO);
    }

}

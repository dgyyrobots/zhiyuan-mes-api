package com.dofast.module.mes.service.electroplatelog;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.electroplatelog.vo.*;
import com.dofast.module.mes.dal.dataobject.electroplatelog.ElectroplateLogDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.electroplatelog.ElectroplateLogConvert;
import com.dofast.module.mes.dal.mysql.electroplatelog.ElectroplateLogMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 制版房记录 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class ElectroplateLogServiceImpl implements ElectroplateLogService {

    @Resource
    private ElectroplateLogMapper electroplateLogMapper;

    @Override
    public Long createElectroplateLog(ElectroplateLogCreateReqVO createReqVO) {
        // 插入
        ElectroplateLogDO electroplateLog = ElectroplateLogConvert.INSTANCE.convert(createReqVO);
        electroplateLogMapper.insert(electroplateLog);
        // 返回
        return electroplateLog.getId();
    }

    @Override
    public void updateElectroplateLog(ElectroplateLogUpdateReqVO updateReqVO) {
        // 校验存在
        validateElectroplateLogExists(updateReqVO.getId());
        // 更新
        ElectroplateLogDO updateObj = ElectroplateLogConvert.INSTANCE.convert(updateReqVO);
        electroplateLogMapper.updateById(updateObj);
    }

    @Override
    public void deleteElectroplateLog(Long id) {
        // 校验存在
        validateElectroplateLogExists(id);
        // 删除
        electroplateLogMapper.deleteById(id);
    }

    private void validateElectroplateLogExists(Long id) {
        if (electroplateLogMapper.selectById(id) == null) {
            throw exception(ELECTROPLATE_LOG_NOT_EXISTS);
        }
    }

    @Override
    public ElectroplateLogDO getElectroplateLog(Long id) {
        return electroplateLogMapper.selectById(id);
    }

    @Override
    public List<ElectroplateLogDO> getElectroplateLogList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return electroplateLogMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ElectroplateLogDO> getElectroplateLogPage(ElectroplateLogPageReqVO pageReqVO) {
        return electroplateLogMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ElectroplateLogDO> getElectroplateLogList(ElectroplateLogExportReqVO exportReqVO) {
        return electroplateLogMapper.selectList(exportReqVO);
    }

}

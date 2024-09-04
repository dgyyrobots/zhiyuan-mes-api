package com.dofast.module.iot.service.alertlog;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.iot.controller.admin.alertlog.vo.*;
import com.dofast.module.iot.dal.dataobject.alertlog.AlertLogDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.iot.convert.alertlog.AlertLogConvert;
import com.dofast.module.iot.dal.mysql.alertlog.AlertLogMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.iot.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 设备告警日志 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class AlertLogServiceImpl implements AlertLogService {

    @Resource
    private AlertLogMapper alertLogMapper;

    @Override
    public Long createAlertLog(AlertLogCreateReqVO createReqVO) {
        // 插入
        AlertLogDO alertLog = AlertLogConvert.INSTANCE.convert(createReqVO);
        alertLogMapper.insert(alertLog);
        // 返回
        return alertLog.getId();
    }

    @Override
    public void updateAlertLog(AlertLogUpdateReqVO updateReqVO) {
        // 校验存在
        validateAlertLogExists(updateReqVO.getId());
        // 更新
        AlertLogDO updateObj = AlertLogConvert.INSTANCE.convert(updateReqVO);
        alertLogMapper.updateById(updateObj);
    }

    @Override
    public void deleteAlertLog(Long id) {
        // 校验存在
        validateAlertLogExists(id);
        // 删除
        alertLogMapper.deleteById(id);
    }

    private void validateAlertLogExists(Long id) {
        if (alertLogMapper.selectById(id) == null) {
            throw exception(ALERT_LOG_NOT_EXISTS);
        }
    }

    @Override
    public AlertLogDO getAlertLog(Long id) {
        return alertLogMapper.selectById(id);
    }

    @Override
    public List<AlertLogDO> getAlertLogList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return alertLogMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<AlertLogDO> getAlertLogPage(AlertLogPageReqVO pageReqVO) {
        return alertLogMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AlertLogDO> getAlertLogList(AlertLogExportReqVO exportReqVO) {
        return alertLogMapper.selectList(exportReqVO);
    }

}

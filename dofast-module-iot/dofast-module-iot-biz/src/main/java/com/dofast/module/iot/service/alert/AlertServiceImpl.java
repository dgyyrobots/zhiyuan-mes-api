package com.dofast.module.iot.service.alert;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.iot.controller.admin.alert.vo.*;
import com.dofast.module.iot.dal.dataobject.alert.AlertDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.iot.convert.alert.AlertConvert;
import com.dofast.module.iot.dal.mysql.alert.AlertMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.iot.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 设备告警 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class AlertServiceImpl implements AlertService {

    @Resource
    private AlertMapper alertMapper;

    @Override
    public Long createAlert(AlertCreateReqVO createReqVO) {
        // 插入
        AlertDO alert = AlertConvert.INSTANCE.convert(createReqVO);
        alertMapper.insert(alert);
        // 返回
        return alert.getId();
    }

    @Override
    public void updateAlert(AlertUpdateReqVO updateReqVO) {
        // 校验存在
        validateAlertExists(updateReqVO.getId());
        // 更新
        AlertDO updateObj = AlertConvert.INSTANCE.convert(updateReqVO);
        alertMapper.updateById(updateObj);
    }

    @Override
    public void deleteAlert(Long id) {
        // 校验存在
        validateAlertExists(id);
        // 删除
        alertMapper.deleteById(id);
    }

    private void validateAlertExists(Long id) {
        if (alertMapper.selectById(id) == null) {
            throw exception(ALERT_NOT_EXISTS);
        }
    }

    @Override
    public AlertDO getAlert(Long id) {
        return alertMapper.selectById(id);
    }

    @Override
    public List<AlertDO> getAlertList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return alertMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<AlertDO> getAlertPage(AlertPageReqVO pageReqVO) {
        return alertMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AlertDO> getAlertList(AlertExportReqVO exportReqVO) {
        return alertMapper.selectList(exportReqVO);
    }

}

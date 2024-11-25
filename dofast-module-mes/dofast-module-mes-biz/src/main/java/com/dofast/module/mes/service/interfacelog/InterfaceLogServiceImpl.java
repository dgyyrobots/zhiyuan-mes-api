package com.dofast.module.mes.service.interfacelog;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.interfacelog.vo.*;
import com.dofast.module.mes.dal.dataobject.interfacelog.InterfaceLogDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.interfacelog.InterfaceLogConvert;
import com.dofast.module.mes.dal.mysql.interfacelog.InterfaceLogMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 接口操作日志 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class InterfaceLogServiceImpl implements InterfaceLogService {

    @Resource
    private InterfaceLogMapper interfaceLogMapper;

    @Override
    public Long createInterfaceLog(InterfaceLogCreateReqVO createReqVO) {
        // 插入
        InterfaceLogDO interfaceLog = InterfaceLogConvert.INSTANCE.convert(createReqVO);
        interfaceLogMapper.insert(interfaceLog);
        // 返回
        return interfaceLog.getId();
    }

    @Override
    public void updateInterfaceLog(InterfaceLogUpdateReqVO updateReqVO) {
        // 校验存在
        validateInterfaceLogExists(updateReqVO.getId());
        // 更新
        InterfaceLogDO updateObj = InterfaceLogConvert.INSTANCE.convert(updateReqVO);
        interfaceLogMapper.updateById(updateObj);
    }

    @Override
    public void deleteInterfaceLog(Long id) {
        // 校验存在
        validateInterfaceLogExists(id);
        // 删除
        interfaceLogMapper.deleteById(id);
    }

    private void validateInterfaceLogExists(Long id) {
        if (interfaceLogMapper.selectById(id) == null) {
            throw exception(INTERFACE_LOG_NOT_EXISTS);
        }
    }

    @Override
    public InterfaceLogDO getInterfaceLog(Long id) {
        return interfaceLogMapper.selectById(id);
    }

    @Override
    public List<InterfaceLogDO> getInterfaceLogList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return interfaceLogMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<InterfaceLogDO> getInterfaceLogPage(InterfaceLogPageReqVO pageReqVO) {
        return interfaceLogMapper.selectPage(pageReqVO);
    }

    @Override
    public List<InterfaceLogDO> getInterfaceLogList(InterfaceLogExportReqVO exportReqVO) {
        return interfaceLogMapper.selectList(exportReqVO);
    }

}

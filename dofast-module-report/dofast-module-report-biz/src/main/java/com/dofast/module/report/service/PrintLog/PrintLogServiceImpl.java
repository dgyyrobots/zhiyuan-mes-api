package com.dofast.module.report.service.PrintLog;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.report.controller.admin.PrintLog.vo.PrintLogCreateReqVO;
import com.dofast.module.report.controller.admin.PrintLog.vo.PrintLogExportReqVO;
import com.dofast.module.report.controller.admin.PrintLog.vo.PrintLogPageReqVO;
import com.dofast.module.report.controller.admin.PrintLog.vo.PrintLogUpdateReqVO;
import com.dofast.module.report.convert.PrintLog.PrintLogConvert;
import com.dofast.module.report.dal.dataobject.PrintLog.PrintLogDO;
import com.dofast.module.report.dal.mysql.PrintLog.PrintLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.report.enums.ErrorCodeConstants.PRINT_LOG_NOT_EXISTS;

/**
 * 打印日志 Service 实现类
 *
 * @author a1
 */
@Service
@Validated
public class PrintLogServiceImpl implements PrintLogService {

    @Resource
    private PrintLogMapper printLogMapper;

    @Override
    public Long createPrintLog(PrintLogCreateReqVO createReqVO) {
        // 插入
        PrintLogDO printLog = PrintLogConvert.INSTANCE.convert(createReqVO);
        printLogMapper.insert(printLog);
        // 返回
        return printLog.getId();
    }

    @Override
    public void updatePrintLog(PrintLogUpdateReqVO updateReqVO) {
        // 校验存在
        validatePrintLogExists(updateReqVO.getId());
        // 更新
        PrintLogDO updateObj = PrintLogConvert.INSTANCE.convert(updateReqVO);
        printLogMapper.updateById(updateObj);
    }

    @Override
    public void deletePrintLog(Long id) {
        // 校验存在
        validatePrintLogExists(id);
        // 删除
        printLogMapper.deleteById(id);
    }

    private void validatePrintLogExists(Long id) {
        if (printLogMapper.selectById(id) == null) {
            throw exception(PRINT_LOG_NOT_EXISTS);
        }
    }

    @Override
    public PrintLogDO getPrintLog(Long id) {
        return printLogMapper.selectById(id);
    }

    @Override
    public List<PrintLogDO> getPrintLogList(Collection<Long> ids) {
        return printLogMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<PrintLogDO> getPrintLogPage(PrintLogPageReqVO pageReqVO) {
        return printLogMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PrintLogDO> getPrintLogList(PrintLogExportReqVO exportReqVO) {
        return printLogMapper.selectList(exportReqVO);
    }

    @Override
    public List<PrintLogDO> getPrintLogListByPrintCode(String printCode) {
        return printLogMapper.selectListByPrintCode(printCode);
    }

    @Override
    public List<PrintLogDO> getPrintLogListByPrintCodeAndType(String printCode, String type) {
        return printLogMapper.selectListByPrintCodeAndType(printCode,type);
    }

}

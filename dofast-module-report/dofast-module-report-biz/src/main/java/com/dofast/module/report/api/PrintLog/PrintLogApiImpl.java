package com.dofast.module.report.api.PrintLog;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.module.report.api.PrintLog.dto.PrintLogDTO;
import com.dofast.module.report.dal.dataobject.PrintLog.PrintLogDO;
import com.dofast.module.report.service.PrintLog.PrintLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrintLogApiImpl implements PrintLogApi {

    @Resource
    private PrintLogService printLogService;

    public List<PrintLogDTO> selectAllByPrintLog(String printCode){
        List<PrintLogDO> printLogListByPrintCode = printLogService.getPrintLogListByPrintCode(printCode);
        List<PrintLogDTO> list=new ArrayList<>();
        for (PrintLogDO printLogDO : printLogListByPrintCode) {
            PrintLogDTO bean = BeanUtil.toBean(printLogDO, PrintLogDTO.class);
            list.add(bean);
        }
        return list;
    }

    @Override
    public List<PrintLogDTO> selectAllByPrintLogAndType(String printCode, String type) {
        List<PrintLogDTO> printLogDTOS = new ArrayList<>();
        for (PrintLogDO printLogDO : printLogService.getPrintLogListByPrintCodeAndType(printCode, type)) {
            printLogDTOS.add(BeanUtil.toBean(printLogDO,PrintLogDTO.class));
        }
        return printLogDTOS;
    }
}

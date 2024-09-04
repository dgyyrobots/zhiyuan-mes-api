package com.dofast.module.report.api.PrintLog;

import com.dofast.module.report.api.PrintLog.dto.PrintLogDTO;

import java.util.List;

public interface PrintLogApi {

    public List<PrintLogDTO> selectAllByPrintLog(String printCode);

    public List<PrintLogDTO> selectAllByPrintLogAndType(String printCode,String type);
}

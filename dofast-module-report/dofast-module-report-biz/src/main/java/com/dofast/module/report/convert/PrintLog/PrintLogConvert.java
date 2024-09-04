package com.dofast.module.report.convert.PrintLog;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.report.controller.admin.PrintLog.vo.PrintLogCreateReqVO;
import com.dofast.module.report.controller.admin.PrintLog.vo.PrintLogExcelVO;
import com.dofast.module.report.controller.admin.PrintLog.vo.PrintLogRespVO;
import com.dofast.module.report.controller.admin.PrintLog.vo.PrintLogUpdateReqVO;
import com.dofast.module.report.dal.dataobject.PrintLog.PrintLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 打印日志 Convert
 *
 * @author a1
 */
@Mapper
public interface PrintLogConvert {

    PrintLogConvert INSTANCE = Mappers.getMapper(PrintLogConvert.class);

    PrintLogDO convert(PrintLogCreateReqVO bean);

    PrintLogDO convert(PrintLogUpdateReqVO bean);

    PrintLogRespVO convert(PrintLogDO bean);

    List<PrintLogRespVO> convertList(List<PrintLogDO> list);

    PageResult<PrintLogRespVO> convertPage(PageResult<PrintLogDO> page);

    List<PrintLogExcelVO> convertList02(List<PrintLogDO> list);


}

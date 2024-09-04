package com.dofast.module.report.dal.mysql.PrintLog;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.report.controller.admin.PrintLog.vo.PrintLogExportReqVO;
import com.dofast.module.report.controller.admin.PrintLog.vo.PrintLogPageReqVO;
import com.dofast.module.report.dal.dataobject.PrintLog.PrintLogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 打印日志 Mapper
 *
 * @author a1
 */
@Mapper
public interface PrintLogMapper extends BaseMapperX<PrintLogDO> {

    default PageResult<PrintLogDO> selectPage(PrintLogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PrintLogDO>()
                .likeIfPresent(PrintLogDO::getPrintName, reqVO.getPrintName())
                .eqIfPresent(PrintLogDO::getPrintType, reqVO.getPrintType())
                .eqIfPresent(PrintLogDO::getPrintCode,reqVO.getPrintCode())
                .betweenIfPresent(PrintLogDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PrintLogDO::getId));
    }

    default List<PrintLogDO> selectList(PrintLogExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<PrintLogDO>()
                .likeIfPresent(PrintLogDO::getPrintName, reqVO.getPrintName())
                .eqIfPresent(PrintLogDO::getPrintType, reqVO.getPrintType())
                .eqIfPresent(PrintLogDO::getPrintCode,reqVO.getPrintCode())
                .betweenIfPresent(PrintLogDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PrintLogDO::getId));
    }

    default List<PrintLogDO> selectListByPrintCode(String printCOde){
        return selectList(new LambdaQueryWrapperX<PrintLogDO>()
                .eq(PrintLogDO::getPrintCode,printCOde)
                .orderByDesc(PrintLogDO::getId));
    }

    default List<PrintLogDO> selectListByPrintCodeAndType(String printCode, String type){
        return selectList(new LambdaQueryWrapperX<PrintLogDO>()
                .eqIfPresent(PrintLogDO::getPrintCode,printCode)
                .eqIfPresent(PrintLogDO::getPrintType,type)
                .orderByDesc(PrintLogDO::getId));
    }
}

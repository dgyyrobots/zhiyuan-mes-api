package com.dofast.module.infra.convert.logger;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;
import com.dofast.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogExcelVO;
import com.dofast.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogRespVO;
import com.dofast.module.infra.dal.dataobject.logger.ApiErrorLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * API 错误日志 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ApiErrorLogConvert {

    ApiErrorLogConvert INSTANCE = Mappers.getMapper(ApiErrorLogConvert.class);

    ApiErrorLogRespVO convert(ApiErrorLogDO bean);

    PageResult<ApiErrorLogRespVO> convertPage(PageResult<ApiErrorLogDO> page);

    List<ApiErrorLogExcelVO> convertList02(List<ApiErrorLogDO> list);

    ApiErrorLogDO convert(ApiErrorLogCreateReqDTO bean);

}

package com.dofast.module.infra.convert.logger;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.infra.api.logger.dto.ApiAccessLogCreateReqDTO;
import com.dofast.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogExcelVO;
import com.dofast.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogRespVO;
import com.dofast.module.infra.dal.dataobject.logger.ApiAccessLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * API 访问日志 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ApiAccessLogConvert {

    ApiAccessLogConvert INSTANCE = Mappers.getMapper(ApiAccessLogConvert.class);

    ApiAccessLogRespVO convert(ApiAccessLogDO bean);

    List<ApiAccessLogRespVO> convertList(List<ApiAccessLogDO> list);

    PageResult<ApiAccessLogRespVO> convertPage(PageResult<ApiAccessLogDO> page);

    List<ApiAccessLogExcelVO> convertList02(List<ApiAccessLogDO> list);

    ApiAccessLogDO convert(ApiAccessLogCreateReqDTO bean);

}

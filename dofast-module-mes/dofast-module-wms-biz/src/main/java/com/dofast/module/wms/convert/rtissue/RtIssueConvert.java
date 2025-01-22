package com.dofast.module.wms.convert.rtissue;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.controller.admin.rtissueline.vo.RtIssueLineListVO;
import com.dofast.module.wms.dal.dataobject.rtissueline.RtIssueLineDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.rtissue.vo.*;
import com.dofast.module.wms.dal.dataobject.rtissue.RtIssueDO;

/**
 * 生产退料单头 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface RtIssueConvert {

    RtIssueConvert INSTANCE = Mappers.getMapper(RtIssueConvert.class);

    RtIssueDO convert(RtIssueCreateReqVO bean);

    RtIssueDO convert(RtIssueUpdateReqVO bean);

    RtIssueRespVO convert(RtIssueDO bean);

    RtIssueUpdateReqVO convert01(RtIssueDO bean);

    RtIssueLineListVO conver02(RtIssueLineDO bean);;

    List<RtIssueRespVO> convertList(List<RtIssueDO> list);

    PageResult<RtIssueRespVO> convertPage(PageResult<RtIssueDO> page);

    List<RtIssueExcelVO> convertList02(List<RtIssueDO> list);

}

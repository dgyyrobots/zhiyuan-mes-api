package com.dofast.module.wms.convert.issueheader;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.issueheader.vo.*;
import com.dofast.module.wms.dal.dataobject.issueheader.IssueHeaderDO;

/**
 * 生产领料单头 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface IssueHeaderConvert {

    IssueHeaderConvert INSTANCE = Mappers.getMapper(IssueHeaderConvert.class);

    IssueHeaderDO convert(IssueHeaderCreateReqVO bean);

    IssueHeaderDO convert(IssueHeaderUpdateReqVO bean);

    IssueHeaderRespVO convert(IssueHeaderDO bean);


    IssueHeaderUpdateReqVO convert01(IssueHeaderDO bean);

    IssueHeaderCreateReqVO convert02(IssueHeaderDO bean);

    List<IssueHeaderRespVO> convertList(List<IssueHeaderDO> list);

    PageResult<IssueHeaderRespVO> convertPage(PageResult<IssueHeaderDO> page);

    List<IssueHeaderExcelVO> convertList02(List<IssueHeaderDO> list);

}

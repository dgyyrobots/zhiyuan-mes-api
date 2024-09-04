package com.dofast.module.qms.convert.index;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.qms.controller.admin.index.vo.*;
import com.dofast.module.qms.dal.dataobject.index.IndexDO;

/**
 * 检测项 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface IndexConvert {

    IndexConvert INSTANCE = Mappers.getMapper(IndexConvert.class);

    IndexDO convert(IndexCreateReqVO bean);

    IndexDO convert(IndexUpdateReqVO bean);

    IndexRespVO convert(IndexDO bean);

    List<IndexRespVO> convertList(List<IndexDO> list);

    PageResult<IndexRespVO> convertPage(PageResult<IndexDO> page);

    List<IndexExcelVO> convertList02(List<IndexDO> list);

}

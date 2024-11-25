package com.dofast.module.wms.convert.feedline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.feedline.vo.*;
import com.dofast.module.wms.dal.dataobject.feedline.FeedLineDO;

/**
 * 上料详情 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface FeedLineConvert {

    FeedLineConvert INSTANCE = Mappers.getMapper(FeedLineConvert.class);

    FeedLineDO convert(FeedLineCreateReqVO bean);

    FeedLineDO convert(FeedLineUpdateReqVO bean);

    FeedLineRespVO convert(FeedLineDO bean);

    List<FeedLineRespVO> convertList(List<FeedLineDO> list);

    PageResult<FeedLineRespVO> convertPage(PageResult<FeedLineDO> page);

    List<FeedLineExcelVO> convertList02(List<FeedLineDO> list);

}

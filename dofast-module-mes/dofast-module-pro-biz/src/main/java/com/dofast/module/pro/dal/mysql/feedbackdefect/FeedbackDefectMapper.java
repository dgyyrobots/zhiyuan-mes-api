package com.dofast.module.pro.dal.mysql.feedbackdefect;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.pro.dal.dataobject.feedbackdefect.FeedbackDefectDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.pro.controller.admin.feedbackdefect.vo.*;

/**
 * 报工缺陷 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface FeedbackDefectMapper extends BaseMapperX<FeedbackDefectDO> {

    default PageResult<FeedbackDefectDO> selectPage(FeedbackDefectPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FeedbackDefectDO>()
                .eqIfPresent(FeedbackDefectDO::getFeedbackId, reqVO.getFeedbackId())
                .eqIfPresent(FeedbackDefectDO::getTaskCode, reqVO.getTaskCode())
                .betweenIfPresent(FeedbackDefectDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(FeedbackDefectDO::getDefectName, reqVO.getDefectName())
                .eqIfPresent(FeedbackDefectDO::getDefectId, reqVO.getDefectId())
                .eqIfPresent(FeedbackDefectDO::getStartMeter, reqVO.getStartMeter())
                .eqIfPresent(FeedbackDefectDO::getEndMeter, reqVO.getEndMeter())
                .eqIfPresent(FeedbackDefectDO::getDefectMeter, reqVO.getDefectMeter())
                .orderByDesc(FeedbackDefectDO::getId));
    }

    default List<FeedbackDefectDO> selectList(FeedbackDefectExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<FeedbackDefectDO>()
                .eqIfPresent(FeedbackDefectDO::getFeedbackId, reqVO.getFeedbackId())
                .eqIfPresent(FeedbackDefectDO::getTaskCode, reqVO.getTaskCode())
                .betweenIfPresent(FeedbackDefectDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(FeedbackDefectDO::getDefectName, reqVO.getDefectName())
                .eqIfPresent(FeedbackDefectDO::getDefectId, reqVO.getDefectId())
                .eqIfPresent(FeedbackDefectDO::getStartMeter, reqVO.getStartMeter())
                .eqIfPresent(FeedbackDefectDO::getEndMeter, reqVO.getEndMeter())
                .eqIfPresent(FeedbackDefectDO::getDefectMeter, reqVO.getDefectMeter())
                .orderByDesc(FeedbackDefectDO::getId));
    }

}

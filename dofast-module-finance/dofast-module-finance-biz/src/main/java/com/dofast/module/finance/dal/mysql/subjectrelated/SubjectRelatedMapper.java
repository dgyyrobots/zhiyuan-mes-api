package com.dofast.module.finance.dal.mysql.subjectrelated;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.finance.dal.dataobject.subjectrelated.SubjectRelatedDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.finance.controller.admin.subjectrelated.vo.*;

/**
 * 收支科目 Mapper
 *
 * @author a1
 */
@Mapper
public interface SubjectRelatedMapper extends BaseMapperX<SubjectRelatedDO> {

    default PageResult<SubjectRelatedDO> selectPage(SubjectRelatedPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SubjectRelatedDO>()
                .likeIfPresent(SubjectRelatedDO::getSubjectName, reqVO.getSubjectName())
                .eqIfPresent(SubjectRelatedDO::getSubjectType, reqVO.getSubjectType())
                .betweenIfPresent(SubjectRelatedDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SubjectRelatedDO::getId));
    }

    default List<SubjectRelatedDO> selectList(SubjectRelatedExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SubjectRelatedDO>()
                .likeIfPresent(SubjectRelatedDO::getSubjectName, reqVO.getSubjectName())
                .eqIfPresent(SubjectRelatedDO::getSubjectType, reqVO.getSubjectType())
                .betweenIfPresent(SubjectRelatedDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SubjectRelatedDO::getId));
    }

}

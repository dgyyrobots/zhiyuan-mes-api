package com.dofast.module.finance.service.subjectrelated;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.finance.controller.admin.subjectrelated.vo.*;
import com.dofast.module.finance.dal.dataobject.subjectrelated.SubjectRelatedDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.finance.convert.subjectrelated.SubjectRelatedConvert;
import com.dofast.module.finance.dal.mysql.subjectrelated.SubjectRelatedMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.finance.enums.ErrorCodeConstants.*;

/**
 * 收支科目 Service 实现类
 *
 * @author a1
 */
@Service
@Validated
public class SubjectRelatedServiceImpl implements SubjectRelatedService {

    @Resource
    private SubjectRelatedMapper subjectRelatedMapper;

    @Override
    public Integer createSubjectRelated(SubjectRelatedCreateReqVO createReqVO) {
        // 插入
        SubjectRelatedDO subjectRelated = SubjectRelatedConvert.INSTANCE.convert(createReqVO);
        subjectRelatedMapper.insert(subjectRelated);
        // 返回
        return subjectRelated.getId();
    }

    @Override
    public void updateSubjectRelated(SubjectRelatedUpdateReqVO updateReqVO) {
        // 校验存在
        validateSubjectRelatedExists(updateReqVO.getId());
        // 更新
        SubjectRelatedDO updateObj = SubjectRelatedConvert.INSTANCE.convert(updateReqVO);
        subjectRelatedMapper.updateById(updateObj);
    }

    @Override
    public void deleteSubjectRelated(Integer id) {
        // 校验存在
        validateSubjectRelatedExists(id);
        // 删除
        subjectRelatedMapper.deleteById(id);
    }

    private void validateSubjectRelatedExists(Integer id) {
        if (subjectRelatedMapper.selectById(id) == null) {
            throw exception(SUBJECT_RELATED_NOT_EXISTS);
        }
    }

    @Override
    public SubjectRelatedDO getSubjectRelated(Integer id) {
        return subjectRelatedMapper.selectById(id);
    }

    @Override
    public List<SubjectRelatedDO> getSubjectRelatedList(Collection<Integer> ids) {
        return subjectRelatedMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<SubjectRelatedDO> getSubjectRelatedPage(SubjectRelatedPageReqVO pageReqVO) {
        return subjectRelatedMapper.selectPage(pageReqVO);
    }

    @Override
    public List<SubjectRelatedDO> getSubjectRelatedList(SubjectRelatedExportReqVO exportReqVO) {
        return subjectRelatedMapper.selectList(exportReqVO);
    }

}

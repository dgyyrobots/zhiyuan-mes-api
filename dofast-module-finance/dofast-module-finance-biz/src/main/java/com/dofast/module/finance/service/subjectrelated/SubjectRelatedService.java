package com.dofast.module.finance.service.subjectrelated;

import java.util.*;
import javax.validation.*;
import com.dofast.module.finance.controller.admin.subjectrelated.vo.*;
import com.dofast.module.finance.dal.dataobject.subjectrelated.SubjectRelatedDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 收支科目 Service 接口
 *
 * @author a1
 */
public interface SubjectRelatedService {

    /**
     * 创建收支科目
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createSubjectRelated(@Valid SubjectRelatedCreateReqVO createReqVO);

    /**
     * 更新收支科目
     *
     * @param updateReqVO 更新信息
     */
    void updateSubjectRelated(@Valid SubjectRelatedUpdateReqVO updateReqVO);

    /**
     * 删除收支科目
     *
     * @param id 编号
     */
    void deleteSubjectRelated(Integer id);

    /**
     * 获得收支科目
     *
     * @param id 编号
     * @return 收支科目
     */
    SubjectRelatedDO getSubjectRelated(Integer id);

    /**
     * 获得收支科目列表
     *
     * @param ids 编号
     * @return 收支科目列表
     */
    List<SubjectRelatedDO> getSubjectRelatedList(Collection<Integer> ids);

    /**
     * 获得收支科目分页
     *
     * @param pageReqVO 分页查询
     * @return 收支科目分页
     */
    PageResult<SubjectRelatedDO> getSubjectRelatedPage(SubjectRelatedPageReqVO pageReqVO);

    /**
     * 获得收支科目列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 收支科目列表
     */
    List<SubjectRelatedDO> getSubjectRelatedList(SubjectRelatedExportReqVO exportReqVO);

}

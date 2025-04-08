package com.dofast.module.pro.service.feedback;

import java.util.*;
import javax.validation.*;
import com.dofast.module.pro.controller.admin.feedback.vo.*;
import com.dofast.module.pro.dal.dataobject.feedback.FeedbackDO;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.pro.dal.dataobject.task.TaskDO;

/**
 * 生产报工记录 Service 接口
 *
 * @author 芋道源码
 */
public interface FeedbackService {

    /**
     * 创建生产报工记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFeedback(@Valid FeedbackCreateReqVO createReqVO);

    /**
     * 更新生产报工记录
     *
     * @param updateReqVO 更新信息
     */
    void updateFeedback(@Valid FeedbackUpdateReqVO updateReqVO);

    /**
     * 删除生产报工记录
     *
     * @param ids 编号
     */
    void deleteFeedback(Collection<Long> ids);

    void deleteFeedback(Long id);

    /**
     * 获得生产报工记录
     *
     * @param id 编号
     * @return 生产报工记录
     */
    FeedbackDO getFeedback(Long id);

    /**
     * 获得生产报工记录列表
     *
     * @param ids 编号
     * @return 生产报工记录列表
     */
    List<FeedbackDO> getFeedbackList(Collection<Long> ids);

    /**
     * 获得生产报工记录分页
     *
     * @param pageReqVO 分页查询
     * @return 生产报工记录分页
     */
    PageResult<FeedbackDO> getFeedbackPage(FeedbackPageReqVO pageReqVO);

    /**
     * 获得生产报工记录列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 生产报工记录列表
     */
    List<FeedbackDO> getFeedbackList(FeedbackExportReqVO exportReqVO);

    List<FeedbackDO> getFeedbackListByTaskId(Long taskId);

    Boolean OneClickCreateFeedback(FeedbackDO taskDO);

    List<Map<String, Object>> getCapacity();

}

package com.dofast.module.wms.service.rtissue;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.rtissue.vo.*;
import com.dofast.module.wms.dal.dataobject.rtissue.RtIssueDO;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.wms.dal.dataobject.rtissue.RtIssueTxBean;

/**
 * 生产退料单头 Service 接口
 *
 * @author 芋道源码
 */
public interface RtIssueService {
    public List<RtIssueTxBean> getTxBeans(Long rtId);
    public String checkUnique(RtIssueBaseVO baseVO);
    /**
     * 创建生产退料单头
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRtIssue(@Valid RtIssueCreateReqVO createReqVO);

    /**
     * 更新生产退料单头
     *
     * @param updateReqVO 更新信息
     */
    void updateRtIssue(@Valid RtIssueUpdateReqVO updateReqVO);

    /**
     * 删除生产退料单头
     *
     * @param id 编号
     */
    void deleteRtIssue(Long id);

    /**
     * 获得生产退料单头
     *
     * @param id 编号
     * @return 生产退料单头
     */
    RtIssueDO getRtIssue(Long id);

    /**
     * 获得生产退料单头列表
     *
     * @param ids 编号
     * @return 生产退料单头列表
     */
    List<RtIssueDO> getRtIssueList(Collection<Long> ids);

    /**
     * 获得生产退料单头分页
     *
     * @param pageReqVO 分页查询
     * @return 生产退料单头分页
     */
    PageResult<RtIssueDO> getRtIssuePage(RtIssuePageReqVO pageReqVO);

    /**
     * 获得生产退料单头列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 生产退料单头列表
     */
    List<RtIssueDO> getRtIssueList(RtIssueExportReqVO exportReqVO);

}

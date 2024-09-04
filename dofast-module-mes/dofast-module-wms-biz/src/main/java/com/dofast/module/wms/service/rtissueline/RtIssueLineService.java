package com.dofast.module.wms.service.rtissueline;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.rtissueline.vo.*;
import com.dofast.module.wms.dal.dataobject.rtissueline.RtIssueLineDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 生产退料单行 Service 接口
 *
 * @author 芋道源码
 */
public interface RtIssueLineService {
    public List<RtIssueLineDO> selectList(RtIssueLineListVO lineListVO);
    public int deleteByRtId(Long rtId);
    /**
     * 创建生产退料单行
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRtIssueLine(@Valid RtIssueLineCreateReqVO createReqVO);

    /**
     * 更新生产退料单行
     *
     * @param updateReqVO 更新信息
     */
    void updateRtIssueLine(@Valid RtIssueLineUpdateReqVO updateReqVO);

    /**
     * 删除生产退料单行
     *
     * @param id 编号
     */
    void deleteRtIssueLine(Long id);

    /**
     * 获得生产退料单行
     *
     * @param id 编号
     * @return 生产退料单行
     */
    RtIssueLineDO getRtIssueLine(Long id);

    /**
     * 获得生产退料单行列表
     *
     * @param ids 编号
     * @return 生产退料单行列表
     */
    List<RtIssueLineDO> getRtIssueLineList(Collection<Long> ids);

    /**
     * 获得生产退料单行分页
     *
     * @param pageReqVO 分页查询
     * @return 生产退料单行分页
     */
    PageResult<RtIssueLineDO> getRtIssueLinePage(RtIssueLinePageReqVO pageReqVO);

    /**
     * 获得生产退料单行列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 生产退料单行列表
     */
    List<RtIssueLineDO> getRtIssueLineList(RtIssueLineExportReqVO exportReqVO);

}

package com.dofast.module.wms.service.rtsalseline;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.rtsalseline.vo.*;
import com.dofast.module.wms.dal.dataobject.rtsalseline.RtSalseLineDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 产品销售退货行 Service 接口
 *
 * @author 芋道源码
 */
public interface RtSalseLineService {
    /**
     * 查询产品销售退货行列表
     *
     * @param lineListVO 产品销售退货行
     * @return 产品销售退货行集合
     */
    public List<RtSalseLineDO> selectWmRtSalseLineList(RtSalseLineListVO lineListVO);
    public int deleteByRtId(Long rtId);
    /**
     * 创建产品销售退货行
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRtSalseLine(@Valid RtSalseLineCreateReqVO createReqVO);

    /**
     * 更新产品销售退货行
     *
     * @param updateReqVO 更新信息
     */
    void updateRtSalseLine(@Valid RtSalseLineUpdateReqVO updateReqVO);

    /**
     * 删除产品销售退货行
     *
     * @param id 编号
     */
    void deleteRtSalseLine(Long id);

    /**
     * 获得产品销售退货行
     *
     * @param id 编号
     * @return 产品销售退货行
     */
    RtSalseLineDO getRtSalseLine(Long id);

    /**
     * 获得产品销售退货行列表
     *
     * @param ids 编号
     * @return 产品销售退货行列表
     */
    List<RtSalseLineDO> getRtSalseLineList(Collection<Long> ids);

    /**
     * 获得产品销售退货行分页
     *
     * @param pageReqVO 分页查询
     * @return 产品销售退货行分页
     */
    PageResult<RtSalseLineDO> getRtSalseLinePage(RtSalseLinePageReqVO pageReqVO);

    /**
     * 获得产品销售退货行列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品销售退货行列表
     */
    List<RtSalseLineDO> getRtSalseLineList(RtSalseLineExportReqVO exportReqVO);

}

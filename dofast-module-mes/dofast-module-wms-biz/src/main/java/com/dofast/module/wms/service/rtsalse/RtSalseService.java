package com.dofast.module.wms.service.rtsalse;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.rtsalse.vo.*;
import com.dofast.module.wms.dal.dataobject.rtsalse.RtSalseDO;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.wms.dal.dataobject.rtsalse.RtSalseTxBean;

/**
 * 产品销售退货单 Service 接口
 *
 * @author 芋道源码
 */
public interface RtSalseService {
    /**
     *
     * @param rtId
     * @return
     */
    public List<RtSalseTxBean> getTxBeans(Long rtId);
    /**
     * 检查编号唯一性
     * @return
     */
    public String checkUnique(RtSalseBaseVO wmRtSalse);

    /**
     * 创建产品销售退货单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRtSalse(@Valid RtSalseCreateReqVO createReqVO);

    /**
     * 更新产品销售退货单
     *
     * @param updateReqVO 更新信息
     */
    void updateRtSalse(@Valid RtSalseUpdateReqVO updateReqVO);

    /**
     * 删除产品销售退货单
     *
     * @param id 编号
     */
    void deleteRtSalse(Long id);

    /**
     * 获得产品销售退货单
     *
     * @param id 编号
     * @return 产品销售退货单
     */
    RtSalseDO getRtSalse(Long id);

    /**
     * 获得产品销售退货单列表
     *
     * @param ids 编号
     * @return 产品销售退货单列表
     */
    List<RtSalseDO> getRtSalseList(Collection<Long> ids);

    /**
     * 获得产品销售退货单分页
     *
     * @param pageReqVO 分页查询
     * @return 产品销售退货单分页
     */
    PageResult<RtSalseDO> getRtSalsePage(RtSalsePageReqVO pageReqVO);

    /**
     * 获得产品销售退货单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品销售退货单列表
     */
    List<RtSalseDO> getRtSalseList(RtSalseExportReqVO exportReqVO);

}

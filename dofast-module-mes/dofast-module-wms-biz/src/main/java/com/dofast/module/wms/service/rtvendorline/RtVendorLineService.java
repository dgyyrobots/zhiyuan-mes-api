package com.dofast.module.wms.service.rtvendorline;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.rtvendorline.vo.*;
import com.dofast.module.wms.dal.dataobject.rtvendorline.RtVendorLineDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 供应商退货行 Service 接口
 *
 * @author 芋道源码
 */
public interface RtVendorLineService {
    public int deleteByRtId(Long rtId);
    /**
     * 创建供应商退货行
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRtVendorLine(@Valid RtVendorLineCreateReqVO createReqVO);

    /**
     * 更新供应商退货行
     *
     * @param updateReqVO 更新信息
     */
    void updateRtVendorLine(@Valid RtVendorLineUpdateReqVO updateReqVO);

    /**
     * 删除供应商退货行
     *
     * @param id 编号
     */
    void deleteRtVendorLine(Long id);

    /**
     * 获得供应商退货行
     *
     * @param id 编号
     * @return 供应商退货行
     */
    RtVendorLineDO getRtVendorLine(Long id);

    /**
     * 获得供应商退货行列表
     *
     * @param ids 编号
     * @return 供应商退货行列表
     */
    List<RtVendorLineDO> getRtVendorLineList(Collection<Long> ids);

    /**
     * 获得供应商退货行分页
     *
     * @param pageReqVO 分页查询
     * @return 供应商退货行分页
     */
    PageResult<RtVendorLineDO> getRtVendorLinePage(RtVendorLinePageReqVO pageReqVO);

    /**
     * 获得供应商退货行列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 供应商退货行列表
     */
    List<RtVendorLineDO> getRtVendorLineList(RtVendorLineExportReqVO exportReqVO);

}

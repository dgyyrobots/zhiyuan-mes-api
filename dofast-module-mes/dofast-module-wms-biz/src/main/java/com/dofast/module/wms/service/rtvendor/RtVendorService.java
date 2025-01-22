package com.dofast.module.wms.service.rtvendor;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.rtvendor.vo.*;
import com.dofast.module.wms.dal.dataobject.rtvendor.RtVendorDO;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.wms.dal.dataobject.rtvendor.RtVendorTxBean;

/**
 * 供应商退货 Service 接口
 *
 * @author 芋道源码
 */
public interface RtVendorService {
    /**
     * 获取库存事务bean
     * @param rtId
     * @return
     */
    public List<RtVendorTxBean> getTxBeans(Long rtId);
    /**
     * 检查供应商退货单编码是否唯一
     * @param wmRtVendor
     * @return
     */
    public String checkCodeUnique(RtVendorBaseVO wmRtVendor);
    /**
     * 创建供应商退货
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRtVendor(@Valid RtVendorCreateReqVO createReqVO);

    /**
     * 更新供应商退货
     *
     * @param updateReqVO 更新信息
     */
    void updateRtVendor(@Valid RtVendorUpdateReqVO updateReqVO);

    /**
     * 删除供应商退货
     *
     * @param id 编号
     */
    void deleteRtVendor(Long id);

    /**
     * 获得供应商退货
     *
     * @param id 编号
     * @return 供应商退货
     */
    RtVendorDO getRtVendor(Long id);

    RtVendorDO getRtVendor(String vendorCode);


    /**
     * 获得供应商退货列表
     *
     * @param ids 编号
     * @return 供应商退货列表
     */
    List<RtVendorDO> getRtVendorList(Collection<Long> ids);

    /**
     * 获得供应商退货分页
     *
     * @param pageReqVO 分页查询
     * @return 供应商退货分页
     */
    PageResult<RtVendorDO> getRtVendorPage(RtVendorPageReqVO pageReqVO);

    /**
     * 获得供应商退货列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 供应商退货列表
     */
    List<RtVendorDO> getRtVendorList(RtVendorExportReqVO exportReqVO);

}

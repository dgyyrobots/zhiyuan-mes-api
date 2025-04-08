package com.dofast.module.mes.service.mdvendor;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.mdvendor.vo.*;
import com.dofast.module.mes.dal.dataobject.mdvendor.MdVendorDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 供应商 Service 接口
 *
 * @author 芋道源码
 */
public interface PadMdVendorService {
    public String checkVendorCodeUnique(MdVendorBaseVO mdVendor);
    public String checkVendorNameUnique(MdVendorBaseVO mdVendor);
    public String checkVendorNickUnique(MdVendorBaseVO mdVendor);
    /**
     * 创建供应商
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMdVendor(MdVendorCreateReqVO createReqVO);

    /**
     * 更新供应商
     *
     * @param updateReqVO 更新信息
     */
    void updateMdVendor(MdVendorUpdateReqVO updateReqVO);

    /**
     * 删除供应商
     *
     * @param id 编号
     */
    void deleteMdVendor(Long id);

    /**
     * 获得供应商
     *
     * @param id 编号
     * @return 供应商
     */
    MdVendorDO getMdVendor(Long id);

    /**
     * 获得供应商
     *
     * @param vendorCode 供应商编号
     * @return 供应商
     */
    MdVendorDO getMdVendor(String vendorCode);

    /**
     * 获得供应商列表
     *
     * @param ids 编号
     * @return 供应商列表
     */
    List<MdVendorDO> getMdVendorList(Collection<Long> ids);

    /**
     * 获得供应商分页
     *
     * @param pageReqVO 分页查询
     * @return 供应商分页
     */
    PageResult<MdVendorDO> getMdVendorPage(MdVendorPageReqVO pageReqVO);

    /**
     * 获得供应商列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 供应商列表
     */
    List<MdVendorDO> getMdVendorList(MdVendorExportReqVO exportReqVO);

}

package com.dofast.module.wms.service.packageline;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.packageline.vo.*;
import com.dofast.module.wms.dal.dataobject.packageline.PackageLineDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 装箱明细 Service 接口
 *
 * @author 芋道源码
 */
public interface PackageLineService {

    /**
     * 创建装箱明细
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPackageLine(@Valid PackageLineCreateReqVO createReqVO);

    /**
     * 更新装箱明细
     *
     * @param updateReqVO 更新信息
     */
    void updatePackageLine(@Valid PackageLineUpdateReqVO updateReqVO);

    /**
     * 删除装箱明细
     *
     * @param id 编号
     */
    void deletePackageLine(Long id);

    /**
     * 获得装箱明细
     *
     * @param id 编号
     * @return 装箱明细
     */
    PackageLineDO getPackageLine(Long id);

    /**
     * 获得装箱明细列表
     *
     * @param ids 编号
     * @return 装箱明细列表
     */
    List<PackageLineDO> getPackageLineList(Collection<Long> ids);

    /**
     * 获得装箱明细分页
     *
     * @param pageReqVO 分页查询
     * @return 装箱明细分页
     */
    PageResult<PackageLineDO> getPackageLinePage(PackageLinePageReqVO pageReqVO);

    /**
     * 获得装箱明细列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 装箱明细列表
     */
    List<PackageLineDO> getPackageLineList(PackageLineExportReqVO exportReqVO);

}

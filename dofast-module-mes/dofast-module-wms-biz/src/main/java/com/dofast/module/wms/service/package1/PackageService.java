package com.dofast.module.wms.service.package1;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.package1.vo.*;
import com.dofast.module.wms.dal.dataobject.package1.PackageDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 装箱单 Service 接口
 *
 * @author 芋道源码
 */
public interface PackageService {
    /**
     * 查询装箱单
     *
     * @param packageId 装箱单主键
     * @return 装箱单
     */
    public PackageDO selectWmPackageByPackageId(Long packageId);
    /**
     * 检查装箱单编号是否唯一
     * @param wmPackage
     * @return
     */
    public String checkPackgeCodeUnique(PackageBaseVO wmPackage);
    /**
     * 创建装箱单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPackage(@Valid PackageCreateReqVO createReqVO);

    /**
     * 更新装箱单
     *
     * @param updateReqVO 更新信息
     */
    void updatePackage(@Valid PackageUpdateReqVO updateReqVO);

    /**
     * 删除装箱单
     *
     * @param id 编号
     */
    void deletePackage(Long id);

    /**
     * 获得装箱单
     *
     * @param id 编号
     * @return 装箱单
     */
    PackageDO getPackage(Long id);

    /**
     * 获得装箱单列表
     *
     * @param ids 编号
     * @return 装箱单列表
     */
    List<PackageDO> getPackageList(Collection<Long> ids);

    /**
     * 获得装箱单分页
     *
     * @param pageReqVO 分页查询
     * @return 装箱单分页
     */
    PageResult<PackageDO> getPackagePage(PackagePageReqVO pageReqVO);

    /**
     * 获得装箱单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 装箱单列表
     */
    List<PackageDO> getPackageList(PackageExportReqVO exportReqVO);

}

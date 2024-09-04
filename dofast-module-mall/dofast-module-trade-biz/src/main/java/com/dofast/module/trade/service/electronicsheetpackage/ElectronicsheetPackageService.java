package com.dofast.module.trade.service.electronicsheetpackage;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.electronicsheetpackage.vo.ElectronicsheetPackageCreateReqVO;
import com.dofast.module.trade.controller.admin.electronicsheetpackage.vo.ElectronicsheetPackageExportReqVO;
import com.dofast.module.trade.controller.admin.electronicsheetpackage.vo.ElectronicsheetPackagePageReqVO;
import com.dofast.module.trade.controller.admin.electronicsheetpackage.vo.ElectronicsheetPackageUpdateReqVO;
import com.dofast.module.trade.dal.dataobject.electronicsheetpackage.ElectronicsheetPackageDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 电子面单 Service 接口
 *
 * @author 惠智造
 */
public interface ElectronicsheetPackageService {

    /**
     * 创建电子面单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createElectronicsheetPackage(@Valid ElectronicsheetPackageCreateReqVO createReqVO);

    /**
     * 更新电子面单
     *
     * @param updateReqVO 更新信息
     */
    void updateElectronicsheetPackage(@Valid ElectronicsheetPackageUpdateReqVO updateReqVO);

    /**
     * 删除电子面单
     *
     * @param id 编号
     */
    void deleteElectronicsheetPackage(Integer id);

    /**
     * 获得电子面单
     *
     * @param id 编号
     * @return 电子面单
     */
    ElectronicsheetPackageDO getElectronicsheetPackage(Integer id);

    /**
     * 获得电子面单列表
     *
     * @param ids 编号
     * @return 电子面单列表
     */
    List<ElectronicsheetPackageDO> getElectronicsheetPackageList(Collection<Integer> ids);

    /**
     * 获得电子面单列表
     *
     * @return 电子面单列表
     */
    List<ElectronicsheetPackageDO> getElectronicsheetPackageListByOrderNo(String orderNo);

    /**
     * 获得电子面单分页
     *
     * @param pageReqVO 分页查询
     * @return 电子面单分页
     */
    PageResult<ElectronicsheetPackageDO> getElectronicsheetPackagePage(ElectronicsheetPackagePageReqVO pageReqVO);

    /**
     * 获得电子面单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 电子面单列表
     */
    List<ElectronicsheetPackageDO> getElectronicsheetPackageList(ElectronicsheetPackageExportReqVO exportReqVO);

    List<ElectronicsheetPackageDO> getElectronicsheetByOrderNo(String orderNo);
}

package com.dofast.module.deliver.service.expresselectronicsheet;

import java.util.*;
import javax.validation.*;
import com.dofast.module.deliver.controller.admin.expresselectronicsheet.vo.*;
import com.dofast.module.deliver.dal.dataobject.expresselectronicsheet.ExpressElectronicsheetDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 电子面单 Service 接口
 *
 * @author a1
 */
public interface ExpressElectronicsheetService {

    /**
     * 创建电子面单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createExpressElectronicsheet(@Valid ExpressElectronicsheetCreateReqVO createReqVO);

    /**
     * 更新电子面单
     *
     * @param updateReqVO 更新信息
     */
    void updateExpressElectronicsheet(@Valid ExpressElectronicsheetUpdateReqVO updateReqVO);

    /**
     * 删除电子面单
     *
     * @param id 编号
     */
    void deleteExpressElectronicsheet(Long id);

    /**
     * 获得电子面单
     *
     * @param id 编号
     * @return 电子面单
     */
    ExpressElectronicsheetDO getExpressElectronicsheet(Long id);

    /**
     * 获得电子面单列表
     *
     * @param ids 编号
     * @return 电子面单列表
     */
    List<ExpressElectronicsheetDO> getExpressElectronicsheetList(Collection<Long> ids);

    /**
     * 获得电子面单分页
     *
     * @param pageReqVO 分页查询
     * @return 电子面单分页
     */
    PageResult<ExpressElectronicsheetDO> getExpressElectronicsheetPage(ExpressElectronicsheetPageReqVO pageReqVO);

    /**
     * 获得电子面单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 电子面单列表
     */
    List<ExpressElectronicsheetDO> getExpressElectronicsheetList(ExpressElectronicsheetExportReqVO exportReqVO);

}

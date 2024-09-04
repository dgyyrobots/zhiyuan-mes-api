package com.dofast.module.wms.service.barcodeconfig;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.barcodeconfig.vo.*;
import com.dofast.module.wms.dal.dataobject.barcodeconfig.BarcodeConfigDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 条码配置 Service 接口
 *
 * @author 芋道源码
 */
public interface BarcodeConfigService {

    public List<BarcodeConfigDO> selectLiat(BarcodeConfigListVO listVO);
    /**
     * 判断某种类型的业务是否需要自动生成赋码
     * @param barcodeType
     * @return
     */
    public boolean isAutoGen(String barcodeType);
    /**
     * 创建条码配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBarcodeConfig(@Valid BarcodeConfigCreateReqVO createReqVO);

    /**
     * 更新条码配置
     *
     * @param updateReqVO 更新信息
     */
    void updateBarcodeConfig(@Valid BarcodeConfigUpdateReqVO updateReqVO);

    /**
     * 删除条码配置
     *
     * @param id 编号
     */
    void deleteBarcodeConfig(Long id);

    /**
     * 获得条码配置
     *
     * @param id 编号
     * @return 条码配置
     */
    BarcodeConfigDO getBarcodeConfig(Long id);

    /**
     * 获得条码配置列表
     *
     * @param ids 编号
     * @return 条码配置列表
     */
    List<BarcodeConfigDO> getBarcodeConfigList(Collection<Long> ids);

    /**
     * 获得条码配置分页
     *
     * @param pageReqVO 分页查询
     * @return 条码配置分页
     */
    PageResult<BarcodeConfigDO> getBarcodeConfigPage(BarcodeConfigPageReqVO pageReqVO);

    /**
     * 获得条码配置列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 条码配置列表
     */
    List<BarcodeConfigDO> getBarcodeConfigList(BarcodeConfigExportReqVO exportReqVO);

}

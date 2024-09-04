package com.dofast.module.iot.service.firmware;

import java.util.*;
import javax.validation.*;
import com.dofast.module.iot.controller.admin.firmware.vo.*;
import com.dofast.module.iot.dal.dataobject.firmware.FirmwareDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 产品固件 Service 接口
 *
 * @author 惠智造
 */
public interface FirmwareService {

    /**
     * 创建产品固件
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFirmware(@Valid FirmwareCreateReqVO createReqVO);

    /**
     * 更新产品固件
     *
     * @param updateReqVO 更新信息
     */
    void updateFirmware(@Valid FirmwareUpdateReqVO updateReqVO);

    /**
     * 删除产品固件
     *
     * @param id 编号
     */
    void deleteFirmware(Long id);

    /**
     * 获得产品固件
     *
     * @param id 编号
     * @return 产品固件
     */
    FirmwareDO getFirmware(Long id);

    /**
     * 获得产品固件列表
     *
     * @param ids 编号
     * @return 产品固件列表
     */
    List<FirmwareDO> getFirmwareList(Collection<Long> ids);

    /**
     * 获得产品固件分页
     *
     * @param pageReqVO 分页查询
     * @return 产品固件分页
     */
    PageResult<FirmwareDO> getFirmwarePage(FirmwarePageReqVO pageReqVO);

    /**
     * 获得产品固件列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品固件列表
     */
    List<FirmwareDO> getFirmwareList(FirmwareExportReqVO exportReqVO);

}

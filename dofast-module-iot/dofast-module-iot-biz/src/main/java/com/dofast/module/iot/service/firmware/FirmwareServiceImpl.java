package com.dofast.module.iot.service.firmware;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.iot.controller.admin.firmware.vo.*;
import com.dofast.module.iot.dal.dataobject.firmware.FirmwareDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.iot.convert.firmware.FirmwareConvert;
import com.dofast.module.iot.dal.mysql.firmware.FirmwareMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.iot.enums.ErrorCodeConstants.*;

/**
 * 产品固件 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class FirmwareServiceImpl implements FirmwareService {

    @Resource
    private FirmwareMapper firmwareMapper;

    @Override
    public Long createFirmware(FirmwareCreateReqVO createReqVO) {
        // 插入
        FirmwareDO firmware = FirmwareConvert.INSTANCE.convert(createReqVO);
        firmwareMapper.insert(firmware);
        // 返回
        return firmware.getId();
    }

    @Override
    public void updateFirmware(FirmwareUpdateReqVO updateReqVO) {
        // 校验存在
        validateFirmwareExists(updateReqVO.getId());
        // 更新
        FirmwareDO updateObj = FirmwareConvert.INSTANCE.convert(updateReqVO);
        firmwareMapper.updateById(updateObj);
    }

    @Override
    public void deleteFirmware(Long id) {
        // 校验存在
        validateFirmwareExists(id);
        // 删除
        firmwareMapper.deleteById(id);
    }

    private void validateFirmwareExists(Long id) {
        if (firmwareMapper.selectById(id) == null) {
            throw exception(FIRMWARE_NOT_EXISTS);
        }
    }

    @Override
    public FirmwareDO getFirmware(Long id) {
        return firmwareMapper.selectById(id);
    }

    @Override
    public List<FirmwareDO> getFirmwareList(Collection<Long> ids) {
        return firmwareMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<FirmwareDO> getFirmwarePage(FirmwarePageReqVO pageReqVO) {
        return firmwareMapper.selectPage(pageReqVO);
    }

    @Override
    public List<FirmwareDO> getFirmwareList(FirmwareExportReqVO exportReqVO) {
        return firmwareMapper.selectList(exportReqVO);
    }

}

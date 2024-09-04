package com.dofast.module.wms.service.barcodeconfig;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.barcodeconfig.vo.*;
import com.dofast.module.wms.dal.dataobject.barcodeconfig.BarcodeConfigDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.barcodeconfig.BarcodeConfigConvert;
import com.dofast.module.wms.dal.mysql.barcodeconfig.BarcodeConfigMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 条码配置 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class BarcodeConfigServiceImpl implements BarcodeConfigService {

    @Resource
    private BarcodeConfigMapper barcodeConfigMapper;

    @Override
    public List<BarcodeConfigDO> selectLiat(BarcodeConfigListVO listVO) {
        return barcodeConfigMapper.selectList(listVO);
    }

    @Override
    public boolean isAutoGen(String barcodeType) {
        BarcodeConfigListVO param = new BarcodeConfigListVO();
        param.setBarcodeType(barcodeType);
        List<BarcodeConfigDO> configs = barcodeConfigMapper.selectList(param);
        if(!CollectionUtils.isEmpty(configs)){
            return "Y".equals(configs.get(0).getAutoGenFlag())?true:false;
        }
        return false;
    }

    @Override
    public Long createBarcodeConfig(BarcodeConfigCreateReqVO createReqVO) {
        // 插入
        BarcodeConfigDO barcodeConfig = BarcodeConfigConvert.INSTANCE.convert(createReqVO);
        barcodeConfigMapper.insert(barcodeConfig);
        // 返回
        return barcodeConfig.getId();
    }

    @Override
    public void updateBarcodeConfig(BarcodeConfigUpdateReqVO updateReqVO) {
        // 校验存在
        validateBarcodeConfigExists(updateReqVO.getId());
        // 更新
        BarcodeConfigDO updateObj = BarcodeConfigConvert.INSTANCE.convert(updateReqVO);
        barcodeConfigMapper.updateById(updateObj);
    }

    @Override
    public void deleteBarcodeConfig(Long id) {
        // 校验存在
        validateBarcodeConfigExists(id);
        // 删除
        barcodeConfigMapper.deleteById(id);
    }

    private void validateBarcodeConfigExists(Long id) {
        if (barcodeConfigMapper.selectById(id) == null) {
            throw exception(BARCODE_CONFIG_NOT_EXISTS);
        }
    }

    @Override
    public BarcodeConfigDO getBarcodeConfig(Long id) {
        return barcodeConfigMapper.selectById(id);
    }

    @Override
    public List<BarcodeConfigDO> getBarcodeConfigList(Collection<Long> ids) {
        return barcodeConfigMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BarcodeConfigDO> getBarcodeConfigPage(BarcodeConfigPageReqVO pageReqVO) {
        return barcodeConfigMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BarcodeConfigDO> getBarcodeConfigList(BarcodeConfigExportReqVO exportReqVO) {
        return barcodeConfigMapper.selectList(exportReqVO);
    }

}

package com.dofast.module.wms.service.barcode;

import cn.hutool.core.io.IoUtil;
import com.dofast.framework.common.util.io.FileUploadUtils;
import com.dofast.framework.common.util.io.FileUtils;
import com.dofast.module.infra.api.file.FileApi;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.utils.BarcodeUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import com.dofast.module.wms.controller.admin.barcode.vo.*;
import com.dofast.module.wms.dal.dataobject.barcode.BarcodeDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.barcode.BarcodeConvert;
import com.dofast.module.wms.dal.mysql.barcode.BarcodeMapper;
import org.springframework.web.multipart.MultipartFile;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 条码清单 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class BarcodeServiceImpl implements BarcodeService {

    @Resource
    private BarcodeMapper barcodeMapper;

    @Resource
    private FileApi fileApi;

    @Override
    @Transactional
    public String generateBarcode(BarcodeBaseVO baseVO) throws IOException {
        File buf = BarcodeUtil.generateBarCode(
                baseVO.getBarcodeContent(),
                baseVO.getBarcodeFormart(),
                "./tmp/barcode/" + baseVO.getBarcodeContent() + ".png");
        MultipartFile file = FileUtils.getMultipartFile(buf);
        try {
            return fileApi.createFile(file.getOriginalFilename(), null, IoUtil.readBytes(file.getInputStream()));
        } catch (IOException exception) {
            throw exception;
        } finally {
            //删除掉临时文件
            if (buf != null && buf.exists()) {
                FileUtils.deleteFile(buf.getAbsolutePath());
            }
        }
    }

    @Override
    public String checkBarcodeUnique(BarcodeBaseVO baseVO) {
        BarcodeDO barcode = barcodeMapper.checkBarcodeUnique(baseVO);
        Long barcodeId = baseVO.getId()==null?-1L:baseVO.getId();
        if(null != barcode && barcode.getId().longValue() != barcodeId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createBarcode(BarcodeCreateReqVO createReqVO) {
        // 插入
        BarcodeDO barcode = BarcodeConvert.INSTANCE.convert(createReqVO);
        barcodeMapper.insert(barcode);
        // 返回
        return barcode.getId();
    }

    @Override
    public void updateBarcode(BarcodeUpdateReqVO updateReqVO) {
        // 校验存在
        validateBarcodeExists(updateReqVO.getId());
        // 更新
        BarcodeDO updateObj = BarcodeConvert.INSTANCE.convert(updateReqVO);
        barcodeMapper.updateById(updateObj);
    }

    @Override
    public void deleteBarcode(Long id) {
        // 校验存在
        validateBarcodeExists(id);
        // 删除
        barcodeMapper.deleteById(id);
    }

    private void validateBarcodeExists(Long id) {
        if (barcodeMapper.selectById(id) == null) {
            throw exception(BARCODE_NOT_EXISTS);
        }
    }

    @Override
    public BarcodeDO getBarcode(Long id) {
        return barcodeMapper.selectById(id);
    }

    @Override
    public List<BarcodeDO> getBarcodeList(Collection<Long> ids) {
        return barcodeMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BarcodeDO> getBarcodePage(BarcodePageReqVO pageReqVO) {
        return barcodeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BarcodeDO> getBarcodeList(BarcodeExportReqVO exportReqVO) {
        return barcodeMapper.selectList(exportReqVO);
    }

}

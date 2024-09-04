package com.dofast.module.wms.api.BarcodeApi;

import com.dofast.module.wms.utils.WmsBarCodeUtil;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class BarcodeApiImpl implements BarCodeUtil {

    @Resource
    WmsBarCodeUtil barCodeUtil;
    @Override
    public void generateBarCode(String type,Long businessId,String businessCode,String businessName)throws IOException {
        barCodeUtil.generateBarCode(type,businessId,businessCode,businessName);
    }
}

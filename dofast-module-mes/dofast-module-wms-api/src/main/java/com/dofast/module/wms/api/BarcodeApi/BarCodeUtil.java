package com.dofast.module.wms.api.BarcodeApi;

import java.io.IOException;

public interface BarCodeUtil {
    void generateBarCode(String type,Long businessId,String businessCode,String businessName)throws IOException;
}

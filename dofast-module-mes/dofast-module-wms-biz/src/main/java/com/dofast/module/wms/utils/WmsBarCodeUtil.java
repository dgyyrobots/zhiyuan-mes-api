package com.dofast.module.wms.utils;

import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.controller.admin.barcode.vo.BarcodeCreateReqVO;
import com.dofast.module.wms.controller.admin.barcodeconfig.vo.BarcodeConfigListVO;
import com.dofast.module.wms.dal.dataobject.barcodeconfig.BarcodeConfigDO;
import com.dofast.module.wms.service.barcode.BarcodeService;
import com.dofast.module.wms.service.barcodeconfig.BarcodeConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

@Component
public class WmsBarCodeUtil {
    private static final Logger log = LoggerFactory.getLogger(WmsBarCodeUtil.class);

    @Autowired
    private BarcodeConfigService wmBarcodeConfigService;

    @Autowired
    private BarcodeService wmBarcodeService;

    public void generateBarCode(String type,Long businessId,String businessCode,String businessName)throws IOException {
        //先判断当前类型的业务是否配置了需要自动生成条码
        if(!wmBarcodeConfigService.isAutoGen(type)){
            //无需自动生成条码
            return ;
        }
        BarcodeConfigListVO param = new BarcodeConfigListVO();
        param.setBarcodeType(type);
        List<BarcodeConfigDO> confgs = wmBarcodeConfigService.selectLiat(param);
        if(CollectionUtils.isEmpty(confgs)){
            log.warn("当前类型的业务未配置对应的条码生成规则！{}",type);
            return ;
        }

        BarcodeConfigDO config = confgs.get(0);
        BarcodeCreateReqVO barcode = new BarcodeCreateReqVO();
        barcode.setBarcodeFormart(config.getBarcodeFormart());
        barcode.setBarcodeType(type);
        barcode.setBarcodeContent(config.getContentFormart().replace("{BUSINESSCODE}",businessCode));
        barcode.setBussinessCode(businessCode);
        barcode.setEnableFlag("Y");
        barcode.setBussinessId(businessId);

        if(Constant.NOT_UNIQUE.equals(wmBarcodeService.checkBarcodeUnique(barcode))){
            log.warn("当前业务对象的赋码已存在！{} {}",type,businessCode);
            return ;
        }

        String path = wmBarcodeService.generateBarcode(barcode);
        barcode.setBarcodeUrl(path);
        wmBarcodeService.createBarcode(barcode);
    }

}

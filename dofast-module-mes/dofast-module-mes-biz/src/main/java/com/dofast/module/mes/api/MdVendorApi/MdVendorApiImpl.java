package com.dofast.module.mes.api.MdVendorApi;

import com.dofast.framework.common.util.bean.BeanUtils;
import com.dofast.module.mes.api.MdVendorApi.dto.MdVendorDTO;
import com.dofast.module.mes.convert.mdvendor.PadMdVendorConvert;
import com.dofast.module.mes.dal.dataobject.mdvendor.MdVendorDO;
import com.dofast.module.mes.service.mdvendor.PadMdVendorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MdVendorApiImpl implements MdVendorApi {

    @Resource
    private PadMdVendorService padMdVendorService;

    @Override
    public MdVendorDTO getVendorByVendorCode(String code) {
        MdVendorDO mdVendorDO = padMdVendorService.getMdVendor(code);
        return PadMdVendorConvert.INSTANCE.convert01(mdVendorDO);
    }

    @Override
    public MdVendorDTO getVendorByVendorId(Long id) {
        MdVendorDO mdVendorDO =  padMdVendorService.getMdVendor(id);
        return PadMdVendorConvert.INSTANCE.convert01(mdVendorDO);
    }
}

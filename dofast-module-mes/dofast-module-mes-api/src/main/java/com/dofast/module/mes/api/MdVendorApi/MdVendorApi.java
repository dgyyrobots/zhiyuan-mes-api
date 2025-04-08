package com.dofast.module.mes.api.MdVendorApi;

import com.dofast.module.mes.api.ItemApi.dto.MdItemDTO;
import com.dofast.module.mes.api.MdVendorApi.dto.MdVendorDTO;

public interface MdVendorApi {

    public MdVendorDTO getVendorByVendorCode(String code);

    public MdVendorDTO getVendorByVendorId(Long id);
}

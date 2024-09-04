package com.dofast.module.mes.api.ProductBomApi;

import com.dofast.module.mes.api.ProductBomApi.dto.MdProductBomDTO;
import com.dofast.module.mes.controller.admin.mdproductbom.vo.MdProductBomListVO;
import com.dofast.module.mes.convert.mdproductbom.MdProductBomConvert;
import com.dofast.module.mes.dal.dataobject.mdproductbom.MdProductBomDO;
import com.dofast.module.mes.service.mdproductbom.MdProductBomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ProductBomApiImpl implements ProductBomApi {
    @Resource
    MdProductBomService mdProductBomService;

    @Override
    public List<MdProductBomDTO> selectListByItemId(Long itemId) {
        MdProductBomListVO listVO = new MdProductBomListVO();
        listVO.setItemId(itemId);
        List<MdProductBomDO> list = mdProductBomService.selectList(listVO);
        List<MdProductBomDTO> productBomDTOS = MdProductBomConvert.INSTANCE.convertList03(list);
        return productBomDTOS;
    }
}

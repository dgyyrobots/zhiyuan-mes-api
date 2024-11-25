package com.dofast.module.mes.api.ItemApi;

import com.dofast.module.mes.api.ItemApi.dto.MdItemDTO;
import com.dofast.module.mes.convert.mditem.MdItemConvert;
import com.dofast.module.mes.dal.dataobject.mditem.MdItemDO;
import com.dofast.module.mes.dal.mysql.mditem.MdItemMapper;
import com.dofast.module.mes.service.mditem.MdItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MdItemApiImpl implements MdItemApi{
    @Resource
    private MdItemService mdItemService;
    @Override
    public MdItemDTO getMditemById(Long itemId) {
        MdItemDO mdItemDO = mdItemService.getMdItem(itemId);
        MdItemDTO mdItemDTO = MdItemConvert.INSTANCE.convert01(mdItemDO);
        return mdItemDTO;
    }
    @Override
    public MdItemDTO getMdItemByCode(String itemCode){
        MdItemDO mdItemDO = mdItemService.getMdItemByItemCode(itemCode);
        MdItemDTO mdItemDTO = MdItemConvert.INSTANCE.convert01(mdItemDO);
        return mdItemDTO;
    }

}

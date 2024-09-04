package com.dofast.module.trade.service.mixinorderitem;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.mes.api.ItemApi.dto.MdItemDTO;
import com.dofast.module.trade.controller.admin.mixinorderitem.vo.MixinOrderItemCreateReqVO;
import com.dofast.module.trade.controller.admin.mixinorderitem.vo.MixinOrderItemExportReqVO;
import com.dofast.module.trade.controller.admin.mixinorderitem.vo.MixinOrderItemPageReqVO;
import com.dofast.module.trade.controller.admin.mixinorderitem.vo.MixinOrderItemUpdateReqVO;
import com.dofast.module.trade.convert.mixinorderitem.MixinOrderItemConvert;
import com.dofast.module.trade.dal.dataobject.mixin.MixinOrderDO;
import com.dofast.module.trade.dal.dataobject.mixinorderitem.MixinOrderItemDO;
import com.dofast.module.trade.dal.mysql.mixinorderitem.MixinOrderItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.trade.enums.ErrorCodeConstants.MIXIN_ORDER_ITEM_NOT_EXISTS;

/**
 * 销售的物料明细 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class MixinOrderItemServiceImpl implements MixinOrderItemService {

    @Resource
    private MixinOrderItemMapper mixinOrderItemMapper;

    @Override
    public Long createMixinOrderItem(MixinOrderItemCreateReqVO createReqVO) {
        // 插入
        MixinOrderItemDO mixinOrderItem = MixinOrderItemConvert.INSTANCE.convert(createReqVO);
        mixinOrderItemMapper.insert(mixinOrderItem);
        // 返回
        return mixinOrderItem.getId();
    }

    @Override
    public void updateMixinOrderItem(MixinOrderItemUpdateReqVO updateReqVO) {
        // 校验存在
        validateMixinOrderItemExists(updateReqVO.getId());
        // 更新
        MixinOrderItemDO updateObj = MixinOrderItemConvert.INSTANCE.convert(updateReqVO);
        mixinOrderItemMapper.updateById(updateObj);
    }

    @Override
    public void deleteMixinOrderItem(Long id) {
        // 校验存在
        validateMixinOrderItemExists(id);
        // 删除
        mixinOrderItemMapper.deleteById(id);
    }

    private void validateMixinOrderItemExists(Long id) {
        if (mixinOrderItemMapper.selectById(id) == null) {
            throw exception(MIXIN_ORDER_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public MixinOrderItemDO getMixinOrderItem(Long id) {
        return mixinOrderItemMapper.selectById(id);
    }

    @Override
    public List<MixinOrderItemDO> getMixinOrderItemList(Collection<Long> ids) {
        return mixinOrderItemMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MixinOrderItemDO> getMixinOrderItemPage(MixinOrderItemPageReqVO pageReqVO) {
        return mixinOrderItemMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MixinOrderItemDO> getMixinOrderItemList(MixinOrderItemExportReqVO exportReqVO) {
        return mixinOrderItemMapper.selectList(exportReqVO);
    }

    @Override
    public void generateMixinOrderItem(MdItemDTO mdItemDTO, MixinOrderDO mixinOrderDO) {
        MixinOrderItemDO mixinOrderItemDO = new MixinOrderItemDO();
        mixinOrderItemDO.setItemId(mdItemDTO.getId());
        mixinOrderItemDO.setItemCode(mdItemDTO.getItemCode());
        mixinOrderItemDO.setItemName(mdItemDTO.getItemName());
        mixinOrderItemDO.setSaleId(mixinOrderDO.getId());
        mixinOrderItemDO.setSaleNo(mixinOrderDO.getSaleNo());
        mixinOrderItemDO.setNum(1);
        mixinOrderItemDO.setTitle(mixinOrderDO.getTitle());
        mixinOrderItemDO.setSinglePrice(BigDecimal.ZERO);
        mixinOrderItemDO.setTotalPrice(BigDecimal.ZERO);
        mixinOrderItemMapper.insert(mixinOrderItemDO);
    }

}

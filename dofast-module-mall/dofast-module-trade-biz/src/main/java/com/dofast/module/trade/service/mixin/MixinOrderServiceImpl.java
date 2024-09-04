package com.dofast.module.trade.service.mixin;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.mes.api.ItemApi.MdItemApi;
import com.dofast.module.mes.api.ItemApi.dto.MdItemDTO;
import com.dofast.module.trade.controller.admin.mixin.vo.*;
import com.dofast.module.trade.controller.admin.mixinorderitem.vo.MixinOrderItemExportReqVO;
import com.dofast.module.trade.convert.mixin.MixinOrderConvert;
import com.dofast.module.trade.dal.dataobject.mixin.MixinOrderDO;
import com.dofast.module.trade.dal.dataobject.mixinorderitem.MixinOrderItemDO;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderDO;
import com.dofast.module.trade.dal.mysql.mixin.MixinOrderMapper;
import com.dofast.module.trade.dal.mysql.order.TradeOrderMapper;
import com.dofast.module.trade.service.mixinorderitem.MixinOrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.trade.enums.ErrorCodeConstants.MIXIN_ORDER_NOT_EXISTS;
import static com.dofast.module.trade.enums.ErrorCodeConstants.TRADE_ORDER_BIND_MIXIN_ORDER_ERROR;

/**
 * 销售订单 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class MixinOrderServiceImpl implements MixinOrderService {

    @Resource
    private MixinOrderMapper mixinOrderMapper;
    @Resource
    private TradeOrderMapper tradeOrderMapper;

    @Resource
    private MixinOrderItemService mixinOrderItemService;

    @Resource
    private MdItemApi mdItemApi;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createMixinOrder(MixinOrderCreateReqVO createReqVO) {
        // 插入
        MixinOrderDO mixinOrder = MixinOrderConvert.INSTANCE.convert(createReqVO);

        mixinOrderMapper.insert(mixinOrder);

        Iterator<Long> orderIdIterator = createReqVO.getOrderList().stream().iterator();

        while (orderIdIterator.hasNext()) {
            Long orderId = orderIdIterator.next();
            Integer count = tradeOrderMapper.update(null,
                    new LambdaUpdateWrapper<TradeOrderDO>()
                    .eq(TradeOrderDO::getId, orderId)
                    .eq(TradeOrderDO::getMixinOrderId, 0)
                    .set(TradeOrderDO::getMixinOrderId, mixinOrder.getId())
            );
            if (count <= 0) {
                throw exception(TRADE_ORDER_BIND_MIXIN_ORDER_ERROR);
            }
        }
        // 返回
        return mixinOrder.getId();
    }

    @Override
    public void updateMixinOrder(MixinOrderUpdateReqVO updateReqVO) {
        // 校验存在
        validateMixinOrderExists(updateReqVO.getId());
        // 更新
        MixinOrderDO updateObj = MixinOrderConvert.INSTANCE.convert(updateReqVO);
        mixinOrderMapper.updateById(updateObj);
    }

    @Override
    public void deleteMixinOrder(Long id) {
        // 校验存在
        validateMixinOrderExists(id);
        // 删除
        mixinOrderMapper.deleteById(id);
    }

    private void validateMixinOrderExists(Long id) {
        if (mixinOrderMapper.selectById(id) == null) {
            throw exception(MIXIN_ORDER_NOT_EXISTS);
        }
    }

    @Override
    public MixinOrderDO getMixinOrder(Long id) {
        return mixinOrderMapper.selectById(id);
    }

    @Override
    public MixinOrderDetailRespVO getMixinOrderDetail(Long id) {
        MixinOrderDO mixinOrderDO = mixinOrderMapper.selectById(id);
        MixinOrderDetailRespVO mixinOrderDetailRespVO = BeanUtil.toBean(mixinOrderDO, MixinOrderDetailRespVO.class);
        MixinOrderItemExportReqVO mixinOrderItemExportReqVO = new MixinOrderItemExportReqVO();
        mixinOrderItemExportReqVO.setSaleId(mixinOrderDO.getId());
        List<MixinOrderItemDO> list = mixinOrderItemService.getMixinOrderItemList(mixinOrderItemExportReqVO);
        if (list.isEmpty()){
            mixinOrderDetailRespVO.setMdItemDTOS(null);
        } else {
            for (MixinOrderItemDO mixinOrderItemDO : list){
                MdItemDTO mdItemDTO = mdItemApi.getMditemById(mixinOrderItemDO.getItemId());
                mixinOrderDetailRespVO.add(mdItemDTO);
            }
        }
        return mixinOrderDetailRespVO;
    }

    @Override
    public List<MixinOrderDO> getMixinOrderList(Collection<Long> ids) {
        return mixinOrderMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MixinOrderDO> getMixinOrderPage(MixinOrderPageReqVO pageReqVO) {
        return mixinOrderMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<MixinOrderDO> getMixinOrderPageDesc(MixinOrderPageReqVO pageReqVO) {
        return mixinOrderMapper.selectPageDesc(pageReqVO);
    }

    @Override
    public List<MixinOrderDO> getMixinOrderList(MixinOrderExportReqVO exportReqVO) {
        return mixinOrderMapper.selectList(exportReqVO);
    }

    @Override
    public BigDecimal getDayOrderMoney() {
        // 查询当天的起始时间
        LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);
        // 查询当天的结束时间
        LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);
        // 构建查询条件，查询finish_time在当天的销售记录，并统计总销售价格
        Map<String, Object> result = mixinOrderMapper.selectTotalSellPrice(startOfDay, endOfDay);
        // 解析查询结果
        BigDecimal totalSalesToday =  result != null ? (BigDecimal) result.get("order_total") : BigDecimal.ZERO;
        return totalSalesToday;
    }

    @Override
    public MixinOrderDO getByNo(String no){
        MixinOrderExportReqVO mixinOrderExportReqVO = new MixinOrderExportReqVO();
//        mixinOrderExportReqVO.setSaleNo(no);
        List<MixinOrderDO> list = mixinOrderMapper.selectList(mixinOrderExportReqVO);
        if (!list.isEmpty()){
            return list.get(0);
        }else {
            throw exception(MIXIN_ORDER_NOT_EXISTS);
        }
    }

    @Override
    public Boolean updatebyIsPrint(Long id) {
        int i = mixinOrderMapper.updateById(new MixinOrderDO().setId(id).setIsPrint("1"));
        return i>0?true:false;
    }
}

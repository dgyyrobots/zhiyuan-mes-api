package com.dofast.module.channel.service.remark;

import com.dofast.framework.common.util.collection.CollectionUtils;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderDO;
import com.dofast.module.trade.dal.mysql.order.TradeOrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;

import java.util.*;

import com.dofast.module.channel.controller.admin.remark.vo.*;
import com.dofast.module.channel.dal.dataobject.remark.RemarkDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.channel.convert.remark.RemarkConvert;
import com.dofast.module.channel.dal.mysql.remark.RemarkMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.channel.enums.ErrorCodeConstants.*;

/**
 * 订单备注 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class RemarkServiceImpl implements RemarkService {

    @Resource
    private RemarkMapper remarkMapper;

    @Resource
    private TradeOrderMapper tradeOrderMapper;

    @Override
    public Long createRemark(RemarkCreateReqVO createReqVO) {
        if (createReqVO.getIsTop()) {
            Integer topSortCode = remarkMapper.getTopSortCode();
            createReqVO.setSortCode(topSortCode);
        }
        // 插入
        RemarkDO remark = RemarkConvert.INSTANCE.convert(createReqVO);
        remarkMapper.insert(remark);
        // 返回
        return remark.getId();
    }

    @Override
    public void updateRemark(RemarkUpdateReqVO updateReqVO) {
        // 校验存在
        validateRemarkExists(updateReqVO.getId());
        // 更新
        RemarkDO updateObj = RemarkConvert.INSTANCE.convert(updateReqVO);
        remarkMapper.updateById(updateObj);
    }

    @Override
    public void deleteRemark(Long id) {
        // 校验存在
        validateRemarkExists(id);
        // 删除
        remarkMapper.deleteById(id);
    }

    @Override
    public void toggleTopRemark(Long id) {
        RemarkDO remarkDO = remarkMapper.selectById(id);
        Integer sortCode = 0;
        if (remarkDO.getSortCode() <= 0) {
            sortCode = remarkMapper.getTopSortCode();
        }
        RemarkDO updateRemarkDO = new RemarkDO();
        updateRemarkDO.setId(remarkDO.getId());
        updateRemarkDO.setSortCode(sortCode);
        remarkMapper.updateById(updateRemarkDO);
    }

    private void validateRemarkExists(Long id) {
        if (remarkMapper.selectById(id) == null) {
            throw exception(REMARK_NOT_EXISTS);
        }
    }

    @Override
    public RemarkDO getRemark(Long id) {
        return remarkMapper.selectById(id);
    }

    @Override
    public List<RemarkDO> getRemarkList(Collection<Long> ids) {
        return remarkMapper.selectBatchIds(ids);
    }

    @Override
    public List<RemarkDO> getRemarkListByTradeOrder(Long id) {
        RemarkExportReqVO reqVO = new RemarkExportReqVO();
        reqVO.setTradeOrderId(id);
        List<RemarkDO> list = remarkMapper.selectList(reqVO);
        return list;
    }

    @Override
    public List<RemarkDO> getRemarkListBySalesOrder(Long id) {
        List<TradeOrderDO> list = tradeOrderMapper.selectList(TradeOrderDO::getMixinOrderId, id);
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        List<Long> orderIds = CollectionUtils.convertList(list, TradeOrderDO::getId);
        return remarkMapper.selectList(new LambdaQueryWrapperX<RemarkDO>()
                .in(RemarkDO::getTradeOrderId, orderIds).or().eq(RemarkDO::getSalId, id)
                .orderByAsc(RemarkDO::getTradeOrderId)
                .orderByDesc(RemarkDO::getSortCode)
        );
    }

    @Override
    public PageResult<RemarkDO> getRemarkPage(RemarkPageReqVO pageReqVO) {
        return remarkMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RemarkDO> getRemarkList(RemarkExportReqVO exportReqVO) {
        return remarkMapper.selectList(exportReqVO);
    }

}

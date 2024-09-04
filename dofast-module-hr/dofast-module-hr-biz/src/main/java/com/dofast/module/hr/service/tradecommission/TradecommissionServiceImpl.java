package com.dofast.module.hr.service.tradecommission;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.hr.controller.admin.tradecommission.vo.*;
import com.dofast.module.hr.dal.dataobject.tradecommission.TradecommissionDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.hr.convert.tradecommission.TradecommissionConvert;
import com.dofast.module.hr.dal.mysql.tradecommission.TradecommissionMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.hr.enums.ErrorCodeConstants.*;

/**
 * 工资提成 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class TradecommissionServiceImpl implements TradecommissionService {

    @Resource
    private TradecommissionMapper tradecommissionMapper;

    @Override
    public Integer createTradecommission(TradecommissionCreateReqVO createReqVO) {
        // 插入
        TradecommissionDO tradecommission = TradecommissionConvert.INSTANCE.convert(createReqVO);
        tradecommissionMapper.insert(tradecommission);
        // 返回
        return tradecommission.getId();
    }

    @Override
    public void updateTradecommission(TradecommissionUpdateReqVO updateReqVO) {
        // 校验存在
        validateTradecommissionExists(updateReqVO.getId());
        // 更新
        TradecommissionDO updateObj = TradecommissionConvert.INSTANCE.convert(updateReqVO);
        tradecommissionMapper.updateById(updateObj);
    }

    @Override
    public void deleteTradecommission(Integer id) {
        // 校验存在
        validateTradecommissionExists(id);
        // 删除
        tradecommissionMapper.deleteById(id);
    }

    private void validateTradecommissionExists(Integer id) {
        if (tradecommissionMapper.selectById(id) == null) {
            throw exception(TRADECOMMISSION_NOT_EXISTS);
        }
    }

    @Override
    public TradecommissionDO getTradecommission(Integer id) {
        return tradecommissionMapper.selectById(id);
    }

    @Override
    public List<TradecommissionDO> getTradecommissionList(Collection<Integer> ids) {
        return tradecommissionMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<TradecommissionDO> getTradecommissionPage(TradecommissionPageReqVO pageReqVO) {
        return tradecommissionMapper.selectPage(pageReqVO);
    }

    @Override
    public List<TradecommissionDO> getTradecommissionList(TradecommissionExportReqVO exportReqVO) {
        return tradecommissionMapper.selectList(exportReqVO);
    }

}

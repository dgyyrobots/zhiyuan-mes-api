package com.dofast.module.purchase.service.retreatGoods;

import com.dofast.module.purchase.dal.dataobject.retreatGoods.RetreatGoodsDO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.purchase.controller.admin.retreatGoods.vo.*;
import com.dofast.module.purchase.dal.dataobject.retreatGoods.RetreatGoodsDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.purchase.convert.retreatGoods.RetreatGoodsConvert;
import com.dofast.module.purchase.dal.mysql.retreatGoods.RetreatGoodsMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.purchase.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * ERP仓退单单身 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class RetreatGoodsServiceImpl implements RetreatGoodsService {

    @Resource
    private RetreatGoodsMapper retreatGoodsMapper;

    @Override
    public Integer createGoods(RetreatGoodsCreateReqVO createReqVO) {
        // 插入
        RetreatGoodsDO goods = RetreatGoodsConvert.INSTANCE.convert(createReqVO);
        retreatGoodsMapper.insert(goods);
        // 返回
        return goods.getId();
    }

    @Override
    public void updateGoods(RetreatGoodsUpdateReqVO updateReqVO) {
        // 校验存在
        validateGoodsExists(updateReqVO.getId());
        // 更新
        RetreatGoodsDO updateObj = RetreatGoodsConvert.INSTANCE.convert(updateReqVO);
        retreatGoodsMapper.updateById(updateObj);
    }

    @Override
    public void deleteGoods(Integer id) {
        // 校验存在
        validateGoodsExists(id);
        // 删除
        retreatGoodsMapper.deleteById(id);
    }

    private void validateGoodsExists(Integer id) {
        if (retreatGoodsMapper.selectById(id) == null) {
            throw exception(GOODS_NOT_EXISTS);
        }
    }

    @Override
    public RetreatGoodsDO getGoods(Integer id) {
        return retreatGoodsMapper.selectById(id);
    }

    @Override
    public List<RetreatGoodsDO> getGoodsList(Collection<Integer> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return retreatGoodsMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RetreatGoodsDO> getGoodsPage(RetreatGoodsPageReqVO pageReqVO) {
        return retreatGoodsMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RetreatGoodsDO> getGoodsList(RetreatGoodsExportReqVO exportReqVO) {
        return retreatGoodsMapper.selectList(exportReqVO);
    }

    @Override
    public  void updateBatch(List<RetreatGoodsDO> retreatGoodsDO){
        retreatGoodsMapper.updateBatch(retreatGoodsDO);
    }

    @Override
    public  void insertBatch(List<RetreatGoodsDO> retreatGoodsDO){
        retreatGoodsMapper.insertBatch(retreatGoodsDO);
    }

}

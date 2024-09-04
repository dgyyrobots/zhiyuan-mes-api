package com.dofast.module.purchase.service.goods;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.purchase.controller.admin.goods.vo.*;
import com.dofast.module.purchase.dal.dataobject.goods.GoodsDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.purchase.convert.goods.GoodsConvert;
import com.dofast.module.purchase.dal.mysql.goods.GoodsMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.purchase.enums.ErrorCodeConstants.*;

/**
 * 采购商品明细 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public Integer createGoods(GoodsCreateReqVO createReqVO) {
        // 插入
        GoodsDO goods = GoodsConvert.INSTANCE.convert(createReqVO);
        goodsMapper.insert(goods);
        // 返回
        return goods.getId();
    }

    @Override
    public void updateGoods(GoodsUpdateReqVO updateReqVO) {
        // 校验存在
        validateGoodsExists(updateReqVO.getId());
        // 更新
        GoodsDO updateObj = GoodsConvert.INSTANCE.convert(updateReqVO);
        goodsMapper.updateById(updateObj);
    }

    @Override
    public void deleteGoods(Integer id) {
        // 校验存在
        validateGoodsExists(id);
        // 删除
        goodsMapper.deleteById(id);
    }

    private void validateGoodsExists(Integer id) {
        if (goodsMapper.selectById(id) == null) {
            throw exception(GOODS_NOT_EXISTS);
        }
    }

    @Override
    public GoodsDO getGoods(Integer id) {
        return goodsMapper.selectById(id);
    }

    @Override
    public List<GoodsDO> getGoodsList(Collection<Integer> ids) {
        return goodsMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<GoodsDO> getGoodsPage(GoodsPageReqVO pageReqVO) {
        return goodsMapper.selectPage(pageReqVO);
    }

    @Override
    public List<GoodsDO> getGoodsList(GoodsExportReqVO exportReqVO) {
        return goodsMapper.selectList(exportReqVO);
    }

}

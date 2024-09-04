package com.dofast.module.wms.service.itemconsumeline;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.itemconsumeline.vo.*;
import com.dofast.module.wms.dal.dataobject.itemconsumeline.ItemConsumeLineDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.itemconsumeline.ItemConsumeLineConvert;
import com.dofast.module.wms.dal.mysql.itemconsumeline.ItemConsumeLineMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 物料消耗记录行 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class ItemConsumeLineServiceImpl implements ItemConsumeLineService {

    @Resource
    private ItemConsumeLineMapper itemConsumeLineMapper;

    @Override
    public Long createItemConsumeLine(ItemConsumeLineCreateReqVO createReqVO) {
        // 插入
        ItemConsumeLineDO itemConsumeLine = ItemConsumeLineConvert.INSTANCE.convert(createReqVO);
        itemConsumeLineMapper.insert(itemConsumeLine);
        // 返回
        return itemConsumeLine.getId();
    }

    @Override
    public void updateItemConsumeLine(ItemConsumeLineUpdateReqVO updateReqVO) {
        // 校验存在
        validateItemConsumeLineExists(updateReqVO.getId());
        // 更新
        ItemConsumeLineDO updateObj = ItemConsumeLineConvert.INSTANCE.convert(updateReqVO);
        itemConsumeLineMapper.updateById(updateObj);
    }

    @Override
    public void deleteItemConsumeLine(Long id) {
        // 校验存在
        validateItemConsumeLineExists(id);
        // 删除
        itemConsumeLineMapper.deleteById(id);
    }

    private void validateItemConsumeLineExists(Long id) {
        if (itemConsumeLineMapper.selectById(id) == null) {
            throw exception(ITEM_CONSUME_LINE_NOT_EXISTS);
        }
    }

    @Override
    public ItemConsumeLineDO getItemConsumeLine(Long id) {
        return itemConsumeLineMapper.selectById(id);
    }

    @Override
    public List<ItemConsumeLineDO> getItemConsumeLineList(Collection<Long> ids) {
        return itemConsumeLineMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ItemConsumeLineDO> getItemConsumeLinePage(ItemConsumeLinePageReqVO pageReqVO) {
        return itemConsumeLineMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ItemConsumeLineDO> getItemConsumeLineList(ItemConsumeLineExportReqVO exportReqVO) {
        return itemConsumeLineMapper.selectList(exportReqVO);
    }

}

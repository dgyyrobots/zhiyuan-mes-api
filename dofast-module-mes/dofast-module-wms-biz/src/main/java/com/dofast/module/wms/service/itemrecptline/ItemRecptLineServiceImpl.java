package com.dofast.module.wms.service.itemrecptline;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.itemrecptline.vo.*;
import com.dofast.module.wms.dal.dataobject.itemrecptline.ItemRecptLineDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.itemrecptline.ItemRecptLineConvert;
import com.dofast.module.wms.dal.mysql.itemrecptline.ItemRecptLineMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 物料入库单行 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ItemRecptLineServiceImpl implements ItemRecptLineService {

    @Resource
    private ItemRecptLineMapper itemRecptLineMapper;

    @Override
    public int deleteByRecptId(Long recptId) {
        return itemRecptLineMapper.deleteByRecptId(recptId);
    }

    @Override
    public List<ItemRecptLineDO> getItemRecptLineList(ItemRecptLineListVO lineListVO) {
        return itemRecptLineMapper.selectList(lineListVO);
    }

    @Override
    public Long createItemRecptLine(ItemRecptLineCreateReqVO createReqVO) {
        // 插入
        ItemRecptLineDO itemRecptLine = ItemRecptLineConvert.INSTANCE.convert(createReqVO);
        itemRecptLineMapper.insert(itemRecptLine);
        // 返回
        return itemRecptLine.getId();
    }

    @Override
    public void updateItemRecptLine(ItemRecptLineUpdateReqVO updateReqVO) {
        // 校验存在
        validateItemRecptLineExists(updateReqVO.getId());
        // 更新
        ItemRecptLineDO updateObj = ItemRecptLineConvert.INSTANCE.convert(updateReqVO);
        itemRecptLineMapper.updateById(updateObj);
    }

    @Override
    public void deleteItemRecptLine(Long id) {
        // 校验存在
        validateItemRecptLineExists(id);
        // 删除
        itemRecptLineMapper.deleteById(id);
    }

    private void validateItemRecptLineExists(Long id) {
        if (itemRecptLineMapper.selectById(id) == null) {
            throw exception(ITEM_RECPT_LINE_NOT_EXISTS);
        }
    }

    @Override
    public ItemRecptLineDO getItemRecptLine(Long id) {
        return itemRecptLineMapper.selectById(id);
    }

    @Override
    public List<ItemRecptLineDO> getItemRecptLineList(Collection<Long> ids) {
        return itemRecptLineMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ItemRecptLineDO> getItemRecptLinePage(ItemRecptLinePageReqVO pageReqVO) {
        return itemRecptLineMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ItemRecptLineDO> getItemRecptLineList(ItemRecptLineExportReqVO exportReqVO) {
        return itemRecptLineMapper.selectList(exportReqVO);
    }

}

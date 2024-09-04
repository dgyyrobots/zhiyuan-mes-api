package com.dofast.module.system.service.expresstemplateitem;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.system.controller.admin.expresstemplateitem.vo.ExpressTemplateItemCreateReqVO;
import com.dofast.module.system.controller.admin.expresstemplateitem.vo.ExpressTemplateItemExportReqVO;
import com.dofast.module.system.controller.admin.expresstemplateitem.vo.ExpressTemplateItemPageReqVO;
import com.dofast.module.system.controller.admin.expresstemplateitem.vo.ExpressTemplateItemUpdateReqVO;
import com.dofast.module.system.convert.expresstemplateitem.ExpressTemplateItemConvert;
import com.dofast.module.system.dal.dataobject.expresstemplateitem.ExpressTemplateItemDO;
import com.dofast.module.system.dal.mysql.expresstemplateitem.ExpressTemplateItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.system.enums.ErrorCodeConstants.EXPRESS_TEMPLATE_ITEM_NOT_EXISTS;

/**
 * 运费模板细节 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class ExpressTemplateItemServiceImpl implements ExpressTemplateItemService {

    @Resource
    private ExpressTemplateItemMapper expressTemplateItemMapper;

    @Override
    public Long createExpressTemplateItem(ExpressTemplateItemCreateReqVO createReqVO) {
        // 插入
        ExpressTemplateItemDO expressTemplateItem = ExpressTemplateItemConvert.INSTANCE.convert(createReqVO);
        expressTemplateItemMapper.insert(expressTemplateItem);
        // 返回
        return expressTemplateItem.getId();
    }

    @Override
    public void updateExpressTemplateItem(ExpressTemplateItemUpdateReqVO updateReqVO) {
        // 校验存在
        validateExpressTemplateItemExists(updateReqVO.getId());
        // 更新
        ExpressTemplateItemDO updateObj = ExpressTemplateItemConvert.INSTANCE.convert(updateReqVO);
        expressTemplateItemMapper.updateById(updateObj);
    }

    @Override
    public void deleteExpressTemplateItem(Long id) {
        // 校验存在
        validateExpressTemplateItemExists(id);
        // 删除
        expressTemplateItemMapper.deleteById(id);
    }

    private void validateExpressTemplateItemExists(Long id) {
        if (expressTemplateItemMapper.selectById(id) == null) {
            throw exception(EXPRESS_TEMPLATE_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public ExpressTemplateItemDO getExpressTemplateItem(Long id) {
        return expressTemplateItemMapper.selectById(id);
    }

    @Override
    public List<ExpressTemplateItemDO> getExpressTemplateItemList(Collection<Long> ids) {
        return expressTemplateItemMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ExpressTemplateItemDO> getExpressTemplateItemPage(ExpressTemplateItemPageReqVO pageReqVO) {
        return expressTemplateItemMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ExpressTemplateItemDO> getExpressTemplateItemList(ExpressTemplateItemExportReqVO exportReqVO) {
        return expressTemplateItemMapper.selectList(exportReqVO);
    }

}

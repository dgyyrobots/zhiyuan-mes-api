package com.dofast.module.system.service.form;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.system.controller.admin.form.vo.*;
import com.dofast.module.system.dal.dataobject.form.FormItemDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.system.convert.form.FormItemConvert;
import com.dofast.module.system.dal.mysql.form.FormItemMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.system.enums.ErrorCodeConstants.*;

/**
 * 字段 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class FormItemServiceImpl implements FormItemService {

    @Resource
    private FormItemMapper formItemMapper;

    @Override
    public Integer createFormItem(FormItemCreateReqVO createReqVO) {
        // 插入
        FormItemDO formItem = FormItemConvert.INSTANCE.convert(createReqVO);
        formItemMapper.insert(formItem);
        // 返回
        return formItem.getId();
    }

    @Override
    public void updateFormItem(FormItemUpdateReqVO updateReqVO) {
        // 校验存在
        validateFormItemExists(updateReqVO.getId());
        // 更新
        FormItemDO updateObj = FormItemConvert.INSTANCE.convert(updateReqVO);
        formItemMapper.updateById(updateObj);
    }

    @Override
    public void deleteFormItem(Integer id) {
        // 校验存在
        validateFormItemExists(id);
        // 删除
        formItemMapper.deleteById(id);
    }

    private void validateFormItemExists(Integer id) {
        if (formItemMapper.selectById(id) == null) {
            throw exception(FORM_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public FormItemDO getFormItem(Integer id) {
        return formItemMapper.selectById(id);
    }

    @Override
    public List<FormItemDO> getFormItemList(Collection<Integer> ids, Long formId) {
        if (ids == null){
            FormItemExportReqVO req = new FormItemExportReqVO();
            req.setFormId(formId);
            return formItemMapper.selectList(req);
        } else {
            return formItemMapper.selectBatchIds(ids);
        }
    }

    @Override
    public PageResult<FormItemDO> getFormItemPage(FormItemPageReqVO pageReqVO) {
        return formItemMapper.selectPage(pageReqVO);
    }

    @Override
    public List<FormItemDO> getFormItemList(FormItemExportReqVO exportReqVO) {
        return formItemMapper.selectList(exportReqVO);
    }

}

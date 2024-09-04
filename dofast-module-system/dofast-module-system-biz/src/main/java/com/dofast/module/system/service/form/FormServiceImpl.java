package com.dofast.module.system.service.form;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.system.controller.admin.form.vo.*;
import com.dofast.module.system.dal.dataobject.form.FormDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.system.convert.form.FormConvert;
import com.dofast.module.system.dal.mysql.form.FormAliasMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.system.enums.ErrorCodeConstants.*;

/**
 * 系统表单 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class FormServiceImpl implements FormService {

    @Resource
    private FormAliasMapper formAliasMapper;

    @Override
    public Integer createForm(FormCreateReqVO createReqVO) {
        // 插入
        FormDO form = FormConvert.INSTANCE.convert(createReqVO);
        formAliasMapper.insert(form);
        // 返回
        return form.getId();
    }

    @Override
    public void updateForm(FormUpdateReqVO updateReqVO) {
        // 校验存在
        validateFormExists(updateReqVO.getId());
        // 更新
        FormDO updateObj = FormConvert.INSTANCE.convert(updateReqVO);
        formAliasMapper.updateById(updateObj);
    }

    @Override
    public void deleteForm(Integer id) {
        // 校验存在
        validateFormExists(id);
        // 删除
        formAliasMapper.deleteById(id);
    }

    private void validateFormExists(Integer id) {
        if (formAliasMapper.selectById(id) == null) {
            throw exception(FORM_NOT_EXISTS);
        }
    }

    @Override
    public FormDO getForm(Integer id) {
        return formAliasMapper.selectById(id);
    }

    @Override
    public List<FormDO> getFormList(Collection<Integer> ids) {
        return formAliasMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<FormDO> getFormPage(FormPageReqVO pageReqVO) {
        return formAliasMapper.selectPage(pageReqVO);
    }

    @Override
    public List<FormDO> getFormList(FormExportReqVO exportReqVO) {
        return formAliasMapper.selectList(exportReqVO);
    }

}

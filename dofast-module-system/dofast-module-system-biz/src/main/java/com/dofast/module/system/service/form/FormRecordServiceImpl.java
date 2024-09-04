package com.dofast.module.system.service.form;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.system.controller.admin.form.vo.*;
import com.dofast.module.system.dal.dataobject.form.FormRecordDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.system.convert.form.FormRecordConvert;
import com.dofast.module.system.dal.mysql.form.FormRecordMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.system.enums.ErrorCodeConstants.*;

/**
 * 表单历史 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class FormRecordServiceImpl implements FormRecordService {

    @Resource
    private FormRecordMapper formRecordMapper;

    @Override
    public Long createFormRecord(FormRecordCreateReqVO createReqVO) {
        // 插入
        FormRecordDO formRecord = FormRecordConvert.INSTANCE.convert(createReqVO);
        formRecordMapper.insert(formRecord);
        // 返回
        return formRecord.getId();
    }

    @Override
    public void updateFormRecord(FormRecordUpdateReqVO updateReqVO) {
        // 校验存在
        validateFormRecordExists(updateReqVO.getId());
        // 更新
        FormRecordDO updateObj = FormRecordConvert.INSTANCE.convert(updateReqVO);
        formRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteFormRecord(Long id) {
        // 校验存在
        validateFormRecordExists(id);
        // 删除
        formRecordMapper.deleteById(id);
    }

    private void validateFormRecordExists(Long id) {
        if (formRecordMapper.selectById(id) == null) {
            throw exception(FORM_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public FormRecordDO getFormRecord(Long id) {
        return formRecordMapper.selectById(id);
    }

    @Override
    public List<FormRecordDO> getFormRecordList(Collection<Long> ids) {
        return formRecordMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<FormRecordDO> getFormRecordPage(FormRecordPageReqVO pageReqVO) {
        return formRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public List<FormRecordDO> getFormRecordList(FormRecordExportReqVO exportReqVO) {
        return formRecordMapper.selectList(exportReqVO);
    }

}

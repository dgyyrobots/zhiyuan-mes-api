package com.dofast.module.bpm.service.definition;

import cn.hutool.core.lang.Assert;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.util.json.JsonUtils;
import com.dofast.module.bpm.controller.admin.definition.vo.form.BpmFormCreateReqVO;
import com.dofast.module.bpm.controller.admin.definition.vo.form.BpmFormPageReqVO;
import com.dofast.module.bpm.controller.admin.definition.vo.form.BpmFormUpdateReqVO;
import com.dofast.module.bpm.convert.definition.BpmFormConvert;
import com.dofast.module.bpm.dal.dataobject.definition.BpmFormDO;
import com.dofast.module.bpm.dal.mysql.definition.BpmFormMapper;
import com.dofast.module.bpm.enums.ErrorCodeConstants;
import com.dofast.module.bpm.enums.definition.BpmModelFormTypeEnum;
import com.dofast.module.bpm.service.definition.dto.BpmFormFieldRespDTO;
import com.dofast.module.bpm.service.definition.dto.BpmModelMetaInfoRespDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.*;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.bpm.enums.ErrorCodeConstants.*;

/**
 * 动态表单 Service 实现类
 *
 * @author 风里雾里
 */
@Service
@Validated
public class BpmFormServiceImpl implements BpmFormService {

    @Resource
    private BpmFormMapper formMapper;

    @Override
    public Long createForm(BpmFormCreateReqVO createReqVO) {
        this.checkFields(createReqVO.getFields());
        // 插入
        BpmFormDO form = BpmFormConvert.INSTANCE.convert(createReqVO);
        formMapper.insert(form);
        // 返回
        return form.getId();
    }

    @Override
    public void updateForm(BpmFormUpdateReqVO updateReqVO) {
        this.checkFields(updateReqVO.getFields());
        // 校验存在
        this.validateFormExists(updateReqVO.getId());
        // 更新
        BpmFormDO updateObj = BpmFormConvert.INSTANCE.convert(updateReqVO);
        formMapper.updateById(updateObj);
    }

    @Override
    public void deleteForm(Long id) {
        // 校验存在
        this.validateFormExists(id);
        // 删除
        formMapper.deleteById(id);
    }

    private void validateFormExists(Long id) {
        if (formMapper.selectById(id) == null) {
            throw exception(ErrorCodeConstants.FORM_NOT_EXISTS);
        }
    }

    @Override
    public BpmFormDO getForm(Long id) {
        return formMapper.selectById(id);
    }

    @Override
    public List<BpmFormDO> getFormList() {
        return formMapper.selectList();
    }

    @Override
    public List<BpmFormDO> getFormList(Collection<Long> ids) {
        return formMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BpmFormDO> getFormPage(BpmFormPageReqVO pageReqVO) {
        return formMapper.selectPage(pageReqVO);
    }


    @Override
    public BpmFormDO checkFormConfig(String configStr) {
        BpmModelMetaInfoRespDTO metaInfo = JsonUtils.parseObject(configStr, BpmModelMetaInfoRespDTO.class);
        if (metaInfo == null || metaInfo.getFormType() == null) {
            throw exception(MODEL_DEPLOY_FAIL_FORM_NOT_CONFIG);
        }
        // 校验表单存在
        if (Objects.equals(metaInfo.getFormType(), BpmModelFormTypeEnum.NORMAL.getType())) {
            BpmFormDO form = getForm(metaInfo.getFormId());
            if (form == null) {
                throw exception(FORM_NOT_EXISTS);
            }
            return form;
        }
        return null;
    }

    /**
     * 校验 Field，避免 field 重复
     *
     * @param fields field 数组
     */
    private void checkFields(List<String> fields) {
        if (true) { // TODO 芋艿：兼容 Vue3 工作流：因为采用了新的表单设计器，所以暂时不校验
            return;
        }
        Map<String, String> fieldMap = new HashMap<>(); // key 是 vModel，value 是 label
        for (String field : fields) {
            BpmFormFieldRespDTO fieldDTO = JsonUtils.parseObject(field, BpmFormFieldRespDTO.class);
            Assert.notNull(fieldDTO);
            String oldLabel = fieldMap.put(fieldDTO.getVModel(), fieldDTO.getLabel());
            // 如果不存在，则直接返回
            if (oldLabel == null) {
                continue;
            }
            // 如果存在，则报错
            throw exception(ErrorCodeConstants.FORM_FIELD_REPEAT, oldLabel, fieldDTO.getLabel(), fieldDTO.getVModel());
        }
    }

}

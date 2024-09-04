package com.dofast.module.mes.service.Autocoderule;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.Autocoderule.vo.*;
import com.dofast.module.mes.dal.dataobject.Autocoderule.AutoCodeRuleDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.Autocoderule.AutoCodeRuleConvert;
import com.dofast.module.mes.dal.mysql.Autocoderule.AutoCodeRuleMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;

/**
 * 编码生成规则 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AutoCodeRuleServiceImpl implements AutoCodeRuleService {

    @Resource
    private AutoCodeRuleMapper autoCodeRuleMapper;

    @Override
    public String checkRuleCodeUnique(String code,Long id) {
        AutoCodeRuleDO rule = autoCodeRuleMapper.checkRuleCodeUnique(code);
        if (StrUtils.isNotNull(rule) && rule.getId().longValue() != id.longValue())
        {
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public String checkRuleNameUnique(String name,Long id) {
        AutoCodeRuleDO rule = autoCodeRuleMapper.checkRuleNameUnique(name);
        if (StrUtils.isNotNull(rule) && rule.getId().longValue() != id.longValue())
        {
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createAutoCodeRule(AutoCodeRuleCreateReqVO createReqVO) {
        // 插入
        AutoCodeRuleDO autoCodeRule = AutoCodeRuleConvert.INSTANCE.convert(createReqVO);
        autoCodeRuleMapper.insert(autoCodeRule);
        // 返回
        return autoCodeRule.getId();
    }

    @Override
    public void updateAutoCodeRule(AutoCodeRuleUpdateReqVO updateReqVO) {
        // 校验存在
        validateAutoCodeRuleExists(updateReqVO.getId());
        // 更新
        AutoCodeRuleDO updateObj = AutoCodeRuleConvert.INSTANCE.convert(updateReqVO);
        autoCodeRuleMapper.updateById(updateObj);
    }

    @Override
    public void deleteAutoCodeRule(Long id) {
        // 校验存在
        validateAutoCodeRuleExists(id);
        // 删除
        autoCodeRuleMapper.deleteById(id);
    }

    private void validateAutoCodeRuleExists(Long id) {
        if (autoCodeRuleMapper.selectById(id) == null) {
            throw exception(AUTO_CODE_RULE_NOT_EXISTS);
        }
    }

    @Override
    public AutoCodeRuleDO getAutoCodeRule(Long id) {
        return autoCodeRuleMapper.selectById(id);
    }

    @Override
    public List<AutoCodeRuleDO> getAutoCodeRuleList(Collection<Long> ids) {
        return autoCodeRuleMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<AutoCodeRuleDO> getAutoCodeRulePage(AutoCodeRulePageReqVO pageReqVO) {
        return autoCodeRuleMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AutoCodeRuleDO> getAutoCodeRuleList(AutoCodeRuleExportReqVO exportReqVO) {
        return autoCodeRuleMapper.selectList(exportReqVO);
    }

    @Override
    public List<AutoCodeRuleDO> getAutoCodeRuleList(AutoCodeRuleListVO listVO) {
        return autoCodeRuleMapper.selectList(listVO);
    }

    @Override
    public AutoCodeRuleDO getOne(String ruleCode) {
        AutoCodeRuleListVO listVO = new AutoCodeRuleListVO();
        listVO.setRuleCode(ruleCode);
        List<AutoCodeRuleDO> autoCodeRuleDOS = getAutoCodeRuleList(listVO);
        if(autoCodeRuleDOS!=null && !autoCodeRuleDOS.isEmpty()){
            return autoCodeRuleDOS.get(0);
        }
        return null;
    }

}

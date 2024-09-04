package com.dofast.module.mes.service.Autocoderule;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.Autocoderule.vo.*;
import com.dofast.module.mes.dal.dataobject.Autocoderule.AutoCodeRuleDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 编码生成规则 Service 接口
 *
 * @author 芋道源码
 */
public interface AutoCodeRuleService {
    public String checkRuleCodeUnique(String code,Long id);

    public String checkRuleNameUnique(String name,Long id);
    /**
     * 创建编码生成规则
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAutoCodeRule(@Valid AutoCodeRuleCreateReqVO createReqVO);

    /**
     * 更新编码生成规则
     *
     * @param updateReqVO 更新信息
     */
    void updateAutoCodeRule(@Valid AutoCodeRuleUpdateReqVO updateReqVO);

    /**
     * 删除编码生成规则
     *
     * @param id 编号
     */
    void deleteAutoCodeRule(Long id);

    /**
     * 获得编码生成规则
     *
     * @param id 编号
     * @return 编码生成规则
     */
    AutoCodeRuleDO getAutoCodeRule(Long id);

    /**
     * 获得编码生成规则列表
     *
     * @param ids 编号
     * @return 编码生成规则列表
     */
    List<AutoCodeRuleDO> getAutoCodeRuleList(Collection<Long> ids);

    /**
     * 获得编码生成规则分页
     *
     * @param pageReqVO 分页查询
     * @return 编码生成规则分页
     */
    PageResult<AutoCodeRuleDO> getAutoCodeRulePage(AutoCodeRulePageReqVO pageReqVO);

    /**
     * 获得编码生成规则列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 编码生成规则列表
     */
    List<AutoCodeRuleDO> getAutoCodeRuleList(AutoCodeRuleExportReqVO exportReqVO);

    List<AutoCodeRuleDO> getAutoCodeRuleList(AutoCodeRuleListVO listVO);

    AutoCodeRuleDO getOne(String ruleCode);

}

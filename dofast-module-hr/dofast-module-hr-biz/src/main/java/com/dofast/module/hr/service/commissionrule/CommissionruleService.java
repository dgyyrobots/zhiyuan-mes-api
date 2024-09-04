package com.dofast.module.hr.service.commissionrule;

import java.util.*;
import javax.validation.*;
import com.dofast.module.hr.controller.admin.commissionrule.vo.*;
import com.dofast.module.hr.dal.dataobject.commissionrule.CommissionruleDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 提成规则 Service 接口
 *
 * @author 惠智造
 */
public interface CommissionruleService {

    /**
     * 创建提成规则
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createCommissionrule(@Valid CommissionruleCreateReqVO createReqVO);

    /**
     * 更新提成规则
     *
     * @param updateReqVO 更新信息
     */
    void updateCommissionrule(@Valid CommissionruleUpdateReqVO updateReqVO);

    /**
     * 删除提成规则
     *
     * @param id 编号
     */
    void deleteCommissionrule(Integer id);

    /**
     * 获得提成规则
     *
     * @param id 编号
     * @return 提成规则
     */
    CommissionruleDO getCommissionrule(Integer id);

    /**
     * 获得提成规则列表
     *
     * @param ids 编号
     * @return 提成规则列表
     */
    List<CommissionruleDO> getCommissionruleList(Collection<Integer> ids);

    /**
     * 获得提成规则分页
     *
     * @param pageReqVO 分页查询
     * @return 提成规则分页
     */
    PageResult<CommissionruleDO> getCommissionrulePage(CommissionrulePageReqVO pageReqVO);

    /**
     * 获得提成规则列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 提成规则列表
     */
    List<CommissionruleDO> getCommissionruleList(CommissionruleExportReqVO exportReqVO);

}

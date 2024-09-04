package com.dofast.module.hr.service.salarycommission;

import java.util.*;
import javax.validation.*;
import com.dofast.module.hr.controller.admin.salarycommission.vo.*;
import com.dofast.module.hr.dal.dataobject.salarycommission.SalarycommissionDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 绩效工资 Service 接口
 *
 * @author 惠智造
 */
public interface SalarycommissionService {

    /**
     * 创建绩效工资
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createSalarycommission(@Valid SalarycommissionCreateReqVO createReqVO);

    /**
     * 更新绩效工资
     *
     * @param updateReqVO 更新信息
     */
    void updateSalarycommission(@Valid SalarycommissionUpdateReqVO updateReqVO);

    /**
     * 删除绩效工资
     *
     * @param id 编号
     */
    void deleteSalarycommission(Integer id);

    /**
     * 获得绩效工资
     *
     * @param id 编号
     * @return 绩效工资
     */
    SalarycommissionDO getSalarycommission(Integer id);

    /**
     * 获得绩效工资列表
     *
     * @param ids 编号
     * @return 绩效工资列表
     */
    List<SalarycommissionDO> getSalarycommissionList(Collection<Integer> ids);

    /**
     * 获得绩效工资分页
     *
     * @param pageReqVO 分页查询
     * @return 绩效工资分页
     */
    PageResult<SalarycommissionDO> getSalarycommissionPage(SalarycommissionPageReqVO pageReqVO);

    /**
     * 获得绩效工资列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 绩效工资列表
     */
    List<SalarycommissionDO> getSalarycommissionList(SalarycommissionExportReqVO exportReqVO);

}

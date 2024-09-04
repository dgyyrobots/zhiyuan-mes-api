package com.dofast.module.hr.service.salary;

import java.util.*;
import javax.validation.*;
import com.dofast.module.hr.controller.admin.salary.vo.*;
import com.dofast.module.hr.dal.dataobject.salary.SalaryDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 工资总 Service 接口
 *
 * @author 惠智造
 */
public interface SalaryService {

    /**
     * 创建工资总
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createSalary(@Valid SalaryCreateReqVO createReqVO);

    /**
     * 更新工资总
     *
     * @param updateReqVO 更新信息
     */
    void updateSalary(@Valid SalaryUpdateReqVO updateReqVO);

    /**
     * 删除工资总
     *
     * @param id 编号
     */
    void deleteSalary(Integer id);

    /**
     * 获得工资总
     *
     * @param id 编号
     * @return 工资总
     */
    SalaryDO getSalary(Integer id);

    /**
     * 获得工资总列表
     *
     * @param ids 编号
     * @return 工资总列表
     */
    List<SalaryDO> getSalaryList(Collection<Integer> ids);

    /**
     * 获得工资总分页
     *
     * @param pageReqVO 分页查询
     * @return 工资总分页
     */
    PageResult<SalaryDO> getSalaryPage(SalaryPageReqVO pageReqVO);

    /**
     * 获得工资总列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 工资总列表
     */
    List<SalaryDO> getSalaryList(SalaryExportReqVO exportReqVO);

}

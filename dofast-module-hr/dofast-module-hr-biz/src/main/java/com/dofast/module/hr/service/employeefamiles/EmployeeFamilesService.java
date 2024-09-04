package com.dofast.module.hr.service.employeefamiles;

import java.util.*;
import javax.validation.*;
import com.dofast.module.hr.controller.admin.employeefamiles.vo.*;
import com.dofast.module.hr.dal.dataobject.employeefamiles.EmployeeFamilesDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 员工家庭成员 Service 接口
 *
 * @author 惠智造
 */
public interface EmployeeFamilesService {

    /**
     * 创建员工家庭成员
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmployeeFamiles(@Valid EmployeeFamilesCreateReqVO createReqVO);


    /**
     * 创建员工家庭成员
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmployeeFamilesMy(@Valid EmployeeFamilesCreateReqVO createReqVO);

    /**
     * 更新员工家庭成员
     *
     * @param updateReqVO 更新信息
     */
    void updateEmployeeFamiles(@Valid EmployeeFamilesUpdateReqVO updateReqVO);


    /**
     * 更新员工家庭成员
     *
     * @param updateReqVO 更新信息
     */
    void updateEmployeeFamilesMy(@Valid EmployeeFamilesUpdateReqVO updateReqVO);

    /**
     * 删除员工家庭成员
     *
     * @param id 编号
     */
    void deleteEmployeeFamiles(Long id);

    /**
     * 删除员工家庭成员
     *
     * @param id 编号
     */
    void deleteEmployeeFamilesMy(Long id);

    /**
     * 获得员工家庭成员
     *
     * @param id 编号
     * @return 员工家庭成员
     */
    EmployeeFamilesDO getEmployeeFamiles(Long id);


    /**
     * 获得员工家庭成员
     *
     * @param id 编号
     * @return 员工家庭成员
     */
    List<EmployeeFamilesDO> getEmployeeFamilesMy(Long id);

    /**
     * 获得员工家庭成员列表
     *
     * @param ids 编号
     * @return 员工家庭成员列表
     */
    List<EmployeeFamilesDO> getEmployeeFamilesList(Collection<Integer> ids);

    /**
     * 获得员工家庭成员分页
     *
     * @param pageReqVO 分页查询
     * @return 员工家庭成员分页
     */
    PageResult<EmployeeFamilesDO> getEmployeeFamilesPage(EmployeeFamilesPageReqVO pageReqVO);

    /**
     * 获得员工家庭成员列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 员工家庭成员列表
     */
    List<EmployeeFamilesDO> getEmployeeFamilesList(EmployeeFamilesExportReqVO exportReqVO);

}

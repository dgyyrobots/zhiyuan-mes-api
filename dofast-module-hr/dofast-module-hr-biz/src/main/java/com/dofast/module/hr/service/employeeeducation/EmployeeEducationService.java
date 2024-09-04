package com.dofast.module.hr.service.employeeeducation;

import java.util.*;
import javax.validation.*;
import com.dofast.module.hr.controller.admin.employeeeducation.vo.*;
import com.dofast.module.hr.dal.dataobject.employeeeducation.EmployeeEducationDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 员工教育培训经历 Service 接口
 *
 * @author 惠智造
 */
public interface EmployeeEducationService {

    /**
     * 创建员工教育培训经历
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmployeeEducation(@Valid EmployeeEducationCreateReqVO createReqVO);



    /**
     * 创建自己的员工教育培训经历
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmployeeEducationMy(@Valid EmployeeEducationCreateReqVO createReqVO);

    /**
     * 更新员工教育培训经历
     *
     * @param updateReqVO 更新信息
     */
    void updateEmployeeEducation(@Valid EmployeeEducationUpdateReqVO updateReqVO);


    /**
     * 更新自己的员工教育培训经历
     *
     * @param updateReqVO 更新信息
     */
    void updateEmployeeEducationMy(@Valid EmployeeEducationUpdateReqVO updateReqVO);

    /**
     * 删除员工教育培训经历
     *
     * @param id 编号
     */
    void deleteEmployeeEducation(Long id);

    /**
     * 删除员工教育培训经历
     *
     * @param id 编号
     */
    void deleteEmployeeEducationMy(Long id);

    /**
     * 获得员工教育培训经历
     *
     * @param id 编号
     * @return 员工教育培训经历
     */
    EmployeeEducationDO getEmployeeEducation(Long id);


    /**
     * 获得自己的员工教育培训经历
     *
     * @param id 编号
     * @return 员工教育培训经历
     */
    List<EmployeeEducationDO> getEmployeeEducationMy(Long id);

    /**
     * 获得员工教育培训经历列表
     *
     * @param ids 编号
     * @return 员工教育培训经历列表
     */
    List<EmployeeEducationDO> getEmployeeEducationList(Collection<Long> ids);

    /**
     * 获得员工教育培训经历分页
     *
     * @param pageReqVO 分页查询
     * @return 员工教育培训经历分页
     */
    PageResult<EmployeeEducationDO> getEmployeeEducationPage(EmployeeEducationPageReqVO pageReqVO);

    /**
     * 获得员工教育培训经历列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 员工教育培训经历列表
     */
    List<EmployeeEducationDO> getEmployeeEducationList(EmployeeEducationExportReqVO exportReqVO);

}

package com.dofast.module.hr.service.employeebasic;

import java.util.*;
import javax.validation.*;
import com.dofast.module.hr.controller.admin.employeebasic.vo.*;
import com.dofast.module.hr.dal.dataobject.employeebasic.EmployeeBasicDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 员工基本信息 Service 接口
 *
 * @author 惠智造
 */
public interface EmployeeBasicService {

    /**
     * 创建员工基本信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmployeeBasic(@Valid EmployeeBasicCreateReqVO createReqVO);


    /**
     * 创建自己的员工基本信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmployeeBasicMy(@Valid EmployeeBasicCreateReqVO createReqVO);

    /**
     * 更新员工基本信息
     *
     * @param updateReqVO 更新信息
     */
    void updateEmployeeBasic(@Valid EmployeeBasicUpdateReqVO updateReqVO);


    /**
     * 更新自己的员工基本信息
     *
     * @param updateReqVO 更新信息
     */
    void updateEmployeeBasicMy(@Valid EmployeeBasicUpdateReqVO updateReqVO);

    /**
     * 删除员工基本信息
     *
     * @param id 编号
     */
    void deleteEmployeeBasic(Long id);

    /**
     * 删除员工基本信息
     *
     * @param id 编号
     */
    void deleteEmployeeBasicMy(Long id);

    /**
     * 获得员工基本信息
     *
     * @param id 编号
     * @return 员工基本信息
     */
    EmployeeBasicDO getEmployeeBasic(Long id);

    /**
     * 获得员工基本信息
     *
     * @param id 编号
     * @return 员工基本信息
     */
    EmployeeBasicDO getEmployeeBasicMy(Long id);

    /**
     * 获得员工基本信息列表
     *
     * @param ids 编号
     * @return 员工基本信息列表
     */
    List<EmployeeBasicDO> getEmployeeBasicList(Collection<Integer> ids);

    /**
     * 获得员工基本信息分页
     *
     * @param pageReqVO 分页查询
     * @return 员工基本信息分页
     */
    PageResult<EmployeeBasicDO> getEmployeeBasicPage(EmployeeBasicPageReqVO pageReqVO);

    /**
     * 获得员工基本信息列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 员工基本信息列表
     */
    List<EmployeeBasicDO> getEmployeeBasicList(EmployeeBasicExportReqVO exportReqVO);



}

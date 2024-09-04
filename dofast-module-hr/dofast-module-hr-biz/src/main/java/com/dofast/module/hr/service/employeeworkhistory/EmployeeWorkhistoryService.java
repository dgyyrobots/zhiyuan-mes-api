package com.dofast.module.hr.service.employeeworkhistory;

import java.util.*;
import javax.validation.*;
import com.dofast.module.hr.controller.admin.employeeworkhistory.vo.*;
import com.dofast.module.hr.dal.dataobject.employeeworkhistory.EmployeeWorkhistoryDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 员工工作经历 Service 接口
 *
 * @author 惠智造
 */
public interface EmployeeWorkhistoryService {

    /**
     * 创建员工工作经历
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmployeeWorkhistory(@Valid EmployeeWorkhistoryCreateReqVO createReqVO);


    /**
     * 创建员工工作经历
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmployeeWorkhistoryMy(@Valid EmployeeWorkhistoryCreateReqVO createReqVO);

    /**
     * 更新员工工作经历
     *
     * @param updateReqVO 更新信息
     */
    void updateEmployeeWorkhistory(@Valid EmployeeWorkhistoryUpdateReqVO updateReqVO);


    /**
     * 更新员工工作经历
     *
     * @param updateReqVO 更新信息
     */
    void updateEmployeeWorkhistoryMy(@Valid EmployeeWorkhistoryUpdateReqVO updateReqVO);

    /**
     * 删除员工工作经历
     *
     * @param id 编号
     */
    void deleteEmployeeWorkhistory(Long id);

    /**
     * 删除员工工作经历
     *
     * @param id 编号
     */
    void deleteEmployeeWorkhistoryMy(Long id);

    /**
     * 获得员工工作经历
     *
     * @param id 编号
     * @return 员工工作经历
     */
    EmployeeWorkhistoryDO getEmployeeWorkhistory(Long id);

    /**
     * 获得员工工作经历
     *
     * @param id 编号
     * @return 员工工作经历
     */
    List<EmployeeWorkhistoryDO> getEmployeeWorkhistoryMy(Long id);

    /**
     * 获得员工工作经历列表
     *
     * @param ids 编号
     * @return 员工工作经历列表
     */
    List<EmployeeWorkhistoryDO> getEmployeeWorkhistoryList(Collection<Long> ids);

    /**
     * 获得员工工作经历分页
     *
     * @param pageReqVO 分页查询
     * @return 员工工作经历分页
     */
    PageResult<EmployeeWorkhistoryDO> getEmployeeWorkhistoryPage(EmployeeWorkhistoryPageReqVO pageReqVO);

    /**
     * 获得员工工作经历列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 员工工作经历列表
     */
    List<EmployeeWorkhistoryDO> getEmployeeWorkhistoryList(EmployeeWorkhistoryExportReqVO exportReqVO);

}

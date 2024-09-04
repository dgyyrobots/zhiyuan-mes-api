package com.dofast.module.hr.service.employeefile;

import java.util.*;
import javax.validation.*;
import com.dofast.module.hr.controller.admin.employeefile.vo.*;
import com.dofast.module.hr.dal.dataobject.employeefile.EmployeeFileDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 员工信息文件 Service 接口
 *
 * @author 惠智造
 */
public interface EmployeeFileService {

    /**
     * 创建员工信息文件
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmployeeFile(@Valid EmployeeFileCreateReqVO createReqVO);

    /**
     * 创建员工信息文件
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmployeeFileMy(@Valid EmployeeFileCreateReqVO createReqVO);

    /**
     * 更新员工信息文件
     *
     * @param updateReqVO 更新信息
     */
    void updateEmployeeFile(@Valid EmployeeFileUpdateReqVO updateReqVO);


    /**
     * 更新员工信息文件
     *
     * @param updateReqVO 更新信息
     */
    void updateEmployeeFileMy(@Valid EmployeeFileUpdateReqVO updateReqVO);

    /**
     * 删除员工信息文件
     *
     * @param id 编号
     */
    void deleteEmployeeFile(Long id);

    /**
     * 删除员工信息文件
     *
     * @param id 编号
     */
    void deleteEmployeeFileMy(Long id);

    /**
     * 获得员工信息文件
     *
     * @param id 编号
     * @return 员工信息文件
     */
    EmployeeFileDO getEmployeeFile(Long id);


    /**
     * 获得员工信息文件
     *
     * @param id 编号
     * @return 员工信息文件
     */
    EmployeeFileDO getEmployeeFileMy(Long id);

    /**
     * 获得员工信息文件列表
     *
     * @param ids 编号
     * @return 员工信息文件列表
     */
    List<EmployeeFileDO> getEmployeeFileList(Collection<Long> ids);

    /**
     * 获得员工信息文件分页
     *
     * @param pageReqVO 分页查询
     * @return 员工信息文件分页
     */
    PageResult<EmployeeFileDO> getEmployeeFilePage(EmployeeFilePageReqVO pageReqVO);

    /**
     * 获得员工信息文件列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 员工信息文件列表
     */
    List<EmployeeFileDO> getEmployeeFileList(EmployeeFileExportReqVO exportReqVO);

}

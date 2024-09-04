package com.dofast.module.mes.service.mdworkstationmachine;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.mdworkstationmachine.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstationmachine.MdWorkstationMachineDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 设备资源 Service 接口
 *
 * @author 芋道源码
 */
public interface MdWorkstationMachineService {
    MdWorkstationMachineDO checkMachineryExists(MdWorkstationMachineBaseVO mdWorkstationMachine);
    int deleteByWorkstationId(Long workstationId);
    /**
     * 创建设备资源
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMdWorkstationMachine(@Valid MdWorkstationMachineCreateReqVO createReqVO);

    /**
     * 更新设备资源
     *
     * @param updateReqVO 更新信息
     */
    void updateMdWorkstationMachine(@Valid MdWorkstationMachineUpdateReqVO updateReqVO);

    /**
     * 删除设备资源
     *
     * @param id 编号
     */
    void deleteMdWorkstationMachine(Long id);

    /**
     * 获得设备资源
     *
     * @param id 编号
     * @return 设备资源
     */
    MdWorkstationMachineDO getMdWorkstationMachine(Long id);

    /**
     * 获得设备资源列表
     *
     * @param ids 编号
     * @return 设备资源列表
     */
    List<MdWorkstationMachineDO> getMdWorkstationMachineList(Collection<Long> ids);

    /**
     * 获得设备资源分页
     *
     * @param pageReqVO 分页查询
     * @return 设备资源分页
     */
    PageResult<MdWorkstationMachineDO> getMdWorkstationMachinePage(MdWorkstationMachinePageReqVO pageReqVO);

    /**
     * 获得设备资源列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 设备资源列表
     */
    List<MdWorkstationMachineDO> getMdWorkstationMachineList(MdWorkstationMachineExportReqVO exportReqVO);

}

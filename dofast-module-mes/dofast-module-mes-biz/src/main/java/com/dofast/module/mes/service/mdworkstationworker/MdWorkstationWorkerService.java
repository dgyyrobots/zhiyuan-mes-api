package com.dofast.module.mes.service.mdworkstationworker;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.mdworkstationworker.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstationworker.MdWorkstationWorkerDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 人力资源 Service 接口
 *
 * @author 芋道源码
 */
public interface MdWorkstationWorkerService {
    int deleteByWorkstationId(Long workstationId);
    String checkPostExist(MdWorkstationWorkerBaseVO mdWorkstationWorker);
    /**
     * 创建人力资源
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMdWorkstationWorker(@Valid MdWorkstationWorkerCreateReqVO createReqVO);

    /**
     * 更新人力资源
     *
     * @param updateReqVO 更新信息
     */
    void updateMdWorkstationWorker(@Valid MdWorkstationWorkerUpdateReqVO updateReqVO);

    /**
     * 删除人力资源
     *
     * @param id 编号
     */
    void deleteMdWorkstationWorker(Long id);

    /**
     * 获得人力资源
     *
     * @param id 编号
     * @return 人力资源
     */
    MdWorkstationWorkerDO getMdWorkstationWorker(Long id);

    /**
     * 获得人力资源列表
     *
     * @param ids 编号
     * @return 人力资源列表
     */
    List<MdWorkstationWorkerDO> getMdWorkstationWorkerList(Collection<Long> ids);

    /**
     * 获得人力资源列表
     *
     * @param ids 编号
     * @return 人力资源列表
     */
    List<MdWorkstationWorkerDO> getMdWorkstationWorkerListByPostId(Set<Long> ids);

    /**
     * 获得人力资源分页
     *
     * @param pageReqVO 分页查询
     * @return 人力资源分页
     */
    PageResult<MdWorkstationWorkerDO> getMdWorkstationWorkerPage(MdWorkstationWorkerPageReqVO pageReqVO);

    /**
     * 获得人力资源列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 人力资源列表
     */
    List<MdWorkstationWorkerDO> getMdWorkstationWorkerList(MdWorkstationWorkerExportReqVO exportReqVO);

}

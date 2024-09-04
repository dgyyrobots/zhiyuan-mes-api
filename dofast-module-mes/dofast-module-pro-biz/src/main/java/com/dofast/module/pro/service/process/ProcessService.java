package com.dofast.module.pro.service.process;

import java.util.*;
import javax.validation.*;
import com.dofast.module.pro.controller.admin.process.vo.*;
import com.dofast.module.pro.dal.dataobject.process.ProcessDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 生产工序 Service 接口
 *
 * @author 芋道源码
 */
public interface ProcessService {
    String checkProcessCodeUnique(ProcessBaseVO baseVO);
    String checkProcessNameUnique(ProcessBaseVO baseVO);
    List<ProcessDO> selectAll(ProcessListVO listVO);
    /**
     * 创建生产工序
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createcess(@Valid ProcessCreateReqVO createReqVO);

    /**
     * 更新生产工序
     *
     * @param updateReqVO 更新信息
     */
    void updatecess(@Valid ProcessUpdateReqVO updateReqVO);

    /**
     * 删除生产工序
     *
     * @param id 编号
     */
    void deletecess(Long id);

    /**
     * 获得生产工序
     *
     * @param id 编号
     * @return 生产工序
     */
    ProcessDO getcess(Long id);

    /**
     * 获得生产工序列表
     *
     * @param ids 编号
     * @return 生产工序列表
     */
    List<ProcessDO> getcessList(Collection<Long> ids);

    /**
     * 获得生产工序简单列表
     *
     * @return 生产工序简单列表
     */
    List<ProcessSimpleVO> getcessSimpleList();

    /**
     * 获得生产工序分页
     *
     * @param pageReqVO 分页查询
     * @return 生产工序分页
     */
    PageResult<ProcessDO> getcessPage(ProcessPageReqVO pageReqVO);

    /**
     * 获得生产工序列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 生产工序列表
     */
    List<ProcessDO> getcessList(ProcessExportReqVO exportReqVO);

}

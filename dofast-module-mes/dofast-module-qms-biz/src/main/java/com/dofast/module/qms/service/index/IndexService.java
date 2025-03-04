package com.dofast.module.qms.service.index;

import java.util.*;
import javax.validation.*;
import com.dofast.module.qms.controller.admin.index.vo.*;
import com.dofast.module.qms.dal.dataobject.index.IndexDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 检测项 Service 接口
 *
 * @author 芋道源码
 */
public interface IndexService {
    /**
     * 检测项编号是否唯一
     * @param qcIndex
     * @return
     */
    public String checkIndexCodeUnique(IndexBaseVO qcIndex);

    /**
     * 检测项名称是否唯一
     * @param qcIndex
     * @return
     */
    public String checkIndexNameUnique(IndexBaseVO qcIndex);

    /**
     * 创建检测项
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIndex(@Valid IndexCreateReqVO createReqVO);

    /**
     * 更新检测项
     *
     * @param updateReqVO 更新信息
     */
    void updateIndex(@Valid IndexUpdateReqVO updateReqVO);

    /**
     * 删除检测项
     *
     * @param id 编号
     */
    void deleteIndex(Long id);

    /**
     * 获得检测项
     *
     * @param id 编号
     * @return 检测项
     */
    IndexDO getIndex(Long id);

    /**
     * 获得检测项列表
     * @param processCode
     * @return
     */
    List<IndexDO> getIndexByProcessCode(String processCode);

    /**
     * 获得检测项列表
     *
     * @param ids 编号
     * @return 检测项列表
     */
    List<IndexDO> getIndexList(Collection<Long> ids);

    /**
     * 获得检测项分页
     *
     * @param pageReqVO 分页查询
     * @return 检测项分页
     */
    PageResult<IndexDO> getIndexPage(IndexPageReqVO pageReqVO);

    /**
     * 获得检测项列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 检测项列表
     */
    List<IndexDO> getIndexList(IndexExportReqVO exportReqVO);

}

package com.dofast.module.wms.service.sn;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.sn.vo.*;
import com.dofast.module.wms.dal.dataobject.sn.SnDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * SN码 Service 接口
 *
 * @author 芋道源码
 */
public interface SnService {

    /**
     * 创建SN码
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSn(@Valid SnCreateReqVO createReqVO);

    /**
     * 更新SN码
     *
     * @param updateReqVO 更新信息
     */
    void updateSn(@Valid SnUpdateReqVO updateReqVO);

    /**
     * 删除SN码
     *
     * @param id 编号
     */
    void deleteSn(Long id);

    /**
     * 获得SN码
     *
     * @param id 编号
     * @return SN码
     */
    SnDO getSn(Long id);

    /**
     * 获得SN码列表
     *
     * @param ids 编号
     * @return SN码列表
     */
    List<SnDO> getSnList(Collection<Long> ids);

    /**
     * 获得SN码分页
     *
     * @param pageReqVO 分页查询
     * @return SN码分页
     */
    PageResult<SnDO> getSnPage(SnPageReqVO pageReqVO);
    PageResult<SnDO> getSnPageSn(SnPageReqVO pageReqVO);
    /**
     * 获得SN码列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return SN码列表
     */
    List<SnDO> getSnList(SnExportReqVO exportReqVO);

}

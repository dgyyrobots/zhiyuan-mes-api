package com.dofast.module.wms.service.transferline;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.transferline.vo.*;
import com.dofast.module.wms.dal.dataobject.transfer.TransferDO;
import com.dofast.module.wms.dal.dataobject.transferline.TransferLineDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 转移单行 Service 接口
 *
 * @author 芋道源码
 */
public interface TransferLineService {
    int deleteByTransferId(Long transferId);
    /**
     * 创建转移单行
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTransferLine(@Valid TransferLineCreateReqVO createReqVO);

    /**
     * 更新转移单行
     *
     * @param updateReqVO 更新信息
     */
    void updateTransferLine(@Valid TransferLineUpdateReqVO updateReqVO);

    /**
     * 删除转移单行
     *
     * @param id 编号
     */
    void deleteTransferLine(Long id);

    /**
     * 获得转移单行
     *
     * @param id 编号
     * @return 转移单行
     */
    TransferLineDO getTransferLine(Long id);

    /**
     * 获得转移单行列表
     *
     * @param ids 编号
     * @return 转移单行列表
     */
    List<TransferLineDO> getTransferLineList(Collection<Long> ids);

    /**
     * 获得转移单行分页
     *
     * @param pageReqVO 分页查询
     * @return 转移单行分页
     */
    PageResult<TransferLineDO> getTransferLinePage(TransferLinePageReqVO pageReqVO);

    /**
     * 获得转移单行列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 转移单行列表
     */
    List<TransferLineDO> getTransferLineList(TransferLineExportReqVO exportReqVO);
    List<TransferLineDO> getTransferLineList(TransferLineListVO exportReqVO);

}

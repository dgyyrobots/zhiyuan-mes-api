package com.dofast.module.wms.service.transfer;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.transfer.vo.*;
import com.dofast.module.wms.controller.admin.transferline.vo.TransferLineListVO;
import com.dofast.module.wms.dal.dataobject.transfer.TransferDO;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.wms.dal.dataobject.transfer.TransferTxBean;

/**
 * 转移单 Service 接口
 *
 * @author 芋道源码
 */
public interface TransferService {
    public List<TransferTxBean> getTxBeans(Long transferid);
    String checkUnique(TransferBaseVO wmTransfer);
    /**
     * 创建转移单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTransfer(@Valid TransferCreateReqVO createReqVO);

    /**
     * 更新转移单
     *
     * @param updateReqVO 更新信息
     */
    void updateTransfer(@Valid TransferUpdateReqVO updateReqVO);

    /**
     * 删除转移单
     *
     * @param id 编号
     */
    void deleteTransfer(Long id);

    /**
     * 获得转移单
     *
     * @param id 编号
     * @return 转移单
     */
    TransferDO getTransfer(Long id);

    /**
     * 获得转移单列表
     *
     * @param ids 编号
     * @return 转移单列表
     */
    List<TransferDO> getTransferList(Collection<Long> ids);

    /**
     * 获得转移单分页
     *
     * @param pageReqVO 分页查询
     * @return 转移单分页
     */
    PageResult<TransferDO> getTransferPage(TransferPageReqVO pageReqVO);

    /**
     * 获得转移单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 转移单列表
     */
    List<TransferDO> getTransferList(TransferExportReqVO exportReqVO);

}

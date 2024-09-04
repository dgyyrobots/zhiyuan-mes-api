package com.dofast.module.wms.service.transaction;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.transaction.vo.*;
import com.dofast.module.wms.dal.dataobject.transaction.TransactionDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 库存事务 Service 接口
 *
 * @author 芋道源码
 */
public interface TransactionService {
    public TransactionDO processTransaction(TransactionUpdateReqVO baseVO);
    /**
     * 创建库存事务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTransaction(@Valid TransactionCreateReqVO createReqVO);

    /**
     * 更新库存事务
     *
     * @param updateReqVO 更新信息
     */
    void updateTransaction(@Valid TransactionUpdateReqVO updateReqVO);

    /**
     * 删除库存事务
     *
     * @param id 编号
     */
    void deleteTransaction(Long id);

    /**
     * 获得库存事务
     *
     * @param id 编号
     * @return 库存事务
     */
    TransactionDO getTransaction(Long id);

    /**
     * 获得库存事务列表
     *
     * @param ids 编号
     * @return 库存事务列表
     */
    List<TransactionDO> getTransactionList(Collection<Long> ids);

    /**
     * 获得库存事务分页
     *
     * @param pageReqVO 分页查询
     * @return 库存事务分页
     */
    PageResult<TransactionDO> getTransactionPage(TransactionPageReqVO pageReqVO);

    /**
     * 获得库存事务列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 库存事务列表
     */
    List<TransactionDO> getTransactionList(TransactionExportReqVO exportReqVO);

}

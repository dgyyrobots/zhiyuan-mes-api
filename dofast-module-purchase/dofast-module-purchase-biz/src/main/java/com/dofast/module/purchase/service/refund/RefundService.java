package com.dofast.module.purchase.service.refund;

import java.util.*;
import javax.validation.*;
import com.dofast.module.purchase.controller.admin.refund.vo.*;
import com.dofast.module.purchase.dal.dataobject.refund.RefundDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 采购退货 Service 接口
 *
 * @author 惠智造
 */
public interface RefundService {

    /**
     * 创建采购退货
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createRefund(@Valid RefundCreateReqVO createReqVO);

    /**
     * 更新采购退货
     *
     * @param updateReqVO 更新信息
     */
    void updateRefund(@Valid RefundUpdateReqVO updateReqVO);

    /**
     * 删除采购退货
     *
     * @param id 编号
     */
    void deleteRefund(Integer id);

    /**
     * 获得采购退货
     *
     * @param id 编号
     * @return 采购退货
     */
    RefundDO getRefund(Integer id);

    /**
     * 获得采购退货列表
     *
     * @param ids 编号
     * @return 采购退货列表
     */
    List<RefundDO> getRefundList(Collection<Integer> ids);

    /**
     * 获得采购退货分页
     *
     * @param pageReqVO 分页查询
     * @return 采购退货分页
     */
    PageResult<RefundDO> getRefundPage(RefundPageReqVO pageReqVO);

    /**
     * 获得采购退货列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 采购退货列表
     */
    List<RefundDO> getRefundList(RefundExportReqVO exportReqVO);

}

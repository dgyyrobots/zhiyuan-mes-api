package com.dofast.module.channel.service.remark;

import java.util.*;
import javax.validation.*;
import com.dofast.module.channel.controller.admin.remark.vo.*;
import com.dofast.module.channel.dal.dataobject.remark.RemarkDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 订单备注 Service 接口
 *
 * @author 惠智造
 */
public interface RemarkService {

    /**
     * 创建订单备注
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRemark(@Valid RemarkCreateReqVO createReqVO);

    /**
     * 更新订单备注
     *
     * @param updateReqVO 更新信息
     */
    void updateRemark(@Valid RemarkUpdateReqVO updateReqVO);

    /**
     * 删除订单备注
     *
     * @param id 编号
     */
    void deleteRemark(Long id);

    /**
     * 开关顶置
     *
     * @param id
     */
    void toggleTopRemark(Long id);

    /**
     * 获得订单备注
     *
     * @param id 编号
     * @return 订单备注
     */
    RemarkDO getRemark(Long id);

    /**
     * 获得订单备注列表
     *
     * @param ids 编号
     * @return 订单备注列表
     */
    List<RemarkDO> getRemarkList(Collection<Long> ids);

    /**
     * 通过商城订单ID获取评论表
     *
     * @param id 商城订单的ID
     * @return
     */
    List<RemarkDO> getRemarkListByTradeOrder(Long id);

    /**
     * 通过销售订单ID获取评论表
     * @param id 销售订单的ID
     * @return
     */
    List<RemarkDO> getRemarkListBySalesOrder(Long id);

    /**
     * 获得订单备注分页
     *
     * @param pageReqVO 分页查询
     * @return 订单备注分页
     */
    PageResult<RemarkDO> getRemarkPage(RemarkPageReqVO pageReqVO);

    /**
     * 获得订单备注列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 订单备注列表
     */
    List<RemarkDO> getRemarkList(RemarkExportReqVO exportReqVO);

}
